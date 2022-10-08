## LAB SETTINGS

* Build Student CLI (Podman/OC/Git...)
```bash=
cd student-cli
docker build -t student-cli:latest .
```
![](https://i.imgur.com/AoSNO06.png)

* Build Student UI (noVNC)
```bash=
cd student-ui
docker build -t student-ui:latest .
```
![](https://i.imgur.com/zKW2cRv.png)

* Create Student Namespace
```bash=
# Login to OpenShift
oc login -u ${USER} ${API_URL}
# Create project
./init ${NAMESPACE_NAME}
```