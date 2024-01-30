PG_CONTAINER_NAME ?= fsdevdb

delete_local_db:
		docker kill $(PG_CONTAINER_NAME) \
    	&& docker rm $(PG_CONTAINER_NAME) \

create_local_db:
		#docker run --name fsdevdb -e POSTGRES_PASSWORD=root -e POSTGRES_USER=root -e POSTGRES_DB=fsdev  -p 5432:5432 -d postgres
		docker run --name $(PG_CONTAINER_NAME) -p 9101:5432 -e POSTGRES_PASSWORD=root -e POSTGRES_USER=root -e POSTGRES_DB=fsdev  -d postgres