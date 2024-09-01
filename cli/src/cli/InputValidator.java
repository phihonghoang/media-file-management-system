package cli;

import java.math.BigDecimal;

public class InputValidator {

    public boolean uploaderValidation(String[] parts) {
        return parts.length == 1;
    }

    public boolean mediaValidation(String[] parts) {
        return parts.length >= 5;
    }

    public boolean sampResValidation(String[] parts, int length) {
        return parts.length >= length;
    }

    public boolean longFormatValidation(String value) {
        try {
            Long.parseLong(value);
            return true;
        } catch(NumberFormatException e) {
            System.out.println("ERROR: 4th element is not valid!");
            return false;
        }
    }

    public boolean bigDecimalFormatValidation(String value) {
        try {
            new BigDecimal(value);
            return true;
        } catch(NumberFormatException e) {
            System.out.println("ERROR: 5th element is not valid!");
            return false;
        }
    }

    public boolean integerFormatValidation(String value, String element) {
        try {
            Integer.parseInt(value);
            return true;
        } catch(NumberFormatException e) {
            System.out.println("ERROR: " + element + "th element is not valid!");
            return false;
        }
    }

}
