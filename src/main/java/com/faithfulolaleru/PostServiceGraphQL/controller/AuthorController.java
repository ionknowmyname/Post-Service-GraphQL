package com.faithfulolaleru.PostServiceGraphQL.controller;

import com.faithfulolaleru.PostServiceGraphQL.entity.AuthorEntity;
import com.faithfulolaleru.PostServiceGraphQL.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

//@Controller
//@AllArgsConstructor
public class AuthorController {

    // private final AuthorRepository authorRepository;


    // @SchemaMapping(typeName = "Query", value = "allAuthors")
    // @QueryMapping      // if method name is same as graph query method name, no need to incude it as value

    /*public List<AuthorEntity> allAuthors(@Argument int count, @Argument int offset) {
        return authorRepository.findAll(count, offset);
    }*/
}
