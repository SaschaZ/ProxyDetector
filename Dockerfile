FROM openjdk:16-slim-buster


ENV USER dockerbuilder
ENV GROUP pdbuilder
ENV HOME_PATH /home/${USER}
ENV PROJECT_FOLDER proxydetector
ENV PROJECT_PATH ${HOME_PATH}/${PROJECT_FOLDER}

WORKDIR /tmp

RUN apt-get update \
    && apt-get install -y --no-install-recommends unzip \
    && apt-get clean

# Add user
RUN addgroup ${GROUP}
RUN adduser --disabled-password --home ${HOME_PATH} -gecos '' ${USER}
RUN adduser ${USER} ${GROUP}

# Add current content to image
ADD --chown=${USER}:${GROUP} . ${PROJECT_PATH}
WORKDIR ${PROJECT_PATH}

USER ${USER}
#EXPOSE 1001

# Remove possible temporary build files
RUN rm -f ./local.properties && \
    find . -name build -print0 | xargs -0 rm -rf && \
    rm -rf .gradle && \
    rm -rf ~/.m2 && \
    rm -rf ~/.gradle

RUN ./gradlew assembleDist && \
    cd ./build/distributions && \
    unzip ./ProxyDetector-1.0-SNAPSHOT.zip

WORKDIR ${PROJECT_PATH}/build/distributions/ProxyDetector-1.0-SNAPSHOT/bin

CMD [ "/bin/sh", "-c", "./ProxyDetector" ]