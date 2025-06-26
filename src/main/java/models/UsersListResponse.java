package models;

import lombok.*;

import java.util.List;

@Getter
public class UsersListResponse {
    Integer page;
    Integer per_page;
    Integer total;
    Integer total_pages;
    List<GetUserResponse.Data> data;
    GetUserResponse.Support support;
}
