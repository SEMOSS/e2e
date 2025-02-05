#!/bin/sh
docker compose rm -f semoss ; docker compose up --build --exit-code-from test ; docker compose rm -f semoss
