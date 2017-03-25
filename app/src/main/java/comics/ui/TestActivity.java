package comics.ui;

import android.os.Bundle;

import pe.nextdots.comics.R;

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createToolbar(true, R.layout.custom_toobar_title);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }
}
