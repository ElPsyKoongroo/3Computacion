function [] = funcion_representa_datos(X,Y, espacioCcas, nombresProblema)

% Funcion para mostrar datos:

figure(), hold on
codifClases = unique(Y);
numClases = length(codifClases);
numCcas = length(espacioCcas);
for i = 1:numClases 
    Xi = X(Y==codifClases(i), espacioCcas);
    if numCcas == 2
        plot(Xi(:,1), Xi(:,2), nombresProblema.simbolos{i}), hold on;
    else
        plot3(Xi(:,1), Xi(:,2), Xi(:,3), nombresProblema.simbolos{i}), hold on;
    end
end

legend(nombresProblema.clases)
xlabel(nombresProblema.descriptores{espacioCcas(1)})
ylabel(nombresProblema.descriptores{espacioCcas(2)})

if numCcas == 3
    zlabel(nombresProblema.descriptores{espacioCcas(3)})
end

hold off
end
