const char* VertexShader = R""""(
#version 400

in vec3 VertexPosition;

uniform mat4 MVP;

void main()
{
	gl_Position = MVP * vec4(VertexPosition, 1.0);
}

)"""";

const char* FragmentShader = R""""(
#version 400

out vec4 FragColor;

void main()
{
	FragColor = vec4(1.0,0.0,0.0,1.0);
}
)"""";