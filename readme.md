# People pictures API

This project is a RESTful API made to fetch the people that work for Tarmac. It also allows to fetch roles and cities related to the company.

## Built With

![language Java 8](https://img.shields.io/badge/language-Java%208-green.svg)
![build-tool Gradle](https://img.shields.io/badge/build_tool-Gradle-blue.svg)
![framework Spring Boot](https://img.shields.io/badge/framework-spring--boot-red.svg)
![framework Spring MVC](https://img.shields.io/badge/framework-spring--mvc-orange.svg)
![database MySQL](https://img.shields.io/badge/database-mysql-lightgrey.svg)
![technology Jersey](https://img.shields.io/badge/technology-jersey-ff69b4.svg)

## Getting Started

### Get the project from Git repository
```
git clone https://github.com/niko-air/people-pictures.git
```

### Change to project folder
```
cd people-pictures 
```

### Fetch all branches from remote repository and switch from 'master' to 'develop' branch
```
git fetch --all
git checkout develop
```

### If you want to import the project to your IDE, please do it using the 'build.gradle' file.

## Installing
The following steps will set your local environment for running the application.

### Install and update Homebrew
```
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"

brew update
```

### Install sdkman
```
curl -s "https://get.sdkman.io" | bash

source "$HOME/.sdkman/bin/sdkman-init.sh"
```

### Install Gradle
```
sdk install gradle 4.10.2
```

### MySQL setup
#### In this step, you will configure a MySQL server by creating a database and a proper user for it.
```
brew install mysql

mysql.server start
sudo mysql

mysql> create database people_pictures;
mysql> create user 'people_pictures_user'@'localhost' identified by 'xtzcnjiufk';
mysql> grant all on people_pictures.* to 'people_pictures_user'@'localhost';
mysql> exit;
```


### Install jq to see results in JSON format
```
brew install jq
```

## Running the application

Start the server by executing 'run-server.sh' file
```
sh jobs/run-server.sh 
```

## Testing the application - Examples

### Execute the following request to the API:

#### Fetch all people that work for the company
```
curl -X GET "http://localhost:8080/people" | jq .
```

#### You can also provide a start and a limit to paginate results. For example, if you want to fetch only the first 5 people of the company
```
curl -X GET "http://localhost:8080/people?limit=5" | jq .
```

#### Or maybe retrieve the last 5 people from the company

```
curl -X GET "http://localhost:8080/people?limit=5&start=55" | jq .
```

#### Fetch a specific person by name
```
curl -X GET "http://localhost:8080/people/petar-tofevski" | jq .
```

#### Fetch people by role and city examples
```
curl -X GET "http://localhost:8080/people/roles/Developer" | jq .

curl -X GET "http://localhost:8080/people/roles/Developer?start=40" | jq .

curl -X GET "http://localhost:8080/people/roles/Developer?start=30&limit=7" | jq .

curl -X GET "http://localhost:8080/people/cities/Montevideo" | jq .

curl -X GET "http://localhost:8080/people/cities/Montevideo?limit=5" | jq .

curl -X GET "http://localhost:8080/people/cities/Montevideo?limit=10&start=10" | jq .
```

#### Fetch all cities
```
curl -X GET "http://localhost:8080/cities" | jq .
```

#### Fetch a specific city by name
```
curl -X GET "http://localhost:8080/cities/minnesota" | jq .
```

#### Fetch all roles
```
curl -X GET "http://localhost:8080/roles" | jq .
```
#### Fetch a specific role by name
```
curl -X GET "http://localhost:8080/roles/director-or-partnerships" | jq .
```

## Shutdown the application
```
sh jobs/shutdown-server.sh
```

## Author

* **Nicolás Ferreyra** - [niko-air](https://github.com/niko-air)
