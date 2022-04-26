# VAVA-ABSolutePower
**1. Kolekcie **<br /><br />
Sa používajú vo viacerých miestach v kóde. Kolekcie väčšinou sú vracane databázou ako Response. Jednými z najdôležitejších sú Zoznamy workoutov pre usera ktorý si otvory v aplikácii moje Workouty, cviká, ktoré sa vyberajú pri vytvorení workoutu Športovcom alebo Trénerom a zoznam športovcov ktorí majú na starosti jednotliví tréneri v aplikácii. <br />***Príklad:***<br /><br />
```
public final class Workout {    private String name;
    private Integer owner;
    private String description;
    private LocalDateTime scheduled_for;
    private ArrayList<Exercise> exercises;
 ```
<br /><br />
**2. Logovanie**<br /><br />Logovanie v našej aplikácii sa používa na zaznamenávanie podozrivej aktivity (pokus o SQL injection keď niekto zadá do prihlasovacieho formulára neplatne znaky a atď.. ), informácii o prihlásení, registrácii, zmeny osobných údajov používateľa. Všetky udalosti budú zaznamenane v tabuľke databázy v tvare<br /> 'aktivita' + 'time' + 'user_id'.<br /> Funkcionalita pre logovanie je implementovaná v 'Database.java'.<br />
**3. Lokalizácia**<br /> Preklad a lokalizácia je vytvorená pre ENG a SK jazyky je implementovaná pomocou ResourceBundle. @TODO v daných súboroch sa nachádzajú všetky preklady nadpisov a textov. Jazyk pre užívateľa sa ukladá do profilu usera v Databáze ako predvolený jazyk aby pri budúcom prihlásení si to nemusel meniť ak nechce používať predvolený SK Language. Načítavanie je zakomponovane v fxmlLoadery kde sa získa jazyk používateľa a načíta sa obrazovka./n
**4.<br /> **5.<br />**6.<br /><br />**7.<br /><br />**8.GUI**/nGrafické rozhranie je implementovane pomocou Java FX z použitím Scene Builder. Každá obrazovka ma svoj súbor FXML. Obrazovka hlavného menu je rozdelená na 2 časti. Na ľavej vždy sa nachádza menu pre Usera aby sa vedel rýchlo dostáť z jednej obrazovky na inú.**9.Pouzivatelia**/n Nasa aplikacia obsahuje 3ch pouzivatelov: Sportsman(Sportovec), Admin/Verifikator a Trainer(Trener)./n>Sportovec je klasicky pouzivatel ktory moze pridavat, odstranovat, vytvarat workouty a posielat ziadosti na zostavu workoutov Trenerom.Ak je sportovec kvalifikovany na Trenera - moze si poziadat o zmenu uctu na Trenera.
>Ulohou Admina/Verifikatora je shcvalovanie ziadosti od Sportovcov 
>Trainer ma a starosti sportovcov ktori si poziadali a boli prijati do timu. Dokaze pridelovat workouty pre sportovcov a upravovat ich
