package database.dynamicType;

public class ByteArrayType implements DynamicType {
    private final byte[] value;

    public ByteArrayType(byte[] value) {
        this.value = value;
    }

    public byte[] getValue() {
        return value;
    }

    public String toString(){
        return value.toString();
    }
}
