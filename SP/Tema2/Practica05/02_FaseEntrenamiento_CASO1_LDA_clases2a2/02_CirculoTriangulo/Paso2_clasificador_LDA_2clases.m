clear, clc;

addpath("../../Funciones/");
load("DatosGenerados/espacio_ccas_circulos_triangulos.mat")

[d1, d2, d12, coeficientes] = funcion_calcula_hiperplanoLDA_2Clases_2_3_dim(XoI, YoI);

dim = size(XoI, 2);

%% Representacion

% x1 = linspace(min(XoI(1, :))-1, max(XoI(1, :))+1, size(XoI, 1));
% x2 = x1';
% A = coeficientes(1);
% B = coeficientes(2);
% C = coeficientes(3);
% D = coeficientes(4);
% 
% x3 = -(A*x1 + B*x2 + D)/C;

funcion_representa_datos(XoI, YoI, 1:dim, nombres_problema_oi);
hold on;
funcion_representa_hiperplano_separacion_2_3_Dim(XoI, coeficientes);
%surf(x1,x2,x3), legend([nombres_problema_oi.clases "d12 LDA"]);

save("DatosGenerados/coeficientes_LDA.mat", "d12", "coeficientes");
rmpath("../../../Funciones/");