# IMDB-JavaFX-Database
A database managing software with GUI, written in Java, using JavaFX API and JDBC.
# 
Egy adatbázis kezelő szoftver grafikus megjelenítő felülettel, Java-ban írva, JavaFX API és JDBC használatával.


Adatbázisom filmek tárolására lett lefejlesztve, melyben megtalálhatók egy-egy film adatai, szereplői, 
azokról több információ, a készítő filmstúdiók. Az adatbázis módosítható, vehetők fel újabb adatok, 
folyamatosan bővíthető egy-egy új megjelenéssel, illetve az azt jellemző információkkal. Emellett az 
adatbázisom webböngészőből elérhető, kezelhető és módosítható. 

## Egyed-kapcsolat modell
![image](https://user-images.githubusercontent.com/86327017/169144707-98ba52b8-8483-4fe1-b7cf-8850f52ac742.png)



## Relációs adatbázisséma 
FILM(filmid, studioid, cím, megjelenés, értékelés, hossz, költség, Oscar-díjak száma) 
SZÍNÉSZ(szineszid, név, születési dátum, életkor, elhunyt) 
FILMSTÚDIÓ(studioid, név, alapítási dátum, Oscar-díjak száma) 
SZEREPLÉSEK(szineszid, filmid, szerep, típus) 
MŰFAJOK(filmid, műfaj) 
DÍJAK(szineszid, díj, darab) 
 
 
## Normalizálás 
Az adatbázisban minden tábla 3NF-ben van, így nem kellett normalizálni. A diagram és a sémák 
létrehozásakor kellően el tudtam különíteni a táblákat, egyedeket. 


## Megvalósítási környezet 
A program Java nyelvben készült, a megjelenítésért a JavaFX, az adatbázissal történő kommunikációért a 
JDBC függvénykönyvtár felelős. Az adatbáziskezelő nyelv a MySQL. 


## A program szolgáltatásai 
### Alapfunkciók:

*A következő alapfunkciók minden táblára meg vannak valósítva.
- Az adatbázis tábláinak megjelenítése. 
Választható menüsorból az adott tábla, ahol rá lehet keresni a rekordokra, majd azokra 
rákattintva meg is jeleníti őket a program táblázatosan. 
- Adatok felvitele az adatbázisba. 
Minden tábla alján található egy Adminisztráció gomb, ami bedob egy felugró ablakot, ahol 
adatokat vehetünk fel az adott táblába. 
- Rekordok törlése. 
Ugyanúgy az Adminisztrációs ablakban törölhetjük is a rekordokat, elsődleges kulcs szerint. Ez fel 
van tüntetve az alkalmazásban Módosítandó ID bemeneti mezőként. 
- Rekordok módosítása. 
Szintén az Adminisztrációs ablakban módosíthatunk a Módosítandó ID szerint. Ha bizonyos 
attribútumokat nem szeretnénk megváltoztatni, üresen lehet hagyni a mezőt. 
- A módosítások után rá kell frissíteni a táblára, hogy látszódjanak a változtatások, azaz újra rá 
kell kattintani a tábla nevére a felső menüsorban. 
Extra funkciók: 
- A könnyebb átláthatóság kedvéért az adminisztrációs panel külön ablakban jelenik meg. 
- Menüsor a táblák közötti váltásra. 
- Az olyan táblákban, ahol egyébként csak id-k találhatók, megjelenít a program egy-egy ahhoz 
tartozó nevet is, így sokkal átláthatóbb, a kapcsolatok egyértelműbbek. Erre a leglátványosabb 
példa a Szereplések tábla megjelenítése. 
- Java nyelv használata, függvénykönyvtárral. 
- Több mint 100 kézzel felvitt adat. 
- 6 összefüggő tábla. 
- Nemtriviális lekérdezések meg vannak jelenítve az alkalmazásban. 
 

## Három nemtriviális lekérdezés 
1. lekérdezés: Az adatbázisban szereplő műfajok és az adott műfajú filmek átlagos 
értékelése, műfajok szerint csoportosítva, értékelés szerint rendezve csökkenő 
sorrenben.
```
SELECT mufaj, AVG(ertekeles) 
FROM mufajok, film 
WHERE mufajok.filmid = film.filmid 
GROUP BY mufaj 
ORDER BY AVG(ertekeles) DESC; 
```
2. lekérdezés: A színészek díjainak száma, név szerint csoportosítva, díjak száma szerinti csökkenő sorrendben:
```SELECT nev, SUM(darab) 
FROM szinesz, dijak 
WHERE szinesz.szineszid = dijak.szineszid 
GROUP BY nev 
ORDER BY SUM(darab) DESC;
```
3. lekérdezés: A 2017-es év legmagasabb értékelésű filmjének főszereplőjét alkotó színész: 
```
SELECT szinesz.nev 
FROM szinesz,szereplesek,film 
WHERE szinesz.szineszid = szereplesek.szineszid and szereplesek.filmid = film.filmid and 
szereplesek.tipus = 'főszereplő' and film.ertekeles = ( 
 SELECT max(ertekeles) 
 FROM film 
 WHERE megjelenes BETWEEN '2017-01-01' and '2017-12-31'); 
 ```


## Használati útmutató 
### Az alkalmazás felépítése: 
Az alkalmazás tetején megtalálhatjuk a Táblaválasztó menüsort. Itt lehet kiválasztani, hogy melyik tábla 
rekordjait szeretnénk látni, illetve lehetőségünk van a speciális lekérdezéseket is megjeleníttetni a 
programmal *(Lekérdezések gomb)*. 
Az alkalmazás bal oldalán a kereső-, kiválasztó mező található. A kereső segítségével kereshetünk a 
rekordok között. A kiválasztómezőben, ha rákattintunk egy számunkra megtetsző névre, akkor a 
program betölti az ahhoz a névhez tartozó összes attribútumot a program jobb oldalán lévő táblázatban. 
A jobb oldalon lévő táblázat megjeleníti a rekordhoz tartozó további attribútumokat, illetve néhány 
tábla esetén egyéb, a megértést segítő, egyéb attribútumokat is. Az oszlopok, attribútumok sorrendje 
felcserélhető, ha az egérrel arrébb húzzuk a címkét. 
A program alsó részében megtalálható az Adminisztráció gomb, ami az Adminisztációs panelt hozza be. 
Az Adminisztrációs panel felépítése: 
A panel felső sorában találhatók a funkciógombok. Ezeket akkor szabad megnyomni, ha minden 
szükséges adatot felvittünk, különben a program nem tesz semmit. A gombok: 

- Szerkesztés: Egy módosítandó ID alapján, szerkeszti az ahhoz tartozó rekordokat a 
mezőkbe megadott adatokkal. Az ID-t is lehet szerkeszteni a mellette lévő ID mezőbe 
történő írással. 
- Törlés: Egy módosítandó ID alapján kitörli az ID-hez tartozó összes rekordot, az ID-vel 
együtt, a táblából. 
- Hozzáadás: Hozzáad a táblához egy új rekordot a mezőkbe beírt attribútumokat 
tartalmazva. Itt nem kell használni a Módosítandó ID mezőt. 
Miután megtörténtek a változtatások, bezárjuk a panelt, majd újra kell tölteni az adott táblát. Ezt 
legegyszerűbben úgy tehetjük meg, hogy újra rákattintunk a tábla gombjára a Táblaválasztó 
menüsorból. 

## A projekt tartalma
- Kotprog: A kötelező program forráskódját tartalmazza. 
- Dokumentacio: A program dokumentációja. 
- Imdb.dia: Az adatbázis EK-diagramja. 
- Imdb.sql: Az adatbázis sql fájlba kiexportálva. 

## A program helyes működéséhez szükség van továbbá:
- Egy javafx sdk-ra (A projekt készültekor ennek verziója 17.0.1)
- Egy mysql connector-ra (8.0.27)
