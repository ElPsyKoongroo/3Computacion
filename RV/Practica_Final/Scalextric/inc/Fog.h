#pragma once

#include <GL/glew.h>
#include <glm/glm.hpp>
#include "ShaderProgram.h"

class Fog {

private:
	GLfloat maxDist;
	GLfloat minDist;
	glm::vec3 color;

public:
	Fog();
	void SetMaxDistance(GLfloat d);
	void SetMinDistance(GLfloat d);
	void SetFogColor(glm::vec3 c);
	void SetUniforms(ShaderProgram* program);
};