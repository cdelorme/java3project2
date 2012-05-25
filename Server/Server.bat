@echo off
@echo "Deleting Old Class Files..."
del *.class
@echo "Creating JavaDocs..."
javadoc -d javadocs/ *.java
@echo "Compiling..."
javac Server.java
@echo "Creating Jar File..."
jar -cmvf ServerManifest.txt Server.jar *.class
@echo "Completed, Executing Jar File"
java -jar Server.jar
@echo "Cleaning up Class Files"
del *.class
pause