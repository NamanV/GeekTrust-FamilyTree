package dxtr.familytree.utility;

import java.util.Objects;

public class EnumUtility {

    public enum GENDER {
        MALE, FEMALE, NONE;
    }

    public enum COMMANDS {
        GET_RELATIONSHIP, ADD_CHILD, ADD_SPOUSE, ADD_KING, ADD_QUEEN, NOT_FOUND;
    }

    public static <T extends Enum<T>> T loadUpperCase(String input, Class<T> c, T defaultVal) {
        if (Objects.isNull(input) || input.isEmpty()) {
            return defaultVal;
        }
        return load(input.toUpperCase(), c, defaultVal);
    }

    public static <T extends Enum<T>> T load(String input, Class<T> c, T defaultVal) {
        if (Objects.nonNull(input) && !input.isEmpty()) {
            try {
                return Enum.valueOf(c, input);
            } catch (IllegalArgumentException ex) {
            }
        }
        return defaultVal;
    }
}
