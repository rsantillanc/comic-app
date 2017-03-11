package comics.core.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Renzo D. Santillán Chavez on 10/03/2017.
 */

public class ComicDataWrapper extends BaseEntity{

    private int code;// The HTTP status code of the returned result.,
    private String status;//(string, optional): A string description of the call status.,
    private final ArrayList<Comic> comics = new ArrayList<>();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Comic> getComics() {
        return comics;
    }

    public void addComic(Comic comic) {
        comics.add(comic);
    }

    @Override
    public String toString() {
        return "ComicDataWrapper{" +
                "code=" + code +
                ", status='" + status + '\'' +
                ", comics=" + comics.size() +
                '}';
    }
}
