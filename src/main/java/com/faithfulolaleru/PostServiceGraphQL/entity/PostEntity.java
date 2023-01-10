package com.faithfulolaleru.PostServiceGraphQL.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "posts")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostEntity extends BaseEntity {

    private String title;

    private String body;

    @DBRef
    private AuthorEntity author;
}
