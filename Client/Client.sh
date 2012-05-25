#!/bin/bash
clear
echo "Deleting Old Class Files..."
rm *.class
echo "Creating JavaDocs..."
javadoc -author -version -link http://docs.oracle.com/javase/6/docs/api/ -d javadocs/ *.java
echo "Compiling..."
javac Client.java
echo "Creating Jar File..."
jar -cmvf ClientManifest.txt Client.jar *.class images/*
echo "Completed, Executing Jar File!"
java -jar Client.jar &
echo "Cleaning up Class Files"
rm *.class