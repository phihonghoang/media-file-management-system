import cli.viewController.ViewController;
import contract.Tag;
import contract.Uploader;
import domainLogic.administration.MediaUploadableCRUD;
import domainLogic.administration.MediaUploadableMap;
import domainLogic.media.*;
import io.MediaUploadablePersistence;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CLI {
    public static void main(String[] args) {

        // TODO: Fehlermeldung, falls der args[0] kein long-wert ist.
        long capacity = Long.parseLong(args[0]);

        MediaUploadableMap model = new MediaUploadableMap(capacity);
        MediaUploadablePersistence persistence = new MediaUploadablePersistence();
        ViewController vc = new ViewController(model, persistence);
        vc.execute();

        //AudioVideo Phi Animal 10 3.60";

    }

}
