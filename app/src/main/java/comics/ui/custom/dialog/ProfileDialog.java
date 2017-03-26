package comics.ui.custom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import comics._utility.C;
import comics.core.view.ViewCallback;
import de.hdodenhof.circleimageview.CircleImageView;
import pe.nextdots.comics.R;

/**
 * Created by Renzo D. Santillán Ch. on 26/03/2017.01:17 AM
 * http://rsantillanc.pe.hu/me/
 */

public class ProfileDialog extends DialogFragment {

    private ViewCallback<View> callback;

    public static ProfileDialog newInstance(ViewCallback<View> callback) {
        ProfileDialog dialog = new ProfileDialog();
        dialog.setCallback(callback);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(STYLE_NO_TITLE);
        return dialog;
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        if (getDialog().getWindow() != null) {
//            getDialog().getWindow().setLayout(
//                            ViewGroup.LayoutParams.MATCH_PARENT,
//                            ViewGroup.LayoutParams.WRAP_CONTENT);
//        }
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_profile, container);
        SharedPreferences pref = getActivity().getSharedPreferences(C.DEFAULT_DATE, Context.MODE_PRIVATE);
        setupUserPhoto(view, pref);
        setupUserName(view, pref);
        setupUserEmail(view, pref);
        setupUserLogout(view);
        return view;
    }

    private void setupUserLogout(View view) {
        view.findViewById(R.id.logout_text_v).setOnClickListener(view1 -> getCallback().done(view));
    }

    private void setupUserEmail(View view, SharedPreferences pref) {
        ((AppCompatTextView) view.findViewById(R.id.email_text_v)).setText(pref.getString(C.Key.USER_EMAIL, C.EMPTY));
    }

    private void setupUserName(View view, SharedPreferences pref) {
        ((AppCompatTextView) view.findViewById(R.id.name_text_v)).setText(pref.getString(C.Key.USER_NAME, C.EMPTY));
    }

    private void setupUserPhoto(View view, SharedPreferences pref) {
        Glide.with(getActivity())
                .load(pref.getString(C.Key.USER_PHOTO, C.EMPTY))
                .into((CircleImageView) view.findViewById(R.id.profile_image));
    }

    public ViewCallback<View> getCallback() {
        return callback;
    }

    public void setCallback(ViewCallback<View> callback) {
        this.callback = callback;
    }
}
