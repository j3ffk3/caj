## Package Stage
FROM registry.access.redhat.com/ubi8/nodejs-14 AS appbuild
COPY package.json package-lock.json .angular-config.json ./
RUN npm install
COPY . .
RUN npm run build

## Run Stage
FROM registry.access.redhat.com/ubi8/nginx-120
COPY ./nginx.conf "${NGINX_CONF_PATH}"
COPY --from=appbuild /opt/app-root/src/dist/redhat-lab /opt/app-root/src
CMD nginx -g "daemon off;"