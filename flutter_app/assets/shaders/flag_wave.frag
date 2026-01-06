#version 460 core

// Fragment shader for flag wave animation
// Creates a subtle waving effect for flag images

precision mediump float;

#include <flutter/runtime_effects.glsl>

// Uniforms passed from Flutter
uniform vec2 uSize;           // Size of the widget
uniform float uTime;          // Current animation time
uniform float uIntensity;     // Wave intensity (0.0 - 1.0)
uniform sampler2D uTexture;   // The flag image texture

// Varying from vertex shader
in vec2 fragCoord;

// Output color
out vec4 fragColor;

void main() {
    // Normalize coordinates (0.0 to 1.0)
    vec2 uv = fragCoord / uSize;

    // Wave parameters
    float frequency = 3.0;      // Number of waves across the flag
    float speed = 2.0;          // Animation speed
    float amplitude = 0.015;    // Wave height

    // Create horizontal wave based on UV.x position and time
    float wave = sin(uv.x * frequency * 3.14159 + uTime * speed) * amplitude * uIntensity;

    // Add a secondary wave for more natural movement
    float wave2 = sin(uv.x * frequency * 2.0 * 3.14159 - uTime * speed * 0.7) * amplitude * 0.5 * uIntensity;

    // Apply wave distortion to UV coordinates
    vec2 distortedUV = uv;
    distortedUV.y += wave + wave2;

    // Sample the texture with distorted coordinates
    vec4 color = texture(uTexture, distortedUV);

    // Add subtle brightness variation to simulate fabric texture
    float brightness = 1.0 + (wave * 0.3);
    color.rgb *= brightness;

    // Output the final color
    fragColor = color;
}
