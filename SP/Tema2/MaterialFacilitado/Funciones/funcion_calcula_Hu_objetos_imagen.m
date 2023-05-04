function [Ximagen] = funcion_calcula_Hu_objetos_imagen(IEtiq, N)
%FUNCION_CALCULA_HU_OBJETOS_IMAGEN Summary of this function goes here
%   Detailed explanation goes here

Ximagen = []
for i = 1:N
    IEtiq_i = IEtiq == i;
    datos = Funcion_Calcula_Hu(IEtiq_i)';
    Ximagen = [Ximagen; datos];
end

end

