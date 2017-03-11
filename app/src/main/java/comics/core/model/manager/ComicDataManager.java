package comics.core.model.manager;

import android.util.Log;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import comics._utility.C;
import comics.core.model.deserialize.ComicDeserializer;
import comics.core.model.entity.ComicDataWrapper;
import comics.core.model.rest.Connection;
import comics.core.model.rest.RestAdapter;
import comics.core.presenter.Operation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Renzo D. Santillán Ch. on 10/03/2017.01:14 AM
 * http://rsantillanc.pe.hu/me/
 */

public class ComicDataManager extends BaseDataManager {


    private int limit;
    private Operation operation;

    private ComicDataManager(Operation operation) {
        this.operation = operation;
        createRestApi();
    }


    public static ComicDataManager newInstance(Operation operation) {
        return new ComicDataManager(operation);
    }


    public void addLimit(int _limit) {
        this.limit = _limit;
    }

    @Override
    void createRestApi() {
        RestAdapter restAdapter = new RestAdapter(Connection.URL_BASE);
        ComicDeserializer deserialize = new ComicDeserializer();
        api = restAdapter.createApiWithCustomConverter(ComicDataWrapper.class, deserialize);
    }

    public void getComicsFromServer() {
        getComics();
    }

    private void getComics() {
        api.getComics(limit, apiKey, timestamp, hash).enqueue(new Callback<ComicDataWrapper>() {
            @Override
            public void onResponse(Call<ComicDataWrapper> call, Response<ComicDataWrapper> response) {
                onRestResponse(response, COMIC);
            }

            @Override
            public void onFailure(Call<ComicDataWrapper> call, Throwable t) {
                onRestFailure(t);
            }
        });
    }


    @Override
    public void onRestResponse(Response response, @Type int type) {

        switch (type) {
            case COMIC:
                if (response.isSuccessful()) {
                    operation.onDone(response.body());
                } else
                    handlerError(response.code(), response.message());
                break;
        }

        //Always notify to hide loader or disabled another view.
        operation.onComplete();
    }

    @Override
    public void onRestFailure(Throwable exception) {
        Log.d(C.Tag.REST, "onRestFailure " + exception.getMessage());

        if (exception instanceof SocketTimeoutException)
            //When server doesn't respond.
            operation.onTimeout();
        else if (exception instanceof ConnectException)
            //Can't connect with server.
            operation.onConnectError();
        else
            //Unknow error
            operation.onError(exception.getMessage());


        //Always notify to hide loader or disabled another view.
        operation.onComplete();
    }


    /**
     * Here, you may implement Marvel's server error.
     *
     * @param code    e.g. 409
     * @param message message from Marvel's server.
     */
    private void handlerError(int code, String message) {
        switch (code) {
            case 409:
                operation.onError(message);
                break;
        }
    }
}
