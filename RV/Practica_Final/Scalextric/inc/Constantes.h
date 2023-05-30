#pragma once
#include <GL/glew.h>

constexpr GLfloat ANCHURA_PISTA = 7.0f;
constexpr GLfloat CURVA_RADIO_EXTERIOR = 12.5;
constexpr GLfloat CURVA_RADIO_INTERIOR = CURVA_RADIO_EXTERIOR-ANCHURA_PISTA;
constexpr GLfloat DESPLAZAMIENTO_CURVA = CURVA_RADIO_INTERIOR+(ANCHURA_PISTA/2);
constexpr int CURVA_P = 2;
constexpr int CURVA_M = 33;

/*


    Xx + R*cos(alfa) = Ax     =>    Xx = Ax - R*cos(alfa)
    Xy + R*sen(alfa) = Ay     =>    Xy = Ay - R*sen(alfa)

              Anti-clockwise
                Scalektrix  
                Convention
           +------------------+
           |                  |
           |        0         |  
           |      / | \       |    
           |     /  |  \      |     
           |    90--+--270    |       
           |     \  |  /      |     
           |      \ | /       |    
           |       180        |
           |                  |   
           +------------------+

*/