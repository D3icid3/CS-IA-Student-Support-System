package MVC.Model;

import java.text.MessageFormat;

public class Card {

    private int id;
    private int subjectId;
    private String front;
    private String back;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String toString() {
        return MessageFormat.format("[{0}]/[{1}]", front, back);
    }
}
