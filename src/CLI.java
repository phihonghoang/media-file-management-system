import cli.ViewController;
import domainLogic.MediaUploadableAdmin;
import eventSystem.infrastructure.EventHandler;
import eventSystem.infrastructure.InsertMuiEvent;
import eventSystem.infrastructure.InsertUploaderEvent;
import eventSystem.listener.InsertMuiListener;
import eventSystem.listener.InsertUploaderListener;
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

        EventHandler<InsertUploaderEvent> insertUploaderHandler = new EventHandler<>();
        InsertUploaderListener insertUploaderListener = new InsertUploaderListener(model);
        insertUploaderHandler.add(insertUploaderListener);

        EventHandler<InsertMuiEvent> insertMuiHandler = new EventHandler<>();
        InsertMuiListener insertMuiListener = new InsertMuiListener(model);
        insertMuiHandler.add(insertMuiListener);

        MediaUploadablePersistence persistence = new MediaUploadablePersistence();
        ViewController vc = new ViewController(model, persistence);

        vc.setInsertUploaderHandler(insertUploaderHandler);
        vc.setInsertMuiHandler(insertMuiHandler);
        vc.execute();

        //AudioVideo Phi Animal 10 3.60;

    }

}
