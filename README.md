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
```
private void change_language(MouseEvent mouseEvent)
    {
        Integer n = Math.toIntExact(Math.round(menu_lang.getValue()));
        String current_lang = app.getUser().getLanguage();

        if ((n == 0 && current_lang.equals("lang_en")) || (n == 1 && current_lang.equals("lang_sk")))
        {
            app.update_language(n);
            app.changeWindow("main_view-" + this.current_page);
        }
    }
```



## 6. JDBC pripojenie na databazu<br /><br />
V projekte používame JDBC Driver na pripojenie k PostgreSQL databáze, ktorý sme stiahli z linku https://jdbc.postgresql.org/download/postgresql-42.3.4.jar (konektor sa nachádza aj v priečinku "databáza")<br />

Driver je potrebné importovať do projektu následovne (IntelliJ): File > Project Structure... > Modules > + > JARs or Directories... > [treba vybrať .jar súbor JDBC drivera] > [zvoliť pridaný modul v zozname modulov] > OK<br /><br />
```
private Connection connect() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://vava-mightygainz.cfjpdf44uln2.us-east-1.rds.amazonaws.com:5432/VAVA_MightyGainz_db", "xoross", "vava.G4inz");
            //System.out.println("Uspesne som sa pripojil k databaze");
        }
        catch (SQLException exSQL) {
            System.out.println("Nepodarilo sa pripojit k databaze: " + exSQL.getMessage());
        }
        return null;
    }
PreparedStatement stmt = conn.prepareStatement(query);
```


## 7.Pre ošetrovanie voči SQL injection používame Prepare Statement Metódu na rozdeľovanie a ošetrovanie vstupov. Funkcia si to preverí a skonvertuje do správneho typu a následne si zavolá Query.<br><br>
```
private ResultSet safeExecuteSQL(String query, Object... variables) {
        if (conn == null) {
            System.out.println("Aplikacia nieje pripojena k databaze, preto nevykonala nasledovne query: " + query);
            return null;
        }

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            int i = 1;
            for (Object variable : variables) {
                System.out.println("obj[" + i + "] type: " + variable.getClass());

                String cls = variable.getClass().toString();

                if(cls.equals("class java.lang.String"))
                    stmt.setString(i, (String)variable);
                else if(cls.equals("class java.lang.Integer"))
                    stmt.setInt(i, (Integer) variable);
                else if(cls.equals("class java.sql.Timestamp"))
                    stmt.setTimestamp(i, (Timestamp) variable);
                //stmt.setString(i, variable);
                i++;
            }
            return stmt.executeQuery();

        } catch (SQLException exSQL) {
            System.out.println("Chyba pocas vykonavania query: " + exSQL.getMessage());
        }
        //System.out.println(stmt);

        return null;
    }
```



## 8.GUI<br /><br />

Grafické rozhranie je implementovane pomocou Java FX z použitím Scene Builder. Každá obrazovka ma svoj súbor FXML. Obrazovka hlavného menu je rozdelená na 2 časti. Na ľavej vždy sa nachádza menu pre Usera aby sa vedel rýchlo dostáť z jednej obrazovky na inú. Každá obrazovka má vlastný controller, ktorý obsahuje dynamické prvky zobrazované v hlavnom okne a metódy použité na spracovanie vstupov (event handlers). <br /><br />

```
public final class LoginController extends Controller {
    @FXML private Text text_username;
    @FXML private TextField textField_username;
    @FXML private Text text_password;
    @FXML private PasswordField passwordField_password;
    @FXML private Button button_login;
    @FXML Button button_back;
    
    public LoginController(App app) {
        super(app);
    }

    @FXML protected void initialize() {
        button_login.setOnAction(this::login);
        button_back.setOnAction(this::go_back);
    }

    private void login(ActionEvent event) {
        String login = textField_username.getText();
        String passwordHash = Password.getHash(passwordField_password.getText());

        if(app.login(login, passwordHash)) app.changeWindow("main_view-homepage");
    }

    private void go_back(ActionEvent event) {
        app.changeWindow("welcome");
    }
}
```
## 9.Pouzivatelia <br /><br />

 Nasa aplikacia obsahuje 3ch pouzivatelov:<br> Sportsman(Sportovec), Admin/Verifikator a Trainer(Trener).<br><br>
Sportovec je klasicky pouzivatel ktory moze pridavat, odstranovat, vytvarat workouty a posielat ziadosti na zostavu workoutov Trenerom.Ak je sportovec kvalifikovany na Trenera - moze si poziadat o zmenu uctu na Trenera.<br>
Ulohou Admina/Verifikatora je shcvalovanie ziadosti od Sportovcov <br>
Trainer ma a starosti sportovcov ktori si poziadali a boli prijati do timu. Dokaze pridelovat workouty pre sportovcov a upravovat ich <br>

## Use cases diagrams <br />
![This is an image](https://github.com/Tomi5548319/VAVA-ABSolutePower/blob/main/UML/01%20UC%20Diagram.png)
<br>
![This is an image](https://github.com/Tomi5548319/VAVA-ABSolutePower/blob/main/UML/02%20UC%20Diagram.png)
<br>

## Process diagrams<br />
![This is an image](https://github.com/Tomi5548319/VAVA-ABSolutePower/blob/main/UML/Edit%20account_process.png)
<br>
![This is an image](https://github.com/Tomi5548319/VAVA-ABSolutePower/blob/main/UML/Creating%20workout_process.png)
<br>
![This is an image](https://github.com/Tomi5548319/VAVA-ABSolutePower/blob/main/UML/Login_process.png)
<br>


## Activity diagrams<br />
![This is an image](https://github.com/Tomi5548319/VAVA-ABSolutePower/blob/main/UML/Logging%20in_seqense.png)
<br>
![This is an image](https://github.com/Tomi5548319/VAVA-ABSolutePower/blob/main/UML/Creating%20workout_sequence.png)
<br>
![This is an image](https://github.com/Tomi5548319/VAVA-ABSolutePower/blob/main/UML/Request%20for%20training_sequence.png)
<br>
