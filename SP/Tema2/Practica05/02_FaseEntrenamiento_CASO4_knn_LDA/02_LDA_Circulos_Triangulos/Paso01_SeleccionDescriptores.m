%% CIRCULOS vs TRIANGULOS

clc, clear;
% Carga de datos

load('../../01_GeneracionDatos/DatosGenerados/nombres_problema.mat');
load('../../01_GeneracionDatos/DatosGenerados/conjunto_datos_estandarizados.mat');
addpath('../../Funciones/');
%addpath('../../01_GeneracionDatos/Funciones/');

%% GENERACION XOI

X = Z;
clear Z;

[numDatos, numDescriptores] = size(X);
codifClases = unique(Y);

clasesOI = [1, 3];
codifClasesOI = codifClases(clasesOI);

filasOI = false(numDatos, 1);
for i = 1:length(clasesOI)
    filasOI = or(filasOI, Y == codifClasesOI(i));
end

XoI = X(filasOI, 1:numDescriptores-1);
YoI = Y(filasOI);

% 3 DESCRIPTORES
dim = 3;
[espacioCcas, JespacioCcas] = funcion_selecciona_vector_ccas(XoI, YoI, dim);

XoI = XoI(:, espacioCcas);

%% Definicion datosProblema
datosProblemaOI.descriptores = nombresProblema.descriptores(espacioCcas);
datosProblemaOI.clases = nombresProblema.clases(clasesOI);
datosProblemaOI.simbolos = nombresProblema.simbolos(clasesOI);
datosProblemaOI.codificacion = nombresProblema.codif_clases(clasesOI);

%% Guardamos los datos

save('DatosGenerados/espacio_ccas_circ_trian.mat', 'espacioCcas', 'JespacioCcas', 'XoI', 'YoI', 'dim', 'datosProblemaOI');