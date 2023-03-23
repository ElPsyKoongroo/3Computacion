function visualiza_datos(X,Y, posClaseInteres)
    clases = unique(Y);
    CoI = clases(posClaseInteres);
    figure() ,hold on;
    PoI = X(Y == CoI, :);
    plot3(PoI(:, 2), PoI(:, 3), PoI(:, 4), '.b'), hold on;
    
    PoI = X(Y ~= CoI, :);
    plot3(PoI(:, 2), PoI(:, 3), PoI(:, 4), '.r'), hold on;
end

