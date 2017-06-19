package tech.mohitkumar.internappdesign.Models;

/**
 * Created by mohitkumar on 17/06/17.
 */

public class NotificationItem {

    String text;
    int res;

    public NotificationItem(String text, int res) {
        setText(text);
        setRes(res);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }
}
