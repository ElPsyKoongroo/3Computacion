I = double(imread('A3.jpg'));

I_intensidad = uint8((I(:,:,1)+I(:,:,2)+I(:,:,3)) / 3);
    [counts, ~] = imhist(imread('P4.jpg'));
    MATLAB = otsuthresh(counts)*255
    MAIRENA = funcion_otsu(counts)
    WIKIPEDIA = funcion_otsu_wiki(counts)