function [Ximagen] = funcion_calcula_Hu_objetos_imagen(IEtiq, N)

Ximagen = [];
for i = 1:N
    IEtiq_i = IEtiq == i;
    datos = Funcion_Calcula_Hu(IEtiq_i)';
    Ximagen = [Ximagen; datos];
end

end
