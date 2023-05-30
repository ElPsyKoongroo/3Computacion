#include "Cubemah.h"
#include <GL/glew.h>
#include <FreeImage.h>
#include "Figura.h"

//
// FUNCIÓN: Cubemah::Cubemah()
//
// PROPÓSITO: Construye el objeto que describe la imagen de fondo
//
Cubemah::Cubemah()
{
    InitCubemap();
    InitCube();
}

//
// FUNCIÓN: Cubemah::~Cubemah()
//
// PROPÓSITO: Destruye el objeto que describe la imagen de fondo
//
Cubemah::~Cubemah()
{
    glDeleteBuffers(4, VBO);
    glDeleteVertexArrays(1, &VAO);
    glDeleteTextures(1, &cubemap);
}

//
// FUNCIÓN: Cubemah::InitCube()
//
// PROPÓSITO: Inicialliza los buffers con los vértices del telón
//
void Cubemah::InitCube()
{
    GLfloat vertices[12] = {
      -1.0f, -1.0f, -1.0f,
      1.0f, -1.0f, -1.0f,
      1.0f, 1.0f, -1.0f,
      -1.0f, 1.0f, -1.0f
    };
    GLfloat normals[12] = {
      0.0f, 0.0f, 1.0f,
      0.0f, 0.0f, 1.0f,
      0.0f, 0.0f, 1.0f,
      0.0f, 0.0f, 1.0f
    };
    GLfloat textures[8] = {
      -1.0f, -1.0f,
      1.0f, -1.0f,
      1.0f, 1.0f,
      -1.0f, 1.0f,
    };
    GLushort indexes[6] = {
      0,1,2,
      0,2,3
    };

    // Create the Vertex Array Object
    glGenVertexArrays(1, &VAO);
    glBindVertexArray(VAO);

    // Create the Vertex Buffer Objects
    glGenBuffers(4, VBO);

    // Copy data to video memory
    // Vertex data
    glBindBuffer(GL_ARRAY_BUFFER, VBO[VERTEX_DATA]);
    glBufferData(GL_ARRAY_BUFFER, sizeof(GLfloat) * 12, vertices, GL_STATIC_DRAW);

    // Normal data
    glBindBuffer(GL_ARRAY_BUFFER, VBO[NORMAL_DATA]);
    glBufferData(GL_ARRAY_BUFFER, sizeof(GLfloat) * 12, normals, GL_STATIC_DRAW);

    // Texture coordinates
    glBindBuffer(GL_ARRAY_BUFFER, VBO[TEXTURE_DATA]);
    glBufferData(GL_ARRAY_BUFFER, sizeof(GLfloat) * 8, textures, GL_STATIC_DRAW);

    // Indexes
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, VBO[INDEX_DATA]);
    glBufferData(GL_ELEMENT_ARRAY_BUFFER, sizeof(GLushort) * 6, indexes, GL_STATIC_DRAW);

    glEnableVertexAttribArray(0); // Vertex position
    glBindBuffer(GL_ARRAY_BUFFER, VBO[VERTEX_DATA]);
    glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 0, 0);

    glEnableVertexAttribArray(1); // Vertex normals
    glBindBuffer(GL_ARRAY_BUFFER, VBO[NORMAL_DATA]);
    glVertexAttribPointer(1, 3, GL_FLOAT, GL_FALSE, 0, 0);

    glEnableVertexAttribArray(2); // Vertex textures
    glBindBuffer(GL_ARRAY_BUFFER, VBO[TEXTURE_DATA]);
    glVertexAttribPointer(2, 3, GL_FLOAT, GL_FALSE, 0, 0);
}

//
// FUNCIÓN: Cubemah::InitCubemap()
//
// PROPÓSITO: Inicialliza las texturas del cubo
//
void Cubemah::InitCubemap()
{
    glActiveTexture(GL_TEXTURE1);

    glGenTextures(1, &cubemap);
    glBindTexture(GL_TEXTURE_CUBE_MAP, cubemap);
    InitTexture(GL_TEXTURE_CUBE_MAP_POSITIVE_X, "textures/posx.jpg");
    InitTexture(GL_TEXTURE_CUBE_MAP_NEGATIVE_X, "textures/negx.jpg");
    InitTexture(GL_TEXTURE_CUBE_MAP_POSITIVE_Y, "textures/posy.jpg");
    InitTexture(GL_TEXTURE_CUBE_MAP_NEGATIVE_Y, "textures/negy.jpg");
    InitTexture(GL_TEXTURE_CUBE_MAP_POSITIVE_Z, "textures/posz.jpg");
    InitTexture(GL_TEXTURE_CUBE_MAP_NEGATIVE_Z, "textures/negz.jpg");

    //InitTexture(GL_TEXTURE_CUBE_MAP_NEGATIVE_Z, "textures/cubemap/SueloTextura2.jpg");
    //InitTexture(GL_TEXTURE_CUBE_MAP_POSITIVE_Z, "textures/SueloTextura2.jpg");

    // Typical cube map settings
    glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
    glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
    glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_R, GL_CLAMP_TO_EDGE);
}

//
// FUNCIÓN: Cubemah::InitTexture()
//
// PROPÓSITO: Carga una textura
//
void Cubemah::InitTexture(GLuint target, const char* filename)
{
    FREE_IMAGE_FORMAT format = FreeImage_GetFileType(filename, 0);
    FIBITMAP* bitmap = FreeImage_Load(format, filename);
    FIBITMAP* pImage = FreeImage_ConvertTo32Bits(bitmap);
    int nWidth = FreeImage_GetWidth(pImage);
    int nHeight = FreeImage_GetHeight(pImage);

    glTexImage2D(target, 0, GL_RGBA8, nWidth, nHeight,
        0, GL_BGRA, GL_UNSIGNED_BYTE, (void*)FreeImage_GetBits(pImage));

    FreeImage_Unload(pImage);
}

//
// FUNCIÓN: Cubemah::Draw()
//
// PROPÓSITO: Dibuja la imagen de fondo
//
void Cubemah::Draw(ShaderProgram* program, glm::mat4 projection, glm::mat4 view)
{
    // Parte rotacional de la matriz View
    glm::mat3 rot3 = glm::mat3(view);
    glm::mat4 rot4 = glm::mat4(rot3);

    // Transformación del Cubemah a coordenadas Clip
    glm::mat4 mvp = projection * rot4;
    // Transformación de coordenadas Clip a coordenadas de modelo del Cubemah
    glm::mat4 inv = glm::inverse(mvp);

    program->SetUniformMatrix4("MVP", inv);
    program->SetUniformMatrix4("ViewMatrix", view);
    program->SetUniformMatrix4("ModelViewMatrix", view);
    program->SetUniformI("CubemapTex", 1);
    program->SetUniformI("DrawCubemah", 1);

    glDepthMask(GL_FALSE);
    glActiveTexture(GL_TEXTURE1);
    glBindTexture(GL_TEXTURE_CUBE_MAP, cubemap);

    glBindVertexArray(VAO);
    glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_SHORT, NULL);
    program->SetUniformI("DrawCubemah", 0);
    glDepthMask(GL_TRUE);
}