function [umbral] = funcion_otsu(histograma)
    
    C=0;
    N=0;
    for i=1:256
    
        C = C + (i * histograma(i));
    
        N = N + histograma(i);
    end
    
    T = round(C/N);
    u = T;
    
    Vari = zeros(256,1);
    
    for i=2:255
    
        % agrupacion 1
        N1=0;
        u1=0;
        for j=1:i
            u1 = u1 +(j * histograma(j));
            N1 = N1 + histograma(j);
        end
    
        u1 = (u1/N1);
        w1 = (N1/N);
    
        % agrupacion 2
        N2=0;
        u2=0;
    
        for j=i+1:256
            u2 = u2 +(j * histograma(j));
            N2 = N2 +histograma(j);
        end
    
        u2 = (u2/N2);
        w2 = (N2/N);
    
        Vari(i)=w1*(u1-u)^2 + w2*(u2-u)^2;
    
    end
    
    [valor, umbral] = max(Vari);
    
    umbral = umbral-1;
    %uc1=graythistogramareshistograma(I)*255
end

