import cli.InputValidator;
import domainLogic.MediaUploadableAdmin;
import observerPattern.CapacityObserver;
import observerPattern.TagsObserver;
import observerPatternContract.Observer;
import simulation.VCMedia;

public class Simulation {
    public static void main(String[] args) {

        InputValidator inputValidator = new InputValidator();

        if (args.length == 0 || !inputValidator.numberFormatValidation(args[0]) || Long.parseLong(args[0]) < 0) {
            return;
        }

        if (!inputValidator.numberFormatValidation(args[1]) || Long.parseLong(args[1]) < 0) {
            return;
        }

        long capacity = Long.parseLong(args[0]);
        long n = Long.parseLong(args[1]);

        MediaUploadableAdmin model = new MediaUploadableAdmin(capacity);

        Observer capacityObserver = new CapacityObserver(model);
        Observer tagsObserver = new TagsObserver(model);
        model.registerObserver(capacityObserver);
        model.registerObserver(tagsObserver);

        VCMedia vcm = new VCMedia(model, n);

        vcm.execute();
    }
}
