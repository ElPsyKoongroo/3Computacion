#pragma once
#include <iostream>

#include <Model.h>
#include <GLFW/glfw3.h>


const int WIDTH = 800;
const int HEIGHT = 600;

class Aplicacion {

	GLFWwindow* window;
	Model model;

	double limitFPS;
	double lastTime;
	double deltaTime;

	// Métodos principales
	void initWindow();
	void initOpenGL();
	void initModel();
	void mainLoop();
	void timing();
	void cleanup();

	// Respuesta a eventos
	static void keyCallback(GLFWwindow* window, int key, int scan, int act, int mods);
	static void mouseButtonCallback(GLFWwindow* window, int bt, int action, int mods);
	static void cursorPositionCallback(GLFWwindow* window, double xpos, double ypos);
	static void framebufferResizeCallback(GLFWwindow* window, int width, int height);

public:
	void Run();
};