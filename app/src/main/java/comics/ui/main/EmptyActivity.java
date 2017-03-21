package comics.ui.main;

import android.os.Bundle;

import comics.ui.BaseActivity;
import pe.nextdots.comics.R;

public class EmptyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createToolbar(false, null);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail2;
    }
}
