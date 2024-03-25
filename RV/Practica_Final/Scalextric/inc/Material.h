#pragma once

#include <glm/glm.hpp>
#include "ShaderProgram.h"

class Material {

private:
	glm::vec3 Ka;        // Reflectividad ambiental (color ante la luz ambiental)
	glm::vec3 Kd;        // Reflectividad difusa    (color ante la luz difusa)
	glm::vec3 Ks;        // Reflectividad especular (color ante la luz especular)
	GLfloat Shininess;   // Factor de brillo (comportamiento ante la luz especular)
	GLuint textureId;    // Identificador de la textura b�sica

public:
	Material();
	void SetAmbientReflect(GLfloat r, GLfloat g, GLfloat b);
	void SetDifusseReflect(GLfloat r, GLfloat g, GLfloat b);
	void SetSpecularReflect(GLfloat r, GLfloat g, GLfloat b);
	void SetShininess(GLfloat f);
	void SetUniforms(ShaderProgram* program);
	void SetTexture(GLuint id);
	void InitTexture(const char* filename);
	GLuint GetTexture();
};