import cli.ViewController;
import domainLogic.MediaUploadableAdmin;
import io.MediaUploadablePersistence;

import java.time.Duration;
import java.time.LocalTime;

public class CLI {
    public static void main(String[] args) throws InterruptedException {


        // TODO: Fehlermeldung, falls der args[0] kein long-wert ist.
        // Außen kontrollieren
        long capacity = Long.parseLong(args[0]);

        MediaUploadableAdmin model = new MediaUploadableAdmin(capacity);
        MediaUploadablePersistence persistence = new MediaUploadablePersistence();
        ViewController vc = new ViewController(model, persistence);
        vc.execute();

        //AudioVideo Phi Animal 10 3.60;

    }

}
