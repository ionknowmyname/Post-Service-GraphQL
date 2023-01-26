package com.faithfulolaleru.PostServiceGraphQL.controller;

import com.faithfulolaleru.PostServiceGraphQL.dto.AuthorRequest;
import com.faithfulolaleru.PostServiceGraphQL.entity.AuthorEntity;
import com.faithfulolaleru.PostServiceGraphQL.exception.GeneralException;
import com.faithfulolaleru.PostServiceGraphQL.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;


    // @SchemaMapping(typeName = "Query", value = "allAuthors")
    // @QueryMapping      // if method name is same as graph query method name, no need to include it as value

    @QueryMapping
    public List<AuthorEntity> getAllAuthors() { // @Argument int count, @Argument int offset
        return authorRepository.findAll();
    }

    @MutationMapping
    public AuthorEntity createAuthor(@Argument AuthorRequest request) {

        Optional<AuthorEntity> foundEntity = authorRepository.findAuthorEntityByUsername(request.getUsername());

        if(foundEntity.isEmpty()) {
            AuthorEntity toSave = AuthorEntity.builder()
                    .username(request.getUsername())
                    .age(request.getAge())
                    .build();

            return authorRepository.save(toSave);
        }

        throw new GeneralException("Author already exists");  // return null
    }
}
