package comics.ui.custom.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by andree on 24/08/2016.
 */
public class VerticalRectangleImageView extends AppCompatImageView {
    public VerticalRectangleImageView(Context context) {
        super(context);
    }

    public VerticalRectangleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalRectangleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), (int) (getMeasuredWidth() + (getMeasuredWidth() * 0.25)));
    }
}
