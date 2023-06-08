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

void Object::SetLocation(glm::vec3 t) {
	
    // glm::mat4 translationMatrix = glm::translate(glm::mat4(1.0f), t);

    // model = translationMatrix * model;

    model[3][0] = t[0];
    model[3][1] = t[1];
    model[3][2] = 0.9;

/*
	glm::mat4 copy = model;

	copy[0][3] = 0;
	copy[1][3] = 0;
	
	model = glm::translate(copy, t);
	copy[2][3] = 0.9;*/
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