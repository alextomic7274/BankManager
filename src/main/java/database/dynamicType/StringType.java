package database.dynamicType;

public class StringType implements DynamicType {
    private final String value;

    public StringType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString(){
        return value;
    }
}
