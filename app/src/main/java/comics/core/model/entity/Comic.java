package comics.core.model.entity;


import java.util.List;

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
    private List<Image> images;
    private Image thumbnail;

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

    public Image getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Comic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", date=" + date +
                ", images=" + images.size() +
                ", thumbnail=" + thumbnail.toString() +
                '}';
    }
}
