# Course Prerequisite Checker 

## Implementation Details

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
