function centroids = funcion_calcula_centroides(X, idx)
    K = size(unique(idx), 1);
    centroids = zeros(K, 3);
    for n = 1:K
        group = X(idx == n, :);
        centroids(n, :) = mean(group);
    end
end

