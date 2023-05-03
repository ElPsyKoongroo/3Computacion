I = double(imread('A1.jpg'));

I_intensidad = uint8((I(:,:,1)+I(:,:,2)+I(:,:,3)) / 3);
[counts, ~] = imhist(imread('A1.jpg'));
a = otsuthresh(counts)*255
b = funcion_otsu(counts)