function [YTest] = funcion_knn(XTest, XTrain, YTrain, k)

    [num_muestras_test, num_descriptores] = size(XTest_oi);
    [num_muestras_train, num_descriptores_train] = size(XTrain_oi);

    copia_XTRAIN = repmat(XTrain_oi, num_muestras_test, 1);
    
    distancias = zeros(num_muestras_test, num_muestras_train);
    for i = 1:num_muestras_test
        rango = ((i-1)*num_muestras_train)+1:i*num_muestras_train;
        distancias_act = (copia_XTRAIN(rango, :) - XTest_oi(i, :)) .^2;
        distancias(i, :) = sum(distancias_act');
    end
end

