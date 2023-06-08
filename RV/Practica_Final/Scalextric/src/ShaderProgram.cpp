#include <GL/glew.h>
#include <SDKDDKVer.h>
#include <Windows.h>
#include "ShaderProgram.h"
#include "resource.h"

//
// FUNCION: ShaderProgram::ShaderProgram()
//
// PROPOSITO: Crea un programa grafico cargando y compilando los shaders que lo forman
//
ShaderProgram::ShaderProgram()
{
	GLint status;
	logInfo = NULL;

	// Crea y compila el vertex shader
	vertexShader = glCreateShader(GL_VERTEX_SHADER);
	glShaderSource(vertexShader, 1, &VertexShader, NULL);
	glCompileShader(vertexShader);
	glGetShaderiv(vertexShader, GL_COMPILE_STATUS, &status);
	if (status == GL_FALSE)
	{
		linked = GL_FALSE;
		GLint logLength;
		glGetShaderiv(vertexShader, GL_INFO_LOG_LENGTH, &logLength);
		logInfo = (char*)malloc(sizeof(char) * logLength);
		GLsizei written;
		glGetShaderInfoLog(vertexShader, logLength, &written, logInfo);
		return;
	}

	// Crea y compila el fragment shader
	fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
	glShaderSource(fragmentShader, 1, &FragmentShader, NULL);
	glCompileShader(fragmentShader);
	glGetShaderiv(fragmentShader, GL_COMPILE_STATUS, &status);
	if (status == GL_FALSE)
	{
		linked = GL_FALSE;
		GLint logLength;
		glGetShaderiv(fragmentShader, GL_INFO_LOG_LENGTH, &logLength);
		logInfo = (char*)malloc(sizeof(char) * logLength);
		GLsizei written;
		glGetShaderInfoLog(fragmentShader, logLength, &written, logInfo);
		return;
	}

	//Crea y enlaza el programa
	program = glCreateProgram();
	glAttachShader(program, vertexShader);
	glAttachShader(program, fragmentShader);
	glLinkProgram(program);
	glGetProgramiv(program, GL_LINK_STATUS, &status);
	if (status == GL_FALSE)
	{
		linked = GL_FALSE;
		GLint logLength;
		glGetProgramiv(program, GL_INFO_LOG_LENGTH, &logLength);
		logInfo = (char*)malloc(sizeof(char) * logLength);
		GLsizei written;
		glGetProgramInfoLog(program, logLength, &written, logInfo);
		return;
	}

	linked = GL_TRUE;
}

//
// FUNCION: ShaderProgram::GetShaderCodeFromResource(int idr)
//
// PROPOSITO: Obtiene el contenido de un fichero de texto almacenado como un recurso
//
char** ShaderProgram::GetShaderCodeFromResource(int idr)
{
	HRSRC shaderHandle = FindResource(NULL, MAKEINTRESOURCE(idr), RT_HTML);
	HGLOBAL shaderGlobal = LoadResource(NULL, shaderHandle);
	LPCTSTR shaderPtr = static_cast<LPCTSTR>(LockResource(shaderGlobal));
	DWORD shaderSize = SizeofResource(NULL, shaderHandle);
	char* shaderCodeLine = (char*)malloc((shaderSize + 1) * sizeof(char));
	memcpy(shaderCodeLine, shaderPtr, shaderSize);
	shaderCodeLine[shaderSize] = '\0';
	char** shaderCode = (char**)malloc(sizeof(char*));
	shaderCode[0] = shaderCodeLine;
	FreeResource(shaderGlobal);
	return shaderCode;
}

//
// FUNCION: ShaderProgram::~ShaderProgram()
//
// PROPOSITO: Destruye el programa grafico
//
ShaderProgram::~ShaderProgram()
{
	glDeleteShader(vertexShader);
	glDeleteShader(fragmentShader);
	glDeleteProgram(program);
}

//
// FUNCION: ShaderProgram::IsLinked()
//
// PROPOSITO: Verifica si el programa se ha compilado y linkado de forma correcta
//
GLboolean ShaderProgram::IsLinked()
{
	return linked;
}

//
// FUNCION: ShaderProgram::GetLog()
//
// PROPOSITO: Obtiene el mensaje de error
//
char* ShaderProgram::GetLog()
{
	return logInfo;
}

//
// FUNCION: ShaderProgram::Use()
//
// PROPOSITO: Activa el funcionamiento del programa en la tarjeta grafica
//
GLvoid ShaderProgram::Use()
{
	glUseProgram(program);
}

//
// FUNCION: ShaderProgram::SetUniformF(const char* name, GLfloat f)
//
// PROPOSITO: Asigna el valor de una variable uniforme de tipo float
//
void ShaderProgram::SetUniformF(const char* name, GLfloat f)
{
	GLuint location = glGetUniformLocation(program, name);
	if (location >= 0) glUniform1f(location, f);
}

//
// FUNCION: ShaderProgram::SetUniformMatrix4(const char* name, glm::mat4 m)
//
// PROPOSITO: Asigna el valor de una variable uniforme de tipo mat4 (matriz 4x4)
//
GLvoid ShaderProgram::SetUniformMatrix4(const char* name, glm::mat4 m)
{
	GLuint location = glGetUniformLocation(program, name);
	if (location >= 0) glUniformMatrix4fv(location, 1, GL_FALSE, &m[0][0]);
}

//
// FUNCION: ShaderProgram::SetUniformVec4(const char* name, glm::vec4 v)
//
// PROPOSITO: Asigna el valor de una variable uniforme de tipo vec4 (vector de 4 float)
//
void ShaderProgram::SetUniformVec4(const char* name, glm::vec4 v)
{
	GLuint location = glGetUniformLocation(program, name);
	if (location >= 0) glUniform4fv(location, 1, &v[0]);
}

//
// FUNCION: ShaderProgram::SetUniformVec3(const char* name, glm::vec3 v)
//
// PROPOSITO: Asigna el valor de una variable uniforme de tipo vec3 (vector de 3 float)
//
void ShaderProgram::SetUniformVec3(const char* name, glm::vec3 v)
{
	GLuint location = glGetUniformLocation(program, name);
	if (location >= 0) glUniform3fv(location, 1, &v[0]);
}

//
// FUNCION: ShaderProgram::SetUniformI(const char* name, GLint i)
//
// PROPOSITO: Asigna el valor de una variable uniforme de tipo entero
//
void ShaderProgram::SetUniformI(const char* name, GLint i)
{
	GLuint location = glGetUniformLocation(program, name);
	if (location >= 0) glUniform1i(location, i);
}
