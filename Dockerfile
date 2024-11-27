FROM bellsoft/liberica-openjdk-alpine:17

#Workspace in container
WORKDIR /home/selenium-docker

#Adding JAR and required suites files
ADD target/config ./config
ADD target/selenium-grid-docker-frank.jar ./
ADD dockerImageRunner.sh dockerImageRunner.sh 

#Installing curl and jq
RUN apk add curl jq
RUN dos2unix dockerImageRunner.sh

ENTRYPOINT sh dockerImageRunner.sh