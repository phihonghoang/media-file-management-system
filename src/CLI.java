import cli.ViewController;
import domainLogic.MediaUploadableMap;
import io.MediaUploadablePersistence;

public class CLI {
    public static void main(String[] args) {

        // TODO: Fehlermeldung, falls der args[0] kein long-wert ist.
        // Außen kontrollieren
        long capacity = Long.parseLong(args[0]);

        MediaUploadableMap model = new MediaUploadableMap(capacity);
        MediaUploadablePersistence persistence = new MediaUploadablePersistence();
        ViewController vc = new ViewController(model, persistence);
        vc.execute();

        //AudioVideo Phi Animal 10 3.60";

    }

}
