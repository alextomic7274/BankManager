package database.dynamicType;

public class ObjectType implements DynamicType {
    private Object value;

    public ObjectType(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public String toString(){
        return value.toString();
    }
}
