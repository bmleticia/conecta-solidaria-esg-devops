docker compose --profile production up -d --build app-production
Write-Host "Producao iniciada em http://localhost:8082"
Write-Host "Health: http://localhost:8082/actuator/health"
