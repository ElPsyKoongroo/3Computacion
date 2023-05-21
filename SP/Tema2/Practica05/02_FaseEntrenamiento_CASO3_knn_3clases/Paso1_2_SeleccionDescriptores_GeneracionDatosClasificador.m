clear, clc;
addpath("../Funciones/");
load("../01_GeneracionDatos/DatosGenerados/conjunto_datos_estandarizados.mat");
load("../01_GeneracionDatos/DatosGenerados/datos_estandarizacion.mat");
load("DatosGenerados/DatosTestNormalizados.mat")
load("../01_GeneracionDatos/DatosGenerados/nombres_problema.mat");
XTrain = Z;
XTest = ZTest;
YTrain = Y;
clear Z;
clear ZTest;

addpath("../../Funciones/");
addpath("../ImagenesPractica5/Test/");

numImagenes = 2;
vecinos_mas_cercanos = 5;

[espacio_ccas, j_valor] = funcion_selecciona_vector_ccas(XTrain, YTrain, 5);

XTrain_oi = XTrain(:, espacio_ccas);
XTest_oi = XTest(:, espacio_ccas);

YTest = funcion_knn(XTest_oi, XTrain_oi, YTrain, vecinos_mas_cercanos);
save("DatosGenerados/DatosKNN");
% load("DatosGenerados\DatosTest.mat")
% XTest = [];
% 
% for imagen = 1:numImagenes
%     nombre_imagen = "Test"+num2str(imagen,'%2d')+'.JPG';
%     I = imread(nombre_imagen);
% 
%     Ib = I < 255*graythresh(I);
%     % Ib_filtrada = funcion_elimina_regiones_ruidosas(Ib);
%     Ib_filtrada = Ib;
%     [IEtiq, N] = bwlabel(Ib_filtrada);
%     
%     XImagen = funcion_calcula_descriptores_objetos(IEtiq, N);
% 
%     XTest = [XTest; XImagen];
% end

%save("DatosGenerados/DatosTest.mat", "XTest");


%% Estandarizacion de XTest

% [rows, num_descriptores] = size(XTest);
% 
% ZTest = zeros(size(XTest));
% for i = 1:(num_descriptores-1)
%     mu_i = medias(i);
%     sigma_i = desviaciones(i);
%     ZTest(:, i) = (XTest(:, i) - mu_i) / sigma_i;
% end
% ZTest(:, num_descriptores) = XTest(:, num_descriptores);
% save("DatosGenerados/DatosTestNormalizados.mat", "ZTest");
