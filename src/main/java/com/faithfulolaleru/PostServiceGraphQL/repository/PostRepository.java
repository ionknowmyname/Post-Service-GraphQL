package com.faithfulolaleru.PostServiceGraphQL.repository;

import com.faithfulolaleru.PostServiceGraphQL.entity.PostEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<PostEntity, String> {
}
