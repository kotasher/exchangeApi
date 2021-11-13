MAVEN := mvn
DOCKER := podman
APP_NAME := thereisnoml/quotes
CONTAINER_NAME := thereisnomlquotes

all:
	${MAVEN} clean package
	${DOCKER} build . -t ${APP_NAME}

run:
	${DOCKER} run -d -p 8080:8080 --name ${CONTAINER_NAME} ${APP_NAME}

stop:
	-${DOCKER} kill ${CONTAINER_NAME}
	-${DOCKER} rm ${CONTAINER_NAME} --force

clean:
	make stop
	rm -rf exchangeApi/target
	rm -rf web/target
	rm -rf target
	${DOCKER} system prune --all --force
