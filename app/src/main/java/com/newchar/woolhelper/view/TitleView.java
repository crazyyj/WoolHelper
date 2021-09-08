package com.newchar.woolhelper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.newchar.woolhelper.R;

/**
 * @author newChar
 * date 2021/9/7
 * @since 当前版本，（以及描述）
 * @since 迭代版本，（以及描述）
 */
public class TitleView extends RelativeLayout {

    private ImageButton mLeftImageButton;
    private ImageButton mIvCommonTitleRightMore;
    private TextView mTvCommonTitleCenterText;

    public TitleView(Context context) {
        super(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTitleText(String text) {
        mTvCommonTitleCenterText.setText(text);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (isInEditMode()) {
            TypedValue typedValue = new TypedValue();
            getContext().getTheme().resolveAttribute(android.R.attr.actionBarSize, typedValue, true);
            int actionBarHeight = getResources().getDimensionPixelSize(typedValue.resourceId);
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            layoutParams.width = LayoutParams.MATCH_PARENT;
            layoutParams.height = actionBarHeight;
            setLayoutParams(layoutParams);
        }

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View titleBar = layoutInflater.inflate(R.layout.common_title_bar, this, true);

        mLeftImageButton = titleBar.findViewById(R.id.ivCommonTitleLeftImage);
        mTvCommonTitleCenterText = titleBar.findViewById(R.id.tvCommonTitleCenterText);
        mIvCommonTitleRightMore = titleBar.findViewById(R.id.ivCommonTitleRightMore);
    }

}
