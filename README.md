bananapp, Spring + React js
===

This is the first server and the client of bananapp, jar-packaged. It consists of a Spring Boot application and a React.js app.

The whole app consists of two RESTful backends and a frontend, which communicates with them through ajax calls.

The first Spring server utilizes Spring Security and Hibernate for login and registration, and calls the second Google App Engine server for image prediction.

The frontend is entirely a React.js application, communicating with the two servers by axios (ajax calls).
