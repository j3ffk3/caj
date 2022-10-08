FROM registry.access.redhat.com/ubi8/nginx-120
COPY ./nginx.conf "${NGINX_CONF_PATH}"
COPY ./dist/reservation-web /opt/app-root/src
CMD nginx -g "daemon off;"
