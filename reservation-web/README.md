## CAJ Web

* Run application in local
```
npm start
```

* Build Artifact
```bash=
ng build
```

* Build Image
```bash=
docker build -f Dockerfile -t caj-web:latest .
```

* Run image
```bash=
# default port 8080
docker run -p 8080:8080 caj-web:latest
```
