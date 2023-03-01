function centroides = funcion_calcula_centroides (IEtiq, N)
    centroides = zeros(N-1, 2);

    for i = 1:N-1
        [row, col] = find(IEtiq == i);
        centroides(i,1) = round(mean(col));
        centroides(i,2) = round(mean(row));
    end
    
end

