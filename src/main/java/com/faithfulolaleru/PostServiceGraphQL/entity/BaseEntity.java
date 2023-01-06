package com.faithfulolaleru.PostServiceGraphQL.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class BaseEntity {

    @Id
    private String id;

    @CreatedDate
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Instant createdAt;

    @LastModifiedDate
    // @UpdateTimestamp
    private Instant updatedAt;


    public BaseEntity() {
        this.createdAt = Instant.now();
    }

}
