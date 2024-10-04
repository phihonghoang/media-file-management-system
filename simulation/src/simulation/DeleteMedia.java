package simulation;

import domainLogic.MediaUploadableAdmin;
import domainLogic.MediaUploadableItem;

import java.util.List;
import java.util.Random;

public class DeleteMedia extends Thread{
    private MediaUploadableAdmin model;
    private Random random;
    private String threadName;

    public DeleteMedia(MediaUploadableAdmin model, String threadName){
        this.model = model;
        this.random = new Random();
        this.threadName = threadName;
    }

    public void run(){

        while(true) {
            synchronized(model){
                if (getRandomAddress() == null) {
                    System.out.println(threadName + ": delete failed");
                } else {
                    MediaUploadableItem muiDel = model.deleteMui(getRandomAddress());
                    System.out.println(threadName + ": delete successful: " + muiDel.getAddress());
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
