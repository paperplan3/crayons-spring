package com.crayons_2_0.service.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crayons_2_0.model.graph.Graph;
@Component
public class GraphService {
    
    @Autowired
    GraphDAO graphDAO;
    
    public List<Graph> findAll() {
        return null;
    }
    
    public Graph findGraphById(long graphId) {
        return null;
    }
    
    public boolean insertGraph(Graph graph) {
        return true;
    }
    
    public boolean deleteGraph(Graph graph) {
        return true;
    }
}
