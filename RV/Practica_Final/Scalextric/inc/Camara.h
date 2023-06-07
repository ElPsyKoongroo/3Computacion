#pragma once
#include <GL/glew.h>
#include <glm/glm.hpp>

enum class CamaraEnum {
    Coche1,
    Coche2,
    Circuito
};

class Camara {
public:
    Camara();
    glm::mat4 ViewMatrix();
    CamaraEnum camaraActiva;

    void SetPosition(GLfloat x, GLfloat y, GLfloat z);
    void SetDirection(GLfloat xDir, GLfloat yDir, GLfloat zDir,
        GLfloat xUp, GLfloat yUp, GLfloat zUp);
    void SetMoveStep(GLfloat step);
    void SetTurnStep(GLfloat step);

    glm::vec3 GetPosition();
    glm::vec3 GetDirection();
    glm::vec3 GetUpDirection();
    GLfloat GetMoveStep();
    GLfloat GetTurnStep();

    void MoveFront();
    void MoveBack();
    void MoveRight();
    void MoveLeft();
    void MoveUp();
    void MoveDown();

    void TurnRight();
    void TurnLeft();
    void TurnUp();
    void TurnDown();
    void TurnCW();
    void TurnCCW();

    void ChangeCamaraView(CamaraEnum v);

private:
    glm::vec3 Pos;
    glm::vec3 Dir;
    glm::vec3 Up;
    glm::vec3 Right;

    GLfloat moveStep;
    GLfloat turnStep;
    GLfloat cosAngle;
    GLfloat sinAngle;

    CamaraEnum view;


};