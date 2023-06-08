#include "Curva.h"
#include <GL/glew.h>
#include <math.h>
#include "Figura.h"
#include "Constantes.h"

/**
Cosos:

    p: Numero de divisiones del interior del circulo
    m: Cantidad de vertices de la circunferencia
    r0: Radio del circulo interior
    r1: Radio del circulo exterior
    angle: angulo del arco del circulo

*/
Curva::Curva(double angle)
{
    GLfloat r1 = CURVA_RADIO_EXTERIOR;
    GLfloat r0 = CURVA_RADIO_INTERIOR;

    numFaces = (2 * (CURVA_M+1) * CURVA_P); // Number of faces
    numVertices = ((CURVA_M+1) * (CURVA_P + 1)); // Number of vertices
    vertices = new GLfloat[numVertices * 3];
    indexes = new GLushort[numFaces * 3];

    normals = new GLfloat[numVertices * 3];
    textures = new GLfloat[numVertices * 2];

    int verticesIndex = 0;
    int indexesIndex = 0;

    int normalsIndex = 0;
    int texturesIndex = 0;

    double angle_step = (angle) / CURVA_M;
    for (int i = 0; i <= CURVA_P; i++)
    {
        GLfloat r = r0 + (r1 - r0)*i / CURVA_P;

        for (int j = 0; j <= CURVA_M; j++)
        {
            GLfloat mCos = (GLfloat)cos(glm::radians(j * angle_step));
            GLfloat mSin = (GLfloat)sin(glm::radians(j * angle_step));
            vertices[verticesIndex] = mCos * r;
            vertices[verticesIndex + 1] = mSin * r;
            vertices[verticesIndex + 2] = 0.0f;
            verticesIndex += 3;

            normals[normalsIndex] =  0.0f;
            normals[normalsIndex + 1] = 0.0f;
            normals[normalsIndex + 2] = 1.0f;
            normalsIndex += 3;

            textures[texturesIndex] = ((GLfloat)j) / CURVA_M;
            textures[texturesIndex + 1] = ((GLfloat)i) / CURVA_P;
            texturesIndex += 2;
        }
    }

    for (int i = 0; i < CURVA_P; i++)
    {
        for (int j = 0; j < CURVA_M; j++)
        {
            indexes[indexesIndex] = (CURVA_M+1) * i + j;
            indexes[indexesIndex + 1] = (CURVA_M + 1) *(i + 1) + j;
            indexes[indexesIndex + 2] = ((CURVA_M + 1) *(i + 1) + j + 1);
            indexesIndex += 3;

            indexes[indexesIndex] = (CURVA_M + 1) *i + j;
            indexes[indexesIndex + 1] = ((CURVA_M + 1) * (i + 1) + j + 1);
            indexes[indexesIndex + 2] = ((CURVA_M + 1) * i + j + 1);
            indexesIndex += 3;
        }
    }


    InitBuffers();
}
