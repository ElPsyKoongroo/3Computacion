function areas = funcion_calcula_areas(IEtiq, N)
    areas = zeros(N-1, 1);
    for i = 1:N-1
        areas(i, 1) = sum(sum(IEtiq == i));
    end
end