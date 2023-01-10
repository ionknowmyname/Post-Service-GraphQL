package com.faithfulolaleru.PostServiceGraphQL.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "authors")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorEntity extends BaseEntity {

    private String username;

    private int age;

//    @DBRef
    private List<PostEntity> posts = new ArrayList<>();
}
