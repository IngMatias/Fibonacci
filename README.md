# Fibonacci [**_Java_**, **_Quarkus_**, **_SpringData_**, **_IBM Db2_**, **_AWS_**]

## Requirements

As is well known, the numbers 1, 2, 3, 5, 8, 13, 21, 34, 55, 89... are called FIBONACCI numbers, such that, starting from 1 and 2, all the others are obtained as the sum of their two predecessors.

We will call the FIBONACCI function the injective application n → F(n), which associates each natural number n with the nth term of the sequence.

The following is requested:

1) Create a Java REST API (you can use a framework like Spring or Quarkus) that implements an algorithm to return the nth Fibonacci number.
2) Store intermediate results in a relational database using an ORM and use it as a cache to improve function execution times.
3) Perform automated tests with 80% code coverage.
5) Store statistics in the database regarding which numbers were queried most frequently.
6) Publish the solution on a cloud platform.

The correct completion of the optional tasks will provide a better evaluation of the candidate's suitability.

You are expected to deliver a repository with the implemented solution.

## Endpoint Summary

### **Get All Fibonacci**

```
GET /fibonacci
```

Retrieve a list of all calculated Fibonacci numbers.

**Parameters:** None.

**Response Example:**

```
[
    {
        "id": 1,
        "n": 0,
        "fiboN": 0
    },
    ...
]
```

### **Get One Fibonacci**

```
GET /fibonacci/{n}
```

Retrieve a specific Fibonacci number by its position in the sequence.

**Parameters:**

**{n}** (Path Parameter): The position of the desired Fibonacci number.

**Response Example:**

```
{
    "id": 21,
    "n": 20,
    "fiboN": 6765
}
```

### **Get All Statistics**

```
GET /fibonacciStatistic
```

Retrieve statistics for all Fibonacci numbers that have been queried at least once. Fibonacci numbers with zero queries will not appear in the list.

**Parameters:** None.

**Response Example:**

[
    {
        "id": 1,
        "fibonacciEntity": {
            "id": 21,
            "n": 20,
            "fiboN": 6765
        },
        "queryCounter": 1
    },
    ...
]

### **Get One Statistic**

```
GET /fibonacciStatistic/{n}
```

Retrieve statistics for a specific Fibonacci number by its position in the sequence.

**Parameters:**

**{n}** (Path Parameter): The position of the Fibonacci number for which statistics are requested.

**Response Example:**

```
{
    "id": 1,
    "fibonacciEntity": {
        "id": 21,
        "n": 20,
        "fiboN": 6765
    },
    "queryCounter": 1
}
```

### **Error Handling**

- All endpoints will throw a FibonacciNMustBeAPositiveIntegerCustomException if the provided value of n is less than zero.
- For ```GET /fibonacci/{n}```, if no related Fibonacci number is found, it will throw a FibonacciEntityNotFoundCustomException.
- For ```GET /fibonacciStatistic/{n}```, if no related statistic is found, it will throw a FibonacciStatisticEntityNotFoundCustomException.


## Arquitecture

The Controller-Service-Repository pattern is widely adopted in many Spring Boot applications for its effective separation of concerns. This pattern provides a clear and structured architecture that enhances maintainability and testability.

- Controller: Located at the top layer, the Controller is responsible for exposing the application's functionality to external entities. It serves as the entry point for incoming requests, handling their routing and responding with appropriate data.

- Service: The Service layer serves as the heart of the application's business logic. It encapsulates all the operations and rules specific to your domain. If these operations involve data manipulation, the Service layer interacts with the Repository to fetch or store data. This separation ensures that business logic remains independent and can be thoroughly tested.

- Repository: Positioned at the bottom layer, the Repository is in charge of data storage and retrieval. It abstracts the underlying data source, whether it's a database, file system, or external service. This separation of concerns allows for easy data access and modification without impacting the business logic.

This well-defined separation of responsibilities facilitates testing and maintainability. Each layer can be tested in isolation by mocking or stubbing adjacent layers, ensuring that any changes or updates can be made with confidence, knowing their impact is limited to the relevant layer. This pattern promotes a modular and organized codebase, making it a preferred choice for building robust Spring Boot applications.

## Improvements for future

- Security (**Rate Limiting**: Control the rate of requests allowed per user or IP to prevent brute-force attacks or abuse)
- Pagination (**Result Limitation**: Limit the number of results returned to the amount specified in the page size)
- Diagrams (**Class Diagrams**, **Sequence Diagrams**)

## How to deploy in AWS[**EC2**] (or any other cloud) with Docker

### Virtual machine creation
When creating and configuring a virtual machine, it's essential to follow these steps:

1) Make a note of all usernames and passwords associated with your virtual machine. If default credentials are provided, ensure you save them securely.
2) Carefully obtain and save the .pem key file associated with your virtual machine. This key file is crucial for secure access.
3) Retrieve and save the public IPv4 address of your virtual machine. This address is needed to establish a connection.
4) If required, configure your virtual machine to allow external connections through specific ports such as 22 (SSH), 80 (HTTP), and/or 443 (HTTPS). This step ensures that your virtual machine can be accessed remotely.

### Establish ssh connection
Ensure you have the following information at hand:
- The .pem key file (the name is not critical, but the file's content matters).
- The virtual machine's user name (for AWS, this is often the name of the operating system, e.g., ec2-user).
- The public IPv4 address of your virtual machine.

Use the following command to establish an SSH connection to your virtual machine:

```
$ ssh -i key.pem <USER>@<IPv4>
```
Replace <USER> with your virtual machine's user name and <IPv4> with its public IPv4 address.

### Package manager
The following installation steps are written using the yum package manager. You can easily adapt them for other package managers like apt-get, pacman, or any suitable for your system.

### Install docker

Before installing Docker, it's a good practice to update your system:
```
$ sudo yum update -y
```

Now, proceed with the Docker installation:
```
$ sudo yum install -y docker
```

To start the Docker service (it might already be initiated), use the following command:
```
$ sudo service docker start
```

Next, you'll need to add your user to the Docker group for proper permissions:
```
$ sudo usermod -a -G docker ec2-user
```

**Note:** After adding your user to the Docker group, you should reconnect to the virtual machine to ensure the changes take effect.

To verify the correct installation of Docker, execute the following command:
```
$ docker ps
```

### Create db2 container

To create a DB2 container correctly, use the following command. Replace <NAME>, <PASSWORD>, and <DATABASE_NAME> with your desired values:
```
$ docker run -itd
    --name <NAME>
    --privileged=true
    -p 50000:50000
    -e LICENSE=accept
    -e DB2INST1_PASSWORD=<PASSWORD>
    -e DBNAME=<DATABASE_NAME>
    -v /home/ec2-user/DB2:/database ibmcom/db2
```

Here's an example using placeholder values:
```
$ docker run -itd
    --name db2inst1
    --privileged=true
    -p 50000:50000
    -e LICENSE=accept
    -e DB2INST1_PASSWORD=db2pass
    -e DBNAME=fibo
    -v /home/ec2-user/DB2:/database ibmcom/db2
```

After running the container creation command, you can list all containers and take note of the corresponding <CONTAINER_ID> by using the following command:
```
$ sudo docker ps -a
```
Ensure that the container's status is listed as _"UP"_.

To access the container, execute the following command, replacing <CONTAINER_ID>:
```
$ sudo docker exec -i -t <CONTAINER_ID> /bin/bash
```

### Start db2 inside container
First, switch to the db2inst1 user within the container using the following command:

This command logs you into the db2inst1 user account, which is typically the user account associated with IBM Db2.
```
$ su – db2inst1
```
Once you are logged in as db2inst1, start the DB2 service with the following command:
```
$ db2start
```
This command initializes and starts the DB2 database management system within the container.
### Connection

If you reviewed the docker run command, you'll notice that you've used db2inst1 as <USER>, db2pass as <PASSWORD>, and fibo as <DATABASE_NAME>. You can use these values to connect to the database from inside your container:

```
$ db2 connect to <DATABASE_NAME> user <NAME> using <PASSWORD>
```

Replace <DATABASE_NAME>, <USER>, and <PASSWORD> with the appropriate values based on your configuration. If you do not encounter any errors, it indicates a successful database connection.

### Install git and clone repository

Install Git using the following command:
```
$ sudo yum install git -y
```

Clone the desired repository using the following command:
```
$ git clone <REPO_URL>
```

### Configurate application.properties
To connect your project to the database, you need to add the following configurations to the application.properties Java resource file. Remember that you have mapped your localhost's port 50000 to the container's port 50000, which is the default DB2 port (as per the Docker run command):
```
quarkus.datasource.username=<NAME>
quarkus.datasource.password=<PASSWORD>
quarkus.datasource.jdbc.url=jdbc:db2://localhost:<PORT>/<DATABASE_NAME>
```
Replace <NAME>, <PASSWORD>, <PORT>, and <DATABASE_NAME> with the appropriate values based on your configuration.


### Install java
To install Java, execute the following command:
```
sudo yum install java -y
```

### Run in DevMode with Maven
Before running your Quarkus application in DevMode, make the Maven wrapper script executable:
```
chmod +x mvnw
```
Now, you can start your Quarkus application in DevMode using the following command:
```
./mvnw quarkus:dev
```

## Resources
https://es.quarkus.io/guides/building-native-image

https://es.quarkus.io/guides/spring-data-jpa

https://es.quarkus.io/guides/getting-started-testing

https://www.baeldung.com/java-quarkus-testing

https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/install-docker.html#install-docker-instructions