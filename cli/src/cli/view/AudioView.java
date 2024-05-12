package cli.view;

import domainLogic.media.AudioImpl;

import java.util.List;

public class AudioView {

    public void insertion(boolean insertionResult) {
        result(insertionResult);
    }

    public void delete(boolean deleteResult) {
        result(deleteResult);
    }

    public void display(List<AudioImpl> list) {
        for (AudioImpl audio : list) {
            System.out.println("Uploader: " + audio.getUploader().getName()
                    + " ,Address: " + audio.getAddress());
        }
    }

    public void update(boolean updateResult) {
        result(updateResult);
    }

    public void persistence() {
        System.out.println("persistence mode");
    }

    public void result(boolean resultValue) {
        if (resultValue) {
            System.out.println("\n" + "success!");
        } else {
            System.out.println("\n" + "fail!");
        }
    }

}
