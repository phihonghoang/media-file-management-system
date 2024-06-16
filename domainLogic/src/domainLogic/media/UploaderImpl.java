package domainLogic.media;

import contract.Uploader;

import java.io.Serial;
import java.io.Serializable;

public class UploaderImpl implements Uploader, Serializable {
    private final String name;
    @Serial
    private static final long serialVersionUID = 1L;

    public UploaderImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
