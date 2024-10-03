import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;

/* Contains methods for reading from and writing to files and related helper(s)*/
public class FileTools {

    /* Write to file */
    /* Takes in a string and filename and outputs it to a file */
    /* If the filename matches that of an existing file and doNotOverwrite is true, the string will be append to the file */ 
    /* If it is false, the string will replace the file's existing contents */
    public static void writeToFile(String fileName, String text, boolean doNotOverwrite) { 
        try {
            File f = new File(fileName); 
            FileWriter fw = new FileWriter(f, doNotOverwrite); 
            PrintWriter pw = new PrintWriter(fw); 
            pw.println(text);
            pw.close();
        } catch (IOException e){ 
            System.err.println("Error occured while writing course graph to file");
        }
    }

    /* Read from file */
    /* Takes in the String name of a file, and returns the file's contents in a String */
    public static String readFromFile(String fileName) { 
        String text = ""; 
        try { 
            Scanner scan = new Scanner(new File(fileName));
            while (scan.hasNextLine()) { 
                text = scan.nextLine(); 
            }
        } catch (FileNotFoundException e) { 
            System.out.println("Error, file not found");
        }
        return text; 
    }

    /* Helper function to parse ints */
    public static int stringToInt(String str) { 
        try{ 
            return Integer.parseInt(str); 
        } catch (NumberFormatException e) {
            return -999; //Would cause an invalid/empty CourseGraph to be Formed, which is caught by CourseGraphAnalyzer
        }
    }

    /* Convert file to graph */
    public static CourseGraph fileToCourseGraph(String fileName, CourseGraph newCourseGraph) { 
        String graphString = readFromFile(fileName); 
        String[] splitGraphString = new String[2]; 
        /* Parse course names and add them as vertices */
        splitGraphString = graphString.split(","); 
        for(int i = 0; i < splitGraphString.length-1; i ++) { 
            newCourseGraph.addCourse(splitGraphString[i]);
        }
        /* Parse out edges and add them */
        String[] splitEdgesString = splitGraphString[splitGraphString.length-1].split("#"); //Edges are stored like this: prequisiteID#courseID#prerequisiteID#courseID
        int j = 1; 
        while(j < splitEdgesString.length) { 
            newCourseGraph.addEdge(stringToInt(splitEdgesString[j]), stringToInt(splitEdgesString[j+1]));
            j += 2; 
        } 
        return newCourseGraph; 
    }

}
