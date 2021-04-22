package io.agileintelligence.ppmtool.service;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.exceptions.ProjectIdException;
import io.agileintelligence.ppmtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }
        catch (Exception ex) {
            throw new ProjectIdException("Project ID: '" + project.getProjectIdentifier() + "' already existed.");
        }
    }

    public Iterable<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project findProjectByIdentifier(String projectId) {
        Project project = projectRepository.findProjectByProjectIdentifier(projectId.toUpperCase());

        if (project == null) {
            throw new ProjectIdException("Project ID: '" + projectId + "' does not exist.");
        }

        return projectRepository.findProjectByProjectIdentifier(projectId);
    }

    public void deleteProjectById(String projectId) {
        try {
            projectRepository.deleteByProjectIdentifier(projectId);
        } catch (Exception ex) {
            throw new ProjectIdException("Project ID: '" + projectId + "' does not exist.");
        }
    }
}
