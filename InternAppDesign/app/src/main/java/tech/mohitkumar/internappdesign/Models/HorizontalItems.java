package tech.mohitkumar.internappdesign.Models;

/**
 * Created by mohitkumar on 10/07/17.
 */

public class HorizontalItems {

    String title;
    int image;

    public HorizontalItems(String title, int image) {
        setTitle(title);
        setImage(image);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
