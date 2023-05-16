function [YTest] = funcion_knn(XTest, XTrain, YTrain, k)

    [num_muestras, num_descriptores] = size(XTest_oi);
    
    [num_muestras_train, num_descriptores_train] = size(XTrain_oi);
    distancias = repmat(XTrain_oi, num_muestras, 1);
    

    for i = 1:num_muestras_train
        rango = ((i-1)*num_muestras_train)+1:i*num_muestras_train;
        distancias(rango, :) - XTest_oi(i, :);
    end
end

