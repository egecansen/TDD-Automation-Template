package models;

import lombok.Data;
import lombok.Getter;

@Data
public class GetUserResponse {

    Data data;
    Support support;

    @Getter
    public static class Data {
        Integer id;
        String email;
        String first_name;
        String last_name;
        String avatar;
    }

    @Getter
    public static class Support {
        String url;
        String text;
    }

}
