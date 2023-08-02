#!/usr/bin/env bash
chmod u+x run.sh

mvn clean install

arg1=$1
dir=/Users/akshanshohm/.m2/repository/org/example/ParkingLot//1.0-SNAPSHOT/ParkingLot-1.0-SNAPSHOT.jar

if [ -z "$1" ] ; then
        java -jar $dir
        exit 1

else
	java -jar $dir $arg1

fi