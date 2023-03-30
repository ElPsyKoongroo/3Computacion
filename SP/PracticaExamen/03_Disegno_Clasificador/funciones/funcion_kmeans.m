function [idx, centroides] = funcion_kmeans(X, K)
N = 5;
K = 5;
% No tengo randsample porque no puedo instalar el paquete
% de statistics. 
samples = randi(size(X,1), K,1);
centroides = X(samples, :);
end

function idx = funcion_calcula_agrupacion(X, centroides)
    
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