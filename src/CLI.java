import cli.ViewController;
import domainLogic.MediaUploadableAdmin;
import eventSystem.infrastructure.*;
import eventSystem.listener.*;
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

        EventHandler<UpdateMuiEvent> updateMuiHandler = new EventHandler<>();
        UpdateMuiListener updateMuiListener = new UpdateMuiListener(model);
        updateMuiHandler.add(updateMuiListener);

        EventHandler<DisplayUploaderEvent> displayUploaderHandler = new EventHandler<>();
        DisplayUploaderListener displayUploaderListener = new DisplayUploaderListener(model);
        displayUploaderHandler.add(displayUploaderListener);

        EventHandler<DisplayContentEvent> displayContentHandler = new EventHandler<>();
        DisplayContentListener displayContentListener = new DisplayContentListener(model);
        displayContentHandler.add(displayContentListener);

        EventHandler<DisplayTagEvent> displayTagHandler = new EventHandler<>();
        DisplayTagListener displayTagListener = new DisplayTagListener(model);
        displayTagHandler.add(displayTagListener);

        EventHandler<SaveJosEvent> saveJosHandler = new EventHandler<>();
        SaveJosListener saveJosListener = new SaveJosListener(model);
        saveJosHandler.add(saveJosListener);

        EventHandler<LoadJosEvent> loadJosHandler = new EventHandler<>();
        LoadJosListener loadJosListener = new LoadJosListener(model);
        loadJosHandler.add(loadJosListener);
        
        ViewController vc = new ViewController();

        vc.setInsertUploaderHandler(insertUploaderHandler);
        vc.setInsertMuiHandler(insertMuiHandler);
        vc.setDeleteUploaderHandler(deleteUploaderHandler);
        vc.setDeleteMuiHandler(deleteMuiHandler);
        vc.setUpdateMuiHandler(updateMuiHandler);
        vc.setDisplayUploaderHandler(displayUploaderHandler);
        vc.setDisplayContentHandler(displayContentHandler);
        vc.setDisplayTagHandler(displayTagHandler);
        vc.setSaveJosHandler(saveJosHandler);
        vc.setLoadJosHandler(loadJosHandler);
        vc.execute();

        //AudioVideo Phi Animal 10 3.60
    }

}
