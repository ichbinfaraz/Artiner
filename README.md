## ARTINER - ARBEITSZEITRECHNER FUR KIMAI

![ARTINER.jpg](/Artiner.png)

### MOTIVATION

+ Um meine Arbeitsstunden im Auge zu behalten, trage ich meine Arbeitsstunden in [Kimai Zeiterfassung](https://www.kimai.org/de/) ein. 

+ Am 15. eines jeden Monats muss ich eine [Excel-Tabelle](../Excel_Muster.jpg) mit meinen Arbeitsstunden der letzten 30 Tage an meinen Vorgesetzten HLE schicken.

+ Aus diesem Grund muss ich jeden Monat die Daten aus Kimai in eine Excel-Tabelle übertragen.

+ Das Sortieren und Kopieren von Daten aus die KIMAI in eine Excel-Tabelle erfordert selbstverständlich viel Zeit und Mühe.

+ Um diese Aufgabe durch Automatisierung zu erleichtern, haben ich ein Programm entwickelt : Artiner



### ÜBER ARTINER

+ Artiner ist ein CLI-Tool, das die Daten der Arbeitsstunden aus einer [CSV-Datei](../April_Mai_Faraz.csv) (die von KIMAI exportiert wurde) ausliest und diese Daten nach Datum sortiert und in Form einer Tabelle darstellt. Diese Daten können nun einfach kopiert und in das Excel-Blatt eingefügt werden.

+ ![Unsorted](../Unsorted.png)

                                Unsorted Data in Kimai

+ ![Sorted](../Output_Tabelle.png)                  

                                    Sorted Data
    

### VORAUSSETZUNG

+ CSV-Datei sollte zugänglich sein

+ Auf dem Computer muss [Java JDK 8+](https://docs.oracle.com/en/java/javase/20/install/installation-jdk-microsoft-windows-platforms.html#GUID-A7E27B90-A28D-4237-9383-A58B416071CA) installiert sein

### ANWENDUNG


**Schritt 1- HERUNTERLADEN** 

![KIMAI](../monthly-work-time-calculator/Dateien/Bilder/KIMAI_Herunterladen.png)

Öffnen Sie [Kimai](https://www.kimai.org/de/) (auf dem obigen Bild sehen Sie das Kimai Zeiterfassungssystem der HLE. Gehen Sie auf die "Meine Zeiten" Tab und laden Sie die Daten als csv-Datei herunter 

Laden Sie nun [artiner.java](../backup.java) und [artiner.sh](../artiner.sh)

**Schritt 2- **AUSFÜHREN (Linux)**

+ Gehen Sie zu dem Ordner, in dem sich die Dateien artiner.java und artiner.sh befinden

+ Geben Sie "bash artiner.sh + die Flags" ein, um das Tool auszuführen

+ Zum Beispiel - "bash artiner.sh -i Mai.csv"


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
 
+ **-r oder --replacement :** Geben Sie den Dateipfad für die [ersatzdatei](../replacment.conf) an. Das Programm liest die Daten aus der Ersetzungsdatei und setzt sie in das Programm ein

+  **-C oder --configfile:**  Geben Sie den Dateipfad für die [Konfigurationsdatei](../artiner.conf) Datei


+ **-o oder --OutputFormat :** Geben Sie das Ausgabeformat an: "T" für nur die Liste, "TL" für  Tabelle+List  

+ **-v  --vacation :** Stunden für einen Urlaubstag berechnen(Geben Sie die Anzahl der erlaubten Urlaubstage pro Jahr an)


**Fall 3- Konfigdatei**

 
Verwenden Sie das Flag -C und geben Sie den Dateipfad der [Konfigurationsdatei](../artiner.conf) an, um das Tool mit dieser Datei zu konfigurieren

**Schritt 4- AUSGABE**- 

Das Programm gibt die sortierten Daten in dem vom Benutzer angegebenen Format aus(Das Programm berechnet oder druckt die Daten für die Pause standardmäßig nicht aus)
                  
                                           OUTPUT TABELLE

![Output](../Output_Tabelle.png) 

                                         LISTE DER STUNDEN

![Stunden](../Output_Stunden.png) 

**0,0** für die Tage ohne Arbeit.

                                        LISTE DER BESCHREIBUNGEN

![Beschreibung](../Output_Beschreibung.png)

**-** für die Tage ohne Arbeit.

              

**Schritt 5** - 

Sie können jetzt einfach alle sortierten Daten aus dem CLI-Ausgabebildschirm auswählen und in Excel-Tabelle kopieren


### ANWENDUNGSZWECK


+ Das Programm speichert keine der gelesenen Daten, weder auf der lokalen Festplatte noch auf einem Server



### BEITRÄGE

Wenn Sie zu diesem Projekt beitragen und es mit neuen Ideen verbessern möchten oder einen Fehler melden möchten, ist Ihre Anfrage sehr willkommen. Schreiben Sie mir einfach eine E-Mail an [ABM](mailto:mfabbas@berlincert.de)



