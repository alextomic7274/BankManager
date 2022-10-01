package database.dynamicType;

public record StringType(String value) implements DynamicType {

    public String toString() {
        return value;
    }
}
