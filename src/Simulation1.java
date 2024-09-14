import domainLogic.MediaUploadableCRUD;
import domainLogic.MediaUploadableAdmin;
import simulation.DeleteMedia;
import simulation.InsertMedia;

public class Simulation1 {
    public static void main(String[] args) {

        // TODO: Fehlermeldung, falls der args[0] kein long-wert ist.
        long capacity = Long.parseLong(args[0]);

        MediaUploadableAdmin model = new MediaUploadableAdmin(capacity);
        model.insertUploader("Phi");

        InsertMedia im = new InsertMedia(model);
        DeleteMedia dm = new DeleteMedia(model);

        im.start();
        dm.start();

    }
}
