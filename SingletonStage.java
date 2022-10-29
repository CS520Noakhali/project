<<<<<<< HEAD
package application;

=======
>>>>>>> 7af1e57fa7e91971d8b3acaecf989a2e459630c9
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
