import cli.controller.AudioController;
import cli.view.AudioView;
import domainLogic.administration.AudioAdmin;

public class CLI {
    public static void main(String[] args) {

        AudioAdmin audioModel = new AudioAdmin();
        AudioView audioView = new AudioView();
        AudioController audioController = new AudioController(audioModel, audioView);

        audioController.start();
    }
}
