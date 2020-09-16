FROM adoptopenjdk/openjdk11-openj9:x86_64-debian-jdk-11.0.3_7_openj9-0.14.3
ADD transfer-api/target/transfer-api.jar transfer-api.jar

ENTRYPOINT java -Xshareclasses -Xtune:virtualized -Xquickstart -jar transfer-api.jar