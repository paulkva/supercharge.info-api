# About

API for https://supercharge.info

# Development

## Local Setup

1. Java 14
2. Maven
3. Docker

## Build

- `mvn -DskipTests install`

## Test

`./database/rebuild-database.sh` (once, run as user that can execute docker commands)

`./start.sh` (each time, run as a user that can execute docker commands)

## Deploy

1. Verify tests pass.
2. `./release.sh` (enter to accept suggested release version)
3. `git push --tags origin master` (to push release to origin)
4. `./deploy.sh <test|prod> <version>`

## Configuration

1. Local/development configuration is read from `classpath:/com/redshiftsoft/tesla/dev-config.properties`.
2. In test/prod the system property `api.config.location` specifies configuration file.
