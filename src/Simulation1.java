import domainLogic.MediaUploadableCRUD;
import domainLogic.MediaUploadableMap;
import simulation.DeleteMedia;
import simulation.InsertMedia;

public class Simulation1 {
    public static void main(String[] args) {

        // TODO: Fehlermeldung, falls der args[0] kein long-wert ist.
        long capacity = Long.parseLong(args[0]);

        MediaUploadableMap model = new MediaUploadableMap(capacity);
        model.insertUploader("Phi", new MediaUploadableCRUD());

        InsertMedia im = new InsertMedia(model);
        DeleteMedia dm = new DeleteMedia(model);

        im.start();
        dm.start();

    }
}
