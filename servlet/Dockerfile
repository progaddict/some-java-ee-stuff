FROM tomcat:9.0.10-jre8-alpine

COPY tomcat-conf/server.xml $CATALINA_HOME/conf/server.xml
COPY tomcat-conf/cert/cert.pem $CATALINA_HOME/conf
COPY tomcat-conf/cert/key-without-password.pem $CATALINA_HOME/conf

COPY target/servlet-1.0-SNAPSHOT.war $CATALINA_HOME/webapps
