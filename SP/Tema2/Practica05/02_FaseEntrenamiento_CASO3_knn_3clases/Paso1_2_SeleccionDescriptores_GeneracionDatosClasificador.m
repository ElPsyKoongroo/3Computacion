clear, clc;
addpath("../../Funciones/");
load("../01_GeneracionDatos/DatosGenerados/conjunto_datos_estandarizados.mat");
load("DatosGenerados/DatosTestNormalizados.mat")
XTrain = Z;
XTest = ZTest;
YTrain = Y;
clear Z;
clear ZTest;

addpath("../../Funciones/");
addpath("../ImagenesPractica5/Test/");

numImagenes = 2;

[espacio_ccas, j_valor] = funcion_selecciona_vector_ccas(XTrain, YTrain, 5);

XTrain_oi = XTrain(:, espacio_ccas);
XTest_oi = XTest(:, espacio_ccas);

% XTest = [];
% 
% for imagen = 1:numImagenes
%     nombre_imagen = "Test"+num2str(imagen,'%2d')+'.JPG';
%     I = imread(nombre_imagen);
% 
%     Ib = I < 255*graythresh(I);
%     Ib_filtrada = funcion_elimina_regiones_ruidosas(Ib);
%     [IEtiq, N] = bwlabel(Ib_filtrada);
%     
%     XImagen = funcion_calcula_descriptores_objetos(IEtiq, N);
% 
%     XTest = [XTest; XImagen];
% end

%save("DatosGenerados/DatosTest.mat", "XTest");

% medias = mean(XTest);
% desviaciones = std(XTest);
% 
% %Cambiar Euler
% desviaciones(23) = eps;
% 
% [rows, num_descriptores] = size(XTest);
% 
% %% Estandarizacion de X
% 
% ZTest = zeros(size(XTest));
% for i = 1:(num_descriptores-1)
%     mu_i = medias(i);
%     sigma_i = desviaciones(i);
%     ZTest(:, i) = (XTest(:, i) - mu_i) / sigma_i;
% end
% ZTest(:, num_descriptores) = XTest(:, num_descriptores);
% save("DatosGenerados/DatosTestNormalizados.mat", "ZTest");
