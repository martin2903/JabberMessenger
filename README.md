# JabberMessenger

## Project description
JabberMessenger is a microblogging platform allowing users to post short 
messages on a timeline. They are also free to follow other users and, naturally, other users can follow them. The messages, called jabs, posted by users they have chosen to follow will be listed on their timeline.

## Implementation
The application was developed using JavaFX and Maven. A PostgreSQL database was used. Concurrent access was achieved by building a multithreaded server to handle simultaneous client requests. The platform was designed following the MVC pattern.

## Project structure
* jabber: containts the main project packages
  * com.jabbermessenger: contains the components, controllers and model implementing the application functionality. The Main class from which the application is ran is also contained in that directory.
  * server: contains the multithreaded server and database access class.
* resources: contains all project views.

## Running a development version of the application

* Running the application requires that Maven, JavaFX and SceneBuilder are installed.
* The PostgreSQL jdbc driver is needed for database connectivity.
* The server could be started in the JabberServer class and the application executed in the Main class found in com.jabbermessenger
