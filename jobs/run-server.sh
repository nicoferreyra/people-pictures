echo "Running people pictures application..."

echo "Starting mySql server..."
mySql.server start

echo "Building API executable JAR..."
gradle build

echo "Executing API executable JAR..."
java -jar build/libs/people-pictures-0.0.1.jar &

until $(curl --output /dev/null --silent --head --fail http://localhost:8080/people); do
    sleep 5
done

echo "Populating database: people, city and role tables..."
curl -i -X POST http://localhost:8080/populate/people
curl -i -X POST http://localhost:8080/populate/cities
curl -i -X POST http://localhost:8080/populate/roles

echo "Executing npm install..."
npm install

echo "Executing npm run-script build..."
npm run-script build

echo "Running ui-app..."
./ng serve &

echo "People pictures application is running!"
