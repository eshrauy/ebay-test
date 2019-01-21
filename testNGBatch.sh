set projectLocation=/home/Rodrigo/Proyectos/ebay-test
 
cd %projectLocation%
 
set classpath=%projectLocation%\bin;%projectLocation%\lib\*
 
java org.testng.TestNG %projectLocation%\testNG.xml
 
pause
