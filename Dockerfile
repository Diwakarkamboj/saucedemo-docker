FROM  bellsoft/liberica-openjdk-alpine:17.0.12

RUN apk add curl jq

#workspace
WORKDIR /home/saucedemo-docker

ADD target/docker-resources ./
ADD runner.sh				runner.sh

#Run the tests
RUN dos2unix runner.sh

#start the runner.sh
ENTRYPOINT sh runner.sh