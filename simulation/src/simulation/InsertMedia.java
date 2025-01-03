package simulation;

import contract.Tag;
import domainLogic.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Random;

public class InsertMedia extends Thread{
    private MediaUploadableAdmin model;
    private String threadName;

    public InsertMedia(MediaUploadableAdmin model, String threadName) {
        this.model = model;
        this.threadName = threadName;
    }

    public void run() {

        while (true) {
            synchronized(model){
                System.out.println(threadName + " created media: " + createRandomMedia());
            }
        }
    }

    public boolean createRandomMedia() {
        Random random = new Random();

        int number = random.nextInt((3-1)+1);

        switch (number) {
            case 0:
                return model.insertMui("Audio",new UploaderImpl("Phi"), new LinkedList<Tag>(),
                        1, Duration.ZERO, new BigDecimal("100"), 500, 500, LocalDateTime.now());

            case 1:
                return model.insertMui("Video",new UploaderImpl("Phi"), new LinkedList<Tag>(),
                        1, Duration.ZERO, new BigDecimal("100"), 500, 500, LocalDateTime.now());

            case 2:
                return model.insertMui("AudioVideo",new UploaderImpl("Phi"), new LinkedList<Tag>(),
                        1, Duration.ZERO, new BigDecimal("100"), 500, 500, LocalDateTime.now());

        }
        return false;
    }

}
