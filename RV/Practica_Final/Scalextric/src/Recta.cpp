#include "Recta.h"

Recta::Recta(GLfloat heigth, GLfloat w)
{
    GLfloat width;
    if (w == -1.0f) { width = ANCHURA_PISTA; }
    else { width = w; }

    numFaces = 2; // Number of faces
    numVertices = 4; // Number of vertices

    GLfloat p_vertices[4][3] = {
       { -width/2, 0, 0.0 },        //DL
       { +width/2, 0, 0.0 },        //DR
       { +width/2, heigth, 0.0 },   //UR
       { -width/2, heigth, 0.0 }    //UL
    };

    GLushort p_indexes[2][3] = { // Array of indexes
       { 0, 1, 2 },
       { 0, 2, 3 },
    };

    GLfloat p_textures[][2] = { // Array of texture coordinates
        { 0.0f, 0.0f },
        { 0.0f, 1.0f },
        { 1.0f, 1.0f },
        { 1.0f, 0.0f }
  };

    GLfloat p_normals[4][3] = {
        { 0.0f, 0.0f, 1.0f }, 
        { 0.0f, 0.0f, 1.0f }, 
        { 0.0f, 0.0f, 1.0f }, 
        { 0.0f, 0.0f, 1.0f }, 
    };

    vertices = new GLfloat[numVertices * 3];
    for (int i = 0; i < numVertices; i++)
        for (int j = 0; j < 3; j++)
            vertices[3 * i + j] = p_vertices[i][j];


    indexes = new GLushort[numFaces * 3];
    for (int i = 0; i < numFaces; i++)
        for (int j = 0; j < 3; j++)
            indexes[3 * i + j] = p_indexes[i][j];


    textures = new GLfloat[numVertices * 2];
    for (int i = 0; i < numVertices; i++) 
        for (int j = 0; j < 2; j++) 
            textures[2 * i + j] = p_textures[i][j];


    normals = new GLfloat[numVertices * 3];
    for (int i = 0; i < numVertices; i++)
        for (int j = 0; j < 3; j++) 
            normals[3 * i + j] = p_normals[i][j];

    InitBuffers();
}

Recta::~Recta() {}