#!/bin/sh

if [ "$#" -ne 1 ]; then
    echo "You must provide exactly 1 argument"
else
    cd Sergio
    command="/usr/lib/eclipse/plugins/org.eclipse.justj.openjdk.hotspot.jre.full.linux.x86_64_17.0.5.v20221102-0933/jre/bin/java
    -Dfile.encoding=UTF-8
    -classpath /mnt/Uni/Programacion/3Computacion/SI/Sergio/bin
    -XX:+ShowCodeDetailsInExceptionMessages si2023.sergiogarcia1alu.p$1.GameRunner"
    echo "ejecutando"
    eval $command
fi
