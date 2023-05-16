function [vector_medias, matriz_cov, probabilidad_priori] = funcion_ajusta_LDA(X, Y)
    [num_muestras, num_descriptores] = size(X);
    codif_clases= unique(Y);
    num_clases = length(codif_clases);
    
    num_muestras_clase_k = zeros(num_clases, 1);
    vector_medias= zeros(num_clases, num_descriptores);
    matriz_cov = cov(X);
    probabilidad_priori = zeros(num_clases, 1);
    
    for i = 1:num_clases

        bin = Y == codif_clases(i);
        num_muestras_clase_k(i) = sum(bin);
    
        vector_medias(i, :) = sum(X(bin, :))/num_muestras_clase_k(i);
    

        probabilidad_priori(i) = num_muestras_clase_k(i) / num_muestras;
    end

    u = zeros(num_descriptores,num_descriptores);
    for i = 1:num_clases
        u = (num_muestras_clase_k(i)-1)*cov(X(Y==codif_clases(i), :));
    end
    d = sum(num_muestras_clase_k)-num_clases;

    matriz_cov = u/d;
    
end