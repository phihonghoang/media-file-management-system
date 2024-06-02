import cli.viewController.ViewController;
import contract.Tag;
import contract.Uploader;
import domainLogic.administration.MediaUploadableCRUD;
import domainLogic.administration.MediaUploadableMap;
import domainLogic.media.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CLI {
    public static void main(String[] args) {

        // gucken ob parsen, wenn nicht Fehlermeldung.
        // args ist mein element das geparst werden soll.

        MediaUploadableMap model = new MediaUploadableMap(50);
        ViewController vc = new ViewController(model);
        vc.execute();

        /*
        String media = "AudioVideo RedLetterMedia Animal 8700 3.60";

        ValidInputMUI vi = new ValidInputMUI();
        System.out.println(vi.validation(media));
         */

    }

}
