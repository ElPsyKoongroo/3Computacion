#include "Object.h"
#include <GL/glew.h>
#include <FreeImage.h>
#include <glm/glm.hpp>
#include <glm/gtc/matrix_transform.hpp>

//
// FUNCIÓN: Object::ResetLocation()
//
// PROPÓSITO: Asigna la posición inicial del objecto 
//
void Object::ResetLocation()
{
    model = glm::mat4(1.0f);
}

//
// FUNCIÓN: Object::SetLocation(glm::mat4 loc)
//
// PROPÓSITO: Asigna la posición del objecto 
//
void Object::SetLocation(glm::mat4 loc)
{
    model = loc;
}

//
// FUNCIÓN: Object::GetLocation()
//
// PROPÓSITO: Obtiene la posición del objecto 
//
glm::mat4 Object::GetLocation()
{
    return model;
}

//
// FUNCIÓN: Object::Translate(glm::vec3 t)
//
// PROPÓSITO: Añade un desplazamiento a la matriz de posición del objeto 
//
void Object::Translate(glm::vec3 t)
{
    model = glm::translate(model, t);
}

//
// FUNCIÓN: Object::Rotate(GLfloat angle, glm::vec3 axis)
//
// PROPÓSITO: Añade una rotación a la matriz de posición del objeto 
//
void Object::Rotate(GLfloat angle, glm::vec3 axis)
{
    model = glm::rotate(model, glm::radians(angle), axis);
}

//
// FUNCIÓN: Object::Draw(ShaderProgram * program, 
//                         glm::mat4 projection, glm::mat4 view)
//
// PROPÓSITO: Dibuja el objeto
//
void Object::Draw(ShaderProgram* program, glm::mat4 projection, glm::mat4 view)
{
    int num = GetNumPieces();
    for (int i = 0; i < num; i++)
    {
        GetPiece(i)->Draw(program, projection, view, model);
    }
}