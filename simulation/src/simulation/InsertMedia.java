package simulation;

import contract.Tag;
import contract.Uploader;
import domainLogic.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

public class InsertMedia extends Thread{
    private MediaUploadableAdmin model;

    public InsertMedia(MediaUploadableAdmin model) {
        this.model = model;
    }

    public void run() {

        while (true) {
            synchronized(model){
                System.out.println("Create media: " + createRandomMedia());
            }
        }
    }

    public boolean createRandomMedia() {
        Random random = new Random();

        int number = random.nextInt((3-1)+1);

        switch (number) {
            case 0:
                return model.insertMUI("Audio",new UploaderImpl("Phi"), new LinkedList<Tag>(), 1, Duration.ZERO, new BigDecimal("100"), 500, 500);

            case 1:
                return model.insertMUI("Video",new UploaderImpl("Phi"), new LinkedList<Tag>(), 1, Duration.ZERO, new BigDecimal("100"), 500, 500);

            case 2:
                return model.insertMUI("AudioVideo",new UploaderImpl("Phi"), new LinkedList<Tag>(), 1, Duration.ZERO, new BigDecimal("100"), 500, 500);

        }
        return false;
    }

}
