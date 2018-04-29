# Solució del problema amb JPA

El problema que resol és el de determinar quants amics té una determinada persona (però fer l'altre és trivial).

Agafa una persona i en determina el número d'amics que té.

Per ara a persona està definida amb el seu ID en el main (App.java)

## Connexió amb la base de dades

Les dades per connectar amb la base de dades estan en el *persistence.xml* i s'expliquen soles: 

~~~~~xml
<properties>
	<property name="javax.persistence.jdbc.url"
		value="jdbc:mysql://localhost/facebook" />
	<property name="javax.persistence.jdbc.user" value="root" />
	<property name="javax.persistence.jdbc.password"
		value="ies2010" />
</properties>
~~~~~

## Execució del programa

El projecte és maven, o sigui que n'hi ha prou amb generar el Jar amb:

    $ mvn package

I després en el directori target hi tindrem l'executable:

    $ java -jar target/YoureMyFriendJPA-0.0.1-SNAPSHOT.jar
    9a35996b-15ff-46ac-a8b1-2c091fff79f1 Alfons Oliva Masolivé (Home)
    -----------------------------------
    1908 amics

