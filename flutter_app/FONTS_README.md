# Fonts Setup Instructions

## Required Fonts

The following fonts need to be downloaded and placed in the `assets/fonts/` directory:

### 1. Rubik (Primary Font)
Download from: https://fonts.google.com/specimen/Rubik

Required files:
- `Rubik-Regular.ttf`
- `Rubik-Bold.ttf`
- `Rubik-Light.ttf`
- `Rubik-Medium.ttf`

### 2. Berkshire Swash (Display/Titles)
Download from: https://fonts.google.com/specimen/Berkshire+Swash

Required files:
- `BerkshireSwash-Regular.ttf`

### 3. Black Ops One (Headings)
Download from: https://fonts.google.com/specimen/Black+Ops+One

Required files:
- `BlackOpsOne-Regular.ttf`

### 4. Electrolize
Download from: https://fonts.google.com/specimen/Electrolize

Required files:
- `Electrolize-Regular.ttf`

### 5. Federant
Download from: https://fonts.google.com/specimen/Federant

Required files:
- `Federant-Regular.ttf`

### 6. Jacques Francois
Download from: https://fonts.google.com/specimen/Jacques+Francois

Required files:
- `JacquesFrancois-Regular.ttf`

### 7. New Rocker
Download from: https://fonts.google.com/specimen/New+Rocker

Required files:
- `NewRocker-Regular.ttf`

### 8. Font Awesome Solid (Icons)
Download from: https://fontawesome.com/download

Required files:
- `FontAwesome-Solid.ttf` (from the webfonts folder)

## Installation Steps

1. Download each font family from the links above
2. Extract the TTF files
3. Place all TTF files in `flutter_app/assets/fonts/`
4. Verify the files match the names in `pubspec.yaml`

## Alternative: Quick Download Script

You can use this helper command (requires internet):

```bash
cd flutter_app/assets/fonts/

# Download Rubik
curl -L "https://fonts.google.com/download?family=Rubik" -o rubik.zip
unzip rubik.zip -d rubik && mv rubik/*.ttf . && rm -rf rubik rubik.zip

# Repeat for other fonts...
```

**Note:** The original Android app uses these font family definitions, but the actual TTF files are not included in the repository. You'll need to download them manually from Google Fonts.
