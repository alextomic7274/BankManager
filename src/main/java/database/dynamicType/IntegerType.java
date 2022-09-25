package database.dynamicType;

public class IntegerType implements DynamicType {
    private final int value;

    public IntegerType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
