import cli.ViewController;
import domainLogic.administration.MediaUploadableCRUD;

public class CLI {
    public static void main(String[] args) {
        /*

        AudioView audioView = new AudioView();
        AudioController audioController = new AudioController(audioModel, audioView);

        audioController.start();
         */

        MediaUploadableCRUD model = new MediaUploadableCRUD();
        ViewController vc = new ViewController(model);

        vc.execute();

    }
}
