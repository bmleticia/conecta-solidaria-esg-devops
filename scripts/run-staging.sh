#!/usr/bin/env bash
set -euo pipefail

docker compose --profile staging up -d --build app-staging
printf '\nStaging iniciado em http://localhost:8081\n'
printf 'Health: http://localhost:8081/actuator/health\n'
