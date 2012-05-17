#!/bin/bash
clear
echo "Deleting Old Class Files..."
rm *.class
echo "Creating JavaDocs..."
javadoc -d javadocs/ *.java
echo "Compiling..."
javac Client.java
echo "Creating Jar File..."
jar -cmvf ClientManifest.txt Client.jar * images/* javadocs/*
echo "Completed, Executing Jar File!"
java -jar Client.jar &