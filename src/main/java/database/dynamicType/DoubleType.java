package database.dynamicType;

public class DoubleType implements DynamicType {
    private final double value;

    public DoubleType(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public String toString(){
        return Double.toString(value);
    }
}
