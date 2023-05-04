%%
I = double(imread('A3.jpg'));

I_intensidad = uint8((I(:,:,1)+I(:,:,2)+I(:,:,3)) / 3);
[counts, ~] = imhist(imread('P4.jpg'));
MATLAB = otsuthresh(counts)*255
MAIRENA = funcion_otsu(counts)
WIKIPEDIA = funcion_otsu_wiki(counts)
%%

clear, clc

I = imread("P4.jpg");

[m, n] = size(I);

% Calcular brillo
B = (1/(m*n))*sum(sum(I))


% Calcular contraste
C = funcion_calcula_contraste(I) %sqrt((1/(m*n)) * sum(sum(double(I-B).^2)))


% Cambiar brillo
DES = -30;
I_2 = I + DES;
subplot(2,1,1), hold on
imshow(I), hold on
subplot(2,1,2), hold on
imshow(I_2)

%% Cambiar contraste

p_max = double(max(I(:))); p_min = double(min(I(:)));

q_max = 105; q_min = 0;

figure(), hold on,
for c = q_min:10:p_max*2
    q_max = c;
    c
    I_3 = q_min + ((q_max-q_min)/(p_max-p_min))*(I-p_min);
    funcion_calcula_contraste(I_3)
    title(['Nivel de contraste: ' num2str(c)]), hold on
    imshow(I_3); pause;
end
hold off
%% Imfilter

HP=ones(9,9)/81 ; HL=[-1 -1 -1 ; -1 8 -1 ; -1 -1 -1];

I_p = imfilter(I, HP);
I_l = imfilter(I, HL);
imshow(I_l); pause;
imshow(I_p)

%% OTSU
clear, clc;
I = imread("P4.jpg");
h = imhist(I);


SergioOtsu  = funcion_otsu_sergio(h);
MairenaOtsu = funcion_otsu(h);

%% Kalvin Klein
clear, clc;
I = imread("A2.jpg");
h = imhist(I);

SergioKK = funcion_ridler_calvard_sergio(h, 0);
