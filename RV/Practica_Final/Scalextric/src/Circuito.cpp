#include <GL/glew.h>
#include <cmath>
#include "Circuito.h"

Circuito::Circuito()
{
    /*       
             ____
            /    \
    Recta   |    |
      1     |    |
            \____/
    */


    /*
       0,30
        |
        |
        |
       0,0
    */

    const float posZ = 0.5f;

    posInicial = glm::vec3(-2.50f, 0.0f, posZ);
    FiguraData r1p1 = RectaData {
        posInicial, 
        4, 
        std::make_pair<GLfloat,glm::vec3>(0.0, glm::vec3(0,0,1))
    };

    FiguraData r1p2 = RectaData {
        glm::vec3(-2.50f, 4.00f, posZ), 
        4,
        std::make_pair<GLfloat,glm::vec3>(0.0, glm::vec3(0,0,1))
    };


    instrucciones.push_back(r1p1);
    instrucciones.push_back(r1p2);


    FiguraData c1 = CurvaData {
        glm::vec3(-2.5f, 8.00f, posZ),
        180.0f,
        true,
        std::make_pair<GLfloat,glm::vec3>(0.0f, glm::vec3(0,0,1))
    };


    instrucciones.push_back(c1);


    FiguraData r2p1 = RectaData{
        glm::vec3(4.5f, 8.00f, posZ),
        4,
        std::make_pair<GLfloat,glm::vec3>(180.f, glm::vec3(0,0,1))
    };

    FiguraData r2p2 = RectaData{
        glm::vec3(4.5f, 4.00f, posZ),
        4,
        std::make_pair<GLfloat,glm::vec3>(180.f, glm::vec3(0,0,1))
    };

    instrucciones.push_back(r2p1);
    instrucciones.push_back(r2p2);


    FiguraData c2 = CurvaData {
        glm::vec3(4.5f, 0.00f, posZ),
        180.0f,
        true,
        std::make_pair<GLfloat,glm::vec3>(180.0, glm::vec3(0,0,1))
    };


    instrucciones.push_back(c2);

}
