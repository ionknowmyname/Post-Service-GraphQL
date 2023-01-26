package com.faithfulolaleru.PostServiceGraphQL.repository;

import com.faithfulolaleru.PostServiceGraphQL.entity.AuthorEntity;
import com.faithfulolaleru.PostServiceGraphQL.entity.PostEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends MongoRepository<PostEntity, String> {

    List<PostEntity> findAllByTitle(String title);

    List<PostEntity> findAllByAuthor(AuthorEntity author);

    Optional<PostEntity> findPostEntityByTitleAndAuthor(String title, AuthorEntity author);
}
