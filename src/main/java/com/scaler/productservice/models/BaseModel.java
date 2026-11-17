package com.scaler.productservice.models;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.MappedSuperclass;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private State state;
    private String name;

    public BaseModel() {
        this.state = State.ACTIVE;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
