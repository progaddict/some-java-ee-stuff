FROM openjdk:8u171-jdk-alpine

ENV PAYARA_PATH /opt/payara

RUN apk update \
 && apk add ca-certificates wget \
 && update-ca-certificates \
 && mkdir -p $PAYARA_PATH/deployments \
 && adduser -D -h $PAYARA_PATH payara \
 && echo payara:payara | chpasswd \
 && chown -R payara:payara /opt

ENV PAYARA_PKG https://search.maven.org/remotecontent?filepath=fish/payara/extras/payara-micro/5.182/payara-micro-5.182.jar
ENV PAYARA_VERSION 182
ENV PKG_FILE_NAME payara-micro.jar

RUN wget --quiet -O $PAYARA_PATH/$PKG_FILE_NAME $PAYARA_PKG

ENV DEPLOY_DIR $PAYARA_PATH/deployments
ENV AUTODEPLOY_DIR $PAYARA_PATH/deployments
ENV PAYARA_MICRO_JAR=$PAYARA_PATH/$PKG_FILE_NAME

# Default payara ports to expose
EXPOSE 4848 8009 8080 8181

USER payara
WORKDIR $PAYARA_PATH

COPY --chown=payara:payara \
  maven/ROOT.war \
  /my-app/ROOT.war
COPY --chown=payara:payara \
  maven/grizzly-npn-bootstrap.jar \
  /my-app/grizzly-npn-bootstrap.jar

ENTRYPOINT [ \
  "java", \
  "-Xbootclasspath/p:/my-app/grizzly-npn-bootstrap.jar", \
  "-jar", \
  "/opt/payara/payara-micro.jar" \
]
CMD ["--sslPort", "8181", "--deploy", "/my-app/ROOT.war"]