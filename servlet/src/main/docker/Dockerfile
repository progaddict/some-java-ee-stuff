FROM tomcat:9.0.10-jre8-alpine

COPY tomcat-conf/server.xml $CATALINA_HOME/conf/server.xml
COPY tomcat-conf/cert/cert.pem $CATALINA_HOME/conf
COPY tomcat-conf/cert/key-without-password.pem $CATALINA_HOME/conf

RUN rm -rf $CATALINA_HOME/webapps/ROOT
COPY maven/ROOT.war $CATALINA_HOME/webapps/ROOT.war
