#!/bin/sh
docker compose -f docker-server.yaml up --remove-orphans --build;
