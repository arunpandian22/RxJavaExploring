package me.arun.arunrxjavaexploring.utils;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.arun.arunrxjavaexploring.R;


/**
 * A Class created for the CustomActionbar to use Across entire Application window
 * Created by Jaison.
 */
public class CustomActionBar extends LinearLayout {

    public static int DEFAULT_LEFT_MARGIN = 48;
    public static int CENTER_GRAVITY_LR_MARGIN = 75;
    public static int MINIMUM_LEFT_MARGIN = 32;
    public static int TYPE_DEFAULT_VALUE = 1;
    public static int FILTER_COUNT_DEFAULT_VALUE = 0;
    public static Drawable HOME_SRC_DEFAULT_ICON;
    public static String TITLE_DEFAULT_VALUE;
    public static String OVERFLOW_DEFAULT_VALUE = "";
    String TAG = "CustomActionBar";
    int type;
    int filterCount;
    Drawable homeSrc;
    String title;
    String menuText;
    Context context;

    boolean isBGStatusChange;


    onActionBarClickListener onActionBarClickListener;
    @BindView(R.id.ivHome)
    ImageView ivHome;
    @BindView(R.id.tvActionbarTitle)
    TextView tvActionbarTitle;
    @BindView(R.id.tvOverflow)
    TextView tvOverflow;
    @BindView(R.id.tvOverflowCount)
    TextView tvOverflowCount;
    @BindView(R.id.llOverflow)
    RelativeLayout llOverflow;


    public CustomActionBar(Context context) {
        super(context);
        this.context = context;
        init(null);

    }

    public CustomActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    public CustomActionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }

    public CustomActionBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        init(attrs);
    }

    /**
     * A method to convert the value from dp to pixel
     *
     * @param dp a param has the value of dp
     * @return it returns the converted pixel values
     */
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * A method to inflate the view and set the attributes
     *
     * @param attrs a param has the value of the attributes of the Custom Actionbar
     */
    public void init(AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customActionBarView = inflater.inflate(R.layout.custom_actionbar, this, true);
        ButterKnife.bind(this, customActionBarView);
        setAttributes(context, attrs);
    }

    /**
     * to set the Attributes to CustomActionBar
     * @param context a param has the context of the current activity
     * @param attrs  a param has the attributes of the CustomActionbar
     */
    public void setAttributes(Context context, AttributeSet attrs) {
        //title=getContext().getResources().getString(R.string.app_name);
        TITLE_DEFAULT_VALUE = "Default Value";
        int resourceID = R.drawable.ic_arrow_back;
        HOME_SRC_DEFAULT_ICON = context.getResources().getDrawable(resourceID);

        if (attrs != null) {

            // Log.d(TAG, "setAttributes: not null");
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomActionBar);
            type = array.getInt(R.styleable.CustomActionBar_type, TYPE_DEFAULT_VALUE);
            filterCount = array.getInt(R.styleable.CustomActionBar_filterCount, FILTER_COUNT_DEFAULT_VALUE);
            //Log.d(TAG, "setAttributes: filter count" + filterCount);
            homeSrc = context.getResources().getDrawable(array.getResourceId(R.styleable.CustomActionBar_homeSrc, resourceID));
            title = array.getString(R.styleable.CustomActionBar_title);
            if (TextUtils.isEmpty(title))
                title = TITLE_DEFAULT_VALUE;
            menuText = array.getString(R.styleable.CustomActionBar_menuText);
            array.recycle();
        } else {
            //  Log.d(TAG, "setAttributes: null");
            title = TITLE_DEFAULT_VALUE;
            type = TYPE_DEFAULT_VALUE;
            homeSrc = HOME_SRC_DEFAULT_ICON;
            filterCount = FILTER_COUNT_DEFAULT_VALUE;
            menuText = OVERFLOW_DEFAULT_VALUE;
        }

        setValues(type);
        setTitle(title);
        Log.d(TAG, "setAttributes: "+dpToPx(CENTER_GRAVITY_LR_MARGIN));
        // setClickFeature();
    }

    /**
     * A method to set the home button for CustomActionBar
     * @param drawable a param has the value of the drawable of the Home button
     */
    public void setHomeSrc(Drawable drawable) {
        if (drawable == null)
            ivHome.setVisibility(View.GONE);
        else {
            //   Log.d(TAG, "setHomeSrc: drawable not null");
            ivHome.setImageDrawable(drawable);
            ivHome.setVisibility(View.VISIBLE);
        }

    }

    /**
     * A method to set the title in CustomActionBar
     * @param title a param has the value of CustomActionBar's Title
     */
    public void setTitle(String title) {
        tvActionbarTitle.setText(title);
    }

    /**
     * A method to set the Menu Text in CustomAction bar
     * @param menuText a param has the value of the menu title
     */
    public void setOverFlowTitle(String menuText) {
        if (TextUtils.isEmpty(menuText)) {
            tvOverflow.setVisibility(View.GONE);
        } else {
            tvOverflow.setVisibility(View.VISIBLE);
            tvOverflow.setText(menuText);
        }

    }

    /**
     *  A method to set the Overflow menu background status changes based on the filter count
     */
    public void setOverFlowStatusChange() {
        //Log.d(TAG, "setOverFlowStatusChange: " + filterCount);

        if (filterCount > 0) {
            tvOverflow.setBackground(context.getResources().getDrawable(R.drawable.cta_border_only));
            tvOverflow.setTextColor(context.getResources().getColor(R.color.overFlowSelectedTextColor));
        } else {
            tvOverflow.setBackground(context.getResources().getDrawable(R.drawable.cta_border_only));
            tvOverflow.setTextColor(context.getResources().getColor(R.color.overFlowIdleTextColor));
        }

    }

    /**
     * a method to set the overflow menu text count with menu title
     * @param count a param has the value of the overflow menu text count
     */
    public void setOverflowWithCount(int count) {

        if (TextUtils.isEmpty(menuText)) {
            tvOverflow.setVisibility(View.GONE);
            return;
        } else
            tvOverflow.setVisibility(View.VISIBLE);

        filterCount = count;

        if (count > 0) {
            tvOverflow.setText(menuText+" - "+count);
        } else {
            tvOverflow.setText(menuText);
        }

        if (isBGStatusChange)
            setOverFlowStatusChange();

    }

    /**
     * a method to set the overflow menu text count above menu title
     * @param count a param has the value of the overflow menu text count
     */
    public void setOverflowCount(int count) {
        if (count > 0) {
            tvOverflowCount.setText("" + count);
            tvOverflowCount.setVisibility(View.VISIBLE);
            tvOverflow.setBackground(context.getResources().getDrawable(R.drawable.cta_border_only));
        } else {
            tvOverflowCount.setVisibility(View.GONE);
            tvOverflow.setBackground(context.getResources().getDrawable(R.drawable.cta_border_only));
        }
        Log.d(TAG, "setOverflowCount: " + count);
    }

    /**
     * A method to customize the customActionbar Based on the given type
     * @param type a param has the value of which type of Actionbar has to set
     */
    public void setValues(int type) {
        //Log.d(TAG, "setValues: type" + type);
        switch (type) {
            case 1:
                setHomeSrc(homeSrc);
                isBGStatusChange = true;
                setMargin(DEFAULT_LEFT_MARGIN);
                setOverflowWithCount(filterCount);
                break;
            case 2:
                setHomeSrc(homeSrc);
                isBGStatusChange = true;
                setOverflowWithCount(filterCount);
                setMargin(DEFAULT_LEFT_MARGIN);
                break;
            case 3:
                setHomeSrc(homeSrc);
                setOverFlowTitle("");
                setMargin(DEFAULT_LEFT_MARGIN);
                break;
            case 4:
                setHomeSrc(null);
                isBGStatusChange = true;
                setMargin(MINIMUM_LEFT_MARGIN);
                setOverflowWithCount(filterCount);
                break;
            case 5:
                setHomeSrc(homeSrc);
                setOverFlowTitle(menuText);
                setMargin(DEFAULT_LEFT_MARGIN);
                break;
            case 6:
                setHomeSrc(homeSrc);
                setOverFlowTitle(menuText);
                changeGravity();
                break;
            case 7:
                setHomeSrc(null);
                setOverFlowTitle(menuText);
                changeGravity();
                break;
        }
    }

    /**
     * A method to set the gravity and padding of the Actionbar
     */
    public void changeGravity() {
        tvActionbarTitle.setPadding(dpToPx(CENTER_GRAVITY_LR_MARGIN), 0, dpToPx(CENTER_GRAVITY_LR_MARGIN), 0);
        tvActionbarTitle.setGravity(Gravity.CENTER);
    }

    /**
     * A method to remove the padding of the ActionBar
     */
    public void removePadding() {
        tvActionbarTitle.setPadding(0, 0, 0, 0);

    }

    /**
     * A method to set the left margin of the CustomActionbar
     * @param leftMargin a param has the value of the left margin which  has to set
     */
    public void setMargin(int leftMargin) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) tvActionbarTitle.getLayoutParams();
        params.setMargins(dpToPx(leftMargin), 0, 0, 0);
        tvActionbarTitle.setLayoutParams(params);
    }

    /**
     * A method to set whether the menu has to enable or not
     * @param isEnable a param has the boolean value of the Menu Click Enable
     */
    public void menuClickDisable(boolean isEnable) {
        if (isEnable) {
            tvOverflow.setTextColor(context.getResources().getColor(R.color.actionBarTitleTextColor));
            tvOverflow.setBackground(context.getResources().getDrawable(R.drawable.cta_border_only));
        } else {
            tvOverflow.setTextColor(context.getResources().getColor(R.color.menuDiableColor));
            tvOverflow.setBackground(context.getResources().getDrawable(R.drawable.cta_border_disable));
        }

        llOverflow.setEnabled(isEnable);
    }


    /**
     * A method to get the Click listener for the Custom ActionBar
     *
     * @return it returns Click listener for the Custom ActionBar
     */
    public CustomActionBar.onActionBarClickListener getOnActionBarClickListener() {
        return onActionBarClickListener;
    }

    /**
     * A method to set the Click listener for the Custom ActionBar
     * @param onActionBarClickListener a param has the value of the click listener for the Custom ActionBar
     */
    public void setOnActionBarClickListener(CustomActionBar.onActionBarClickListener onActionBarClickListener) {
//        Log.d(TAG, "setOnActionBarClickListener: ");
        this.onActionBarClickListener = onActionBarClickListener;
    }

    /**
     * A method to set the visibility of OverFlow menu of Custom ActionBar
     * @param isVisibility a param has the boolean value to decide the overflow menu visibility
     */
    public void actionBarOverFlowIsVisibility(Boolean isVisibility) {
        if (isVisibility){
            llOverflow.setVisibility(VISIBLE);
            tvOverflow.setVisibility(VISIBLE);
//            tvOverflowCount.setVisibility(VISIBLE);
        }else{
            llOverflow.setVisibility(GONE);
            tvOverflow.setVisibility(GONE);
//            tvOverflowCount.setVisibility(GONE);
        }

    }
    @OnClick({R.id.ivHome, R.id.llOverflow})
    public void onViewClicked(View view) {
        // Log.d(TAG, "onViewClicked: ");
        switch (view.getId()) {
            case R.id.ivHome:
                if (onActionBarClickListener != null) {
                    //Log.d(TAG, "onViewClicked: not null");
                    onActionBarClickListener.onHomeClick();
                }

                break;
            case R.id.llOverflow:
                if (onActionBarClickListener != null && !TextUtils.isEmpty(menuText))
                    onActionBarClickListener.onOverflowClick();
                break;
        }
    }

    /**
     * A interface has the click listener abstract methods
     */
    public interface onActionBarClickListener {
        void onHomeClick();

        void onOverflowClick();
    }
}
