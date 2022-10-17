FROM registry.redhat.io/openshift4/ose-cli:latest
RUN curl -sL https://rpm.nodesource.com/setup_14.x | bash - && yum install ncurses openssh-clients git podman nodejs java-11-openjdk-devel -y && npm install -g @angular/cli && mkdir -p /var/data
