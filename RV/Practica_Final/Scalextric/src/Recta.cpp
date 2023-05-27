#include "Recta.h"

Recta::Recta(GLfloat width, GLfloat heigth)
{
    numFaces = 2; // Number of faces
    numVertices = 4; // Number of vertices

    GLfloat p_vertices[4][3] = {
       //{ +width, +width, +width }, // A0 // Positive X
       //{ +width, -width, +width }, // D0 
       //{ +width, -width, -width }, // D1 
       //{ +width, +width, -width }, // A1 

       //{ -width, -width, +width }, // C0 // Negative X
       //{ -width, +width, +width }, // B0 
       //{ -width, +width, -width }, // B1 
       //{ -width, -width, -width }, // C1  

       { +width, +heigth, +width }, // A0 // Positive Z
       { -width, +heigth, +width }, // B0 
       { -width, -heigth, +width }, // C0 
       { +width, -heigth, +width }, // D0 
    };

    /*
                        
              3  ____ 0
                |  /|
                | / |
                |/__| 1
               2
    
    */

    GLushort p_indexes[2][3] = { // Array of indexes
       { 0, 1, 2 },
       { 0, 2, 3 },
       //{ 4, 5, 6 },
       //{ 4, 6, 7 }
    };

    vertices = new GLfloat[numVertices * 3];
    for (int i = 0; i < numVertices; i++)
        for (int j = 0; j < 3; j++)
            vertices[3 * i + j] = p_vertices[i][j];

    indexes = new GLushort[numFaces * 3];
    for (int i = 0; i < numFaces; i++)
        for (int j = 0; j < 3; j++)
            indexes[3 * i + j] = p_indexes[i][j];

    InitBuffers();
}
