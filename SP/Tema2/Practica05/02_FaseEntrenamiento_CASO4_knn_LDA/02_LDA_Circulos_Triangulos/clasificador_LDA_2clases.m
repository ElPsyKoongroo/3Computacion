clc, clear;
addpath('../../Funciones/');

%% CARGAMOS LOS DATOS DE INTERES

load('DatosGenerados/espacio_ccas_circ_trian.mat');
load('../../02_FaseEntrenamiento_CASO3_knn_3clases/DatosGenerados/DatosTestNormalizados.mat');
load('../01_knn_CirculosTriangulos_Cuadrados/DatosGenerados/knn_YTest.mat');

XTest = ZTest;
clear ZTest;
circ_trian = 69;
XTestOI = XTest(YTest == circ_trian, espacioCcas);
%% Valores clasificador LDA

[vector_medias, matriz_cov, prob_priori] = funcion_ajusta_LDA(XoI, YoI);

%% Clasificador LDA

[YLDA, d] = funcion_aplica_LDA(XTestOI, vector_medias, matriz_cov,...
    prob_priori, unique(datosProblemaOI.codificacion));

YTest(YTest == circ_trian, :) = YLDA(:);