docker compose --profile staging up -d --build app-staging
Write-Host "Staging iniciado em http://localhost:8081"
Write-Host "Health: http://localhost:8081/actuator/health"
