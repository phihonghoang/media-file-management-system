package eventSystem.listener;

import domainLogic.*;
import eventSystem.infrastructure.EventListener;
import eventSystem.infrastructure.LoadJosEvent;
import io.MediaUploadablePersistence;

import java.util.Set;

public class LoadJosListener implements EventListener<LoadJosEvent> {
   private MediaUploadableAdmin model;
   private MediaUploadablePersistence persistence;

   public LoadJosListener(MediaUploadableAdmin model) {
       this.model = model;
       this.persistence = new MediaUploadablePersistence();
   }

    @Override
    public String onEvent(LoadJosEvent event) {
        String filename = "MediaUploadable.jos";

        MediaUploadableAdmin modelPersistence = persistence.load(filename);

        if (modelPersistence != null) {
            Set<String> uploaderList = model.getMap().keySet();
            uploaderList.forEach(uploader -> model.deleteUploader(uploader));

            insert(modelPersistence);
        }

        return "";
    }

    // TODO: In util packen eventuell
    public void insert(MediaUploadableAdmin mapPersistence) {

       for (String uploader : mapPersistence.getMap().keySet()) {

           model.insertUploader(uploader);

           for (MediaUploadableItem mui : mapPersistence.getMap().get(uploader).getList()) {

               if (mui instanceof AudioImpl) {
                   model.insertMui(mui.getMediaType(), mui.getUploader(), mui.getTags(), mui.getSize(),
                           mui.getAvailability(), mui.getCost(), ((AudioImpl) mui).getSamplingRate(), 0, mui.getUploadTime());
               } else if (mui instanceof VideoImpl) {
                   model.insertMui(mui.getMediaType(), mui.getUploader(), mui.getTags(), mui.getSize(),
                           mui.getAvailability(), mui.getCost(), ((VideoImpl) mui).getResolution(), 0, mui.getUploadTime());
               } else if (mui instanceof AudioVideoImpl) {
                   model.insertMui(mui.getMediaType(), mui.getUploader(), mui.getTags(), mui.getSize(),
                           mui.getAvailability(), mui.getCost(), ((AudioVideoImpl) mui).getSamplingRate(), ((AudioVideoImpl) mui).getResolution(), mui.getUploadTime());
               }
           }
       }

    }
}
