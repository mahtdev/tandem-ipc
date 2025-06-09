#!/bin/bash

PROCESS_NUMBER=1
PROCESS_NAME=KSKA

export CLASSPATH=:/usr/tandem/javaexth11/lib/tdmext.jar
export _RLD_LIB_PATH=/usr/tandem/javaexth11/lib
export JAVA_HOME=/usr/tandem/nssjava/jdk180_180
export PATH=$PATH:${JAVA_HOME}/bin

add_define=TCP^PROCESS^NAME class=map File=\$ZTC9
run -cpu=${PROCESS_NUMBER} -name=/G/${PROCESS_NAME} java -Dks.process.name=BOX6 -jar test-atalla-1.0.0.0.jar