import java.util.Scanner;
public class CourseGraphAnalyzer {
    
/* Prints Menu */
public void showMenu() { 
    System.out.println("\nOPTIONS ========== \n 99  QUIT  \n 0   List all Courses \n 1   Search for course \n 2   Check if course is required to qualify for another course \n 3   Check if course is a direct prerequisite to another course \n 4   Generate a course path ");
    System.out.println("SELECT OPTION: ");
}

    public static void main(String[] args) {
        boolean stillRunning = true; 
        int runID = 1; 
        boolean keepPrompting = true; 
        int fileUploadAttempts = 1; 

        CourseGraphAnalyzer courseGraphAnalyzer = new CourseGraphAnalyzer();
        Scanner scan = new Scanner(System.in); 

        CourseGraph courses = new CourseGraph();
        //File Upload 
        while(keepPrompting && fileUploadAttempts < 11) {
            System.out.println("COURSE GRAPH ANALYZER =====");
            System.out.println("\nEnter the name of the file (Do not include .txt): ");
            System.out.println("ATTEMPT " + fileUploadAttempts + " of 10" );
            String input = scan.nextLine(); 
            courses = FileTools.fileToCourseGraph(input, courses); 
            if(courses.isEmpty()) { 
                System.out.println("The file you tried to upload does not exist, or contains an empty or invalid Course Graph.");
            } else { 
                keepPrompting = false; 
                System.out.println("File was successfully uploaded");
            }
            fileUploadAttempts++; 
            if(fileUploadAttempts == 11) { 
                System.out.println("Limit reached for file upload attempts, \nQUITTING PROGRAM...");
                stillRunning = false; 
            }
        }

        CourseGraphAnalysis myCourseGraphAnalysis = new CourseGraphAnalysis(courses); 
        while(stillRunning && runID < 90000) { 
            courseGraphAnalyzer.showMenu();
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
                case 0 : //List out all courses
                    if(!myCourseGraphAnalysis.getCourseGraph().isEmpty()){ 
                        System.out.println("\nListing all " + myCourseGraphAnalysis.getCourseGraph().getAdjacencyList().size() + " courses:" + myCourseGraphAnalysis.listCourses());
                    } else { 
                        System.out.println("There are no courses in this course graph");
                    }
                    break; 
                case 1 : //Search for a course, and list the course with prereqs if it exists
                    System.out.println("Searching for... (ENTER COURSE NAME):");
                    String searchedCourse = scan.nextLine(); 
                    if(myCourseGraphAnalysis.isCourse(searchedCourse)) { 
                        System.out.print("Course found: ");
                        System.out.println(myCourseGraphAnalysis.getCourseGraph().getNametoCourse().get(searchedCourse));
                        // Print prerequisites of Course
                        System.out.print("Prerequisites: ");
                        if(myCourseGraphAnalysis.getCourseGraph().getNametoCourse().get(searchedCourse).getAdjacentVertices().size() == 0) { 
                            System.out.println("None");
                            break; 
                        }
                        for(int j = 0; j < myCourseGraphAnalysis.getCourseGraph().getNametoCourse().get(searchedCourse).getAdjacentVertices().size(); j++) { 
                            int edgeID = myCourseGraphAnalysis.getCourseGraph().getNametoCourse().get(searchedCourse).getAdjacentVertices().get(j); 
                            System.out.print(" " + myCourseGraphAnalysis.getCourseGraph().getAdjacencyList().get(edgeID)); 
                        }
                        System.out.println("\n");
                        break;
                    } 
                    System.out.println("Error: Course not found");
                    break; 
                case 2 : //Check if Course A is required for Course B 
                    System.out.println("What is the course you want to qualify for? (ENTER COURSE NAME)");
                    String course = scan.nextLine(); 
                    System.out.println("You want to check if qualifying for " + course + " requires you to take... (ENTER COURSE NAME)");
                    String requirement = scan.nextLine(); 
                    /* Display results of the requirements analysis and/or course progression */
                    if(myCourseGraphAnalysis.getCourseGraph().getNametoCourse().get(course) == null || myCourseGraphAnalysis.getCourseGraph().getNametoCourse().get(requirement) == null ){ 
                        System.out.println("Error: One or both courses do not exist");
                        break; 
                    }
                    int requirementID = myCourseGraphAnalysis.getCourseGraph().getNametoCourse().get(requirement).getID();
                    int courseID = myCourseGraphAnalysis.getCourseGraph().getNametoCourse().get(course).getID();
                    myCourseGraphAnalysis.callIsRequired(requirementID, courseID); 
                    break; 
                case 3 : //Check if Course A is a direct prereq for Course B
                System.out.println("What is the course you want to qualify for? (ENTER COURSE NAME)");
                    String courseGoal = scan.nextLine(); 
                    System.out.println("You want to check if a direct prerequisite of " + courseGoal + " is... (ENTER COURSE NAME)");
                    String prereq = scan.nextLine(); 
                    /* Display results of the requirements analysis and/or course progression */
                    if(myCourseGraphAnalysis.getCourseGraph().getNametoCourse().get(courseGoal) == null || myCourseGraphAnalysis.getCourseGraph().getNametoCourse().get(prereq) == null ){ 
                        System.out.println("Error: One or both courses do not exist");
                        break; 
                    }
                    int prereqID = myCourseGraphAnalysis.getCourseGraph().getNametoCourse().get(prereq).getID();
                    int courseGoalID = myCourseGraphAnalysis.getCourseGraph().getNametoCourse().get(courseGoal).getID();
                    myCourseGraphAnalysis.callIsPrerequisite(prereqID, courseGoalID); 
                    break; 
                    case 4 : //Build course path
                    System.out.println("Choose a type of course path to build: \n 0 Path through entire major \n 1 Path to a single course");
                    String pathInputString = scan.nextLine(); 
                    try{
                        input = Integer.parseInt(pathInputString);
                        }
                        catch(Exception ex) {
                        System.out.println("Error: Must enter a valid NUMBER for the option");
                        input = 999; 
                        }
                        switch(input) { 
                        case 0: 
                            myCourseGraphAnalysis.getCoursePath();
                            break; 
                        case 1: //Course path with single destination major 
                            System.out.println("You want to find a path through the major which allows you to qualify for... (ENTER COURSE NAME)");
                            int destinationCourseID = myCourseGraphAnalysis.getCourseGraph().getNametoCourse().get(scan.nextLine()).getID();
                            myCourseGraphAnalysis.getCoursePath(destinationCourseID);
                            break; 
                        default: 
                            System.out.println("No option selected");
                        }
                    break; 
                default: 
                    System.out.println("\nNo option selected");
            }
        }
        System.out.println("Goodbye");
        scan.close();
    }

}
