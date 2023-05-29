#include "Light.h"
#include <glm/glm.hpp>

//
// FUNCI�N: Light::Light()
//
// PROP�SITO: Construye una luz con los valores por defecto
//
Light::Light()
{
	Ldir = glm::vec3(0.0f, 0.0f, -1.0f);
	La = glm::vec3(1.0f, 1.0f, 1.0f);
	Ld = glm::vec3(1.0f, 1.0f, 1.0f);
	Ls = glm::vec3(0.0f, 0.0f, 0.0f);
}

//
// FUNCI�N: Light::SetLightDirection(glm::vec3 d)
//
// PROP�SITO: Asigna la direcci�n de la luz (expresada en coordenadas de modelo)
//
void Light::SetLightDirection(glm::vec3 d)
{
	Ldir = d;
}

//
// FUNCI�N: Light::SetAmbientLight(glm::vec3 a)
//
// PROP�SITO: Asigna el color de la componente ambiental
//
void Light::SetAmbientLight(glm::vec3 a)
{
	La = a;
}

//
// FUNCI�N: Light::SetDifusseLight(glm::vec3 d)
//
// PROP�SITO: Asigna el color de la componente difusa
//
void Light::SetDifusseLight(glm::vec3 d)
{
	Ld = d;
}

//
// FUNCI�N: Light::SetSpecularLight(glm::vec3 s)
//
// PROP�SITO: Asigna el color de la componente especular
//
void Light::SetSpecularLight(glm::vec3 s)
{
	Ls = s;
}

//
// FUNCI�N: Light::SetUniforms(ShaderProgram* program)
//
// PROP�SITO: Configura la luz en el programa gr�fico
//
void Light::SetUniforms(ShaderProgram* program)
{
	program->SetUniformVec3("Light.Ldir", Ldir);
	program->SetUniformVec3("Light.La", La);
	program->SetUniformVec3("Light.Ld", Ld);
	program->SetUniformVec3("Light.Ls", Ls);
}