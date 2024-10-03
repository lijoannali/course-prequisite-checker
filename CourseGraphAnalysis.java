import java.util.LinkedList;


public class CourseGraphAnalysis { 
    CourseGraph courseGraph; 

    /* Constructor */
    public CourseGraphAnalysis(CourseGraph courseGraph) { 
        this.courseGraph = courseGraph; 
    }

    /* toString */
    public String toString() { 
        return courseGraph.toString(); 
    }
    
    /* listCourses */
    /* Returns a String with all the courses in the CourseGraph */
    public String listCourses() { 
        String courseList = "";
        for(int i = 0; i < courseGraph.getAdjacencyList().size(); i++) { 
            courseList += "\n" + courseGraph.getNameFromID(i); 
        } 
        return courseList; 
    }

    /* isCourse */
    /* Returns true if the String entered is the name of a course, false if it is not */
    public boolean isCourse(String courseName){ 
        return courseGraph.getNametoCourse().containsKey(courseName); 
    }

    /* callIsPrerequisite */
    /* Wrapper for IsPrerequisite */
    /* Prints messages indicating Yes if the course with prereqID is a direct prerequisite for the course with courseID */
    /* Prints out the course progression from the prerequisite to the course */
    /* Otherwise, prints message saying No, it is not a preqrequisite */
    public void callIsPrerequisite(int prereqID, int courseID) { 
        if(isPrerequisite(prereqID, courseID)) { 
            System.out.println("Yes, " + courseGraph.getNameFromID(prereqID) + " is a direct prerequisite for " +  courseGraph.getNameFromID(courseID) );
            return; 
        } 
        System.out.println("No, " + courseGraph.getNameFromID(prereqID) + " is not a direct prerequisite for " +  courseGraph.getNameFromID(courseID) );
    }
    
    /* isPrerequisite */
    /* Returns true if the course with prereqID is a direct prerequisite of the course with courseID */
    public boolean isPrerequisite(int prereqID, int courseID) { 
        return courseGraph.getAdjacencyList().get(courseID).getAdjacentVertices().contains(prereqID); 
    }

    /* isRequired */
    /* Returns true if the course with the id prereqID is required to qualify for the course with id courseID */
    /* Builds a list of the course progression from the required prerequisite to the course */
    /* Takes in and adds to a LinkedList the course progression from the prerequisite to the course */
    public boolean isRequired(int prereqID, int courseID, LinkedList<String> courseProgression) { 
        Course course = courseGraph.getAdjacencyList().get(courseID); 
        boolean isRequired = false;
        
        for(int i = 0; i < course.getAdjacentVertices().size(); i++) {
            if (course.getAdjacentVertices().get(i) == prereqID || isRequired(prereqID, course.getAdjacentVertices().get(i), courseProgression)) { 
                /* Add course to progression */
                if(!courseProgression.contains(courseGraph.getNameFromID(course.getAdjacentVertices().get(i)))) { 
                    courseProgression.add(courseGraph.getNameFromID(course.getAdjacentVertices().get(i)));
                }
                isRequired = true; 
            }
        }
        return isRequired; 
    }

    /* (Helper version of IsRequired for getCoursePath) */
    /* Returns true if the course with the id prereqID is required to qualify for the course with id courseID */
    /* Does not create a course progression */
    public boolean isRequired(int prereqID, int courseID) { 
        Course course = courseGraph.getAdjacencyList().get(courseID); 
        boolean isRequired = false;
        
        for(int i = 0; i < course.getAdjacentVertices().size(); i++) {
            if (course.getAdjacentVertices().get(i) == prereqID || isRequired(prereqID, course.getAdjacentVertices().get(i))) { 
                isRequired = true; 
            }
        }
        return isRequired; 
    }

    /* callIsRequired */
    /* Wrapper for IsRequired */
    public void callIsRequired(int prereqID, int courseID) {
        LinkedList<String> courseProgression = new LinkedList<String>();
        if (isRequired(prereqID, courseID, courseProgression)) { 
            System.out.println("Yes, " + courseGraph.getNameFromID(prereqID) + " is required to take " + courseGraph.getNameFromID(courseID));
            System.out.println("Course progression from " + courseGraph.getNameFromID(prereqID) + " to " + courseGraph.getNameFromID(courseID) + ":");
            for(int i = 0; i < courseProgression.size(); i++) { 
                System.out.println(i+1 + ". " + courseProgression.get(i));
            }
            System.out.print(courseProgression.size() + ". " + courseGraph.getNameFromID(courseID) + "\n");
        } else { 
            System.out.println("No, " + courseGraph.getNameFromID(prereqID) + " is not required to take " + courseGraph.getNameFromID(courseID));
        }
    }

    /* getCoursePath - Whole Major */
    /* Wrapper for sortCourses */
    /* Prints a valid order in which to take all the courses in the major which respects prerequisite requirements */
    public void getCoursePath() { 
        LinkedList<Course> sortedCourses = new LinkedList<Course>(); //stores courses in order according to topological sorting
        LinkedList<Course> visited = new LinkedList<Course>(); //store courses visited by depth first search traversal
        for(int i = 0; i < courseGraph.getAdjacencyList().size(); i++) { 
            if(!visited.contains(courseGraph.getAdjacencyList().get(i))) { 
                sortCourses(courseGraph.getAdjacencyList().get(i), visited, sortedCourses); //By default, starts at Course with id=0
            }
        }
        System.out.println("\nYou can take all " + sortedCourses.size() + " courses in the major in this order: ");
        
        for(int i = 0; i < sortedCourses.size(); i++) { 
            System.out.println(i+1 + ". " + sortedCourses.get(i)); 
        }
    }
    /* sortCourses for Whole Major Course Path */
    /* Builds a sorted list with all the courses in the major in a order of completion which respects preqrequisite requirements */
    public void sortCourses(Course course, LinkedList<Course> visited, LinkedList<Course> sortedCourses) { 
        visited.add(course);
        if(courseGraph.getAdjacencyList().contains(course)) { 
            for(int i = 0; i < course.getAdjacentVertices().size(); i++) { //If make this interactive, can have a choose your own adventure- build my path method
                Course prerequisite = courseGraph.getAdjacencyList().get(course.getAdjacentVertices().get(i)); 
                if(!visited.contains(prerequisite)) { 
                    sortCourses(prerequisite, visited, sortedCourses);
                }
            }
        }
        sortedCourses.add(course); 
    }

    /* getCoursePath with destination course */
    /* Wrapper for sortCourses given a destination course */
    /* Returns a valid order in which to qualify for the destination course which respects preqrequisite requirements */
    public void getCoursePath(int destinationCourseID) { 
        LinkedList<Course> sortedCourses = new LinkedList<Course>(); //courses in order according to topological sort
        LinkedList<Course> visited = new LinkedList<Course>(); //courses visited by depth first search
        for(int i = 0; i < courseGraph.getAdjacencyList().size(); i++) { 
            if(!visited.contains(courseGraph.getAdjacencyList().get(i)) && !visited.contains(courseGraph.getAdjacencyList().get(destinationCourseID))) { 
                sortCourses(courseGraph.getAdjacencyList().get(i), visited, sortedCourses, destinationCourseID); //By default, starts at Course with id=0
            }
        }
        System.out.println("\nYou can complete the requirement(s) below in the following order to qualify for " + courseGraph.getNameFromID(destinationCourseID));
        
        for(int i = 0; i < sortedCourses.size(); i++) { 
            System.out.println(i+1 + ". " + sortedCourses.get(i)); 
        }
    }

    /* getCoursePath given multiple destination courses */ //Not included in CourseGraphAnalyzer
    /* Wrapper for sortCourses given a destination course */
    /* Returns a valid order in which to qualify for the destination course which respects preqrequisite requirements */
    public void getCoursePath(int[] arrDestinationCourseIDs) { 
        LinkedList<Course> sortedCourses = new LinkedList<Course>(); //courses in order according to topological sort
        LinkedList<Course> visited = new LinkedList<Course>(); //courses visited by depth first search
        for(int i = 0; i < arrDestinationCourseIDs.length; i++) { 
            if(!visited.contains(courseGraph.getAdjacencyList().get(arrDestinationCourseIDs[i]))) { 
                sortCourses(courseGraph.getAdjacencyList().get(arrDestinationCourseIDs[i]), visited, sortedCourses, arrDestinationCourseIDs[i]); 
            }
        }
        
        System.out.println("\nYou can complete the requirement(s) below in the following order to qualify for");
        for(int i = 0; i < arrDestinationCourseIDs.length-1; i++){ 
            System.out.print(courseGraph.getNameFromID(arrDestinationCourseIDs[i]) + ", ");
        }
        System.out.println(courseGraph.getNameFromID(arrDestinationCourseIDs[arrDestinationCourseIDs.length-1]));
        for(int i = 0; i < sortedCourses.size(); i++) { 
            System.out.println(i+1 + ". " + sortedCourses.get(i)); 
        }
    }

    /* sortCourses towards destination course(s) */
    /* Returns a list with all the courses in the major in a order of completion which respects preqrequisite requirements */
    public void sortCourses(Course course, LinkedList<Course> visited, LinkedList<Course> sortedCourses, int destinationCourseID) { 
        visited.add(course);
        if(courseGraph.getAdjacencyList().contains(course)) { 
            for(int i = 0; i < course.getAdjacentVertices().size(); i++) { 
                Course prerequisite = courseGraph.getAdjacencyList().get(course.getAdjacentVertices().get(i)); 
                if(!visited.contains(prerequisite)) { 
                    sortCourses(prerequisite, visited, sortedCourses);
                }
            }
        }
        if(isRequired(course.getID(), destinationCourseID)) { 
            sortedCourses.add(course); 
        }
    }

    /* Getters and Setters */
    public CourseGraph getCourseGraph() { 
        return courseGraph; 
    }
}