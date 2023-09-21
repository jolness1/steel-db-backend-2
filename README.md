# steel-db-backend-2


A simple application to store data about popular steel alloys and their general properties

Getting Started:
1) Run `docker pull postgres` to pull the postgres image to the local machine
2) Then `docker run -d --name postgresContainer -p 5432:5432 -e POSTGRES_PASSWORD=password postgres` to initialize the container
3) Run `./gradlew flywayMigrate` to setup the data base tables
