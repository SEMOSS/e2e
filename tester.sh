#!/bin/sh
docker compose up --build --exit-code-from test && docker compose rm -f semoss && docker container rm -f e2e-semoss-1
