package application;

import javafx.stage.Stage;

public class SingletonStage extends Stage {

    private static SingletonStage stage = null;

    private SingletonStage() {

    }

    public static SingletonStage getInstance() {
        if (stage == null) {
            stage = new SingletonStage();
        }
        return stage;
    }

}
