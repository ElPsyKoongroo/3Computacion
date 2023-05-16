function [espacio_ccas, j_espacio_ccas] = funcion_selecciona_vector_ccas(XoI,YoI, dim)
    
    [num_muestras, num_descriptores] = size(XoI);
    combinaciones = nchoosek(1:num_descriptores, dim);

    j_individual = [];

    for i = 1:length(combinaciones)
        X = XoI(:, combinaciones(i,:));
        j_individual(i) = indiceJ(X, YoI);
    end


    [valores_j_ordenados, indices_j] = sort(j_individual, 'descend');

    espacio_ccas = combinaciones(indices_j(1), :);
    j_espacio_ccas = valores_j_ordenados(1);
end

%{

function [espacioCcas, valorJ] = funcion_selecciona_vector_ccas(X, Y, dim)
%FUNCION_SELECCIONA_VECTOR_CCAS donde X y Y, son los datos del problema
% específico a tratar y dim es la dimensión del espacio de características
% con el que se quiere trabajar


[~, numDescriptores] = size(X);

combinaciones = nchoosek(1:numDescriptores, 3);
nCombis = length(combinaciones);

valoresJ = zeros(nCombis, 1);

for i = 1:nCombis
    XoI = X(:, combinaciones(i, :));
    valoresJ(i) = indiceJ(XoI, Y);
end

[valoresJOrd, idxs] = sort(valoresJ, 'descend');

espacioCcas = combinaciones(idxs(1), :);
valorJ = valoresJOrd(1);
end

%}