package org.negrdo.repositories;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.negrdo.entities.Project;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProjectRepository implements PanacheMongoRepository<Project> {
}
