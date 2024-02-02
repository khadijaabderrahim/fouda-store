PG_CONTAINER_NAME ?= fsdevdb

delete_local_db:
		docker kill $(PG_CONTAINER_NAME) \
    	&& docker rm $(PG_CONTAINER_NAME) \

create_local_db:
		docker run --name $(PG_CONTAINER_NAME) -p 9101:5432 -e POSTGRES_PASSWORD=root -e POSTGRES_USER=root -e POSTGRES_DB=fsdev  -d postgres

run_local_db:
		docker start $(PG_CONTAINER_NAME)

flyway_migrate:
		mvn clean flyway:migrate

build:
		mvn clean install

build_docker_image:
		mvn clean install && docker build -t fouda-store-be:1.0-SNAPSHOT .

run_back_end_docker_image:
		docker run -e RUN_ENV=PROD fouda-store-be:1.0-SNAPSHOT
