function [vector_medias, matrices_covarianzas, probabilidad_priori] = funcion_ajusta_QDA(X, Y)

    
    %FUNCION_AJUSTA_QDA dado un conjunto de datos X-Y, la función calcula los
    % vectores de medias, matrices de covarizanzas y probabilidades a priori
    % para cada una de las clases del problema (dadas por los valores 
    % distintos de Y).
    
    [num_muestras, num_descripores] = size(X);
    codif_clases                    = unique(Y, "rows", "stable");
    num_clases                      = length(codif_clases);
    
    %num_muestras_clase_k = zeros(num_clases, 1);
    vector_medias = zeros(num_clases, num_descripores);
    matrices_covarianzas= zeros(num_descripores, num_descripores, num_clases);
    probabilidad_priori = zeros(num_clases, 1);
    
    for i = 1:num_clases
        % Calculo nk
        bin = Y == codif_clases(i);
        num_muestras_clase_k = sum(bin);
    
        % Calcula vectorMedias
        vector_medias(i, :) = sum(X(bin, :))./num_muestras_clase_k;
    
        % Calcula matrices covarianzas
        matrices_covarianzas(:, :, i) = cov(X(bin, :));
    
        % Calculo probabilidadPrior
        probabilidad_priori(i) = num_muestras_clase_k / num_muestras;
    end



end

