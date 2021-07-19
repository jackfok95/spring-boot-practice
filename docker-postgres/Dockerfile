# this dockerfile is not used.
# use docker compose instead.
FROM postgres:11.1
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD postgres
ENV POSTGRES_DB store
COPY ./init-sqls /docker-entrypoint-initdb.d