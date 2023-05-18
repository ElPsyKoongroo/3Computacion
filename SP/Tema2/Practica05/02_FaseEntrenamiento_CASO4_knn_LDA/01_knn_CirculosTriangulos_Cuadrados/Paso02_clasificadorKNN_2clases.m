clear, clc;
addpath('../../Funciones/');

%% CARGAMOS LOS  DATOS DE INTERES

load('DatosGenerados/conjunto_datos.mat');
load("../../01_GeneracionDatos/DatosGenerados/conjunto_datos_estandarizados.mat");
load('../../02_FaseEntrenamiento_CASO3_knn_3clases/DatosGenerados/DatosTestNormalizados.mat');

X_test = ZTest;
clear ZTest;

X_train = Z(:, espacio_ccas);
clear Z;

X_test = X_test(:, espacio_ccas);
%% CLASIFICADOR KNN CIRCULOS-TRIANGULOS VS CUADRADOS
k = 5;

YTest = funcion_knn(X_test, X_train, Y_train, k);

%% Guardamos datos
save('DatosGenerados/knn_YTest.mat', 'YTest', 'k');