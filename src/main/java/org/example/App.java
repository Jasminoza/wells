package org.example;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.example.view.MainView;
import org.flywaydb.core.Flyway;

public class App {
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";

    public static void main(String[] args) {
        migrateFlyway();

        MainView mainView = new MainView();
        mainView.mainMenu();
    }

    private static void migrateFlyway() {
        Config config = ConfigFactory.load();

        Flyway flyway = Flyway
                .configure().dataSource(
                        config.getString(DB_URL),
                        config.getString(DB_USER),
                        config.getString(DB_PASSWORD)
                ).load();

        flyway.repair();
        flyway.migrate();
    }
}
