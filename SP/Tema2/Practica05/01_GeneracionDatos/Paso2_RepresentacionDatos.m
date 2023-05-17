clc, clear all;


load("DatosGenerados/conjunto_datos.mat");
load("DatosGenerados/nombres_problema.mat");


espacio_ccas_size = 2;

codifClases = nombresProblema.codif_clases;
numClases = length(nombresProblema.clases);
nombreDescriptores = nombresProblema.descriptores;
nombreClases = nombresProblema.clases;
numMuestras = length(X);

% El de Euler no se cuenta
% num_descriptores = 1:(size(X, 2)-1);
num_descriptores = 1:22;
% MATLAB dice que la otra funcion es mala
combinaciones = nchoosek(num_descriptores, espacio_ccas_size);

for combinacion = 1:length(combinaciones)
    combinacion_actual = combinaciones(combinacion, :);

    funcion_representa_datos(X, Y, combinacion_actual, nombresProblema);
end

%%
for j=1:23

    % Valores máximo y mínimos para representar en la misma escala
    vMin = min(X(:,j));
    vMax = max(X(:,j));
    
    hFigure = figure; hold on
    bpFigure = figure; hold on
    
    for i=1:3
    
    Xij = X(Y==codifClases(i),j); % datos clase i del descriptor j
    
    figure(hFigure)
    subplot(numClases,1,i), histogram(Xij),
    xlabel(nombreDescriptores{j})
    ylabel('Histograma')
    axis([ vMin vMax 0 numMuestras/4]) % inf escala automática eje y
    title(nombreClases{i})
    
    figure(bpFigure)
    
    subplot(1,numClases,i), boxplot(Xij)
    xlabel('Diagrama de Caja')
    ylabel(nombreDescriptores{j})
    axis([ 0 2 vMin vMax ])
    title(nombreClases{i})
    end
end

close all
