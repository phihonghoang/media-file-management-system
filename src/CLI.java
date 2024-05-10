import cli.controller.AudioController;

public class CLI {
    public static void main(String[] args) {
        AudioController audioController = new AudioController();
        audioController.start();
    }
}
