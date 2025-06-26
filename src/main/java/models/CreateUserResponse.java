package models;

import lombok.Data;

@Data
public class CreateUserResponse {

    String name;
    String job;
    String id;
    String createdAt;

}
