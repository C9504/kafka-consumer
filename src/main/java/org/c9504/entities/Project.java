package org.c9504.entities;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "projects")
public class Project extends PanacheMongoEntity {

    public Integer id;
    public String value;

    public Project() {

    }

    public Project(Integer id, String value) {
        this.id = id;
        this.value = value;
    }
}
