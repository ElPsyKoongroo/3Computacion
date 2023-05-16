function [YTest] = funcion_knn(XTest, XTrain, YTrain, k)

    [num_muestras_test, num_descriptores] = size(XTest);
    [num_muestras_train, num_descriptores_train] = size(XTrain);

    copia_XTRAIN = repmat(XTrain, num_muestras_test, 1);
    YTest = zeros(num_descriptores_train, 1);
    for i = 1:num_muestras_test
        rango = ((i-1)*num_muestras_train)+1:i*num_muestras_train;
        distancias_act = (copia_XTRAIN(rango, :) - XTest(i, :)) .^2;
        distancias_elemento_i = sum(distancias_act');

        % Calcular los mas cercanos
        [~, idxs] = sort(distancias_elemento_i(:));

        k_vecinos = zeros(1, k);
        k_vecinos(:) = YTrain(idxs(1:k));

        % Con mode te saca el que mas instancias tenga
        YTest(i) = mode(k_vecinos(:));
    end
end

