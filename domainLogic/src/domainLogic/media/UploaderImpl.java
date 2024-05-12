package domainLogic.media;

import contract.Uploader;

public class UploaderImpl implements Uploader {
    private final String name;

    public UploaderImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
