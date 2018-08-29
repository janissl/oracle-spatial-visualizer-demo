# oracle-spatial-visualizer-demo
A simple Java demo application to show how to visualize spatial objects from an Oracle XE database.<br>
It extracts spatial object data from user-defined tables in an Oracle database (Express Edition) and visualizes them in a separate window in a black-and-white mode.

Tables from which to extract data are defined by a user in a plaintext file _tables.txt_ (one table name per line). This file must exist in the current work directory.
Before running this application, the tables must be populated with user-defined spatial object data.

The application has been developed in course of my master studies at Riga Technical University (Specialized Database Systems (DSP 403), Prof. Dr. J. Eiduks, website (Latvian-only): https://datubaze.wordpress.com/macibu-kursi/db3-specializetas-dbs-jaunais/).

Prerequisites:
* installed Oracle SQL Developer
* installed Oracle Database XE (version 11g was used originally)

External libraries (add to the project as a reference):
* ojdbc8.jar (located in _.../sqldeveloper/jdbc/lib_ subfolder of your SQL Developer installation)
* sdoapi.jar (located in _.../sqldeveloper/sqldeveloper/lib_ subfolder of your SQL Developer installation)

Run the application (from a commandline):
```
$>java -cp * SpatialVisualizerDemo
```
