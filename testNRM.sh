#!/bin/sh
docker pull quay.io/semoss/docker:ubi8-rhel \
; docker compose rm -f semoss \
; docker compose up --build --exit-code-from test
