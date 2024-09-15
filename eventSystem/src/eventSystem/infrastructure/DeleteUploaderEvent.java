package eventSystem.infrastructure;

import java.util.EventObject;

public class DeleteUploaderEvent extends EventObject {
    private String uploader;

    public DeleteUploaderEvent(Object source, String uploader) {
        super(source);
        this.uploader = uploader;
    }

    public String getUploader() {
        return uploader;
    }
}
