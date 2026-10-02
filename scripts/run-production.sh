#!/usr/bin/env bash
set -euo pipefail

docker compose --profile production up -d --build app-production
printf '\nProducao iniciada em http://localhost:8082\n'
printf 'Health: http://localhost:8082/actuator/health\n'
