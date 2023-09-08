#!/usr/bin/env bash

set -euo pipefail

echo

datum=$(date)
pfad=$(pwd)
javaV=$(java --version)
bool=$#
config=unset
anfng=unset
letze=unset
count=0
taskf=0
pause=x
replf=0
input=unset
outfm=unset
urlbt=unset
javab=${javaV:0:7}



nojava(){
>&2 cat << EOF

Artiner kann nicht ausgeführt werden, da Java JDK auf diesem Computer nicht installiert ist

EOF
exit 1
}


usage(){
>&2 cat << EOF

ANWENDUNG-ARTINER

AUFRUF: bash artiner.sh [OPTIONEN]

Option          Lange Option                Bedeutung

-f <jjjj-mm-tt> --firstdate                 Das vom Benutzer angegebene Datum als erstes Datum des Berechnungszeitraums festlegen
-l <jjjj-mm-tt> --lastdate                  Das vom Benutzer angegebene Datum als letztes Datum des Berechnungszeitraums festlegen
-c              --countpause                Auch "Pause" berechnen
-F <Verz>       --taskfilter                Nur den Bericht für bestimmte Tätigkeiten anzeigen (die in taskfilter.txt angegeben sind)
-h              --help                      Zeigt das Hilfemenü an
-p              --pauseonly                 Nur den Bericht für die "Pause" ausgeben
-r <Verz>       --replacements-file        Geben Sie den Dateipfad für die ersatzdatei an. Das Programm liest die Daten aus der
                                            Ersetzungsdatei und setzt sie in das Programm ein
-C <Verz>       --configfile               Geben Sie den Dateipfad für die artiner.conf Datei
-i <Verz>       --input-file                Geben Sie den Dateipfad für die csv-Datei an
-o <Auswahl>    --output-format             Geben Sie das Ausgabeformat an: "Alles" für Table + Liste; "Tabelle" für nur Tabelle

-v <Nummer>     --vacation                  Stunden für einen Urlaubstag berechnen und drucken (Anzahl der erlaubten Urlaubstage pro
                                            Jahr angeben)

EOF
exit 1
}

if [ ! $javab = "openjdk" ]; then
nojava
fi


args=$(getopt -a -o f:l:cF:hpr:C:i:o:v: --long configfile:,firstdate:,lastdate:,countpause,taskfilter:,help,pauseonly,replacements-file:,input-file:,output-format:,vacation: -- "$@")
if [[ $? -gt 0 ]]; then
  usage
fi

eval set -- ${args}
while :
do
  case $1 in
    -f | --firstdate)         anfng="f"$2   ; shift 2 ;;
    -l | --lastdate)          letze="l"$2   ; shift 2 ;;
    -h | --help)              usage         ; shift   ;;
    -c | --countpause)        count="c"     ; shift   ;;
    -C | --configfile)        config="C"$2   ; shift 2 ;;
    -f | --taskfilter)        taskf=f1   ; shift   ;;
    -p | --pauseonly)         pause=p1   ; shift   ;;
    -r | --replacementsfile)  repld="r"$2   ; shift 2  ;;
    -i | --input-file)        input="i"$2   ; shift 2 ;;
    -o | --output-format)     outfm="o"$2   ; shift 2 ;;
    -v | --vacation)          urlbt="v"$2   ; shift 2 ;;
    # -- means the end of the arguments; drop this, and break out of the while loop
    --) shift; break ;;
  esac
done


if [[ $#>0 ]]; then
  if [[ $1=$* ]]; then
    echo Unbekannte Option: "<$*>" ; echo; echo "-h oder --help für hilfe Menu" ; exit 0
  fi
fi


#if [ -f artiner.conf ]; then
     #if [ $bool -eq 0 ]; then
      # read -p " Möchten Sie das Programm mit den Standardwerten aus artiner.conf ausführen?(Ja/Nein): " prompt
        # if [ $prompt = "Ja" ]; then
       #    java artiner.java noflags
      #   fi
     #else
      # java artiner.java  $anfng $letze $count $taskf $pause $replf $input $outfm $urlbt
     #fi
#else
   if [ $bool -eq 0 ]; then
      read -p "Keine Flags. Möchten Sie das Hilfemenü sehen[Y][n]:" prompt
         if [ $prompt = "n" ]; then
           exit 0
         else
           usage
         fi
   else
      java temp.java $anfng $config $letze $count $taskf $pause $replf $input $outfm $urlbt
   fi

exit 0
