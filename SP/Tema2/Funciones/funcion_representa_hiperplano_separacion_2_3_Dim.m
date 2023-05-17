function funcion_representa_hiperplano_separacion_2_3_Dim(XoI, coeficientes)

    A = coeficientes(1);
    B = coeficientes(2);
    C = coeficientes(3);

    if length(coeficientes) == 4
        D = coeficientes(4);
        x1max = max(max(XoI));
        x1min = min(min(XoI));
        x2max = max(max(XoI));
        x2min = min(min(XoI));
        paso1 = 0.1;
        paso2 = 0.1;
        [x1Plano, x2Plano] = meshgrid(x1min:paso1:x1max,x2min:paso2:x2max);
        x3Plano = -(A*x1Plano + B*x2Plano + D)/ (C+eps);
        surf(x1Plano,x2Plano, x3Plano); 
    end
    
    
end

