FROM ubuntu:23.10 as build
MAINTAINER Anjan Nandy, https://github.com/anjannandy
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y openjdk-17-jdk && \
    apt-get clean

RUN mkdir /build
WORKDIR /build
COPY . ./
RUN chmod +x gradlew
RUN ./gradlew clean :distTar


FROM ubuntu:23.10
MAINTAINER Anjan Nandy, https://github.com/anjannandy
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y openjdk-17-jre && \
    apt-get clean

WORKDIR /app

COPY --from=build /build/build/distributions/api-services-0.0.1-SNAPSHOT.tar /app/services.tar
RUN tar xf /app/services.tar --strip-components=1 -C /app

RUN chmod +x /app/bin/*

EXPOSE 8080
ENTRYPOINT ["/app/bin/api-services"]
