public class FileToolsTester {
    static CourseGraph myCourseGraph; 

    static void testToFileString(CourseGraph courseGraph) { 
        System.out.println("\nTesting toFileString");
        System.out.println(courseGraph.toFileString());
    }

    static void testWriteToFile(String filename, String text) { 
        System.out.println("\nTesting Write To File: \nWriting to " + filename + ".txt");
        FileTools.writeToFile(filename,text, false);
    }

    static void testReadFromFile(String fileName) { 
        System.out.println("\nTesting Read from File");
        System.out.println("Trying to read: " + fileName + ".txt");
        System.out.println("File contents: ");
        System.out.println(FileTools.readFromFile(fileName));
    }

    static void testFiletoCourseGraph(String fileName, CourseGraph newCourseGraph) { 
        System.out.println("\nTesting Conversion of File to Course Graph");
        System.out.print("CourseGraph generated from " + fileName + ".txt:\n");
        System.out.println(FileTools.fileToCourseGraph(fileName, newCourseGraph).toDebugString());
    }

    public static void main(String[] args) {
        CourseGraph myCourseGraph = new CourseGraph(); 
        /* Build a sample course graph */
        myCourseGraph.addCourse("CSC110");
        myCourseGraph.addCourse("MTH153");
        myCourseGraph.addCourse("MTH111");
        myCourseGraph.addCourse("MTH112");
        myCourseGraph.addEdge(2, 3);

        myCourseGraph.addCourse("MTH211");
        myCourseGraph.addEdge(1, 4);
        myCourseGraph.addEdge(2, 4);

        myCourseGraph.addCourse("MTH212");

        myCourseGraph.addCourse("CSC120");
        myCourseGraph.addEdge(0, 6);
        
        myCourseGraph.addCourse("CSC205");
        myCourseGraph.addEdge(3, 7);
        
        myCourseGraph.addCourse("CSC210");
        myCourseGraph.addEdge(6, 8);

        myCourseGraph.addCourse("CSC220");
        myCourseGraph.addEdge(8, 9);

        myCourseGraph.addCourse("CSC223");
        myCourseGraph.addEdge(8, 10);

        myCourseGraph.addCourse("CSC230");
        myCourseGraph.addEdge(8, 11);
        myCourseGraph.addEdge(0, 11);

        myCourseGraph.addCourse("CSC240");
        myCourseGraph.addEdge(8, 12);

        /* Test the creation of the string to write to file */
        testToFileString(myCourseGraph);
        System.out.print("\nOriginal Course Graph: ");
        System.out.println(myCourseGraph.toDebugString());
        
        /* Test write to file */
        testWriteToFile("myCourseGraph", myCourseGraph.toFileString());
        
        /* Test Read From File */
        testReadFromFile("123abc");
        testReadFromFile("myCourseGraph");

        //Test Conversion of File to Course Graph
        CourseGraph myCourseGraphCopy = new CourseGraph(); 
        testFiletoCourseGraph("123ABC", myCourseGraphCopy);
        testFiletoCourseGraph("myCourseGraph", myCourseGraphCopy);
    }

}
