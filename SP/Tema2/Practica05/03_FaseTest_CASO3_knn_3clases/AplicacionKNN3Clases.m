clc, clear;

addpath('../Funciones');

load ("../02_FaseEntrenamiento_CASO1_LDA_clases2a2/02_CirculoTriangulo/DatosGenerados/espacio_ccas_circulos_triangulos.mat");
load ("../02_FaseEntrenamiento_CASO1_LDA_clases2a2/02_CirculoTriangulo/DatosGenerados/coeficientes_LDA.mat");

espacio_ccas_circulos_triangulo = espacio_ccas_j;
nombres_problema_circulos_triangulo = nombres_problema_oi;
d12_circulo_triangulo = d12;

load('../02_FaseEntrenamiento_CASO3_knn_3clases/DatosGenerados/DatosTestNormalizados.mat')
load("../01_GeneracionDatos/DatosGenerados/conjunto_datos_estandarizados.mat");
load("../01_GeneracionDatos/DatosGenerados/datos_estandarizacion.mat");
load("../01_GeneracionDatos/DatosGenerados/nombres_problema.mat");
addpath("../ImagenesPractica5/Test/")
XTrain = Z;
XTest = ZTest;
YTrain = Y;

clear Z;
clear ZTest;

codif_clases = nombresProblema.codif_clases;
nombre_clases = nombresProblema.clases;

dim = 5;
k = dim;
%[espacio_ccas, j_valor] = funcion_selecciona_vector_ccas(XTrain, YTrain, dim);

num_imagenes = 1;
for imagen = 1:num_imagenes
    nombre_imagen = "Test" + num2str(imagen) + ".JPG";
    I = imread(nombre_imagen);

    Ib = I < 255*graythresh(I);
    [IEtiq, N] = bwlabel(Ib);

    YTest = funcion_knn(XTest, XTrain, YTrain, k);

    figure(), hold on;
    for forma = 1:N
        posClaseOI = find(ismember(codif_clases, YTest(forma)));
        titulo = "Pertenece a la clase: " + nombre_clases(posClaseOI);
        funcion_visualiza(I, IEtiq == forma, [255, 0, 0], true); title(titulo), hold on;
        pause;
    end
    close all
end