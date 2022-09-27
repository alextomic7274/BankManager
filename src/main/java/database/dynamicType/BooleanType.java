package database.dynamicType;

public class BooleanType implements DynamicType {
    private boolean value;

    public BooleanType(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public String toString(){
        return Boolean.toString(value);
    }
}
