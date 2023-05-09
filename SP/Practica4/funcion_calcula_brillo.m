function B = funcion_calcula_brillo(I)
    [m,n,d] = size(I);
    if d == 2
        B = (1/(m*n))*sum(sum(I));
    else 
        B = (1/(m*n))*sum(sum(sum(I)));
    end
end

