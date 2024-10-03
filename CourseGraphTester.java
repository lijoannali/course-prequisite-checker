public class CourseGraphTester {
    static CourseGraph myCourseGraph; 

    static void testCourseGraph() { 
        System.out.println("\nTesting Constructor");
        myCourseGraph = new CourseGraph(); 
    }

    static void testIsEmpty() { 
        System.out.println("\nTesting isEmpty: " + myCourseGraph.isEmpty());
    }

    static void testToString() { 
        System.out.println("\nTesting ToString");
        System.out.println(myCourseGraph.toString());
    }

    static void testToDebugString() { 
        System.out.println("\nTesting toDebugString");
        System.out.println(myCourseGraph.toDebugString());
    }

    static void testAddCourse(String name) { 
        System.out.println("\nAdding Course: " + name);
        myCourseGraph.addCourse(name);
        System.out.println(myCourseGraph.toDebugString());
        //ToString or print out of adj list here 
    }

    static void testAddEdge(int prereqID, int courseID) { 
        System.out.println("\nAdding edge: " + courseID + " <-- " + prereqID);
        myCourseGraph.addEdge(prereqID, courseID);
        System.out.println(myCourseGraph.toDebugString());
    }

    static void testRemoveEdge(int prereqID, int courseID) { 
        System.out.println("\nTesting remove edge");
        System.out.println("\nRemoving edge: " + courseID + " <-- " + prereqID);
        myCourseGraph.removeEdge(prereqID, courseID);
        System.out.println(myCourseGraph.toDebugString());
    }
    
    static void testRemoveCourse(int id) { 
        System.out.println("\nTesting remove Course");
        System.out.println("Attempting to Remove Course with ID " + id);
        myCourseGraph.removeCourse(id);
        System.out.println(myCourseGraph.toDebugString());
    }

    public static void main(String[] args) { 
        //Empty CourseGraph (Directed Graph)
        testCourseGraph();
        testIsEmpty();
        testToString();

        testAddCourse("CSC110");
        testAddCourse("CSC110"); //Duplicate names not allowed 
        testAddCourse("CSC120");
        testAddCourse("CSC210");
        testAddCourse("CSC250");
        testAddCourse("MTH153");

        testAddEdge(-2, 0);
        testAddEdge(8, 0); //Invalid Course IDs cause errors 
        testAddEdge(5, 0); //Invalid Course IDs cause errors 
        testAddEdge(1, 1); //Self edges cannot be added
        
        testAddEdge(0, 1);
        testAddEdge(0, 1); //Duplicate edges cannot be added
        testAddEdge(1, 0); //Parallel edges cannot be added
        testAddEdge(1, 2);
        testAddEdge(2, 3);
        testAddEdge(4, 3);

        //Testing remove edge
        testRemoveEdge(3, 3);
        testRemoveEdge(4, 3);
        testRemoveEdge(2, 3);

        testAddEdge(2, 3);
        testAddEdge(4, 3);

        //Testing remove course
        testRemoveCourse(6);
        testRemoveCourse(0);

        testAddCourse("CSC110");
        testAddEdge(4, 0);

        testRemoveCourse(1);
        testAddCourse("CSC210");
        testAddEdge(4, 1);
    }
}
