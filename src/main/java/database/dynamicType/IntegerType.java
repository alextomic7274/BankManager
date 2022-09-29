package database.dynamicType;

public record IntegerType(int value) implements DynamicType {

    public String toString() {
        return Integer.toString(value);
    }
}
