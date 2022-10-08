## Fare
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
-Dsonar.password=${PASSWORD} 
```

* Build Artifact
```bash=
./gradlew clean build
```

* Build Image (COPY Artifact into image)
```bash=
docker build -f Dockerfile-openjdk -t fare:latest .
```

* Build Image (Gradle build in image)
```bash=
docker build -f Dockerfile-gradle -t fare:latest .