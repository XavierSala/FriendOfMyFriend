from database.database import MyDB
from friend import Friend

db = MyDB()

# resultat = db.buscaPersona('fff301fa-d30a-498a-ad59-e440931bf02d')
# resultat = db.buscaPersona('9eddf57a-413c-40a2-a8b9-ec959c6ad5d0')
resultat = db.buscaPersonaRandom()
candidat = Friend(resultat[0], resultat[1], resultat[2])


amics = set()
nousAmics = set()
nousAmics.add(resultat)

print(candidat.nom)
print("-----------------------")

while(len(nousAmics) != 0):
    possiblesAmics = set()
    for unamic in nousAmics:
        nouAmic = Friend(unamic[0], unamic[1], unamic[2])
        possiblesAmics = possiblesAmics.union(db.buscaAmics(nouAmic) - amics)
        amics = amics.union(possiblesAmics)

    nousAmics = possiblesAmics

print(len(amics), "amics")
