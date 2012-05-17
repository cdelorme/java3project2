@echo off
@echo "Deleting Old Class Files..."
del *.class
@echo "Compiling..."
javac Server.java
@echo "Creating Jar File..."
jar -cmvf ServerManifest.txt Server.jar *
@echo "Completed, Executing Jar File"
java -jar Server.jar
pause