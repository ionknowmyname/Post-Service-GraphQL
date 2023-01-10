package com.faithfulolaleru.PostServiceGraphQL.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequest {

    private String username;
    private int age;
}
