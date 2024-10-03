# Course Prerequisite Checker 

This program is a tool for modeling course prerequisite relationships using directed acyclic graphs.
It allows users to build a graph of courses and their prerequisites, modify the graph by adding or removing courses and edges, and run various analyses such as checking prerequisite relationships and generating valid course paths.

An example is provided based on the computer science curriculum at Smith College to demonstrate the program. 

### User Manual:  

#### Steps to Begin Using the Program:  
1. Download all the files, and navigate to the directory containing them from the terminal or command line.  
2. Compile all `.java` files.  
3. Execute `CourseGraphBuilder.java` (Make sure to run from the terminal OR open a folder in VSCode, or else the write-to-file command will not save anything).  
4. Write `CourseGraph` to file.  
5. Execute `CourseGraphAnalyzer`.  
6. Enter the name of the file without the extension.  
7. Run analyses on the file.  

#### Notes:  
- A `CourseGraph` refers to a directed acyclic graph represented by an adjacency list of in-edges, in which vertices represent courses, and edges point from prerequisites to the courses that require them.  
- The program aims to catch errors caused by invalid course name inputs or invalid menu option inputs.  
- `CourseGraphBuilder.java` has some pre-added courses and relationships, representing the CSC courses at Smith College. The included file `coursesCSC.txt` is a sample text representation of this CSC major `CourseGraph`.  

---

### Running `CourseGraphBuilder`:  

- **99**  
  Ends or quits the program. Quit will also occur if the program is run 90,000 times (a safeguard against infinite loops caused by bugs).  

- **0**  
  Lists all courses with their prerequisites, all with ID numbers. Some courses and relationships are added beforehand but can be commented out to add courses and edges from scratch.

- **1**  
  Add a course: Enter a course name to add it to the list. An ID will be generated for it.  

- **2**  
  Add an edge from a prerequisite to a course. Enter the course name, then the prerequisite. If both exist and there’s no parallel or self-edge, a new edge is added.

- **3**  
  Remove a course: Enter the course name to remove the course and its associated edges from the graph.

- **4**  
  Remove an edge from a prerequisite to a course. Enter the course name, then the prerequisite. Both must exist for the operation to succeed.

- **5**  
  Write to file: Works only in VSCode with an open folder, or from the terminal. Prompts for a filename and writes the `CourseGraph` to a text file. A version number is appended to avoid duplicate names.

---

### Running `CourseGraphAnalyzer`:  

- The driver catches invalid (string) inputs where integers are expected.  
1. Create a `CourseGraph` using `CourseGraphBuilder`, then write it to a text file.  
2. Execute `CourseGraphAnalyzer.java`, and enter the name of a `.txt` `CourseGraph` file located in the working directory.  
   
   **Possible Bugs**:  
   While testing has not revealed errors, some `.txt` files may be invalid or empty. If the entered file name is invalid, the program will prompt for reentry up to 10 attempts before quitting.

- **99**  
  Ends the program. Includes a safeguard for infinite loops after 90,000 runs.  

- **0**  
  Lists all courses in the `CourseGraph` representing a major.  

- **1**  
  Search for a course: Enter a course name to see its prerequisites or a message if it’s not found.  

- **2**  
  Check if a course A is required for B. Enter course B, then A, to check if A is a prerequisite for B. If it is, the program shows the course progression (e.g., 110→120→210).  

- **3**  
  Check if A is a direct prerequisite for B. This answers whether A has a stored prerequisite relationship to B.  

- **4**  
  Generate a course path:  
  - **0**: Generates a topological order of all courses in the `CourseGraph`, showing a valid sequence to satisfy all prerequisites.  
  - **1**: Generates a topological order of courses needed to qualify for a specific course entered by the user.  

---

### Explanation of Examples and Testers  

- **CourseGraphTester.java**  
  Tests methods like `IsEmpty`, `toString`, adding/removing courses and edges. A `CourseGraph` is initialized, built, and modified from scratch in this tester.  

- **CourseGraphAnalysisTester.java**  
  Uses the built-in CSC `CourseGraph` to test analysis methods (`IsRequired`, `IsPrerequisite`, etc.) and course path generation methods on the CSC courses.  

- **FileToolsTester.java**  
  Uses a prebuilt `CourseGraph` to write the string representation to a text file and then reads the file back, creating a copy of the original `CourseGraph`.  


## What I learned 
- The theory behind graph representations, and the reasoning for using an adjacency list over an adjacency matrix for sparse graphs 
- By using a Hash Map, I made the program more user friendly: Now, users could modify the graph and run analyses using course names rather than arbitrary ID values, which change if vertices are ever removed or re-added, making them easy to confuse
- How to catch invalid inputs given to the Drivers through the scanner when it was expecting an integer but got a String
- The topological sort algorithm's implementation

## External References 
- One of the example codes used to check if there is a path between two vertices was the inspiration for the IsRequired method. I used the logic of the method on this page and added more error checks and a way to track the path without including duplications to adapt the logic for my program. https://course.ccs.neu.edu/cs2510su18/lecture30.html
* Used provided code from here to read in files and write to files https://www.youtube.com/watch?v=XC6Oj-Pn_mc
https://www.youtube.com/watch?v=k3K9KHPYZFc



## Version History 
1a_1b - Basic classes were written to represent the directed graph (CourseGraph) and the constructor, toString, and accessor/manipulator functions for that class and the vertex class (Course) were implemented and tested. 


2a - Add vertex (course) and add edge functions were implemented and tested 


2a1 - A modified version of DFS was used to implement the IsPrerequisite function (see User Manual)


2b - Interactive Driver for creating a course graph and adding courses and edges was made 


2b_ExtendedGeneric - Added a generic Vertex class that Course extends. 


3a - Added, tested, debugged methods to delete vertex and edge


3b- Extra debugging of vertex deletion 


3c_CSCcourses - Added sample CSC major courses and course relationships into the Analysis class, which was also created and tested at this stage 


3d_AddedIsPrereq - Reorganized code, moved all analysis methods like IsPrereq to the CourseGraphAnalysis class 


3d_DFS_isRequired - Implemented DFS- based method to check if course is required 


4a-TopoSort- Implemented DFS- based topological sort to generate course paths 


6a_DriverForCourseAnalysis - Added IsPrereq, IsRequired, to CourseGraphAnalyzer, the driver at this stage which allows for user interaction with CourseAnalysis 


6b_DriverWithCoursePath - Added topological sorting methods to CourseGraphAnalyzer 


7a_BetterVariableNames- Rehaul of variable names to remove inconsistencies and confusion 


8a_ReadandWrite - Implemented FileTools class that has methods to write a string to a .txt file, read a .txt file and return a string with its contents, and convert a .txt file to 
9 - Cleanup of code, variable names, formatting and organization
