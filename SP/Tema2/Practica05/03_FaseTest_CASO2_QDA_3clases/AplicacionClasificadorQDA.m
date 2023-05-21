clc, clear;

addpath('../Funciones');
load('../02_FaseEntrenamiento_CASO3_knn_3clases/DatosGenerados/DatosTestNormalizados.mat')

%% CARGAMOS LOS VALORES DE ENTRENAMIENTO PARA CALCULAR QDA
load('../02_FaseEntrenamiento_CASO2_QDA_3clases/DatosGenerados/espacio_ccas_circulos_cuadrados_triangulos.mat');
load('../02_FaseEntrenamiento_CASO2_QDA_3clases/DatosGenerados/datos_QDA.mat');

XTest = ZTest;

XTest = XTest(:, espacio_ccas_j);
codifClases = unique(nombres_problema_oi.codif);
[YQDA, d] = funcion_aplica_QDA(XTest, medias, m_covarianza,...
    prob_priori, codifClases);

%%
colores = [ 0 255 0; 255 0 0; 0 0 255]; 
num_objetos = length(YQDA);
num_imagenes = 1;
for imagen = 1:num_imagenes
    nombre_imagen = "Test" + num2str(imagen) + ".JPG";
    I = imread(nombre_imagen);

    Ib = I < 255*graythresh(I);
    [IEtiq, N] = bwlabel(Ib);
    
    figure, hold on;
    for i = 1:num_objetos
        YiOI = YQDA(i);
        posClaseOI = find(ismember(codifClases, YiOI));
    
        claseOI = nombres_problema_oi.clases{posClaseOI};
        reconocimiento = ['Reconocimeinto objeto: ' claseOI];
    
        Ib = IEtiq == i;
        color = colores(posClaseOI, :);
        funcion_visualiza(I, Ib, color, true);
        title(reconocimiento), hold on;
        pause;
    end
    close all;
end