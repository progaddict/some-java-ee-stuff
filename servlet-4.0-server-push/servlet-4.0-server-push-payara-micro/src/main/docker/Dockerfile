FROM payara/micro:5.182

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