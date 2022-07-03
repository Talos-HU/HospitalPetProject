docker build . -t hospital
docker network create hospital-net
docker stop hospital_db
docker stop hospital_api
docker run --net hospital-net -e POSTGRES_PASSWORD=drhouse --name hospital_db -e POSTGRES_USER=hospitaladministrator -e POSTGRES_DB=hospital -d --rm postgres:14.4-alpine
docker run --net hospital-net --name hospital_api -p8080:8080 --rm hospital