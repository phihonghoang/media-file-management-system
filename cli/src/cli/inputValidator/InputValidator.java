package cli.inputValidator;

import contract.Tag;
import domainLogic.administration.MediaUploadableCRUD;
import domainLogic.administration.MediaUploadableMap;
import domainLogic.media.AudioImpl;
import domainLogic.media.AudioVideoImpl;
import domainLogic.media.MediaUploadableItem;
import domainLogic.media.VideoImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

public class InputValidator {

    public boolean uploaderValidation(String[] parts) {
        return parts.length == 1;
    }

    public boolean mediaValidation(String[] parts) {
        return parts.length >= 5;
    }

    public boolean numberFormatValidation(String[] parts) {
        if (!longFormatValidation(parts[3])) {
            System.out.println("ERROR: 4th element is not valid!");
            return false;
        }

        if (!bigDecimalFormatValidation(parts[4])) {
            System.out.println("ERROR: 5th element is not valid!");
            return false;
        }

        return true;
    }

    public boolean longFormatValidation(String value) {
        try {
            Long.parseLong(value);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public boolean bigDecimalFormatValidation(String value) {
        try {
            new BigDecimal(value);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

}
