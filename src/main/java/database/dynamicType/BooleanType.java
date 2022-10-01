package database.dynamicType;

public record BooleanType(Boolean value) implements DynamicType {

    public String toString() {
        return Boolean.toString(value);
    }
}
