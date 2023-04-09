function [X_sin_outliers, Y_sin_outliers] = elimina_outliers(X,Y,posClaseInteres)
    posOutliers = funcion_detecta_outliers(X,Y,posClaseInteres);
    
    X_sin_outliers = X;
    Y_sin_outliers = Y;
    
    X_sin_outliers(posOutliers, :) = [];
    Y_sin_outliers(posOutliers, :) = [];
end

