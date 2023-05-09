function posOutliers = funcion_detecta_outliers(X,Y,posClaseInteres)
    clases = unique(Y);
    CoI = clases(posClaseInteres);
    
    X_i = X(Y==CoI, :);
    
    
    
    % Columas de la 2 a la 4 porque la primera columna indica
    % la imagen de la que se extrageron los datos. Asi ignoramos
    % la primera columna y nos quedamos solo con los datos RGB
    %    \__________
    %            VVV
    X_i = X_i(:, 2:4);
    p_bajo = prctile(X_i, 25, 1); 
    p_alto = prctile(X_i, 75, 1); 
    inter = p_alto-p_bajo;
    
    
    rango_max = [p_bajo-(inter*1.5); p_alto+1.5*(inter)];
    [rows, ~] = size(X_i);
    posOutliers = false(rows,1);
    
    for i = 1:rows 
        actual_color = X_i(i, :);
        out_of_range = sum(actual_color < rango_max(1, :) | actual_color > rango_max(2, :));
        if out_of_range 
            posOutliers(i) = 1;
        end
    end
end

