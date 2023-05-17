function [vector_medias, matriz_cov, probabilidad_priori] = funcion_ajusta_LDA(X, Y)
    [num_muestras, num_descriptores] = size(X);
    codif_clases= unique(Y);
    num_clases = length(codif_clases);
    
    num_muestras_clase_k = zeros(num_clases, 1);
    vector_medias= zeros(num_clases, num_descriptores);
    matriz_cov = zeros(num_descriptores);
    probabilidad_priori = zeros(num_clases, 1);
    
    for i = 1:num_clases
        
        % Calcular el numero de muestras de la clase k
        bin = Y == codif_clases(i);
        num_muestras_clase_k(i) = sum(bin);
        
        %Calcular el vector de medias
        vector_medias(i, :) = sum(X(bin, :))/num_muestras_clase_k(i);
    
        % Calcular la probabilidad priori clase k
        probabilidad_priori(i) = num_muestras_clase_k(i) / num_muestras;

        % Calcular la matriz de cov media
        XoI = X(bin, :);
        matriz_cov = matriz_cov + (num_muestras_clase_k(i)-1)*cov(XoI);
    end

    %Calcular matriz covarianza media
    matriz_cov = matriz_cov/ (sum(num_muestras_clase_k)-num_clases);
    
end