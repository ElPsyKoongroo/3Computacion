function [XImagen] = funcion_calcula_extension_en_objetos(IEtiq, N)
    
    XImagen = [];

    for etiqueta = 1:N
        Ib = IEtiq == etiqueta;
        extension = funcion_calcula_extension_1objeto(Ib);
        XImagen = [XImagen; extension];
    end

    XImagen;

end

