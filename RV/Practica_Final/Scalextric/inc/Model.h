#pragma once

#include <GL/glew.h>
#include <GLFW\glfw3.h>
#include <glm/gtc/matrix_transform.hpp>
#include <ShaderProgram.h>
#include <Figura.h>
#include <Curva.h>
#include <iostream>

class Model {
	ShaderProgram* program;
	Figura* fig0;

	GLfloat xAngle;
	GLfloat yAngle;
	int figure;
	GLint p;
	GLint m;
	GLfloat r0;
	GLfloat r1;

	glm::mat4 projection;
public:
	void initialize(int w, int h);
	void finalize();
	void render();
	void update();
	void key_pressed(int key);
	void mouse_button(int button, int action);
	void mouse_move(double xpos, double ypos);
	void resize(int w, int h);
	void changeCurve(int opc, GLint par, GLfloat parf);
};