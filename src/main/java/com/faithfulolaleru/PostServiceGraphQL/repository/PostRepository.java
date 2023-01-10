package com.faithfulolaleru.PostServiceGraphQL.repository;

import com.faithfulolaleru.PostServiceGraphQL.entity.AuthorEntity;
import com.faithfulolaleru.PostServiceGraphQL.entity.PostEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends MongoRepository<PostEntity, String> {

    Optional<PostEntity> findPostEntityByTitle(String title);

    List<PostEntity> findAllByAuthor(String authorId);

    Optional<PostEntity> findPostEntityByTitleAndAuthor(String title, AuthorEntity author);
}
