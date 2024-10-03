import java.util.List;
import java.util.ArrayList;

public class Course extends Vertex<String> { 
    private List<Integer> adjacentVertices; 

    /* Constructor */
    public Course(int id, String name) { 
        super(id, name); 
        adjacentVertices = new ArrayList<Integer>();
    }

    /* toString */
    public String toString() { 
        return getName(); 
    }
    
    /* Getter For Adjacency List */
    public List<Integer> getAdjacentVertices() { 
        return adjacentVertices; 
    }

}