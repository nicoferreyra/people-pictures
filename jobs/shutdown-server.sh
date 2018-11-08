echo "Shutting down people pictures application..."

echo "Stopping API server..."
kill $(lsof -t -i:8080)

echo "Stopping mySql server..."
mySql.server stop

echo "People pictures application is down!"