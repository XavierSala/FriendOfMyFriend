#!/usr/bin/env python3
import pymysql


class MyDB(object):
    BUSCAPERSONARANDOM = "SELECT * FROM PERSONES ORDER BY RAND() LIMIT 1"
    BUSCAPERSONA = "SELECT * FROM PERSONES WHERE id=%s"
    BUSCAAMICS = """
        SELECT p.id, p.nom, p.sexe FROM AMICS a
		 	   INNER JOIN PERSONES p ON p.id=a.id2
			   WHERE a.id1 = %s and p.sexe = %s
        """

    def __init__(self):
        self._db_connection = pymysql.connect(
            'localhost', 'root', 'ies2010', 'facebook')
        self._db_cur = self._db_connection.cursor()

    def buscaPersonaRandom(self):
        num = self._db_cur.execute(self.BUSCAPERSONARANDOM)
        result = None
        if num >= 1:
            result = self._db_cur.fetchone()
        return result

    def buscaPersona(self, id):
        num = self._db_cur.execute(self.BUSCAPERSONA, id)

        if num >= 1:
            result = self._db_cur.fetchone
        return result

    def buscaAmics(self, persona):
        num = self._db_cur.execute(self.BUSCAAMICS, [persona.id, persona.sexe])
        result = set()
        if num >= 1:
            result = {item for item in self._db_cur.fetchall()}
        return result

    def __del__(self):
        self._db_connection.close()
