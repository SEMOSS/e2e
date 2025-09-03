#!/bin/sh
docker compose -f dcp.yaml --env-file .env --env-file .env.local up --remove-orphans --build semoss;

docker compose -f dcp.yaml --env-file .env --env-file .env.local up --remove-orphans --build --exit-code-from test test;
