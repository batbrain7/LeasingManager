package tech.mohitkumar.internappdesign.Models;

/**
 * Created by mohitkumar on 18/06/17.
 */

public class CommentData {
    String comments;
    String time;

    public CommentData(String comments, String time) {
        setComments(comments);
        setTime(time);
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
