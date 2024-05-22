

package controller;
import model.Project;
import view.AboutPanel;
import view.ProjectsPanel;
// i added

@SuppressWarnings("unused")
public class ProjectController {
    private Project model; 

    @SuppressWarnings("unused")
    private ProjectsPanel view; 



    public ProjectController(Project model, ProjectsPanel view){
        this.model  = model;
        this.view = view;



    }

    public void updateView(){
        
    }

}
