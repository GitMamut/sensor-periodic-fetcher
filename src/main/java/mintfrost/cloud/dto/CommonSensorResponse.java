package mintfrost.cloud.dto;

import java.util.Date;
import java.util.List;

public class CommonSensorResponse {
    private String name;
    private Date date;
    private List<CommonSensorValue> values;

    public CommonSensorResponse() {
    }

    public CommonSensorResponse(String name, Date date, List<CommonSensorValue> values) {
        this.name = name;
        this.date = date;
        this.values = values;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setValues(List<CommonSensorValue> values) {
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public List<CommonSensorValue> getValues() {
        return values;
    }

    @Override
    public String toString() {
        return "CommonSensorResponse{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", values=" + values +
                '}';
    }

    public static class CommonSensorValue {
        private String name;
        private String value;

        public CommonSensorValue() {
        }

        public CommonSensorValue(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "CommonSensorValue{" +
                    "name='" + name + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
}
