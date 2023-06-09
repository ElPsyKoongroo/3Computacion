#include "Camara.h"
#include <GL/glew.h>
#include <math.h>
#include <glm/glm.hpp>
#include <glm/gtc/matrix_transform.hpp>

//
// FUNCIÓN: Camara::Camara()
//
// PROPÓSITO: Construye una cámara
//
// COMENTARIOS: 
//     La posición inicial es (0,0,0).
//     La orientación incial es el sistema de coordenadas del modelo
//     El tamaño del paso inicial es 0.1
//     El tamaño del giro inicial es 1.0 grados
//
Camara::Camara()
{
    Pos = glm::vec3(0.0f, 0.0f, 0.0f);
    Dir = glm::vec3(0.0f, 0.0f, 1.0f);
    Up = glm::vec3(0.0f, 1.0f, 0.0f);
    Right = glm::vec3(1.0f, 0.0f, 0.0f);

    moveStep = 0.1f;
    turnStep = 1.0f;
    cosAngle = (GLfloat)cos(glm::radians(turnStep));
    sinAngle = (GLfloat)sin(glm::radians(turnStep));
}

//
// FUNCIÓN: Camara::ViewMatrix()
//
// PROPÓSITO: Obtiene la matriz View para situar la cámara.
//
glm::mat4 Camara::ViewMatrix()
{
    // Creates a rotation matrix using vectors Dir, Up and Right
    glm::mat4 matrix(1.0f);
    matrix[0][0] = Right.x;
    matrix[1][0] = Right.y;
    matrix[2][0] = Right.z;
    matrix[3][0] = 0.0f;
    matrix[0][1] = Up.x;
    matrix[1][1] = Up.y;
    matrix[2][1] = Up.z;
    matrix[3][1] = 0.0f;
    matrix[0][2] = Dir.x;
    matrix[1][2] = Dir.y;
    matrix[2][2] = Dir.z;
    matrix[3][2] = 0.0f;
    matrix[0][3] = 0.0f;
    matrix[1][3] = 0.0f;
    matrix[2][3] = 0.0f;
    matrix[3][3] = 1.0f;

    return glm::translate(matrix, -Pos);
}

//
// FUNCIÓN: Camara::SetPosition(GLfloat x, GLfloat y, GLfloat z)
//
// PROPÓSITO: Asigna la posición de la cámara con respecto al 
//            sistema de coordenadas del modelo.
//
void Camara::SetPosition(GLfloat x, GLfloat y, GLfloat z)
{
    Pos = glm::vec3(x, y, z);
}

//
// FUNCIÓN: Camara::SetDirection(GLfloat xDir, GLfloat yDir, GLfloat zDir, 
//                                 GLfloat xUp, GLfloat yUp, GLfloat zUp)
//
// PROPÓSITO: Asigna la orientación de la cámara.
//
void Camara::SetDirection(GLfloat xD, GLfloat yD, GLfloat zD,
    GLfloat xU, GLfloat yU, GLfloat zU)
{
    Dir = glm::vec3(xD, yD, zD);
    Up = glm::vec3(xU, yU, zU);
    Right = glm::cross(Up, Dir);
}

//
// FUNCIÓN: Camara::SetMoveStep(GLfloat step)
//
// PROPÓSITO: Asigna el avance en cada paso.
//
void Camara::SetMoveStep(GLfloat step)
{
    moveStep = step;
}

//
// FUNCIÓN: Camara::SetTurnStep(GLfloat step)
//
// PROPÓSITO: Asigna el ángulo de giro en cada paso.
//
void Camara::SetTurnStep(GLfloat step)
{
    turnStep = step;
    cosAngle = (GLfloat)cos(glm::radians(turnStep));
    sinAngle = (GLfloat)sin(glm::radians(turnStep));
}

//
// FUNCIÓN: Camara::GetPosition()
//
// PROPÓSITO: Obtiene la posición de la cámara.
//
glm::vec3 Camara::GetPosition()
{
    return Pos;
}

//
// FUNCIÓN: Camara::GetDirection()
//
// PROPÓSITO: Obtiene la orientación de la cámara (eje Z).
//
glm::vec3 Camara::GetDirection()
{
    return Dir;
}

//
// FUNCIÓN: Camara::GetUpDirection()
//
// PROPÓSITO: Obtiene la orientación cenital de la cámara (eje Y).
//
glm::vec3 Camara::GetUpDirection()
{
    return Up;
}

//
// FUNCIÓN: Camara::GetMoveStep()
//
// PROPÓSITO: Obtiene el avance en cada paso.
//
GLfloat Camara::GetMoveStep()
{
    return moveStep;
}

//
// FUNCIÓN: Camara::GetTurnStep()
//
// PROPÓSITO: Obtiene el ángulo de giro en cada paso.
//
GLfloat Camara::GetTurnStep()
{
    return turnStep;
}

//
// FUNCIÓN: Camara::MoveFront()
//
// PROPÓSITO: Mueve el observador un paso (moveStep) en la dirección -Dir 
//
//
void Camara::MoveFront()
{
    Pos -= moveStep * Dir;
}

//
// FUNCIÓN: Camara::MoveBack()
//
// PROPÓSITO: Mueve el observador un paso (moveStep) hacia atrás en la dirección Dir 
//
//
void Camara::MoveBack()
{
    Pos += moveStep * Dir;
}

//
// FUNCIÓN: Camara::MoveLeft()
//
// PROPÓSITO: Mueve el observador un paso (moveStep) hacia la izquierda. 
//
//
void Camara::MoveLeft()
{
    Pos -= moveStep * Right;
}

//
// FUNCIÓN: Camara::MoveRight()
//
// PROPÓSITO: Mueve el observador un paso (moveStep) hacia la derecha. 
//
//
void Camara::MoveRight()
{
    Pos += moveStep * Right;
}

//
// FUNCIÓN: Camara::MoveUp()
//
// PROPÓSITO: Mueve el observador un paso (moveStep) hacia arriba. 
//
//
void Camara::MoveUp()
{
    Pos += moveStep * Up;
}

//
// FUNCIÓN: Camara::MoveDown()
//
// PROPÓSITO: Mueve el observador un paso (moveStep) hacia abajo. 
//
void Camara::MoveDown()
{
    Pos -= moveStep * Up;
}

//
// FUNCIÓN: Camara::TurnRight()
//
// PROPÓSITO: Rota el observador un paso (angleStep) hacia su derecha.
//
void Camara::TurnRight()
{
    Dir.x = cosAngle * Dir.x - sinAngle * Right.x;
    Dir.y = cosAngle * Dir.y - sinAngle * Right.y;
    Dir.z = cosAngle * Dir.z - sinAngle * Right.z;

    // Right = Up x Dir
    Right = glm::cross(Up, Dir);
}

//
// FUNCIÓN: Camara::TurnLeft()
//
// PROPÓSITO: Rota el observador un paso (angleStep) hacia su izquierda.
//
void Camara::TurnLeft()
{
    Dir.x = cosAngle * Dir.x + sinAngle * Right.x;
    Dir.y = cosAngle * Dir.y + sinAngle * Right.y;
    Dir.z = cosAngle * Dir.z + sinAngle * Right.z;

    // Right = Up x Dir
    Right = glm::cross(Up, Dir);
}

//
// FUNCIÓN: Camara::TurnUp()
//
// PROPÓSITO: Rota el observador un paso (angleStep) hacia arriba.
//
void Camara::TurnUp()
{
    Dir.x = cosAngle * Dir.x - sinAngle * Up.x;
    Dir.y = cosAngle * Dir.y - sinAngle * Up.y;
    Dir.z = cosAngle * Dir.z - sinAngle * Up.z;

    // Up = Dir x Right
    Up = glm::cross(Dir, Right);
}

//
// FUNCIÓN: Camara::TurnDown()
//
// PROPÓSITO: Rota el observador un paso (angleStep) hacia abajo.
//
void Camara::TurnDown()
{
    Dir.x = cosAngle * Dir.x + sinAngle * Up.x;
    Dir.y = cosAngle * Dir.y + sinAngle * Up.y;
    Dir.z = cosAngle * Dir.z + sinAngle * Up.z;

    // Up = Dir x Right
    Up = glm::cross(Dir, Right);
}

//
// FUNCIÓN: Camara::TurnCW()
//
// PROPÓSITO: Rota el observador un paso (angleStep) en sentido del reloj.
//
void Camara::TurnCW()
{
    Up.x = cosAngle * Up.x + sinAngle * Right.x;
    Up.y = cosAngle * Up.y + sinAngle * Right.y;
    Up.z = cosAngle * Up.z + sinAngle * Right.z;

    // Right = Up x Dir
    Right = glm::cross(Up, Dir);
}

//
// FUNCIÓN: Camara::TurnDown()
//
// PROPÓSITO: Rota el observador un paso (angleStep) en sentido contrario al reloj.
//
void Camara::TurnCCW()
{
    Up.x = cosAngle * Up.x - sinAngle * Right.x;
    Up.y = cosAngle * Up.y - sinAngle * Right.y;
    Up.z = cosAngle * Up.z - sinAngle * Right.z;

    // Right = Up x Dir
    Right = glm::cross(Up, Dir);
}

void Camara::ChangeCamaraView(glm::vec3 position, glm::vec3 direction)
{
    
}
