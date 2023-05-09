function extension = funcion_calcula_extension_1objeto(Ib)
    %Ib = IEtiq == 1;
    Ib_centrada = Funcion_Centra_Objeto(Ib);
    
    regionprops(Ib_centrada, 'Extent');

    

    ang = 0:5:355;
    extensiones = zeros(length(ang), 1);
    for i = 1:length(ang)
        Ib_rot = imrotate(Ib_centrada, ang(i));

        [f,c] = find(Ib_rot);
        
        fmax = max(f)+0.5; fmin = min(f)-0.5;
        cmax = max(c)+0.5; cmin = min(c)-0.5;
        
        numPix   = length(f);
        numPixBB = (fmax-fmin)*(cmax-cmin);
        
        E = numPix/numPixBB;

        extensiones(i) = E;
    end

    extension = max(extensiones);

end

