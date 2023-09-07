# Kurzdokumentation

[zurück zur Startseite **trial-and-error**](../../README.md)

[zurück zu **Test-Entwicklungen**](../START.md)

---

## ARTINER - ARBEITSZEITRECHNER FUR KIMAI

---

![ARTINER.jpg](../monthly-work-time-calculator/Dateien/Bilder/Artiner.png)

### MOTIVATION


+ Als Werkstudent trage ich jeden Werktag meine Arbeitszeiten in die [Kimai Zeiterfassung](https://www.kimai.org/de/) ein, um den Überblick über meine Arbeitszeiten zu behalten.  

+ Vor dem 15. eines jeden Monats muss ich die Daten meiner Arbeitszeiten vom 16. des Vormonats bis zum 15. des aktuellen Monats in eine Excel-Tabelle eingeben. Dann exportiere ich diese [Excel-Tabelle (link zeigt die PDF-Layout)](../monthly-work-time-calculator/Dateien/Bilder/Excel_Muster.jpg) in eine PDF-Datei und schicke sie an [HLE](mailto:hleppin@berlincert.de), der diese PDF-Datei dann unterschreibt und an die Team-Assistenz weiterleitet. 

+ Das Sortieren und Kopieren von Daten aus die KIMAI in eine Excel-Tabelle erfordert selbstverständlich viel Zeit und Mühe.

+ Um diese Aufgabe durch Automatisierung zu erleichtern, haben wir ein Programm entwickelt : Artiner

### ÜBER ARTINER

Artiner ist ein CLI-Tool, es liest die Daten aus der o.g. Kimai CSV-Datei, sortiert die Daten nach Datum und geben eine sortierte Ausgabe, die einfach in Excel kopiert werden kann.

Der verwendete Code ist zu sehen unter `code/java/`

### VORAUSSETZUNG

+ CSV-Datei [(Muster)](../monthly-work-time-calculator/Dateien/CSV/April_Mai_Faraz.csv) sollte zugänglich sein

+ Auf dem Computer muss [Java JDK 8+](https://docs.oracle.com/en/java/javase/20/install/installation-jdk-microsoft-windows-platforms.html#GUID-A7E27B90-A28D-4237-9383-A58B416071CA) installiert sein


### ANWENDUNG


**Schritt 1- HERUNTERLADEN** 

![KIMAI](../monthly-work-time-calculator/Dateien/Bilder/KIMAI_Herunterladen.png)

Öffnen Sie [Kimai](https://www.kimai.org/de/) (auf dem obigen Bild sehen Sie das Kimai Zeiterfassungssystem der [HLE](mailto:hleppin@berlincert.de)). Gehen Sie auf die "Meine Zeiten" Tab und laden Sie die Daten als csv-Datei herunter 

*Wenn Sie möchten, können Sie auch die Daten in der "Suchen" option in Kimai nach "Projekt" Filtern, vor dem downloaden.*


Laden Sie nun [artiner.zip](../../test-development/monthly-work-time-calculator/downloads/artiner.zip) herunter und entpacken Sie es auf Ihrem Computer. Dieses `zip` enthält folgende Dateien :

   + [artiner.jar](../monthly-work-time-calculator/Dateien/Jar/artiner.jar)

   + [artiner.bat](../monthly-work-time-calculator/code/cmd/artiner.bat) *(Windows)* **UND** [artiner.sh](../monthly-work-time-calculator/code/bash/artiner.sh) *(Linux)*







**Schritt 2- AUSFÜHREN (Windows)** 

Öffnen Sie jetzt `cmd` und geben Sie den vollständigen Dateipfad von `artiner.bat` ein, um Artiner auszuführen.

*- zum Beispiel, wenn sich die Datei `artiner.bat` im unterverzeichnis ``Downloads/Artiner/`` befindet, sollten Sie den folgenden Befehl in cmd eingeben:* 

```
C:\Users\abm\Downloads\Artiner\artiner.bat 

```

                                      ALTERNATIV


Fügen Sie die Datei artiner.bat zur Systemvariable path in Windows hinzu[(Video-Tutorial - 20:10 bis 24:15)](https://www.youtube.com/watch?v=etb_Y1Rlt4E). Dann brauchen Sie nur noch den Namen der Datei: "artiner.bat" in cmd eingeben, um Artiner zu starten.

**Schritt 3 - INPUT** 


**Fall 1- Falsche Arguments**

+ wenn keine Flaggen angegeben sind, bietet das Tool dem Benutzer die Möglichkeit, das Hilfemenü anzuzeigen

+ bei falscher Input zeigt das Programm einen Fehlerdialog: "Unbekannte Option"

**Fall 2- Flags**

Allgemein kann ein Benutzer das Programm mit den folgenden Flags konfigurieren und ausführen: 

+ **-i oder --InputFile :** Geben Sie den Dateipfad für die csv-Datei an

+ **-f oder --FirstDate :** Das vom Benutzer angegebene Datum(jjjj-mm-tt) als Startdatum des Berechnungszeitraums festlegen

+ **-l oder --LastDate :** Das vom Benutzer angegebene Datum(jjjj-mm-tt) als letztes Datum des Berechnungszeitraums festlegen

+ **-c oder --CountPause :** Auch "Pause" berechnen

+ **-F oder --taskFilter :** Geben Sie den Namen der Tätigkeit an, die herausgeFiltert werden soll



+ **-h oder --help :**  Zeigt das Hilfemenü an

+ **-p oder --PauseOnly :** Nur den Bericht für die "Pause" ausgeben
 
+ **-r oder --replacement :** Geben Sie den Dateipfad für die [ersatzdatei](../../test-development/monthly-work-time-calculator/Dateien/Konfigdateien/replacment.conf) an. Das Programm liest die Daten aus der Ersetzungsdatei und setzt sie in das Programm ein

+  **-C oder --configfile:**  Geben Sie den Dateipfad für die [Konfigurationsdatei](../../test-development/monthly-work-time-calculator/Dateien/Konfigdateien/artiner.conf) Datei


+ **-o oder --OutputFormat :** Geben Sie das Ausgabeformat an: "T" für nur die Liste, "TL" für  Tabelle+List  

+ **-v  --vacation :** Stunden für einen Urlaubstag berechnen(Geben Sie die Anzahl der erlaubten Urlaubstage pro Jahr an)


**Fall 3- Konfigdatei**

 
Verwenden Sie das Flag -C und geben Sie den Dateipfad der [Konfigurationsdatei](../../test-development/monthly-work-time-calculator/Dateien/Konfigdateien/artiner.conf) an, um das Tool mit dieser Datei zu konfigurieren

**Schritt 4- AUSGABE**- 

Das Programm gibt die sortierten Daten in dem vom Benutzer angegebenen Format aus(Das Programm berechnet oder druckt die Daten für die Pause standardmäßig nicht aus)
                  
                                           OUTPUT TABELLE

![Output](../monthly-work-time-calculator/Dateien/Bilder/Output_Tabelle.png) 

                                         LISTE DER STUNDEN

![Stunden](../monthly-work-time-calculator/Dateien/Bilder/Output_Stunden.png) 

**0,0** für die Tage ohne Arbeit.

                                        LISTE DER BESCHREIBUNGEN

![Beschreibung](../monthly-work-time-calculator/Dateien/Bilder/Output_Beschreibung.png)

**-** für die Tage ohne Arbeit.

              

**Schritt 5** - 

Sie können jetzt einfach alle sortierten Daten aus dem CLI-Ausgabebildschirm auswählen und in Excel-Tabelle kopieren


### ANWENDUNGSZWECK ??


+ Das Programm speichert keine der gelesenen Daten, weder auf der lokalen Festplatte noch auf einem Server


### FUNKTIONSWEISE DES PROGRAMMS

Um mehr über den Quellcode, die Logik und den Algorithmus von Artiner zu erfahren, klicken Sie hier - [InsideArtiner](werk.md)

### BEITRÄGE

Wenn Sie zu diesem Projekt beitragen und es mit neuen Ideen verbessern möchten oder einen Fehler melden möchten, ist Ihre Anfrage sehr willkommen. Schreiben Sie mir einfach eine E-Mail an [ABM](mailto:mfabbas@berlincert.de)


---


[zurück zu **Test-Entwicklungen**](../START.md)

[zurück zur Startseite **trial-and-error**](../../README.md)



   + [artiner.conf](../../test-development/monthly-work-time-calculator/Dateien/Sonstiges/artiner.properties) (die Standardkonfiguration Datei für Artiner)
