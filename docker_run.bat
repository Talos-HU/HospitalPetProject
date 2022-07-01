docker build . -t hospital
docker run --rm -e POSTGRES_PASSWORD=drhouse --name hospitalDB -e POSTGRES_USER=hospitaladministrator -e POSTGRES_DB=hospital -d -p5433:5432 postgres:14.4-alpine
mvn spring-boot:run