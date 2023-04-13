%% 
% imaqmex('feature','-limitPhysicalMemoryUsage',false);
if exist("video")
    stop(video)
end

clc, clear, close all;
addpath("funciones/");
imaqhwinfo('linuxvideo');
video = videoinput('linuxvideo',1,'RGB24_320x240');
video.TriggerRepeat = inf;
video.FrameGrabInterval = 10;
start(video);

video_obj = VideoWriter("video_salida.avi");
video_obj.FrameRate = 30 / video.FrameGrabInterval;


N_FRAMES = 100;
factor = 0.5;
frames = zeros(240*factor, 320*factor, 3, N_FRAMES);
figure(), hold on;
i = 1;

I_old = getdata(video,1);
I_old = uint8 (imresize(I_old, factor));
while (i < N_FRAMES)
    I = getdata(video,1);
    I = uint8 (imresize(I, factor));

    I_deteccion = calcula_deteccion_multiples_esferas(I, I_old);
    frames(:,:,:,i) = I_deteccion; 
    imshow(I_deteccion), hold on;
    I_old = I;
    i = i+1;
end
stop(video);


open(video_obj);
writeVideo(video_obj, uint8(frames));
close(video_obj);


rmpath("funciones/");
close all;
