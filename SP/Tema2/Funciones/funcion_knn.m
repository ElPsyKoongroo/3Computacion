function [YTest] = funcion_knn(XTest, XTrain, YTrain, k)

    NTest = size(XTest,1);
    YTest = zeros(NTest,1);
    
    NTrain = size(XTrain, 1);
    NP = XTrain';

    for i=1:NTest
        P = XTest(i, :)';
        
        P_amp = repmat(P, 1, NTrain);
        vectorDistancia = sqrt(sum((P_amp - NP).^2));
    
        [~, idxs] = sort(vectorDistancia);
        % Seleccionamos las clases de los KVecinos mas cercanos
        ClasesKVecinos = YTrain(idxs(1:k));
        YTest(i) = mode(ClasesKVecinos);
    end
%{
    [num_muestras_test, num_descriptores] = size(XTest);
    [num_muestras_train, num_descriptores_train] = size(XTrain);

    copia_XTRAIN = repmat(XTrain, num_muestras_test, 1);
    YTest = zeros(num_descriptores_train, 1);
    for i = 1:num_muestras_test

        % obtener el rango que hay que mirar en cada iteracion

        % calcular las distancias
        distancias_act = (XTrain - XTest(i, :)) .^2;
        distancias_elemento_i = sqrt(sum(distancias_act'));

        % Calcular los mas cercanos
        [~, idxs] = sort(distancias_elemento_i(:));

        % Obtener la clase de los k mas cercanos
        k_vecinos = YTrain(idxs(1:k));

        % Con mode te saca el que mas instancias tenga
        YTest(i) = mode(k_vecinos(:));
    end
%}
end

