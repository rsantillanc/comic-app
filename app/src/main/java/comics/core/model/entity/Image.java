package comics.core.model.entity;

/**
 * Created by Renzo D. Santillán Ch. on 10/03/2017.11:48 PM
 * http://rsantillanc.pe.hu/me/
 */

public class Image extends BaseEntity {
    //The directory path of to the image.,
    public String path;
    //The file extension for the image.
    public String extension;

    @Override
    public String toString() {
        return "Image{" +
                "path='" + path + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }
}
