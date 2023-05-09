function [XImagen] = funcion_calcula_extend_hu_objetos_imagen(IEtiq, N)
    stats = regionprops(IEtiq, 'extent');
    extension = cat(1, stats.Extent);
    momentos_hu = funcion_calcula_Hu_objetos_imagen(IEtiq, N);
    XImagen = [extension, momentos_hu];
end

