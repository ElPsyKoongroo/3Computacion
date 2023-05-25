const char* VertexShader = R""""(
#version 400

 in vec3 VertexPosition;

 uniform mat4 Transform;

 void main()
 {
   gl_Position = Transform * vec4(VertexPosition, 1.0);
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