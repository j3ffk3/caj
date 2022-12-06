## SCHEDULE
* Swagger UI
```
http://${DOMAIN}/swagger-ui.html
```

* Test
```bash=
./gradlew clean test --info
```

* Code Scan
```bash=
./gradlew sonarqube \
  -Dsonar.host.url=${SONARQUBE_URL} \
  -Dsonar.login=${USER} \
  -Dsonar.password=${PASSWORD} \
  -Dsonar.projectName=schedule-${STUDENT_ID} \
  -Dsonar.projectKey=schedule-${STUDENT_ID} 

```

* Build Artifact
```bash=
./gradlew clean build
```

* Build Image (COPY Artifact into image)
```bash=
docker build -f Dockerfile-openjdk -t schedule:latest .
```

* Build Image (Gradle build in image)
```bash=
docker build -f Dockerfile-gradle -t schedule:latest .


* Run image
```bash=
# default port 8080
docker run -p 8080:8080 schedule:latest
```