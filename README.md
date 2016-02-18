# BPMService
Connector para ejecutar comandos en JBPM6 de manera remota

-- Compilación

mvn clean package

-- Ejecución

mvn exec:java

(OPCIONAL)

En caso de querer indicar otro servidor remoto que no es el default, el comando debería ser:

mvn exec:java -Dbpm.host=localhost -Dbpm.port=8080 -Dbpm.deploymentId=ar.gob.mecon:ProcesosPruebas:1.8 -Dbpm.username=bpmsAdmin -Dbpm.password=Redhat01.