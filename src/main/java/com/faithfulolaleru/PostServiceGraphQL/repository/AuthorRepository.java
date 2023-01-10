package com.faithfulolaleru.PostServiceGraphQL.repository;

import com.faithfulolaleru.PostServiceGraphQL.entity.AuthorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<AuthorEntity, String> {

    Optional<AuthorEntity> findAuthorEntityByUsername(String username);
}
