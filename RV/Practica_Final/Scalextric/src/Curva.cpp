#include "Curva.h"
#include <GL/glew.h>
#include <math.h>
#include "Figura.h"

/**
Cosos:

    p: Numero de divisiones del interior del circulo
    m: Cantidad de vertices de la circunferencia
    r0: Radio del circulo interior
    r1: Radio del circulo exterior
    angle: angulo del arco del circulo

*/
Curva::Curva(GLint p, GLint m, GLfloat r0, GLfloat r1, double angle)
{
    numFaces = (2 * (m+1) * p); // Number of faces
    numVertices = ((m+1) * (p + 1)); // Number of vertices
    vertices = new GLfloat[numVertices * 3];
    indexes = new GLushort[numFaces * 3];

    int verticesIndex = 0;
    int indexesIndex = 0;

    double angle_step = (angle) / m;
    for (int i = 0; i <= p; i++)
    {
        GLfloat r = r0 + (r1 - r0)*i / p;

        for (int j = 0; j <= m; j++)
        {
            GLfloat mCos = (GLfloat)cos(glm::radians(j * angle_step));
            GLfloat mSin = (GLfloat)sin(glm::radians(j * angle_step));
            vertices[verticesIndex] = mCos * r;
            vertices[verticesIndex + 1] = mSin * r;
            vertices[verticesIndex + 2] = 0.0f;
            verticesIndex += 3;
        }
    }

    for (int i = 0; i < p; i++)
    {
        for (int j = 0; j <= m; j++)
        {
            indexes[indexesIndex] = (m+1) * i + j;
            indexes[indexesIndex + 1] = (m + 1) *(i + 1) + j;
            indexes[indexesIndex + 2] = ((m + 1) *(i + 1) + j + 1);
            indexesIndex += 3;

            indexes[indexesIndex] = (m + 1) *i + j;
            indexes[indexesIndex + 1] = ((m + 1) * (i + 1) + j + 1);
            indexes[indexesIndex + 2] = ((m + 1) * i + j + 1);
            indexesIndex += 3;
        }
    }


    InitBuffers();
}
