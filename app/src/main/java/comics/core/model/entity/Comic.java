package comics.core.model.entity;


/**
 * Created by Renzo D. Santillán Ch. on 10/03/2017.11:50 PM
 http://rsantillanc.pe.hu/me/
 */

public class Comic extends BaseEntity {
    private int id;
    private String title;
    private String description;
    private float price;
    private long date;
    private Image image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
