#version 330

// Input vertex attributes (from vertex shader)
in vec2 fragTexCoord;
in vec4 fragColor;

// Input uniform values
uniform sampler2D texture0;
uniform vec4 colDiffuse;

// Output fragment color
out vec4 finalColor;

void main() {
    vec2 textureSize = vec2(800, 800);
    float outlineSize = 3.0;
    vec4 outlineColor = vec4(0.0f, 0.0f, 0.0f, 1.0f);

    vec2 cord = fragTexCoord;
    cord.y = 1 - cord.y;

    vec4 texel = texture(texture0, cord);
    vec2 texelScale = vec2(0.0);
    texelScale.x = outlineSize/textureSize.x;
    texelScale.y = outlineSize/textureSize.y;

    vec4 corners = vec4(0.0);
    corners.x = texture(texture0, cord + vec2(texelScale.x, texelScale.y)).a;
    corners.y = texture(texture0, cord + vec2(texelScale.x, -texelScale.y)).a;
    corners.z = texture(texture0, cord + vec2(-texelScale.x, texelScale.y)).a;
    corners.w = texture(texture0, cord + vec2(-texelScale.x, -texelScale.y)).a;

    float outline = min(dot(corners, vec4(1.0)), 1.0);
    vec4 color = mix(vec4(0.0), outlineColor, outline);
    finalColor = mix(color, texel, texel.a);
}