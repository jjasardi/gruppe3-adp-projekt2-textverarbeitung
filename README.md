# Textverarbeitung der Gruppe ADP


Um das Programm auszuführen öffnen Sie die Texteditor.java Datei mit dem IDE ihrer Wahl.
Führen Sie anschliessend die main() methode aus.

Folgendes sind die gültigen Befehle:

## Befehle
|Befehl|Beschreibung|
|---|---|
|ADD [n]|Ruft  zur  Eingabe  eines  Absatzes  auf  und  fügt  den  eingegebenen  Absatz  hinzu. Wird keine Absatznummer n angegeben, wird der Absatz am Ende angefügt|
|DEL [n]|Löscht  einen  Absatz.  Wird  keine  Absatznummer  n  angegeben,  wird  der  letzte Absatz gelöscht. |
|DUMMY [n]|Fügt  einen  fest  einprogrammierten  Blindtext  ein.  Wird  keine  Absatznummer  n angegeben, wird der Absatz am Ende angefügt.  
|EXIT|Beendet das Programm.|
|FORMAT RAW|Setzt  das  Ausgabeformat  auf  die  Ausgabe  der  Absätze  mit  vorangestellter Absatznummern. Dies ist das eingestellte Standardverhalten.|
|FORMAT FIX <b>|Setzt das Ausgabeformat auf eine Ausgabe mit einer maximalen Spaltenbreite von b Zeichen ein.|
|INDEX|Gibt  einen  Index  (Wortverzeichnis)  aller  Begriffe  aus,  die  über  alle  Absätze gesehen  öfter  als  dreimal  vorkommen.  Ein  Begriff  beginnt  mit  einem Grossbuchstaben. Der Index listet die Absätze, wo der jeweilige Begriff vorkommt, als Komma getrennte Zahlenfolge auf|
|PRINT|Ausgabe des Textes gemäss dem aktuell eingestellten Ausgabeformat.|
|REPLACE [n]|Ruft zuerst zur Eingabe eines zu suchenden Wortes oder Textteils im Absatz n auf und anschliessend zur Eingabe des Textes, mit dem das Gesuchte ersetzt werden soll.  Das  Suchen  und  Ersetzten  erfolgt  pro  Absatz,  nicht  über  Absatzgrenzen hinweg. Wird keine Absatznummer n angegeben, wird der letzte Absatz geändert. |
