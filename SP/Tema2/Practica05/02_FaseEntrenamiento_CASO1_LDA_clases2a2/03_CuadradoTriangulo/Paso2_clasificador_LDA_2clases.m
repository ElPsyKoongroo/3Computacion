clear, clc;

addpath("../../../Funciones/");
load("DatosGenerados/espacio_ccas_cuadrados_triangulos.mat")

[d1, d2, d12, coeficientes] = funcion_calcula_hiperplanoLDA_2Clases_2_3_dim(XoI, YoI);

dim = size(XoI, 2);

%% Representacion

funcion_representa_datos(XoI, YoI, 1:dim, nombres_problema_oi);
hold on;
funcion_representa_hiperplano_separacion_2_3_Dim(XoI,coeficientes), 
legend([nombres_problema_oi.clases "d12 LDA"]);


save("DatosGenerados/coeficientes_LDA.mat", "d12", "coeficientes");

rmpath("../../../Funciones/");