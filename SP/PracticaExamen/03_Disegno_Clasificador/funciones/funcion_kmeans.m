function [idx, centroids] = funcion_kmeans(X, K)
    N = 5;
    % No tengo randsample porque no puedo instalar el paquete
    % de statistics. 
    samples = randi(size(X,1), K,1);
    centroides = X(samples, :);
    
    idx = funcion_calcula_grupos(X, centroides);
    centroids = funcion_calcula_centroides(X, idx);

    cambiado = true;

    while cambiado
        new_centroids = funcion_calcula_centroides(X, idx);
        new_idx = funcion_calcula_grupos(X, new_centroids);
        
        if ~isequal(idx, new_idx)
            idx = new_idx;
            % centroids = new_centroids;
        else 
            cambiado = false;
        end
    end

    centroids = new_centroids;
end

