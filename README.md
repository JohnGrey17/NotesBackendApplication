

# Note application

**Introduction**

I am pleased to present to you my Note Application project.
I approached the creation of this project with great responsibility and attention to detail.
In it, I have implemented all the essential features that a modern note-taking service should have — allowing users to quickly record, organize, and access information anytime and anywhere.

The goal of this project was to create a simple, fast, and reliable notification and note management system that helps users stay organized, keep track of important details, and never lose valuable information.
## Content of presentation

- [Description of the project](#description-of-the-project)
- [How to Run the Application](#how-to-run-the-application)
- [Functionality](#functionality)
- [Technologies](#technologies)

## Description of the project

* The main idea was to create a functionality that provides a simple, fast, and efficient note management system with flexible organization options and user-friendly interaction.

* A user can perform a quick registration process and immediately start working with their personal notes.

* After authentication, each user gains access to their own workspace, where they can create, read, update, and delete (CRUD) their notes.

* Each note contains a title, content, and one or more tags for better categorization and search.

* Notes are automatically sorted from newest to oldest, allowing users to instantly access their most recent ideas.

* Users can filter and sort notes by tags, which helps to organize their content according to themes or projects.

* The application also supports pagination — users can specify the desired number of notes per page, while the system applies default pagination values if no parameters are provided.

* All notes include a timestamp (createdAt, updatedAt) to help users track their creation and modification history.

* The backend architecture is optimized for speed and scalability, providing a seamless experience when managing large collections of notes.

* Future improvements may include features like reminders, notifications, and cloud synchronization across devices.


## How to Run the Application

**Prerequisites**
Ensure you have the following installed on your machine:

* **Java** **17**+
* **Maven** **4**+
* **MongoDb** 
* **Docker**

**To run the application locally, follow these steps:**

1. Clone the repository from here
```

   https://github.com/JohnGrey17/NotesBackendApplication.git

```       
2. Set up MongoDb:
   Create a new Mongo database and note the database URL, username, and password.
3. Configure environment variables: Create a .env file in the project root directory and use that variables:

```
SPRING_DATA_MONGODB_URI=
SPRING_DATA_MONGODB_DATABASE=
SPRING_PROFILES_ACTIVE=

```

4. Add that variable to application.property file:

```
spring.application.name=notes-backend

spring.data.mongodb.uri=${SPRING_DATA_MONGODB_URI}
spring.data.mongodb.database=${SPRING_DATA_MONGODB_DATABASE}

server.servlet.context-path=${BASE_URL}
```

5. Run the application:

```
mvn clean packege

mvn spring-boot:run
```

**Using Docker**

1. Build the Docker image:

```
docker build -t note-app .
```

2. Build and Run the Docker Containers:

```
docker-compose up
```

**Additional Information**

*Swagger Documentation:*

You can access the API documentation provided by Swagger at:

```
http://localhost:8080/swagger-ui/
```

## Functionality

_The API provides the following functionalities:_

- **User maneging**
    * Add new user
    * Get list of user
    * Get user by ID
    * Get user by userName
- **Notes maneging**
    * Create a new note
    * Get paginated list of notes
    * Get paginated list of notes by tag
    * Get note content by note ID
    * Get paginated note IDs by user ID
    * Update note by ID
    * Delete note by ID
    * Get word statistics for note

---

### User

- **POST /users/add**: Add new user

Body

```
{
    "userName": "steave@example.com"
}

```

- **GET /users/getAll** : Get all users

- **GET /users/id/{id}** : Get user by id 

```
example

/users/id/690870ff743e0dc31259aac6
```
- **GET /users/name/{userName}** : Get user by userName

```
example

/users/name/userName
```
### Note

- **POST /notes/add**: Add new Note
  Body
  ```
  {
    "title": "steave@example.com",
    "text": "TEST text",
    "Tag":["BUSINESS"]
  }
  ```
  
- **GET /getAll/userId/{userId}/paged**: Get all notes by user id
  

```
example

/notes/getAll/userId/690807b27ad08d8074b33037/paged?page=0&size=5
```

- **GET /getAll/tag/{tag}/paged**: Get paginated notes by tag
```
example
/notes/getAll/tag/BUSINESS/paged?page=0&size=5
```
- **GET /getContent/noteId/{noteId}**:Get note content by note ID
```
example
/notes/getContent/noteId/69080a48082dd2ceacb64c43
```
- **GET /notes/getId/userId/{userId}/paged?page=0&size=3**:Get paginated note IDs by user ID

```
example
notes/getId/userId/690807b27ad08d8074b33037/paged?page=0&size=3
```

- **GET /notes/stats/{noteId}**: Get word statistics for note
  

```
example

/notes/stats/6908135e872688dfac5fa644

```

- **PATCH /notes/update/noteId/{notesId}**: Update note by ID 
 ```
  {
    "title": "new title",(optional)
    "text": "new Text",(optional)
    "tag": [BUSSINESS](optional)
}
```
- **DELETE /notes/delete/noteId/{noteID}**: Delete note by ID

```
example

/notes/delete/noteId/69080a48082dd2ceacb64c43
```

## Technologies

Here are the main technologies I used in the development of this project:

- **Java 21:** The core programming language used for developing the application, leveraging its modern features and improvements.
- **Spring Boot:** A framework that simplifies the development of Java applications, providing features like dependency injection, aspect-oriented programming, and built-in support for various functionalities.
- **Swagger:** Used for API documentation, providing an interactive interface to explore and test the API endpoints.
- **Docker:** Used to containerize the application, ensuring consistency across different environments and simplifying the deployment process.
- **MongoDb:** A noSQL database management system used to store and manage application data.
- **MapStruct** Convenient way to transfer model betweenDto and model
- **Lombock** Convenient implement new model

