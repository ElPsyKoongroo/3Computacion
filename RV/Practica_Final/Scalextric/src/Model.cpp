#include "Model.h"
#include <GLFW\glfw3.h>
#include <iostream>

//
// FUNCI�N: Model::initialize(int, int)
//
// PROP�SITO: Initializa el modelo 3D
//
void Model::initialize(int w, int h)
{
  glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

  resize(w, h);
}

//
// FUNCI�N: Model::finalize()
//
// PROP�SITO: Libera los recursos del modelo 3D
//
void Model::finalize()
{
}

//
// FUNCI�N: Model::resize(int w, int h)
//
// PROP�SITO: Asigna el viewport y el clipping volume
//
void Model::resize(int w, int h)
{
  glViewport(0, 0, w, h);
}

//
// FUNCI�N: Model::render()
//
// PROP�SITO: Genera la imagen
//
void Model::render()
{
  glClear(GL_COLOR_BUFFER_BIT);
}

//
// FUNCI�N: Model::update()
//
// PROP�SITO: Anima la escena
//
void Model::update()
{
}

//
// FUNCI�N: Model::key_pressed(int)
//
// PROP�SITO: Respuesta a acciones de teclado
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
//  FUNCI�N: Model:::mouse_button(int button, int action)
//
//  PROP�SITO: Respuesta del modelo a un click del rat�n.
//
void Model::mouse_button(int button, int action)
{
}

//
//  FUNCI�N: Model::mouse_move(double xpos, double ypos)
//
//  PROP�SITO: Respuesta del modelo a un movimiento del rat�n.
//
void Model::mouse_move(double xpos, double ypos)
{
}