clear, clc;
addpath("../../Funciones/");
load("../../01_GeneracionDatos/DatosGenerados/conjunto_datos_estandarizados.mat");
load("../../01_GeneracionDatos/DatosGenerados/datos_estandarizacion.mat");
load("../../01_GeneracionDatos/DatosGenerados/nombres_problema.mat")

% Cambiar el nombre a la Z
X = Z;
clear Z;

% Recodificar las clases Circulo-Triangulo

clases = unique(Y);

triangulo = clases(3);
cuadrado = clases(2);
circulo_triangulo = clases(1);

Y_train = Y;
Y_train(Y_train== triangulo) = circulo_triangulo;

% Calcular el mejor espacio ccas
dim = 4;
[espacio_ccas, j_valor] = funcion_selecciona_vector_ccas(X,Y,dim);

% actualizar datos problema

nombres_problema_oi.descriptores = nombresProblema.descriptores(espacio_ccas);
nombres_problema_oi.codif_clases = [circulo_triangulo, cuadrado];
nombres_problema_oi.clases = ["Circulos-Triangulos", "Cuadrados"];
nombres_problema_oi.simbolos = ["*r", "*b"];

save("DatosGenerados/nombres_problema.mat", "nombres_problema_oi");
save("DatosGenerados/conjunto_datos", "espacio_ccas", "j_valor", "Y_train")
