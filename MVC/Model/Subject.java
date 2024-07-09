package MVC.Model;

public class Subject {
    private int id;
    private String name;

    private int user_Id;

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

    public int getUserId(){return user_Id;}
    public void setUser_id(int user_Id){this.user_Id = user_Id;}


    public Subject withName(String name) {
        setName(name);
        return this;
    }


    public String toString() {
        return name;
    }


}
