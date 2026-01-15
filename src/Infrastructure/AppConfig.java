//  © 2K26 ❱──💀──❰ pat_mic ? code is life : life is code
package Infrastructure;

import java.net.URL;

public abstract class AppConfig {
    //Paths Storage
    public static final String DATABASE = "jdbc:sqlite:src\\DataAccess\\storage\\Databases\\MAHormiguero.sqlite";
    public static final String ANTNEST_FILE = "src\\DataAccess\\storage\\DataFiles\\AntNest.txt";
    public static final String ANTFOOD_FILE = "src\\DataAccess\\storage\\DataFiles\\AntFood.txt";
    public static final String LOGFILE  = "src\\\\DataAccess\\storage\\Logs\\AppErrors.log";


    public static final URL URL_MAIN    = AppConfig.class.getResource("Resource/logo.png");
    public static final URL URL_LOGO    = AppConfig.class.getResource("Resource/logo.png");
    public static final URL URL_SPLASH  = AppConfig.class.getResource("/Infrastructure/Assets/Img/Splash.png");

    //AppMSGs
    public static final String MSG_DEFAULT_ERROR    = "Ups! Error inesperado. Por favor, contacte al administrador del sistema.";
    public static final String MSG_DEFAULT_CLASS    = "undefined";
    public static final String MSG_DEFAULT_METHOD   = "undefined";

    private AppConfig() {}
}
