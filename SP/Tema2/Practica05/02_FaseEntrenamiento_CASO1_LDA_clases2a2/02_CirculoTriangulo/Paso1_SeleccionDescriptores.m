clear all, clc;

load("../../01_GeneracionDatos/DatosGenerados/conjunto_datos_estandarizados.mat");
load("../../01_GeneracionDatos/DatosGenerados/nombres_problema.mat");
addpath("../../../Funciones/");

X = Z;
clear Z;


%% Circulos vs Triangulo
codif_clases = unique(Y);
clases_oi = [1 3];
codif_clases_oi = codif_clases(clases_oi);

filas_oi = false(size(Y));

for i=1:length(codif_clases_oi)
    filas_oi = or(filas_oi, Y==codif_clases_oi(i));
end
YoI = Y(filas_oi);
XoI = X(filas_oi, :);
dim = 3;

[espacio_ccas_j, j_valor] = funcion_selecciona_vector_ccas(XoI, YoI, dim);
nombres_problema_oi.clases = nombresProblema.clases(clases_oi);
nombres_problema_oi.descriptores = nombresProblema.descriptores(espacio_ccas_j);
nombres_problema_oi.simbolos = nombresProblema.simbolos(clases_oi);

XoI = XoI(:, espacio_ccas_j);
funcion_representa_datos(XoI, YoI, 1:dim, nombres_problema_oi);


save("DatosGenerados/espacio_ccas_circulos_triangulos.mat", "espacio_ccas_j", "j_valor", "XoI", "YoI", "nombres_problema_oi");
rmpath("../../../Funciones/")

