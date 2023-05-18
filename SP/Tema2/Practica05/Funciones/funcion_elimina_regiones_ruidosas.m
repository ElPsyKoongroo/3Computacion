function [Ib_corregida]= funcion_elimina_regiones_ruidosas(Ib)

    [IEtiq, N] = bwlabel(Ib);
    stats = regionprops(IEtiq, "Area");
    areas = cat(1, stats.Area);
    
    [rows, cols, ~] = size(Ib);
    filtro_por_pixel = round((rows*cols)* (0.1/100));
    filtro_por_area5 = round(max(areas)/5);

    mejor_filtro = 0;

    if filtro_por_pixel > filtro_por_area5 
        mejor_filtro = filtro_por_pixel;
    else 
        mejor_filtro = filtro_por_area5;
    end

    
    for etiqueta = 1:N
        if areas(etiqueta) < mejor_filtro 
            IEtiq(IEtiq == etiqueta) = 0;
        end
    end
    Ib_corregida = logical(IEtiq);
end
