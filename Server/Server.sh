#!/bin/bash
clear
echo "Deleting Old Class Files..."
rm *.class
echo "Creating JavaDocs..."
javadoc -d javadocs/ *.java
echo "Compiling..."
javac Server.java
echo "Creating Jar File..."
jar -cmvf ServerManifest.txt Server.jar *.class
DEBUGFILE=serverDebug.txt
echo "Completed, Executing Jar File!"
echo "Debug output will be written to " $DEBUGFILE
java -jar Server.jar > $DEBUGFILE &
echo "To end server, Kill Process ID:" $!
echo "Cleaning up Class Files"
rm *.class
