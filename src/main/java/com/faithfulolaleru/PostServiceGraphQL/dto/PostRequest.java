package com.faithfulolaleru.PostServiceGraphQL.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {

    private String title;
    private String body;
    private String authorId;
}
