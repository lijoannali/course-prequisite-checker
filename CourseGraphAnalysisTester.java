public class CourseGraphAnalysisTester {
    static CourseGraphAnalysis myCourseGraphAnalysis; 

    static void testCourseGraphAnalysis(CourseGraph courseGraph) { 
        System.out.println("\nTesting Constructor: ");
        myCourseGraphAnalysis = new CourseGraphAnalysis(courseGraph); 
    }

    static void testToString() { 
        System.out.println("\nTesting ToString");
        System.out.println(myCourseGraphAnalysis);
    }

    static void testIsRequired(int prereqID, int courseID) {
        System.out.println("\nIs " + myCourseGraphAnalysis.getCourseGraph().getNameFromID(prereqID) + " required for " + myCourseGraphAnalysis.getCourseGraph().getNameFromID(courseID) + "?");
        myCourseGraphAnalysis.callIsRequired(prereqID, courseID);
    }

    static void testIsPrerequisite(int prereqID, int courseID) { 
        System.out.println("\nIs " + myCourseGraphAnalysis.getCourseGraph().getNameFromID(prereqID) + " a direct prerequisite for " + myCourseGraphAnalysis.getCourseGraph().getNameFromID(courseID) + "?");
        myCourseGraphAnalysis.callIsPrerequisite(prereqID, courseID);
    }

    static void testIsCourse(String courseName) { 
        System.out.println("\nTesting isCourse for: " + courseName);
        System.out.println(myCourseGraphAnalysis.isCourse(courseName));
    }

    

    /* Creates Graph of Computer Science Courses as example */
    static void buildCoursesCSC(CourseGraph coursesCSC) { 
        /* Building the CSC Courses Graph */
        coursesCSC.addCourse("CSC110");
        coursesCSC.addCourse("MTH153");
        coursesCSC.addCourse("MTH111");
        coursesCSC.addCourse("MTH112");
        coursesCSC.addEdge(2, 3);

        coursesCSC.addCourse("MTH211");
        coursesCSC.addEdge(1, 4);
        coursesCSC.addEdge(2, 4);

        coursesCSC.addCourse("MTH212");

        coursesCSC.addCourse("CSC120");
        coursesCSC.addEdge(0, 6);
        
        coursesCSC.addCourse("CSC205");
        coursesCSC.addEdge(3, 7);
        
        coursesCSC.addCourse("CSC210");
        coursesCSC.addEdge(6, 8);

        coursesCSC.addCourse("CSC220");
        coursesCSC.addEdge(8, 9);

        coursesCSC.addCourse("CSC223");
        coursesCSC.addEdge(8, 10);

        coursesCSC.addCourse("CSC230");
        coursesCSC.addEdge(8, 11);
        coursesCSC.addEdge(0, 11);
        
        coursesCSC.addCourse("CSC231");
        coursesCSC.addEdge(8, 12);
        
        coursesCSC.addCourse("CSC249");
        coursesCSC.addEdge(8, 13);
        
        coursesCSC.addCourse("CSC250");
        coursesCSC.addEdge(0, 14);
        coursesCSC.addEdge(1, 14);

        coursesCSC.addCourse("CSC252");
        coursesCSC.addEdge(8, 15);
        coursesCSC.addEdge(2, 15);
        coursesCSC.addEdge(1, 15);

        coursesCSC.addCourse("CSC253");
        coursesCSC.addEdge(0, 16);
        coursesCSC.addEdge(8, 16);
        coursesCSC.addEdge(1, 16);
        coursesCSC.addEdge(2, 16);

        coursesCSC.addCourse("CSC262");
        coursesCSC.addEdge(12, 17);
        
        coursesCSC.addCourse("CSC266");
        coursesCSC.addEdge(12, 18);
        coursesCSC.addEdge(14, 18);
        
        coursesCSC.addCourse("CSC270");
        coursesCSC.addEdge(12, 19);

        coursesCSC.addCourse("CSC274");
        coursesCSC.addEdge(0, 20);

        coursesCSC.addCourse("CSC294");
        coursesCSC.addEdge(8, 21);
        coursesCSC.addEdge(14, 21);
        coursesCSC.addEdge(4, 21);

        coursesCSC.addCourse("JuniorStanding");
        coursesCSC.addCourse("SeniorStanding");
        coursesCSC.addEdge(22, 23);
        
        coursesCSC.addCourse("CSC327");
        coursesCSC.addEdge(22, 24);
        
        coursesCSC.addCourse("CSC256");
        coursesCSC.addEdge(8, 25);
        coursesCSC.addEdge(3, 25);

        coursesCSC.addCourse("CSC370");
        coursesCSC.addEdge(22, 26);
        coursesCSC.addEdge(8, 26);
        coursesCSC.addEdge(1, 26);
        
        coursesCSC.addCourse("CSC251");
        coursesCSC.addEdge(8, 27);
        
        coursesCSC.addCourse("CSC372");
        coursesCSC.addEdge(22, 28);
        coursesCSC.addEdge(0, 28);
        coursesCSC.addEdge(8, 28);
        coursesCSC.addEdge(1, 28);
        coursesCSC.addEdge(2, 28);

        coursesCSC.addCourse("CSC268");
        coursesCSC.addEdge(8, 29);
        coursesCSC.addEdge(2, 29);

        coursesCSC.addCourse("CSC356");
        coursesCSC.addEdge(22, 30);
        coursesCSC.addEdge(8, 30);
        coursesCSC.addEdge(25, 30);

        coursesCSC.addCourse("CSC240");
        coursesCSC.addEdge(8, 31);
    }

    public static void main(String[] args) {
        CourseGraph coursesCSC = new CourseGraph(); 
        testToString();

        /* Building the CSC Courses Graph */
        buildCoursesCSC(coursesCSC);
        testCourseGraphAnalysis(coursesCSC);
        
        System.out.println("\nDISPLAYING ALL COURSES: ");
        System.out.println(myCourseGraphAnalysis.getCourseGraph().toDebugString());

        /* Is required */
        System.out.println("\nTesting IsRequired with invalid ID:");
        testIsRequired(-1, 3); //invalid ID
        testIsRequired(1, 8); 
        testIsRequired(2, 3);
        testIsRequired(0, 11);
        testIsRequired(0, 30);
        testIsRequired(3, 7);

        testIsPrerequisite(0,8);  
        testIsPrerequisite(6,8);  

        testIsCourse("ENG101");
        testIsCourse("CSC110");

        //myCourseGraphAnalysis.getCoursePath();
        myCourseGraphAnalysis.getCoursePath();
        myCourseGraphAnalysis.getCoursePath(8);
        myCourseGraphAnalysis.getCoursePath(30);
        myCourseGraphAnalysis.getCoursePath(14);
        myCourseGraphAnalysis.getCoursePath(21);

        int[] arr = {11}; 
        myCourseGraphAnalysis.getCoursePath(arr);
        int[] arr0 = {11, 15}; 
        myCourseGraphAnalysis.getCoursePath(arr0);
        int[] arr2 = {15, 11, 21}; 
        myCourseGraphAnalysis.getCoursePath(arr2);
    }

}
