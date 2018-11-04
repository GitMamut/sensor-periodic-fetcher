package mintfrost.cloud.response;

public class CurrentOutdoorResponse {
    private String date;
    private String value;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CurrentOutdoorResponse{" +
                "date='" + date + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
