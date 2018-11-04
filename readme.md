# People pictures API

This project is a REST API made to fetch Tarmac.io people. It also allows to fetch roles and cities where the company operates.

## Built With

![technology Java 8](https://img.shields.io/badge/technology-Java%208-green.svg) - Programming language
![technology Gradle](https://img.shields.io/badge/technology-Gradle-blue.svg) - Build tool
![technology Spring Boot] (https://img.shields.io/badge/technology-spring--boot-red.svg) - Web framework
![technology Spring MVC] (https://img.shields.io/badge/technology-spring--mvc-green.svg) - MVC framework
![technology MySql] (https://img.shields.io/badge/technology-mysql-lightgrey.svg) - MVC framework

## Getting Started

Get the project from the Git repository
```
git clone https://github.com/niko-air/people-pictures.git
```

Move to the project folder
```
cd people-pictures 
```

Fetch all branches from remote repositories and move to develop branch
```
git fetch --all
git checkout develop
```

## Installing
The following steps will set your local environment for running the application.

### Install Homebrew

Execute in your terminal the folowing commands

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

### MySql setup
In this step, you will configure a MySql server creating a database and a proper user for it.
```
brew install mysql

mysql.server start
sudo mysql

mysql> create database people_pictures;
mysql> create user 'people_pictures_user'@'localhost' identified by 'xtzcnjiufk';
mysql> grant all on people_pictures.* to 'people_pictures_user'@'localhost';

exit;
```


### Install jq to see results:
```
brew install jq
```

## Running the tests


## Author

* **Nicolás Ferreyra** - *Initial work* - [PurpleBooth](https://github.com/niko-air)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.
