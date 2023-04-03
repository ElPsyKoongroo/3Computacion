function idx = funcion_calcula_grupos(X, centroides)
    [rows, cols] = size(X);
    idx = zeros(rows, 1);
    n_centroides = size(centroides, 1);
    
    for i = 1:rows 
        distancias = [];
        for c = 1:n_centroides 
            distancias(c) = norm(X(i,:) - centroides(c, :));
        end
        [~, id] = min(distancias);
        idx(i) = id;
    end
end
