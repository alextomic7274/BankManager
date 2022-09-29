package database.dynamicType;

import java.sql.Date;

public record DateType(Date value) implements DynamicType {

    public String toString() {
        return value.toString();
    }
}
