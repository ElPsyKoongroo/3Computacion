#include "Light.h"
#include <glm/glm.hpp>

//
// FUNCIÓN: Light::Light()
//
// PROPÓSITO: Construye una luz con los valores por defecto
//
Light::Light()
{
	Ldir = glm::vec3(0.0f, 0.0f, -1.0f);
	La = glm::vec3(1.0f, 1.0f, 1.0f);
	Ld = glm::vec3(1.0f, 1.0f, 1.0f);
	Ls = glm::vec3(0.0f, 0.0f, 0.0f);
}

//
// FUNCIÓN: Light::SetLightDirection(glm::vec3 d)
//
// PROPÓSITO: Asigna la dirección de la luz (expresada en coordenadas de modelo)
//
void Light::SetLightDirection(glm::vec3 d)
{
	Ldir = d;
}

//
// FUNCIÓN: Light::SetAmbientLight(glm::vec3 a)
//
// PROPÓSITO: Asigna el color de la componente ambiental
//
void Light::SetAmbientLight(glm::vec3 a)
{
	La = a;
}

//
// FUNCIÓN: Light::SetDifusseLight(glm::vec3 d)
//
// PROPÓSITO: Asigna el color de la componente difusa
//
void Light::SetDifusseLight(glm::vec3 d)
{
	Ld = d;
}

//
// FUNCIÓN: Light::SetSpecularLight(glm::vec3 s)
//
// PROPÓSITO: Asigna el color de la componente especular
//
void Light::SetSpecularLight(glm::vec3 s)
{
	Ls = s;
}

//
// FUNCIÓN: Light::SetUniforms(ShaderProgram* program)
//
// PROPÓSITO: Configura la luz en el programa gráfico
//
void Light::SetUniforms(ShaderProgram* program)
{
	program->SetUniformVec3("Light.Ldir", Ldir);
	program->SetUniformVec3("Light.La", La);
	program->SetUniformVec3("Light.Ld", Ld);
	program->SetUniformVec3("Light.Ls", Ls);
}