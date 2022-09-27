package database.dynamicType;

import java.sql.Date;

public class DateType implements DynamicType {
    private final Date value;

    public DateType(Date value) {
        this.value = value;
    }

    public Date getValue() {
        return value;
    }

    public String toString(){
        return value.toString();
    }
}
