import cli.ViewController;
import domainLogic.MediaUploadableAdmin;
import eventSystem.infrastructure.*;
import eventSystem.listener.DeleteMuiListener;
import eventSystem.listener.DeleteUploaderListener;
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

        EventHandler<DeleteUploaderEvent> deleteUploaderHandler = new EventHandler<>();
        DeleteUploaderListener deleteUploaderListener = new DeleteUploaderListener(model);
        deleteUploaderHandler.add(deleteUploaderListener);

        EventHandler<DeleteMuiEvent> deleteMuiHandler = new EventHandler<>();
        DeleteMuiListener deleteMuiListener = new DeleteMuiListener(model);
        deleteMuiHandler.add(deleteMuiListener);

        MediaUploadablePersistence persistence = new MediaUploadablePersistence();
        ViewController vc = new ViewController(model, persistence);

        vc.setInsertUploaderHandler(insertUploaderHandler);
        vc.setInsertMuiHandler(insertMuiHandler);
        vc.setDeleteUploaderHandler(deleteUploaderHandler);
        vc.setDeleteMuiHandler(deleteMuiHandler);
        vc.execute();

        //AudioVideo Phi Animal 10 3.60

    }

}
