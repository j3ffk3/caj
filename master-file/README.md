## MASTER-FILE
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
  -Dsonar.projectName=master-file-${STUDENT_ID} \
  -Dsonar.projectKey=master-file-${STUDENT_ID} 
```

* Build Artifact
```bash=
./gradlew clean build
```

* Build Image (COPY Artifact into image)
```bash=
docker build -f Dockerfile-openjdk -t master-file:latest .
```

* Build Image (Gradle build in image)
```bash=
docker build -f Dockerfile-gradle -t master-file:latest .

* Run image
```bash=
# default port 8080
docker run -p 8080:8080 master-file:latest
```