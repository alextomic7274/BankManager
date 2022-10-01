package database.dynamicType;

public record DoubleType(double value) implements DynamicType {

    public String toString() {
        return Double.toString(value);
    }
}
