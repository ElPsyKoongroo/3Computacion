function [espacio_ccas, j_espacio_ccas] = funcion_selecciona_vector_ccas(X,Y, dim)
    
    [num_muestras, num_descriptores] = size(X);
    combinaciones = nchoosek(1:num_descriptores, dim);

    valores_j = [];

    for i = 1:length(combinaciones)
        XoI = X(:, combinaciones(i,:));
        valores_j(i) = indiceJ(XoI, Y);
    end


    [valores_j_ordenados, indices_j] = sort(valores_j, 'descend');

    espacio_ccas = combinaciones(indices_j(1), :);
    j_espacio_ccas = valores_j_ordenados(1);
end