FROM anapsix/alpine-java
COPY generic_email_sender.jar /home/generic_email_sender.jar
ENTRYPOINT ["java", -jar, "/home/generic_email_sender.jar"]
RUN ls -l /home/generic_email_sender.jar

