# VAVA-ABSolutePower
## 1. Kolekcie  <br /><br />
Sa používajú vo viacerých miestach v kóde. Kolekcie väčšinou sú vracane databázou ako Response. Jednými z najdôležitejších sú Zoznamy workoutov pre usera ktorý si otvory v aplikácii moje Workouty, cviká, ktoré sa vyberajú pri vytvorení workoutu Športovcom alebo Trénerom a zoznam športovcov ktorí majú na starosti jednotliví tréneri v aplikácii. <br />***Príklad:***<br /><br />
```
public final class Workout {    private String name;
    private Integer owner;
    private String description;
    private LocalDateTime scheduled_for;
    private ArrayList<Exercise> exercises;
 ```
<br /><br />
## 2. Logovanie <br /><br />

Logovanie v našej aplikácii sa používa na zaznamenávanie podozrivej aktivity (pokus o SQL injection keď niekto zadá do prihlasovacieho formulára neplatne znaky a atď.. ), informácii o prihlásení, registrácii, zmeny osobných údajov používateľa. Všetky udalosti budú zaznamenane v tabuľke databázy v tvare
<br /> 'aktivita' + 'time' + 'user_id'<br /> 
Funkcionalita pre logovanie je implementovaná v 'Database.java'.<br /><br />
```
 public void log(String activity, int user_id) {
        System.out.println("Pridavam log: " + activity + ", user: " + user_id);
        Timestamp time = Timestamp.valueOf(LocalDateTime.now());

        this.safeExecuteSQL(
                "INSERT INTO user_activity_logs (activity, time, user_id) VALUES\n" +
                        "(?, ?, ?)", activity, time, user_id);
    }

    public void log(String activity, String login) {
        ResultSet rs = this.safeExecuteSQL(
                "SELECT id\n" +
                        "FROM users\n" +
                        "WHERE login = ?", login);
```

## 3. Lokalizácia<br /><br /> 

Preklad a lokalizácia je vytvorená pre ENG a SK jazyky je implementovaná pomocou ResourceBundle. @TODO v daných súboroch sa nachádzajú všetky preklady nadpisov a textov. Jazyk pre užívateľa sa ukladá do profilu usera v Databáze ako predvolený jazyk aby pri budúcom prihlásení si to nemusel meniť ak nechce používať predvolený SK Language. Načítavanie je zakomponovane v fxmlLoadery kde sa získa jazyk používateľa a načíta sa obrazovka.<br /><br />



## 6. JDBC pripojenie na databazu<br /><br />
V projekte používame JDBC Driver na pripojenie k PostgreSQL databáze, ktorý sme stiahli z linku https://jdbc.postgresql.org/download/postgresql-42.3.4.jar (konektor sa nachádza aj v priečinku "databáza")<br />

Driver je potrebné importovať do projektu následovne (IntelliJ): File > Project Structure... > Modules > + > JARs or Directories... > [treba vybrať .jar súbor JDBC drivera] > [zvoliť pridaný modul v zozname modulov] > OK<br /><br />


## 7.<br /><br />


## 8.GUI<br /><br />

Grafické rozhranie je implementovane pomocou Java FX z použitím Scene Builder. Každá obrazovka ma svoj súbor FXML. Obrazovka hlavného menu je rozdelená na 2 časti. Na ľavej vždy sa nachádza menu pre Usera aby sa vedel rýchlo dostáť z jednej obrazovky na inú.<br /><br />

## 9.Pouzivatelia <br /><br />

### Nasa aplikacia obsahuje 3ch pouzivatelov:<br> Sportsman(Sportovec), Admin/Verifikator a Trainer(Trener).<br><br>
Sportovec je klasicky pouzivatel ktory moze pridavat, odstranovat, vytvarat workouty a posielat ziadosti na zostavu workoutov Trenerom.Ak je sportovec kvalifikovany na Trenera - moze si poziadat o zmenu uctu na Trenera.<br>
Ulohou Admina/Verifikatora je shcvalovanie ziadosti od Sportovcov <br>
Trainer ma a starosti sportovcov ktori si poziadali a boli prijati do timu. Dokaze pridelovat workouty pre sportovcov a upravovat ich <br>
![This is an image](/UML/01 UC Diagram.png)
