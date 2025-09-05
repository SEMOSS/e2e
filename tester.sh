#!/bin/sh
docker compose -f dcp.yaml up --remove-orphans --build --exit-code-from test;
