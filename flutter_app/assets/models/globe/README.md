# 3D Earth Globe Models

This directory should contain GLB format 3D Earth models for the app's globe visualization.

## Required Files

You need to add the following GLB model files to this directory:

1. **earth.glb** - High-quality Earth model (recommended: 50k-100k polygons)
2. **earth_lowpoly.glb** - Low-poly Earth model for older devices (recommended: 5k-10k polygons)

## Free Model Sources

### Recommended Sources:

1. **Poly Haven** (https://polyhaven.com/)
   - Free, high-quality 3D models under CC0 license
   - Search for "earth" or "globe"
   - Download as GLB format

2. **Sketchfab** (https://sketchfab.com/)
   - Filter by "Downloadable" and "Free"
   - Search for "earth low poly" and "earth high poly"
   - Look for models with Creative Commons licenses
   - Download as GLB/GLTF format

3. **NASA 3D Resources** (https://nasa3d.arc.nasa.gov/)
   - Official NASA 3D models
   - Free for educational use
   - High-quality Earth textures

4. **Quaternius** (https://quaternius.com/)
   - Free low-poly models under CC0
   - Good for the low-poly variant

### Model Requirements:

- **Format:** GLB (Binary GLTF)
- **Size:**
  - High-quality: 1-2 MB max
  - Low-poly: 500 KB max
- **Features:**
  - Textured with Earth surface
  - Centered at origin (0,0,0)
  - Reasonable scale (adjust if needed in code)

### Conversion to GLB:

If you download GLTF or other formats, convert to GLB using:
- **Online:** https://gltf.report/ or https://products.aspose.app/3d/conversion
- **Blender:** File → Export → glTF 2.0 → Select GLB format

## License

Make sure to:
1. Check the license of any model you download
2. Add attribution in LICENSE.txt if required
3. Use models compatible with commercial apps

## Testing

After adding models:
```bash
flutter run
```

The app will automatically:
- Use `earth.glb` for high-performance devices
- Use `earth_lowpoly.glb` for medium/low-performance devices
- Show a loading indicator while the model loads
