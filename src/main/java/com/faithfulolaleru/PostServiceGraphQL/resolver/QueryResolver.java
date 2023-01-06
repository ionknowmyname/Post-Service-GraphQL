package com.faithfulolaleru.PostServiceGraphQL.resolver;

import com.faithfulolaleru.PostServiceGraphQL.entity.AuthorEntity;
import com.faithfulolaleru.PostServiceGraphQL.repository.AuthorRepository;
import com.faithfulolaleru.PostServiceGraphQL.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import graphql.kickstart.tools.GraphQLQueryResolver;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class QueryResolver implements GraphQLQueryResolver {

    private final AuthorRepository authorRepository;

    private final PostRepository postRepository;

    public List<AuthorEntity> getAllAuthors() {
        return authorRepository.findAll();
    }
}
