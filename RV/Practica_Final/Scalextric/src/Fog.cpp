#include "Fog.h"
#include <glm/glm.hpp>

//
// FUNCI�N: Fog::Fog()
//
// PROP�SITO: Construye un descriptor de niebla con los valores por defecto
//
Fog::Fog()
{
	maxDist = 1000.0f;
	minDist = 100.0f;
	color = glm::vec3(1.0f, 1.0f, 1.0f);
}

//
// FUNCI�N: Fog::SetMaxDistance(GLfloat d)
//
// PROP�SITO: Asigna la distancia a la que se satura la niebla
//
void Fog::SetMaxDistance(GLfloat d)
{
	maxDist = d;
}

//
// FUNCI�N: Fog::SetMinDistance(GLfloat d)
//
// PROP�SITO: Asigna la distancia a la que comienza el efecto de la niebla
//
void Fog::SetMinDistance(GLfloat d)
{
	minDist = d;
}

//
// FUNCI�N: Fog::SetFogColor(glm::vec3 c)
//
// PROP�SITO: Asigna el color de la niebla
//
void Fog::SetFogColor(glm::vec3 c)
{
	color = c;
}

//
// FUNCI�N: Fog::SetUniforms(ShaderProgram* program)
//
// PROP�SITO: Configura el efecto de la niebla en el programa
//
void Fog::SetUniforms(ShaderProgram* program)
{
	program->SetUniformVec3("Fog.color", color);
	program->SetUniformF("Fog.maxDist", maxDist);
	program->SetUniformF("Fog.minDist", minDist);
}