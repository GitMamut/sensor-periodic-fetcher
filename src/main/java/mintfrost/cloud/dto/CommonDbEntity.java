package mintfrost.cloud.dto;

import java.util.Date;

public class CommonDbEntity {
    private java.util.Date date;
    private String value;

    public CommonDbEntity(Date date, String value) {
        this.date = date;
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public String getValue() {
        return value;
    }
}
