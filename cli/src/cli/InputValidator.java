package cli;

import java.math.BigDecimal;

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
