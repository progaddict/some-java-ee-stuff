FROM payara/micro:5.182

COPY maven/ROOT.war /my-app/ROOT.war

CMD ["--sslPort", "8181", "--deploy", "/my-app/ROOT.war"]