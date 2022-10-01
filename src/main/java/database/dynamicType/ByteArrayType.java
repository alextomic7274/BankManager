package database.dynamicType;

import java.util.Arrays;

public record ByteArrayType(byte[] value) implements DynamicType {

    public String toString() {
        return Arrays.toString(value);
    }
}
