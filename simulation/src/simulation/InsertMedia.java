package simulation;

import contract.Tag;
import contract.Uploader;
import domainLogic.administration.MediaUploadableCRUD;
import domainLogic.administration.MediaUploadableMap;
import domainLogic.media.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

public class InsertMedia extends Thread{
    private MediaUploadableMap model;

    public InsertMedia(MediaUploadableMap model) {
        this.model = model;
    }

    public void run() {

        while (true) {
            synchronized(model){
                boolean res = model.insertMUI("Phi", createRandomMedia());
                System.out.println("Create media: " + res);
            }
        }
    }

    public MediaUploadableItem createRandomMedia() {
        Random random = new Random();

        int number = random.nextInt((3-1)+1);

        switch (number) {
            case 0:
                return  new AudioImpl(new ArrayList<>(), 1, new UploaderImpl("Phi"), new BigDecimal("100"), 500);

            case 1:
                return new VideoImpl(new ArrayList<>(),  2, new UploaderImpl("Phi"), new BigDecimal("100"), 500);

            case 2:
                return new AudioVideoImpl(new ArrayList<>(), 3, new UploaderImpl("Phi"), new BigDecimal("100"), 500, 500);

        }
        return null;
    }

}
