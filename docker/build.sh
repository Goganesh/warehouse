#!/usr/bin/env bash
docker run --name=mydb --env="POSTGRES_PASSWORD=postgres" --publish 5432:5432 -d postgres
#docker image prune -a -f