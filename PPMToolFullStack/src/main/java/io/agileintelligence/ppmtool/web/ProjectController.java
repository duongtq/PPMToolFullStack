package io.agileintelligence.ppmtool.web;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.service.MapValidationErrorService;
import io.agileintelligence.ppmtool.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final MapValidationErrorService validationErrorService;

    @Autowired
    public ProjectController(ProjectService projectService, MapValidationErrorService validationErrorService) {
        this.projectService = projectService;
        this.validationErrorService = validationErrorService;
    }

    @PostMapping
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
        ResponseEntity<?> errorMap = validationErrorService.validateMapError(result);

        if (errorMap != null) {
            return errorMap;
        }

        Project newProject = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(newProject, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Project>> getProjects() {
        List<Project> projects = (List<Project>) projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable(value = "projectId") String projectId) {
        Project project = projectService.findProjectByIdentifier(projectId.toUpperCase());

        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable(value = "projectId") String projectId) {
        projectService.deleteProjectById(projectId.toUpperCase());
        return new ResponseEntity<>("Project with ID: '" + projectId.toUpperCase() + "' was deleted.", HttpStatus.OK);
    }

}
