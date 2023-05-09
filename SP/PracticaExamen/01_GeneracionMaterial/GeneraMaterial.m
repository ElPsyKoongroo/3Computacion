%% Guardar fotitos

stop(video);
clc, clear;
video = videoinput('linuxvideo',1,'RGB24_320x240');
video.TriggerRepeat = inf;

% preview(video)
start(video)
preview(video)
pause(2);
for i = 1:15
    I = getsnapshot(video);
    imwrite(I, ("ejemplos"+i+".jpg"));
    pause(2);
end
stop(video)
close all;
closepreview(video);

%% Guardar video
stop(video)

clc, clear;
video = videoinput('linuxvideo',1,'RGB24_320x240');
aviobj = VideoWriter("Videito.avi");
video.TriggerRepeat = inf;
video.FrameGrabInterval = 2;

set(video, 'LoggingMode', 'memory');
open(aviobj)
start(video)
preview(video);
pause(2);
while (video.FramesAcquired < 150)
    I = getdata(video, 1);
    writeVideo(aviobj, I);
end
stop(video)
close(aviobj)
closepreview(video);

%% Visualizar fotitos
clc, clear;

figure(), hold on;
for i = 1:10
    I = imread("ejemplos"+i+".jpg");
    imshow(I);
    pause(1);
end

close all;

%% Guarda fotitos
clc, clearvars;
Fotitos = uint8 (zeros(240,320,3,15));
% figure(), hold on;
for i = 1:15
    I = imread("ejemplos"+i+".jpg");
    imshow(I);
    Fotitos(:,:,:,i) = uint8 (I);
end

save('DatosDeEjemplo', 'Fotitos');
close all;