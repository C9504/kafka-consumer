package org.c9504.repositories;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.c9504.entities.Project;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProjectRepository implements PanacheMongoRepository<Project> {
}
