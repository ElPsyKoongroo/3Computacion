function X = funcion_calcula_descriptores_objetos(IEtiq, N)
    X = zeros(N, 23);
    
    
    COMPACTICIDAD      = 1;
    EXCENTRICIDAD      = 2;
    SOLIDEZ            = 3;
    EXTENSION_BB       = 4;
    EXTENSION_BB_INV   = 5;
    HU                 = 6:12;
    DF                 = 13:22;
    EULER              = 23;
    
    
    momentos_hu = funcion_calcula_Hu_objetos_imagen(IEtiq, N);
    ext_inv     = funcion_calcula_extension_en_objetos(IEtiq, N);
    desc_four   = funcion_calcula_DF_objetos(IEtiq, N);

    X(:, HU) = momentos_hu;
    X(:, EXTENSION_BB_INV) = ext_inv;
    X(:, DF) = desc_four;

    for etiqueta = 1:N
        Ib = IEtiq == etiqueta;
        

        propiedades = regionprops(Ib, 'Solidity', 'Extent', 'EulerNumber', 'Eccentricity', 'Perimeter', 'Area');
        X(etiqueta, EULER)          = propiedades.EulerNumber;
        X(etiqueta, EXTENSION_BB)   = propiedades.Extent;
        X(etiqueta, SOLIDEZ)        = propiedades.Solidity;
        X(etiqueta, EXCENTRICIDAD)  = propiedades.Eccentricity;
        X(etiqueta, COMPACTICIDAD) =  propiedades.Perimeter.^2 / propiedades.Area;
    end
end

