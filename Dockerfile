# chromium > 76 is required. For now, this is only available in the 'edge' build
FROM alpine:edge
USER root

RUN apk add openjdk11
RUN java --version

# Install gradle
ENV GRADLE_VERSION 6.8
WORKDIR /usr/local
RUN wget  https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip && \
    unzip gradle-$GRADLE_VERSION-bin.zip && \
    rm -f gradle-$GRADLE_VERSION-bin.zip && \
    ln -s gradle-$GRADLE_VERSION gradle && \
    echo -ne "- with Gradle $GRADLE_VERSION\n" >> /root/.built

WORKDIR /app

ENV GRADLE_HOME /usr/local/gradle
ENV PATH ${PATH}:${GRADLE_HOME}/bin
ENV GRADLE_USER_HOME /gradle

COPY build.gradle /app/build.gradle
COPY . /app

RUN gradle build
#RUN gradle test
WORKDIR /app/build/reports/tests/test/
RUN ls -lh

# add app
