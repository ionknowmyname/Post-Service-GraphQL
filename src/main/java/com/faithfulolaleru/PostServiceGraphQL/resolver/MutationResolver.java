package com.faithfulolaleru.PostServiceGraphQL.resolver;

import com.faithfulolaleru.PostServiceGraphQL.dto.AuthorRequest;
import com.faithfulolaleru.PostServiceGraphQL.dto.PostRequest;
import com.faithfulolaleru.PostServiceGraphQL.entity.AuthorEntity;
import com.faithfulolaleru.PostServiceGraphQL.entity.PostEntity;
import com.faithfulolaleru.PostServiceGraphQL.exception.GeneralException;
import com.faithfulolaleru.PostServiceGraphQL.repository.AuthorRepository;
import com.faithfulolaleru.PostServiceGraphQL.repository.PostRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Component
public class MutationResolver implements GraphQLMutationResolver {

    private final AuthorRepository authorRepository;

    private final PostRepository postRepository;


    public AuthorEntity createAuthor(@RequestBody AuthorRequest request) {

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

    public PostEntity createPost(@RequestBody PostRequest request) {

        Optional<AuthorEntity> foundAuthor = authorRepository.findById(request.getAuthorId());
        if(foundAuthor.isEmpty()) {
            throw new GeneralException("Author doesn't exist");
        }

        Optional<PostEntity> foundPost = postRepository.findPostEntityByTitleAndAuthor(request.getTitle(), foundAuthor.get());
        if(foundPost.isPresent()) {
            throw new GeneralException("Author already created post with title");
        }

        PostEntity toSave = PostEntity.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .author(foundAuthor.get())
                .build();

        return postRepository.save(toSave);
    }

    public PostEntity updatePostById(String id, @RequestBody PostRequest request) {

        Optional<PostEntity> foundPost = postRepository.findById(id);

        if(foundPost.isPresent()) {
            PostEntity toUpdate = PostEntity.builder()
                .title(request.getTitle() != null ? request.getTitle() : foundPost.get().getTitle())
                .body(request.getBody() != null ? request.getBody() : foundPost.get().getBody())
                .author(foundPost.get().getAuthor())  // author shouldn't update
                .build();

            return postRepository.save(toUpdate);
        }

        throw new GeneralException("Post not found");
    }

    public boolean deletePostById(String id) {
        try {
            postRepository.deleteById(id);

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Error occurred during deletion --> {}", ex.getMessage());

            return false;
        }
    }
}
