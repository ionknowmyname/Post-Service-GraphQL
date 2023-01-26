package com.faithfulolaleru.PostServiceGraphQL.controller;

import com.faithfulolaleru.PostServiceGraphQL.dto.AuthorRequest;
import com.faithfulolaleru.PostServiceGraphQL.dto.PostRequest;
import com.faithfulolaleru.PostServiceGraphQL.entity.AuthorEntity;
import com.faithfulolaleru.PostServiceGraphQL.entity.PostEntity;
import com.faithfulolaleru.PostServiceGraphQL.exception.GeneralException;
import com.faithfulolaleru.PostServiceGraphQL.repository.AuthorRepository;
import com.faithfulolaleru.PostServiceGraphQL.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@Slf4j
public class PostController {

    private final PostRepository postRepository;

    private final AuthorRepository authorRepository;


    @QueryMapping
    public List<PostEntity> getAllPosts() {
        return postRepository.findAll();
    }

    @QueryMapping
    public PostEntity getPostById(@Argument String id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new GeneralException("Post with id not found"));
    }

    @QueryMapping
    public List<PostEntity> getPostsByTitle(@Argument String title) {
        return postRepository.findAllByTitle(title);
    }

    @QueryMapping
    public List<PostEntity> getAllPostsByAuthor(@Argument String authorId) {
        AuthorEntity foundAuthor = authorRepository.findById(authorId)
                .orElseThrow(() -> new GeneralException("Author with id not found"));

        return postRepository.findAllByAuthor(foundAuthor);
    }

    @MutationMapping
    public PostEntity createPost(@Argument PostRequest request) {

        AuthorEntity foundAuthor = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new GeneralException("Author with id not found"));

        // allow to create duplicate posts/post with same title

        return postRepository.save(PostEntity.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .author(foundAuthor)
                .build());
    }

    @MutationMapping
    public PostEntity updatePostById(@Argument String id, @Argument PostRequest request) {

        PostEntity foundPost = postRepository.findById(id)
                .orElseThrow(() -> new GeneralException("Post with id not found"));

        // author cannot be updated

        return postRepository.save(PostEntity.builder()
                .title(request.getTitle() != null ? request.getTitle() : foundPost.getTitle())
                .body(request.getBody() != null ? request.getBody() : foundPost.getBody())
                .author(foundPost.getAuthor())
                .build());
    }

    @MutationMapping
    public Boolean deletePostById(@Argument String id) {

        try{
            postRepository.deleteById(id);

            return true;
        } catch(Exception ex) {
            log.error("Error while deleting post", ex.getMessage());
            ex.printStackTrace();

            return false;
        }
    }
}
