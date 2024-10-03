import java.util.Scanner;

public class CourseGraphBuilder {
    /* Prints Menu */
    public void showMenu() { 
        System.out.println("\nOPTIONS ========== \n 99  QUIT \n 0 LIST ALL COURSES \n ADD =========== \n  1   COURSE \n  2   EDGE FROM PREREQUISITE TO COURSE \n REMOVE =========== \n  3   COURSE \n  4   EDGE FROM PREREQUISITE TO COURSE  \n  5   SAVE GRAPH AS FILE");
        System.out.println("SELECT OPTION: ");
    }

    public static void main(String[] args) {
        boolean stillRunning = true; 
        int runID = 1; 
        int fileVersion = 0; 
        CourseGraphBuilder myCourseGraphBuilder = new CourseGraphBuilder();
        CourseGraph courseGraph = new CourseGraph();  

        /*OPTIONAL, SAMPLE COURSES FOR DEMO ONLY */
        Scanner scan = new Scanner(System.in); 
        courseGraph.addCourse("CSC110");
        courseGraph.addCourse("MTH153");
        courseGraph.addCourse("MTH111");
        courseGraph.addCourse("MTH112");
        courseGraph.addEdge(2, 3);

        courseGraph.addCourse("MTH211");
        courseGraph.addEdge(1, 4);
        courseGraph.addEdge(2, 4);

        courseGraph.addCourse("MTH212");

        courseGraph.addCourse("CSC120");
        courseGraph.addEdge(0, 6);
        
        courseGraph.addCourse("CSC205");
        courseGraph.addEdge(3, 7);
        
        courseGraph.addCourse("CSC210");
        courseGraph.addEdge(6, 8);

        courseGraph.addCourse("CSC220");
        courseGraph.addEdge(8, 9);

        courseGraph.addCourse("CSC223");
        courseGraph.addEdge(8, 10);

        courseGraph.addCourse("CSC230");
        courseGraph.addEdge(8, 11);
        courseGraph.addEdge(0, 11);
        
        courseGraph.addCourse("CSC231");
        courseGraph.addEdge(8, 12);
        
        courseGraph.addCourse("CSC249");
        courseGraph.addEdge(8, 13);
        
        courseGraph.addCourse("CSC250");
        courseGraph.addEdge(0, 14);
        courseGraph.addEdge(1, 14);

        courseGraph.addCourse("CSC252");
        courseGraph.addEdge(8, 15);
        courseGraph.addEdge(2, 15);
        courseGraph.addEdge(1, 15);

        courseGraph.addCourse("CSC253");
        courseGraph.addEdge(0, 16);
        courseGraph.addEdge(8, 16);
        courseGraph.addEdge(1, 16);
        courseGraph.addEdge(2, 16);

        courseGraph.addCourse("CSC262");
        courseGraph.addEdge(12, 17);
        
        courseGraph.addCourse("CSC266");
        courseGraph.addEdge(12, 18);
        courseGraph.addEdge(14, 18);
        
        courseGraph.addCourse("CSC270");
        courseGraph.addEdge(12, 19);

        courseGraph.addCourse("CSC274");
        courseGraph.addEdge(0, 20);

        courseGraph.addCourse("CSC294");
        courseGraph.addEdge(8, 21);
        courseGraph.addEdge(14, 21);
        courseGraph.addEdge(4, 21);

        courseGraph.addCourse("JuniorStanding");
        courseGraph.addCourse("SeniorStanding");
        courseGraph.addEdge(22, 23);
        
        courseGraph.addCourse("CSC327");
        courseGraph.addEdge(22, 24);
        
        courseGraph.addCourse("CSC256");
        courseGraph.addEdge(8, 25);
        courseGraph.addEdge(3, 25);

        courseGraph.addCourse("CSC370");
        courseGraph.addEdge(22, 26);
        courseGraph.addEdge(8, 26);
        courseGraph.addEdge(1, 26);
        
        courseGraph.addCourse("CSC251");
        courseGraph.addEdge(8, 27);
        
        courseGraph.addCourse("CSC372");
        courseGraph.addEdge(22, 28);
        courseGraph.addEdge(0, 28);
        courseGraph.addEdge(8, 28);
        courseGraph.addEdge(1, 28);
        courseGraph.addEdge(2, 28);

        courseGraph.addCourse("CSC268");
        courseGraph.addEdge(8, 29);
        courseGraph.addEdge(2, 29);

        courseGraph.addCourse("CSC356");
        courseGraph.addEdge(22, 30);
        courseGraph.addEdge(8, 30);
        courseGraph.addEdge(25, 30);

        courseGraph.addCourse("CSC240");
        courseGraph.addEdge(8, 31);    
        /* OPTIONAL, FOR DEMO ONLY */
        
        while(stillRunning && runID < 90000) { 
            myCourseGraphBuilder.showMenu();
            int input; 
            String inputString = scan.nextLine(); 
            try{
                input = Integer.parseInt(inputString);
              }
              catch(Exception ex) {
                System.out.println("Please enter a valid NUMBER for the option");
                input = 999; 
              }
          
            switch(input) { 
                case 99 : //QUIT
                    System.out.println("QUITTING PROGRAM...");
                    stillRunning = false; 
                    break; 
                case 0 : 
                    if(!courseGraph.isEmpty()){ 
                        System.out.println("\nCOURSE LIST: " + courseGraph);
                    } else { 
                        System.out.println("There are no courses, enter 1 to add a course");
                    }
                    break; 
                case 1 : //Add Course
                    System.out.println("Enter name for new course:");
                    courseGraph.addCourse(scan.nextLine());
                    System.out.println("\nCOURSE LIST: " + courseGraph);
                    break; 
                case 2 : //Add Edge
                    System.out.println("Adding a prerequisite to...(ENTER COURSE NAME)");
                    String course = scan.nextLine(); 
                    System.out.println("Enter a prerequisite for..." + course + " (ENTER COURSE NAME)");
                    String prereq = scan.nextLine(); 
                    /* Only add edge if both courses exist */
                    if(courseGraph.getNametoCourse().get(course) == null || courseGraph.getNametoCourse().get(prereq) == null ){ 
                        System.out.println("Error: One or both courses do not exist");
                        break; 
                    }
                    int prereqID = courseGraph.getNametoCourse().get(prereq).getID();
                    int courseID = courseGraph.getNametoCourse().get(course).getID();
                    courseGraph.addEdge(prereqID, courseID);
                    System.out.println(courseGraph);
                    break; 
                case 3 : //Remove Course 
                    System.out.println("Current courses: " + courseGraph.getNametoCourse().keySet());
                    System.out.println("Removing the course... (ENTER COURSE NAME");
                    String courseToRemove = scan.nextLine(); 
                    /* Only remove course if it exists */
                    if(courseGraph.getNametoCourse().get(courseToRemove) == null) { 
                        System.out.println("Error: The course does not exist");
                        break; 
                    }
                    courseGraph.removeCourse(courseGraph.getNametoCourse().get(courseToRemove).getID());
                    System.out.println("\nCOURSE LIST: " + courseGraph);
                    break; 
                case 4 : //Remove Edge between classes 
                    System.out.println("Removing prerequisite from...(ENTER COURSE NAME)");
                    String courseToRemovePrereqFrom = scan.nextLine(); 
                    System.out.println("The prerequisite to remove from " + courseToRemovePrereqFrom + " is... (ENTER COURSE NAME)");
                    String prereqToRemoveFromCourse = scan.nextLine(); 
                    /* Only add edge if both courses exist */
                    if(courseGraph.getNametoCourse().get(courseToRemovePrereqFrom) == null || courseGraph.getNametoCourse().get(prereqToRemoveFromCourse) == null ){ 
                        System.out.println("Error: One or both courses do not exist");
                        break; 
                    }
                    int prereqToRemoveFromCourseID = courseGraph.getNametoCourse().get(prereqToRemoveFromCourse).getID();
                    int courseToRemovePrereqFromID = courseGraph.getNametoCourse().get(courseToRemovePrereqFrom).getID();
                    courseGraph.removeEdge(prereqToRemoveFromCourseID, courseToRemovePrereqFromID);
                    System.out.println(courseGraph);
                    break; 
                case 5 : 
                    System.out.println("Writing Course Graph to File...");
                    System.out.println("Enter a name for your course graph file");
                    String fileName = scan.nextLine(); 
                    FileTools.writeToFile(fileName + "_v" + fileVersion, courseGraph.toFileString(), false);
                    System.out.println("File Saved as " + fileName + "_v" + fileVersion+ ".txt");
                    fileVersion++; 
                    break; 
                default: 
                    System.out.println("\nInvalid option selected");
            }
        }
        System.out.println("Goodbye");
        scan.close();
    }
    
}
