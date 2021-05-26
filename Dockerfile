FROM openjdk:15 as builder

COPY . /project
WORKDIR /project
RUN ./gradlew installDist

FROM openjdk:15

COPY --from=builder /project/build/install/kblogger /project

CMD ["/project/bin/kblogger"]
