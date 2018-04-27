## Els amics dels meus amics són els meus amics

La gran xarxa social “Friends” (que encara no ho sabeu, però serà el que substituirà Facebook) us ha contractat perquè li dissenyeu un programa que digui si dos persones són amics o no seguint la regla de que els amics dels meus amics són amics meus (i els amics dels amics dels meus amics també ho són … ).

![Arbre](imatges/friends0.png)

Per exemple:

* En Joan és amic d’en Frederic perquè seguint la cadena d’amistats d’en Joan podem arribar a en Frederic
* En canvi en Frederic no és amic de’n Joan perquè seguint la seva cadena només podem arribar a en Toni (que no té amics)

A més com que els de “Friends” són una mica “especials”, pensen que els homes i les dones no poden ser amics, per tant la cadena només és vàlida si no es passa per cap amic d’un altre sexe.

En aquest cas, en Joan no és amic d’en Filomeno perquè per arribar-hi hem de passar per una dona

![No Sex](imatges/friends1.png)

La base de dades que tenen té aquesta estructura

![BDD](imatges/friends2.png)

* Aquí teniu una còpia de seguretat de la base de dades per MySQL (aquí)
* Una còpia de la base de dades per PostgreSQL es pot aconseguir (aquí)
* La relació d'amics indica que: **id1 és amic de id2** però no a l'inrevés.

### Opció 1

Desenvolupeu un programa que donats dos “friend” diferents us digui si són amics o no.

    Exemple:

        Friend1: Pol Lloberes Maluquer
        Friend2: Didac Taberner Maruny
        --> Són amics

### Opció 2

Desenvolupeu un programa que donat un usuari “friend” us digui quants amics té.

        Friend: Òscar Llach Ingla
        --> Té : Té 0 amics

        Friend: Ramona Isern Pelegrí 1
        --> Té : 1511 amics
