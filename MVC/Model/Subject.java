package MVC.Model;

public class Subject {
    private int id;
    private String name;

    private int subjectId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubjectId(int subjectId){
        this.subjectId = subjectId ;
    }

    public int getSubjectId(){return id;}

    public Subject withName(String name) {
        setName(name);
        return this;
    }


    public String toString() {
        return name;
    }

}
