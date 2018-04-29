#!/usr/bin/env python3


class Friend(object):

    def __init__(self, id, nom, sexe):
        self.id = id
        self.nom = nom
        self.sexe = sexe

    def __str__(self):
        return "{} {} {}".format(self.id, self.nom, self.sexe)

    def __hash__(self):
        return hash(self.id)

    def __eq__(self, other):
        return id == other.id
