FROM gradle:jdk17

WORKDIR /APP

COPY . .

ENTRYPOINT ["gradle", "bootRun"]