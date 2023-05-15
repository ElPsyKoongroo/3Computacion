function [valor_medio] = funcion_ridler_calvard_sergio(h, umbral)
    
    nPix = sum(h);
    valor_medio = round(sum((1:256) * h) / nPix);
    salir = false;
    
    while ~salir
        G1 = h(1:(valor_medio-1));
        G2 = h(valor_medio:256);
    
        media_g1 = sum((1:valor_medio-1) * G1) / sum(G1);
        media_g2 = sum((valor_medio:256) * G2) / sum(G2);
    
        nueva_media = round((1/2) * (media_g1+media_g2));
        if abs(nueva_media-valor_medio) <= umbral
            salir = true;
        else
            valor_medio = nueva_media;
        end
    end
end

