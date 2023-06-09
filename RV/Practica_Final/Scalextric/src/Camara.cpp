#include "Camara.h"
#include <GL/glew.h>
#include <math.h>
#include <glm/glm.hpp>
#include <glm/gtc/matrix_transform.hpp>

//
// FUNCI�N: Camara::Camara()
//
// PROP�SITO: Construye una c�mara
//
// COMENTARIOS: 
//     La posici�n inicial es (0,0,0).
//     La orientaci�n incial es el sistema de coordenadas del modelo
//     El tama�o del paso inicial es 0.1
//     El tama�o del giro inicial es 1.0 grados
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
// FUNCI�N: Camara::ViewMatrix()
//
// PROP�SITO: Obtiene la matriz View para situar la c�mara.
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
// FUNCI�N: Camara::SetPosition(GLfloat x, GLfloat y, GLfloat z)
//
// PROP�SITO: Asigna la posici�n de la c�mara con respecto al 
//            sistema de coordenadas del modelo.
//
void Camara::SetPosition(GLfloat x, GLfloat y, GLfloat z)
{
    Pos = glm::vec3(x, y, z);
}

//
// FUNCI�N: Camara::SetDirection(GLfloat xDir, GLfloat yDir, GLfloat zDir, 
//                                 GLfloat xUp, GLfloat yUp, GLfloat zUp)
//
// PROP�SITO: Asigna la orientaci�n de la c�mara.
//
void Camara::SetDirection(GLfloat xD, GLfloat yD, GLfloat zD,
    GLfloat xU, GLfloat yU, GLfloat zU)
{
    Dir = glm::vec3(xD, yD, zD);
    Up = glm::vec3(xU, yU, zU);
    Right = glm::cross(Up, Dir);
}

//
// FUNCI�N: Camara::SetMoveStep(GLfloat step)
//
// PROP�SITO: Asigna el avance en cada paso.
//
void Camara::SetMoveStep(GLfloat step)
{
    moveStep = step;
}

//
// FUNCI�N: Camara::SetTurnStep(GLfloat step)
//
// PROP�SITO: Asigna el �ngulo de giro en cada paso.
//
void Camara::SetTurnStep(GLfloat step)
{
    turnStep = step;
    cosAngle = (GLfloat)cos(glm::radians(turnStep));
    sinAngle = (GLfloat)sin(glm::radians(turnStep));
}

//
// FUNCI�N: Camara::GetPosition()
//
// PROP�SITO: Obtiene la posici�n de la c�mara.
//
glm::vec3 Camara::GetPosition()
{
    return Pos;
}

//
// FUNCI�N: Camara::GetDirection()
//
// PROP�SITO: Obtiene la orientaci�n de la c�mara (eje Z).
//
glm::vec3 Camara::GetDirection()
{
    return Dir;
}

//
// FUNCI�N: Camara::GetUpDirection()
//
// PROP�SITO: Obtiene la orientaci�n cenital de la c�mara (eje Y).
//
glm::vec3 Camara::GetUpDirection()
{
    return Up;
}

//
// FUNCI�N: Camara::GetMoveStep()
//
// PROP�SITO: Obtiene el avance en cada paso.
//
GLfloat Camara::GetMoveStep()
{
    return moveStep;
}

//
// FUNCI�N: Camara::GetTurnStep()
//
// PROP�SITO: Obtiene el �ngulo de giro en cada paso.
//
GLfloat Camara::GetTurnStep()
{
    return turnStep;
}

//
// FUNCI�N: Camara::MoveFront()
//
// PROP�SITO: Mueve el observador un paso (moveStep) en la direcci�n -Dir 
//
//
void Camara::MoveFront()
{
    Pos -= moveStep * Dir;
}

//
// FUNCI�N: Camara::MoveBack()
//
// PROP�SITO: Mueve el observador un paso (moveStep) hacia atr�s en la direcci�n Dir 
//
//
void Camara::MoveBack()
{
    Pos += moveStep * Dir;
}

//
// FUNCI�N: Camara::MoveLeft()
//
// PROP�SITO: Mueve el observador un paso (moveStep) hacia la izquierda. 
//
//
void Camara::MoveLeft()
{
    Pos -= moveStep * Right;
}

//
// FUNCI�N: Camara::MoveRight()
//
// PROP�SITO: Mueve el observador un paso (moveStep) hacia la derecha. 
//
//
void Camara::MoveRight()
{
    Pos += moveStep * Right;
}

//
// FUNCI�N: Camara::MoveUp()
//
// PROP�SITO: Mueve el observador un paso (moveStep) hacia arriba. 
//
//
void Camara::MoveUp()
{
    Pos += moveStep * Up;
}

//
// FUNCI�N: Camara::MoveDown()
//
// PROP�SITO: Mueve el observador un paso (moveStep) hacia abajo. 
//
void Camara::MoveDown()
{
    Pos -= moveStep * Up;
}

//
// FUNCI�N: Camara::TurnRight()
//
// PROP�SITO: Rota el observador un paso (angleStep) hacia su derecha.
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
// FUNCI�N: Camara::TurnLeft()
//
// PROP�SITO: Rota el observador un paso (angleStep) hacia su izquierda.
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
// FUNCI�N: Camara::TurnUp()
//
// PROP�SITO: Rota el observador un paso (angleStep) hacia arriba.
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
// FUNCI�N: Camara::TurnDown()
//
// PROP�SITO: Rota el observador un paso (angleStep) hacia abajo.
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
// FUNCI�N: Camara::TurnCW()
//
// PROP�SITO: Rota el observador un paso (angleStep) en sentido del reloj.
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
// FUNCI�N: Camara::TurnDown()
//
// PROP�SITO: Rota el observador un paso (angleStep) en sentido contrario al reloj.
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
