import json
import re
import unicodedata
from pathlib import Path
from urllib.request import Request, urlopen

CHUNK_SIZE = 25

root = Path(__file__).resolve().parents[1]
json_path = root / 'assets/data/flags_data.json'
state_path = root / 'assets/data/.flag_download_state.json'

sitemap_url = 'https://www.3dflagsplus.com/sitemap.xml'


def slugify(text):
    text = text.lower()
    text = text.replace('&', 'and')
    text = re.sub(r"['’]", '', text)
    text = unicodedata.normalize('NFKD', text)
    text = ''.join(c for c in text if not unicodedata.combining(c))
    text = re.sub(r'[^a-z0-9]+', '-', text)
    return text.strip('-')


def choose_url(slugs, urls):
    candidates = []
    for url in urls:
        for slug in slugs:
            if slug and slug in url:
                candidates.append(url)
                break
    if not candidates:
        return None
    flagged = [u for u in candidates if 'flag' in u]
    if flagged:
        candidates = flagged
    animated = [u for u in candidates if 'animated' in u]
    if animated:
        candidates = animated
    return sorted(set(candidates), key=len)[0]


def size_score(url):
    if '/s0/' in url:
        return 10000
    m = re.search(r'/s(\d+)/', url)
    if m:
        return int(m.group(1))
    m = re.search(r'/w(\d+)-h(\d+)', url)
    if m:
        return int(m.group(1))
    if '/s72' in url:
        return 72
    return 0


def pick_gif(gif_urls, predicate):
    candidates = [u for u in gif_urls if predicate(u)]
    if not candidates:
        return None
    by_name = {}
    for u in candidates:
        name = u.split('/')[-1]
        score = size_score(u)
        if name not in by_name or score > by_name[name][0]:
            by_name[name] = (score, u)
    best = max(by_name.values(), key=lambda x: x[0])[1]
    return best


with json_path.open() as f:
    data = json.load(f)

flags = []
for continent in data.get('continents', []):
    for flag in continent.get('flags', []):
        flags.append((continent['id'], flag))

state = {'index': 0}
if state_path.exists():
    state = json.loads(state_path.read_text())

start_index = state.get('index', 0)
end_index = min(start_index + CHUNK_SIZE, len(flags))

xml = urlopen(Request(sitemap_url, headers={'User-Agent': 'Mozilla/5.0'})).read().decode('utf-8', errors='ignore')
urls = re.findall(r'<loc>(.*?)</loc>', xml)

missing_pages = []
missing_gifs = []

for idx in range(start_index, end_index):
    continent_id, flag = flags[idx]
    flag_id = flag['id']
    country_name = flag['countryName']

    out_dir = root / 'assets/images/flags' / continent_id
    out_dir.mkdir(parents=True, exist_ok=True)
    square_path = out_dir / f'{flag_id}.gif'
    wave_path = out_dir / f'{flag_id}_waving.gif'

    if square_path.exists() and wave_path.exists():
        flag['flagImage'] = f'assets/images/flags/{continent_id}/{flag_id}.gif'
        continue

    id_slug = slugify(flag_id.replace('_', ' '))
    name_slug = slugify(country_name)

    overrides = {
        'cote-divoire': ['cote-divoire', 'ivory-coast'],
        'cte-divoire': ['cote-divoire', 'ivory-coast'],
        'bosnia-herzegovina': ['bosnia-and-herzegovina'],
        'czech-rep': ['czech-republic'],
        'north-macedonia': ['macedonia'],
        'united-states': ['united-states'],
        'united-kingdom': ['united-kingdom'],
    }
    slug_candidates = [name_slug, id_slug]
    slug_candidates += overrides.get(name_slug, [])
    slug_candidates += overrides.get(id_slug, [])

    page_url = choose_url(slug_candidates, urls)
    if not page_url:
        missing_pages.append((flag_id, country_name))
        continue

    try:
        html = urlopen(Request(page_url, headers={'User-Agent': 'Mozilla/5.0'})).read().decode('utf-8', errors='ignore')
    except Exception:
        missing_pages.append((flag_id, country_name))
        continue

    gif_urls = re.findall(r'https?://[^\s\"\']+\.gif', html)
    gif_urls = list(dict.fromkeys(gif_urls))

    square_url = pick_gif(gif_urls, lambda u: 'flag-icon-animation' in u or ('flag-icon' in u and 'animation' in u))
    wave_url = pick_gif(gif_urls, lambda u: 'flag-animation' in u and 'icon' not in u and 'pole' not in u)

    if not square_url or not wave_url:
        missing_gifs.append((flag_id, country_name, page_url))
        continue

    square_path.write_bytes(urlopen(Request(square_url, headers={'User-Agent': 'Mozilla/5.0'})).read())
    wave_path.write_bytes(urlopen(Request(wave_url, headers={'User-Agent': 'Mozilla/5.0'})).read())

    flag['flagImage'] = f'assets/images/flags/{continent_id}/{flag_id}.gif'

json_path.write_text(json.dumps(data, ensure_ascii=False, indent=2))

state['index'] = end_index
state_path.write_text(json.dumps(state, indent=2))

print(f'Processed flags {start_index} to {end_index} of {len(flags)}')
print('missing pages', len(missing_pages))
print('missing gifs', len(missing_gifs))
