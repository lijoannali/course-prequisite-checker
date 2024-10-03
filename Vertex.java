public class Vertex<T> { 
    private int id; 
    private T name;

    /* Constructor */
    public Vertex(int id, T name) { 
        this.id = id; 
        this.name = name; 
    }

    /* Getters and Setters */
    public int getID() { 
        return id; 
    }

    public T getName() { 
        return name; 
    }

    public void setID(int id) { 
        this.id = id; 
    }

}