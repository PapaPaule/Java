Hallo Herr Unterstein,

Mein Projekt enthält nur den statischen Modus und den Testmodus.
Der statische Modus wird demnach immer aufgerufen.
Nachfolgend sind die möglichen Argumente zum Aufruf der Funktionen.

Kommandos		Funktion

--genre="X"		Listet alle Filme des Genres X nach dem Overall-rating sortiert auf und schreibt diese in die Datei 'ergebnis.txt'
--actor="X"		Listet alle Filme, in denen der Schauspieler X mitspielt, nach dem Overall-rating sortiert auf und schreibt diese in die Datei 'ergebnis.txt'
--director="X"		Listet alle Filme, die vom Regisseur X produziert wurden, nach dem Overall-rating sortiert auf und schreibt diese in die Datei 'ergebnis.txt'
--film="X"		Listet alle Filme, die X im Namen haben nach dem Overall-rating sortiert auf und listet anschließend alle Filme, die von Usern bewertet wurden, 
			die auch Film X bewertet haben nach dem Overall-rating sortiert auf und schreibt die gesamte Liste in die Datei 'ergebnis.txt'
--limit=i		Legt die Anzahl an Filmen fest, die angezeigt werden sollen. i ist hierbei eine Zahl. Gibt man Limit nicht an, werden maximal 200 Filme angezeigt.
--test=true		Startet den Testmodus mit 3 vordefinierten Abfragen und schreibt das Ergebnis in die Datei 'results.txt'


Alle Kommandos (außer Testmodus) können miteinander kombiniert werden. Hierbei wird die Ergebnisliste priorisiert. Je mehr der gegebenen Kriterien erfüllt sind,
desto höher findet man den entsprechenden Film in der Liste.
Wird der Testmodus gestartet, werden alle anderen Argumente ignoriert.