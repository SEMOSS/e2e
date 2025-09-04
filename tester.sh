#!/bin/sh
docker compose up --remove-orphans --build --exit-code-from test;
