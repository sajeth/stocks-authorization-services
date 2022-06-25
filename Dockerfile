FROM openjdk:17-slim
WORKDIR /app
EXPOSE 8080


ARG JAR_FILE=target.nosync/stocks-authorization-services-0.0.1.jar

ADD ${JAR_FILE} ./app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]

#Installing firefox
#RUN echo "deb http://deb.debian.org/debian/ unstable main contrib non-free" >> /etc/apt/sources.list.d/debian.list
#RUN apt-get update
#RUN apt-get install -y --no-install-recommends firefox


#Installing chrome
#RUN apt-get -y install wget
#RUN wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
#RUN apt install -y --no-install-recommends ./google-chrome*.deb