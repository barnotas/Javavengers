

package controller;
import model.Project;
import view.ProjectsPanel;
<<<<<<< HEAD
// i added
=======
/**
 * ProjectController class provides event handling 
 * feature for project page.
 * @author Bega Bernard
 * @author Mahri Yalkapova
 * @author Ahmed Hassan 
 * @author Barno Tashpulatova
 * @version 1.1
 */
>>>>>>> main

@SuppressWarnings("unused")
public class ProjectController {
    /**
     * A field for model parameter.
     */
    private Project model; 

    @SuppressWarnings("unused")
    /**
     * A field for view model.
     */
    private ProjectsPanel view; 



/**
 * Constructs project controller class 
    with default model and view parameter 
 * @param model
 * @param view
 */
public ProjectController(Project model, ProjectsPanel view){
    this.model  = model;
    this.view = view;
}
     /**
      * 
      */
    public void updateView(){
        
    }

}
