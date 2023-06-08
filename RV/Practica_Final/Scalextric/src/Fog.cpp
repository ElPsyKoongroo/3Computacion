#include "Fog.h"
#include <glm/glm.hpp>

//
// FUNCIÓN: Fog::Fog()
//
// PROPÓSITO: Construye un descriptor de niebla con los valores por defecto
//
Fog::Fog()
{
	maxDist = 1000.0f;
	minDist = 100.0f;
	color = glm::vec3(1.0f, 1.0f, 1.0f);
}

//
// FUNCIÓN: Fog::SetMaxDistance(GLfloat d)
//
// PROPÓSITO: Asigna la distancia a la que se satura la niebla
//
void Fog::SetMaxDistance(GLfloat d)
{
	maxDist = d;
}

//
// FUNCIÓN: Fog::SetMinDistance(GLfloat d)
//
// PROPÓSITO: Asigna la distancia a la que comienza el efecto de la niebla
//
void Fog::SetMinDistance(GLfloat d)
{
	minDist = d;
}

//
// FUNCIÓN: Fog::SetFogColor(glm::vec3 c)
//
// PROPÓSITO: Asigna el color de la niebla
//
void Fog::SetFogColor(glm::vec3 c)
{
	color = c;
}

//
// FUNCIÓN: Fog::SetUniforms(ShaderProgram* program)
//
// PROPÓSITO: Configura el efecto de la niebla en el programa
//
void Fog::SetUniforms(ShaderProgram* program)
{
	program->SetUniformVec3("Fog.color", color);
	program->SetUniformF("Fog.maxDist", maxDist);
	program->SetUniformF("Fog.minDist", minDist);
}