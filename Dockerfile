FROM openjdk:16-slim-buster

# Use the version number of the android sdk tools from here: https://developer.android.com/studio/index.html#downloads.
ENV ANDROID_SDK_TOOLS_VERSION="7583922"
ENV ANDROID_PLATFORM_VERSION="30"
ENV ANDROID_BUILD_TOOLS_VERSION="30.0.3"

WORKDIR /tmp

RUN apt-get update \
    && apt-get install -y --no-install-recommends unzip \
    && apt-get clean

# Add user
ARG user_id
ENV USER_ID=${user_id}
ARG group_id
ENV GROUP_ID=${group_id}

RUN addgroup --gid ${GROUP_ID} builder
RUN adduser --disabled-password --home /home/builder -gecos '' --uid ${USER_ID} --gid ${GROUP_ID} builder

# Add current content to image
ADD --chown=builder:builder . /home/builder/project
WORKDIR /home/builder/project

EXPOSE 80

# Remove possible temporary build files
RUN rm -f ./local.properties && \
    find . -name build -print0 | xargs -0 rm -rf && \
    rm -rf .gradle && \
    rm -rf ~/.m2 && \
    rm -rf ~/.gradle


WORKDIR /home/builder/project
USER builder

RUN ./gradlew assembleDist && \
    cp ./build/distributions/ProxyDetector-1.0-SNAPSHOT.zip ./ProxyDetector.zip && \
    unzip ./ProxyDetector.zip

WORKDIR /home/builder/project/ProxyDetector-1.0-SNAPSHOT/bin

CMD [ "/bin/sh", "-c", "./ProxyDetector" ]