docker_up:
	@docker-compose up -d

consul_up:
	@./register-services.sh && \
	 ./register-variables.sh

compile: 
	@cd example.app && mvn package

run:
	@cd example.app && java -jar target/example.app-1.0-SNAPSHOT.jar

up: docker_up consul_up

down:
	@docker-compose down
