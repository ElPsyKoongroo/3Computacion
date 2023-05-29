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

    float posZ = 0.0f;
    posInicial = glm::vec3(-2.50f, 0.0f, posZ);

    Figurita recta1_parte1 { TipoFigura::Recta,   
                             posInicial, glm::vec3(-2.50f, 2.0f, posZ), 
                             std::make_pair<GLfloat,glm::vec3>(0.0, glm::vec3(0,0,0)) };

    Figurita recta1_parte2 { TipoFigura::Recta,   
                             glm::vec3(-2.50f, 4.00f, posZ), 
                             glm::vec3(-2.50f, 6.00f, posZ),
                             std::make_pair<GLfloat,glm::vec3>(0.0, glm::vec3(0,0,0)) };

    


    instrucciones.push_back(recta1_parte1);
    instrucciones.push_back(recta1_parte2);


    Figurita curva1 {
        TipoFigura::Curva,
        glm::vec3(-2.5f, 6.0f, posZ),
        glm::vec3(+7.5f, 6.0f, posZ),
        std::make_pair<GLfloat,glm::vec3>(0.0, glm::vec3(0,0,0)) };


    instrucciones.push_back(curva1);
}
