@echo off
@echo "Deleting Old Class Files..."
del *.class
@echo "Creating JavaDocs..."
javadoc -d javadocs/ *.java
@echo "Compiling..."
javac Client.java
@echo "Creating Jar File..."
jar -cmvf ClientManifest.txt Client.jar * images/* javadocs/*
@echo "Completed, Executing Jar File!"
java -jar Client.jar
pause