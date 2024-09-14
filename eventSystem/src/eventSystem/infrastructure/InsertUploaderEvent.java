package eventSystem.infrastructure;

import java.util.EventObject;

public class InsertUploaderEvent extends EventObject {
    private String uploader;

    public InsertUploaderEvent(Object source, String uploader) {
        super(source);
        this.uploader = uploader;
    }

    public String getUploader() {
        return uploader;
    }
}
