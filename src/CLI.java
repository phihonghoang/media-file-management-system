import cli.ViewController;
import domainLogic.MediaUploadableAdmin;
import io.MediaUploadablePersistence;
import observerPattern.CapacityObserver;
import observerPattern.TagsObserver;
import observerPatternContract.Observer;

public class CLI {
    public static void main(String[] args) throws InterruptedException {


        // TODO: Fehlermeldung, falls der args[0] kein long-wert ist.
        // Außen kontrollieren
        long capacity = Long.parseLong(args[0]);

        MediaUploadableAdmin model = new MediaUploadableAdmin(capacity);

        Observer capacityObserver = new CapacityObserver(model);
        Observer tagsObserver = new TagsObserver(model);
        model.registerObserver(capacityObserver);
        model.registerObserver(tagsObserver);

        MediaUploadablePersistence persistence = new MediaUploadablePersistence();
        ViewController vc = new ViewController(model, persistence);
        vc.execute();

        //AudioVideo Phi Animal 10 3.60;

    }

}
