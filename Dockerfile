FROM tomcat:9.0
COPY target/jtodo-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/jtodo.war
EXPOSE 8080
