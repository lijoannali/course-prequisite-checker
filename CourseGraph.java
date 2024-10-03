import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class CourseGraph {
    private List<Course> adjacencyList; 
    private int nextCourseID; //ID to be assigned to the next course that is added
    private Map<String,Course> nameToCourse; 

    /* Constructor */
    public CourseGraph() { 
        adjacencyList = new ArrayList<Course>(); 
        nextCourseID = 0; 
        nameToCourse = new HashMap<String, Course>(); 
    }

    /* isEmpty */
    /* Returns true if the CourseGraph is empty, false otherwise */
    public boolean isEmpty() { 
        if(adjacencyList.size() == 0) { 
            return true; 
        }
        return false; 
    }

    /* toString */
    /* Returns representation of the course graph as a list of every course along with its prerequisites */
    public String toString() { 
        String graphString = ""; 
        for(int i = 0; i < adjacencyList.size(); i++) { 
            Course current = adjacencyList.get(i); 
            graphString += "\n\n" + current + "\n  Prerequisites: "; 
            // Append the names of the prerequisites of each Course
            for(int j = 0; j < current.getAdjacentVertices().size(); j++) { 
                int edgeID = current.getAdjacentVertices().get(j); 
                graphString += " " + adjacencyList.get(edgeID); 
            }
        }
        return graphString; 
    }

    /* toString for Debug */
    /* Displays the course graph with the IDs of each course and prerequisite */
    public String toDebugString() { 
        String graphString = ""; 
        for(int i = 0; i < adjacencyList.size(); i++) { 
            Course current = adjacencyList.get(i); 
            graphString += "\n\n" + "(" + current.getID() + ") " + current + "\nPrerequisites: "; 
            // Append the names and ids of the edges of each Course
            for(int j = 0; j < current.getAdjacentVertices().size(); j++) { 
                graphString += " (" + current.getAdjacentVertices().get(j) + ") " + adjacencyList.get(current.getAdjacentVertices().get(j)); 
            }
        }
        return graphString; 
    }

    /* toString for Writing to a File */
    /* Returns a string representation of the course graph which can be read into CourseGraphAnalyzer */
    public String toFileString() { 
        String coursesString = ""; 
        String edgesString = ""; 
        for(int i = 0; i < adjacencyList.size(); i++) { 
            coursesString += getNameFromID(i) + ","; //Course Names are comma separated 
        // Print edges of each Course
        for(int j = 0; j < adjacencyList.get(i).getAdjacentVertices().size(); j++) { 
            edgesString +=  "#" + adjacencyList.get(i).getAdjacentVertices().get(j) + "#" + i ; //Edges are stored like this: prequisiteID#courseID#prerequisiteID#courseID
            }
        }
        return coursesString += edgesString; 
    }

    /* Get Name From Course ID */
    /* Returns the course name given the id of the course, if it exists */
    public String getNameFromID(int id) { 
        if(id < 0 || id >= adjacencyList.size()) { 
            return "(Error: Course ID is invalid)"; 
        } else { 
            return adjacencyList.get(id).getName(); 
        }
    }

    /* Add Course */
    /* Takes in the name of a course, and adds a course with the given name to the course graph */
    public void addCourse(String name) { 
        //Check if class doesn't already exist 
        if(nameToCourse.containsKey(name)) { 
            System.out.println("Error: duplicate class name");
            return; 
        }
        Course newCourse = new Course(nextCourseID, name); 
        nameToCourse.put(name, newCourse); 
        adjacencyList.add(newCourse);
        nextCourseID++; 
    }

    /* Add Edge between classes */
    /* Given the id of both courses, adds an edge pointing from the prerequisite of a course towards the course */
    public void addEdge(int prereqID, int courseID) { 
        //Check that the edge is not a self edge 
        if (prereqID == courseID) { 
            System.out.println("Error: Cannot add a class as its own prerequisite");
            return; 
        }
        //Checks ids are not out of bounds  
        if(courseID < 0 || courseID >= adjacencyList.size() || prereqID < 0 || prereqID >= adjacencyList.size()){ 
            System.out.println("Error: Course ID is invalid");
            return; 
        } 
        // Checks that both vertices exist 

        //I DONT THINK I NEED THIS IF IDS ARE INTEGERS!!!
        boolean prereqIDExists = false; 
        boolean courseIDExists = false; 
        for(int i = 0; i < adjacencyList.size(); i++) { 
            if(adjacencyList.get(i).getID() == prereqID) { 
                prereqIDExists = true; 
            }
            if(adjacencyList.get(i).getID() == courseID) { 
                courseIDExists = true; 
            }
        }
        if(!courseIDExists || !prereqIDExists) { 
            System.out.println("Error: Classes not found");
            return; 
        }
        //I DONT THINK I NEED THIS IF IDS ARE INTEGERS!!!


        //Prevents addition of duplicate edges 
        Course courseIDCourse = adjacencyList.get(courseID); 
        for(int i = 0; i < courseIDCourse.getAdjacentVertices().size(); i++) { 
            if (courseIDCourse.getAdjacentVertices().get(i) == prereqID) { 
                System.out.println("Error: Duplicate course edge");
                return; 
            }
        }
        //Prevents addition of parallel edges
        Course prereqIDCourse = adjacencyList.get(prereqID); 
        for(int i = 0; i < prereqIDCourse.getAdjacentVertices().size(); i++) { 
            if (prereqIDCourse.getAdjacentVertices().get(i) == courseID) { 
                System.out.println("Error: A edge already exists between these classes");
                return; 
            }
        }
        //Add edge and store in-edge in adjacency list
            adjacencyList.get(courseID).getAdjacentVertices().add(prereqID);
    }

    /* Remove Course */
    /* Removes a course and all edges which contain the removed course */
    public void removeCourse(int id) {
        if(id < 0 || id >= adjacencyList.size()) { 
            System.out.println("Error: Course ID is invalid");
            return;
        }
        nameToCourse.remove(adjacencyList.get(id).getName()); //Remove course from nameToCourse
        adjacencyList.remove(id); //Remove the course
        /* Remove edges containing the deleted course */
        for(int i = 0; i < adjacencyList.size(); i++) { 
            List<Integer> currentAdjacentVertices = adjacencyList.get(i).getAdjacentVertices(); 
            for(int j = 0; j < currentAdjacentVertices.size(); j++) { 
                if(currentAdjacentVertices.get(j) == id) { 
                    currentAdjacentVertices.remove(j); 
                }
            }
        /* Decrement IDs of courses which were greater than the ID of the removed course by 1 */
            if(adjacencyList.get(i).getID() > id) { 
                adjacencyList.get(i).setID(adjacencyList.get(i).getID()-1); 
            }
        }
        /* Decrement course IDs in the in-edge lists */
        for(int i = 0; i < adjacencyList.size(); i++) {  
            List<Integer> currentAdjacentVertices = adjacencyList.get(i).getAdjacentVertices(); 
            for(int j = 0; j < currentAdjacentVertices.size(); j++) { 
                if(currentAdjacentVertices.get(j) > id) { 
                    currentAdjacentVertices.set(j, currentAdjacentVertices.get(j)-1); 
                }
            }
        }
        nextCourseID --; 
    }
    
    /* Remove edge */
    /* Removes a course-prerequisite connection */
    public void removeEdge(int prereqID, int courseID) { 
        Course courseIDCourse = adjacencyList.get(courseID); 
        for(int i = 0; i < courseIDCourse.getAdjacentVertices().size(); i++) { 
            if(courseIDCourse.getAdjacentVertices().get(i) == prereqID) { 
                courseIDCourse.getAdjacentVertices().remove(i); 
                return; 
            } 
        }
        System.out.println("Error: Edge between courses not found");
    }

    /* Getters and Setters */
    public List<Course> getAdjacencyList() { 
        return adjacencyList; 
    }

    public Map<String, Course> getNametoCourse() { 
        return nameToCourse; 
    }

}
