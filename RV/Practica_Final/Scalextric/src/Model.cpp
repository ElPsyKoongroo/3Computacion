#include "Model.h"
#include <GLFW\glfw3.h>
#include <iostream>

//
// FUNCIÓN: Model::initialize(int, int)
//
// PROPÓSITO: Initializa el modelo 3D
//
void Model::initialize(int w, int h)
{
  glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

  resize(w, h);
}

//
// FUNCIÓN: Model::finalize()
//
// PROPÓSITO: Libera los recursos del modelo 3D
//
void Model::finalize()
{
}

//
// FUNCIÓN: Model::resize(int w, int h)
//
// PROPÓSITO: Asigna el viewport y el clipping volume
//
void Model::resize(int w, int h)
{
  glViewport(0, 0, w, h);
}

//
// FUNCIÓN: Model::render()
//
// PROPÓSITO: Genera la imagen
//
void Model::render()
{
  glClear(GL_COLOR_BUFFER_BIT);
}

//
// FUNCIÓN: Model::update()
//
// PROPÓSITO: Anima la escena
//
void Model::update()
{
}

//
// FUNCIÓN: Model::key_pressed(int)
//
// PROPÓSITO: Respuesta a acciones de teclado
//
void Model::key_pressed(int key)
{
  switch (key)
  {
  case GLFW_KEY_R:
    glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
    break;
  case GLFW_KEY_G:
    glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
    break;
  case GLFW_KEY_B:
    glClearColor(0.0f, 0.0f, 1.0f, 1.0f);
    break;
  case GLFW_KEY_W:
    glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    break;
  }
}

//
//  FUNCIÓN: Model:::mouse_button(int button, int action)
//
//  PROPÓSITO: Respuesta del modelo a un click del ratón.
//
void Model::mouse_button(int button, int action)
{
}

//
//  FUNCIÓN: Model::mouse_move(double xpos, double ypos)
//
//  PROPÓSITO: Respuesta del modelo a un movimiento del ratón.
//
void Model::mouse_move(double xpos, double ypos)
{
}