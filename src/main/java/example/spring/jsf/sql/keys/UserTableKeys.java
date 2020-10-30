package example.spring.jsf.sql.keys;

import java.util.ArrayList;
import java.util.List;

public enum UserTableKeys {
    TABLE_NAME("user"),
    ID("user_id"),
    FIRST_NAME("first_name"),
    LAST_NAME("last_name"),
    HOUSE_NUMBER("house_number"),
    STREET("street"),
    CITY("city"),
    POSTCODE("postcode");

    private String key;

    UserTableKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static List<String> getKeys() {
        List<String> keyList = new ArrayList<String>();
        keyList.add(FIRST_NAME.getKey());
        keyList.add(LAST_NAME.getKey());
        keyList.add(HOUSE_NUMBER.getKey());
        keyList.add(STREET.getKey());
        keyList.add(CITY.getKey());
        keyList.add(POSTCODE.getKey());

        return keyList;
    }
}
