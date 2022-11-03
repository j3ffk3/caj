## Create Ignition File
1. Clone Code
```bash=
cd /var/data
git clone https://github.com/j3ffk3/caj.git
cd caj/openshift-install/
```
![](https://i.imgur.com/khsMHdF.png)


2. Install OpenShift Install Client
```bash=
wget -c https://mirror.openshift.com/pub/openshift-v4/x86_64/clients/ocp/4.10.34/openshift-install-linux-4.10.34.tar.gz -O - | tar -xz
mv openshift-install /usr/local/bin/
openshift-install version
```
![](https://i.imgur.com/emeOJ23.png)

3. Download pull secret
```
https://console.redhat.com/openshift/install/pull-secret
```
Replace ${PULL_SECRET} in the install-config.yaml

4. Create SSH_PUB_KEY
```bash=
ssh-keygen -t rsa
cat ~/.ssh/id_rsa.pub
```
Replace ${SSH_KEY} in the install-config.yaml

5. Backup install-config.yaml
```bash=
cp install-config.yaml install-config.yaml.bak
ls
```

6. Create Manifest
```bash=
openshift-install create manifests --dir=$(pwd)
ls -alh
```

7. Disable Master node schedulable
```bash=
sed -i 's/mastersSchedulable: true/mastersSchedulable: false/g' manifests/cluster-scheduler-02-config.yml
cat manifests/cluster-scheduler-02-config.yml
```

8. Create Ignition
```bash=
openshift-install create ignition-configs --dir=$(pwd)
ls -alh
```
![](https://i.imgur.com/HiK6wkx.png)