# steel-db-backend-2


A simple application to store data about popular steel alloys and their general properties

## Prerequisites:
1) Java SDK 20.0.2 minimum  
2) Docker

## Guides to installing required software
1) Java: [SDKman Install](https://sdkman.io/install) [Java Install](https://sdkman.io/usage)
2) Docker: [Docker Desktop](https://www.docker.com/products/docker-desktop/) *or* [Colima](https://formulae.brew.sh/formula/colima)
   (personally I use Colima as it is lighter weight, still uses the same commands)


## Getting Started
1) Run `docker pull postgres` to pull the postgres image to the local machine
2) Then `docker run -d --name postgresContainer -p 5432:5432 -e POSTGRES_PASSWORD=password postgres` to initialize the container
3) Run `./gradlew flywayMigrate` to set up the database tables
