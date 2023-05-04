clear,clc

%% metodo de Ridler y Calvard (ISODATA)

I = imread('A2.jpg');

h = imhist(I);

C=0;
N=0;
for i=1:256

    C = C +(i * h(i));

    N = N +h(i);
end

T = round(C/N);

T1=-1;

cambio=false;
while(cambio==false)

    % dos agrupaciones

    % agrupacion1 

    N1 =0;
    C1=0;
    for i=1:T

        C1 = C1 +(i * h(i));
    
        N1 = N1 +h(i);

    end

    m1 = round(C1/N1);

    % agrupacion2

    N2 =0;
    C2=0;
    for i=T+1:256

        C2 = C2 +(i * h(i));
    
        N2 = N2 +h(i);

    end

    m2 = round(C2/N2);


    T1 = round((m1+m2)/2);

    if(abs(T-T1) ==0)

        cambio=true;
    else
        T=T1;
    end

end

umbral = T;





























