# Grid Dynamics Internship Project
Online shop project on Java with Spring Boot and MySQL.

## Software Must Have
* Docker
* IDE (Intellij IDEA, Eclipse, etc...)
* Postman
## Installing and configurating

### Downloading repository

Download or clone project on your enviroment.
```bash
git clone https://github.com/borysenkox/gd-internship
```

### Configurating MySQL on Docker

Downloading MySQL server with version 8.0.22 on Docker:
```bash
docker pull mysql/mysql-server:8.0.22
```
Wait until it'll be downloaded and installed.
After that check if mysql/mysql-server image was downloaded:
```bash
docker images
```
If everything is okay - create container of MySQL server on Docker with ports 3306 and 33060 (default ports for MySQL):
```bash
docker run -p 3306:3306 -p 33060:33060 --name=<name> --restart on-failure -d mysql/mysql-server:8.0.22
```
* *name* - name of container in Docker.<br>
Openning this ports make MySQL server available on your local machine (not only Docker VM).
Wait until ```STATUS``` in
```bash
docker ps 
```
won't be ```healthy```.

After that, generate one-time-use password to access MySQL server with command:
```bash
docker logs <name> 2>&1 | grep GENERATED
```
Save password, but not so deep, prepare it for entering after execution:
```bash
docker exec -it <name> mysql -uroot -p
```
If everything is okay you will be writing commands as ```<mysql>```
Change one-time *pass* with yours with entering:
```bash
ALTER USER 'root'@'localhost' IDENTIFIED BY '<pass>';
```

To connect outside from docker:

```bash
update mysql.user set host = '%' where user='root';
```
Restart server:
```bash
docker restart <name>
```
<b>SQL server is installed and configured!</b>

### Connection to MySQL

Input command in terminal:
```bash
docker exec -it <name> mysql -uroot -p
```
Enter your *password*.

<b>Congratulations, you are connected!</b>

### Running SQL scripts on Docker

Move SQL script from your local machine to Docker (<b>ATTENTION!</b> You have to be in directory where file is located.):
```bash
docker cp <file.ext> <container>:</path/to/file.ext>
```
* *<file.ext>* - name of file;
* *<сontainer>* - name of container (mysql2 for example);
* *</path/to/file.ext>* - path to the file.

After successful moving script - connect to the MySQL (watch the <b>Connection to MySQL</b>).

Run the script with
```bash
source <path_to_sql_file>/file.sql
```

<b>Script is successfully inserted!</b>


