function representa_esfera(centro, radio, color)
    [R,G,B] = sphere(100);
    x = radio*R(:)+centro(1); 
    y = radio*G(:)+centro(2); 
    z = radio*B(:)+centro(3);
    plot3(x,y,z,'Color', color);
end

