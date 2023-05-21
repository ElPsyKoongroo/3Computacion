clear, clc;
addpath("../Funciones/");

load ("../02_FaseEntrenamiento_CASO1_LDA_clases2a2/02_CirculoTriangulo/DatosGenerados/espacio_ccas_circulos_triangulos.mat");
load ("../02_FaseEntrenamiento_CASO1_LDA_clases2a2/02_CirculoTriangulo/DatosGenerados/coeficientes_LDA.mat");

espacio_ccas_circulos_triangulo = espacio_ccas_j;
nombres_problema_circulos_triangulo = nombres_problema_oi;
d12_circulo_triangulo = d12;

load("../02_FaseEntrenamiento_CASO4_knn_LDA/01_knn_CirculosTriangulos_Cuadrados/DatosGenerados/conjunto_datos.mat")
load("../02_FaseEntrenamiento_CASO4_knn_LDA/01_knn_CirculosTriangulos_Cuadrados/DatosGenerados/nombres_problema.mat")
load('../02_FaseEntrenamiento_CASO3_knn_3clases/DatosGenerados/DatosTestNormalizados.mat')
load("../01_GeneracionDatos/DatosGenerados/conjunto_datos_estandarizados.mat");
load("../01_GeneracionDatos/DatosGenerados/datos_estandarizacion.mat");
load("../01_GeneracionDatos/DatosGenerados/nombres_problema.mat")
addpath("../ImagenesPractica5/Test/")


XTest = ZTest;
YTrain = Y;

% Cambiar el nombre a la Z
XTrain = Z;
clear Z;

% Recodificar las clases Circulo-Triangulo

clases = unique(Y);

triangulo = clases(3);
cuadrado = clases(2);
circulo_triangulo = clases(1);

Y_train = Y;
Y_train(Y_train== triangulo) = circulo_triangulo;

nombre_clases_knn = nombres_problema_oi.clases;
codif_clases_knn = nombres_problema_oi.codif_clases;

k = 5;
num_imagenes = 1;
for imagen = 1:num_imagenes
    nombre_imagen = "Test" + num2str(imagen) + ".JPG";
    I = imread(nombre_imagen);

    Ib = I < 255*graythresh(I);
    [IEtiq, N] = bwlabel(Ib);

    YTest = funcion_knn(XTest, XTrain, Y_train, k);

    figure(), hold on;
    for forma = 1:N
        posClaseOI = find(ismember(covoyvdif_clases_knn, YTest(forma)));
        %titulo = "Pertenece a la clase: " + nombre_clases_knn(posClaseOI);
        if YTest(forma) ~= 69
            titulo = "Pertenece a la clase: " + nombre_clases_knn(posClaseOI);
        else
            Xi = XTest(forma, espacio_ccas_circulos_triangulo);
            x1 = Xi(1);
            x2 = Xi(2);
            x3 = Xi(3);

            d12_circulo_triangulo_eval = eval(d12_circulo_triangulo);
            if x1 > 0.6
                titulo = "Pertenece a la clase: circulo";
            elseif x1 < 0.6
                titulo = "Pertenece a la clase: triangulo";
            else
                titulo = "Pertenece a la clase: ninguna";
            end
        end

        funcion_visualiza(I, IEtiq == forma, [255, 0, 0], true); title(titulo), hold on;
    end
    close all
end