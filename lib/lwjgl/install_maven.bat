set rep=C:\Users\Alain\Documents\NetBeansProjects\J3dXml\lib\lwjgl
rem set rep=.
mvn install:install-file -Dfile=%REP%\lwjgl.jar -DgroupId=org.lwjgl -DartifactId=lwjgl -Dversion=2.0 -Dpackaging=jar
mvn install:install-file -Dfile=%REP%\lwjgl_util.jar -DgroupId=org.lwjgl -DartifactId=lwjgl_util -Dversion=2.0 -Dpackaging=jar
                          