clc
I = imread('ImagenBinaria.tif');
Ib = I>0;
[i_etiq, N] = Etiqueta(Ib)