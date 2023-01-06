package com.faithfulolaleru.PostServiceGraphQL.repository;

import com.faithfulolaleru.PostServiceGraphQL.entity.AuthorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<AuthorEntity, String> {
}
