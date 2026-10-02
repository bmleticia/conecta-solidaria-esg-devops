#!/usr/bin/env bash
set -euo pipefail

docker compose --profile staging --profile production down
