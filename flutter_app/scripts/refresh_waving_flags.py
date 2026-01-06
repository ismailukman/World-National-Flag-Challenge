import json
import re
import struct
import unicodedata
from pathlib import Path
from urllib.request import Request, urlopen

CHUNK_SIZE = 20

root = Path(__file__).resolve().parents[1]
json_path = root / 'assets/data/flags_data.json'
state_path = root / 'assets/data/.flag_waving_state.json'

sitemap_url = 'https://www.3dflagsplus.com/sitemap.xml'

skip_ids = {'cape_verde', 'south_sudan'}


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


def gif_dims(url):
    req = Request(
        url,
        headers={
            'Range': 'bytes=0-9',
            'User-Agent': 'Mozilla/5.0',
        },
    )
    try:
        data = urlopen(req, timeout=30).read(10)
    except Exception:
        return None
    if data[:3] != b'GIF':
        return None
    return struct.unpack('<HH', data[6:10])


def pick_waving(gif_urls):
    candidates = []
    for u in gif_urls:
        lower = u.lower()
        if not any(t in lower for t in ['flag-animation', 'flag-wave', 'flag-waving', 'flag-waves']):
            continue
        if any(t in lower for t in ['icon', 'round', 'circle', 'pole', 'coat', 'globe', 'world', 'logo']):
            continue
        candidates.append(u)

    scored = []
    for u in candidates:
        dims = gif_dims(u)
        if not dims:
            continue
        w, h = dims
        if h == 0:
            continue
        ratio = w / h
        score = (1000 if ratio >= 1.2 else 0) + ratio * 100 + w / 10
        scored.append((score, ratio, w, h, u))

    if not scored:
        return None

    scored.sort(reverse=True)
    return scored[0][4]


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

missing = 0
updated = 0

for idx in range(start_index, end_index):
    continent_id, flag = flags[idx]
    flag_id = flag['id']
    if flag_id in skip_ids:
        continue

    id_slug = slugify(flag_id.replace('_', ' '))
    name_slug = slugify(flag['countryName'])

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
        missing += 1
        continue

    try:
        html = urlopen(Request(page_url, headers={'User-Agent': 'Mozilla/5.0'})).read().decode('utf-8', errors='ignore')
    except Exception:
        missing += 1
        continue

    gif_urls = re.findall(r"https?://[^\\s\"']+\\.gif", html)
    gif_urls = list(dict.fromkeys(gif_urls))

    wave_url = pick_waving(gif_urls)
    if not wave_url:
        missing += 1
        continue

    out_dir = root / 'assets/images/flags' / continent_id
    out_dir.mkdir(parents=True, exist_ok=True)
    wave_path = out_dir / f'{flag_id}_waving.gif'
    wave_path.write_bytes(
        urlopen(Request(wave_url, headers={'User-Agent': 'Mozilla/5.0'}), timeout=30).read()
    )
    updated += 1

state['index'] = end_index
state_path.write_text(json.dumps(state, indent=2))

print(f'Processed flags {start_index} to {end_index} of {len(flags)}')
print('updated', updated)
print('missing', missing)
