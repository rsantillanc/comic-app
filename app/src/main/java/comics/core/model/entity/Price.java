package comics.core.model.entity;

import io.realm.RealmObject;

/**
 * Created by Renzo D. Santillán Ch. on 11/03/2017.02:26 PM
 * http://rsantillanc.pe.hu/me/
 */

public class Price extends RealmObject {
    public String type;//(string, optional): A description of the price (e.g. print price, digital price).,
    public float price;// (float, optional):
}
