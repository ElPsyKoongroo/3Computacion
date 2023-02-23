%{ 
    Lee la imagen del fichero "ImagenBinaria.tif". Comprueba que es 
    una imagen binaria en la que están localizados los píxeles que 
    integran los objetos que se muestran en la imagen. 
%}



clc
I = imread('ImagenBinaria.tif');
Ib = I>0;




