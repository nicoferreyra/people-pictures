echo "Shutting down people pictures application..."

echo "Stopping ui-app..."
kill $(lsof -t -i:4200)

echo "Stopping API server..."
kill $(lsof -t -i:8080)

echo "Stopping mySql server..."
mySql.server stop

echo "People pictures application is down!"
