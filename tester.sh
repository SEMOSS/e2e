#!/bin/sh
docker compose -f docker.yaml up --remove-orphans --build --exit-code-from semoss;
