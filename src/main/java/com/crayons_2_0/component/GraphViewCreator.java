package com.crayons_2_0.component;

import java.util.List;

import com.crayons_2_0.model.graph.Graph;
import com.crayons_2_0.model.graph.UnitNode;
import com.crayons_2_0.model.graph.UnitNode.UnitType;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;

import org.springframework.security.core.userdetails.User;

public class GraphViewCreator {
    private List<UnitNode> units;
    private User user;
    private List<Integer> horizontalPosition;
    private List<Integer> verticalPosition;
    
    public GraphViewCreator(Graph graph, User user) {
        this.user = user;
    }
    
    /* generate a graph
     * if user is an author return an author view
     * otherwise return a student view */
    public GridLayout getGraphView() {
        return getStudentView();
    }
    
    // the view where some units can be unavailable 
    private GridLayout getStudentView() {
        return null;
    }
    
    // all units are available
    private GridLayout getAuthorView() {
        return null;
    }
    
    private void calculateHorizontalPositions() {
        
    }
    
    private void calculateVerticalPositions() {
        
    }
    
    private Button generateButton(UnitNode unit) {
        return null;
    }
    
    private boolean checkUnitAvailability(UnitNode unit, User user) {
        return true;
    }
    
    private void addButton(UnitNode unit, GridLayout layout) {
        
    }
}
