package database.dynamicType;

public record ObjectType(Object value) implements DynamicType {

    public String toString() {
        return value.toString();
    }
}
