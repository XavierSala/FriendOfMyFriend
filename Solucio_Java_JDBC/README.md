# Solució del problema amb JDBC

El problema que resol és el de determinar quants amics té una determinada persona.

Agafa una persona aleatòriament i en determina el número d'amics que té.

## Connectar amb la base de dades

Les dades per connectar amb la base de dades es defineixen en el **App.java** (url JDBC, usuari, contrasenya)

    ...
    FacebookDAO dades = new FacebookDAOMySQL("jdbc:mysql://localhost/facebook", "root", "ies2010");
    ... 

## Execució del programa

El projecte és maven, o sigui que n'hi ha prou amb generar el Jar amb:

    $ mvn package

I després en el directori target hi tindrem l'executable:

    $ java -jar target/YoureMyFriend-0.0.1-SNAPSHOT.jar
    Charly Pesarrodona Taberner
    -------------------
    --> Té : 1908 amics
