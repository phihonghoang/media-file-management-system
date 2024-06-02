package simulation;

import domainLogic.administration.MediaUploadableMap;
import domainLogic.media.MediaUploadableItem;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Random;

public class DeleteMedia extends Thread{
    private MediaUploadableMap model;
    private Random random;

    public DeleteMedia(MediaUploadableMap model){
        this.model = model;
        this.random = new Random();
    }

    public void run(){

        while(true) {
            synchronized(model){
                if (getRandomAddress() == null) {
                    System.out.println("Delete failed");
                } else {
                    MediaUploadableItem muiDel = model.deleteMUI(getRandomAddress());
                    System.out.println("Delete successful: " + muiDel.getAddress());
                }
            }
        }
    }


    public String getRandomAddress() {
        List<MediaUploadableItem> list = model.getMap().get("Phi").getList();

        if (list == null || list.isEmpty()) {
            return null;
        }

        int randomNumber = random.nextInt(list.size());

        return model.getMap().get("Phi").getList().get(randomNumber).getAddress();
    }

}
