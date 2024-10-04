package simulation;

import domainLogic.MediaUploadableAdmin;

public class VCMedia {
    private MediaUploadableAdmin model;
    private long n;

    public VCMedia(MediaUploadableAdmin model, long n) {
        this.model = model;
        this.n = n;
    }

    public void execute() {

        model.insertUploader("Phi");

        for (int i = 0; i < n; i++) {
            InsertMedia im = new InsertMedia(model, "InsertMedia-" + i);
            DeleteMedia dm = new DeleteMedia(model, "DeleteMedia-" + i);

            im.start();
            dm.start();
        }
    }

}
