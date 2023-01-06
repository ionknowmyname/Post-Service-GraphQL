package com.faithfulolaleru.PostServiceGraphQL.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "posts")
@Data
@EqualsAndHashCode(callSuper = true)
public class PostEntity extends BaseEntity {

    private String title;

    private String body;

    @DBRef
    private AuthorEntity author;
}
