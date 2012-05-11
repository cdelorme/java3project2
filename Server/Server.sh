#!/bin/bash
clear
echo "Deleting Old Class Files..."
rm *.class
echo "Compiling..."
javac Server.java
echo "Creating Jar File..."
jar -cmvf ServerManifest.txt Server.jar *.class
echo "Completed, Executing Jar File!"
java -jar Server.jar &