PG_CONTAINER_NAME ?= fsdevdb
IMG_TAG ?= latest
SKIP_TESTS ?= false

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
		mvn clean install -DskipTests=$(SKIP_TESTS)

build_docker_image:
		make build && docker build --build-arg="BUILD_VERSION=$(IMG_TAG)" -t ziedayadi/fouda-store-backend:$(IMG_TAG) .

run_back_end_docker_image:
		docker run -e RUN_ENV=DEV ziedayadi/fouda-store-backend:$(IMG_TAG)

run_front_end_docker_image:
		docker run -e RUN_ENV=DEV ziedayadi/fouda-store-frontend:$(IMG_TAG)

