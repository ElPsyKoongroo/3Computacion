function XImagen = funcion_calcula_DF_objetos(IEtiq, N)
    XImagen = [];
    for i = 1:N
        IEtiq_i = IEtiq == i;
        datos = Funcion_Calcula_DF(IEtiq_i, 10)';
        XImagen = [XImagen; datos];
    end
end

