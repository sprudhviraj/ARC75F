package cordova-plugin-ARC;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
package com.x75f.userapp.utils;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.x75f.userapp.R;

import java.text.DecimalFormat;
/**
 * This class echoes a string called from JavaScript.
 */
public class ARC extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
    
public class SeekArc extends View {

    private static final String TAG = "nitish";
    public static double LEFT_BOUND = 210;
    public static double RIGHT_BOUND = 150;
    private static int INVALID_PROGRESS_VALUE = -1;
    // The initial rotational offset -90 means we start at 12 o'clock
    private final int mAngleOffset = -90;

    /**
     * The Drawable for the seek arc thumbnail
     */
    private Drawable mThumb;

    /**
     * The Maximum value that this SeekArc can be set to
     */
    private int mMax = 360;

    /**
     * The Current value that the SeekArc is set to
     */
    private int mProgress = 50;

    /**
     * The width of the progress line for this SeekArc
     */
    private int mProgressWidth = 0;

    /**
     * The Width of the background arc for the SeekArc
     */
    private int mArcWidth = 0;

    /**
     * The Angle to start drawing this Arc from
     */
    private int mStartAngle = -90;

    /**
     * The Angle through which to draw the arc (Max is 360)
     */
    private float MAX_SWEEP_PROGRESS = 300;
    private int mSweepAngle = 300;

    /**
     * The rotation of the SeekArc- 0 is twelve o'clock
     */
    private int mRotation = 0;

    /**
     * Give the SeekArc rounded edges
     */
    private boolean mRoundedEdges = false;

    /**
     * Enable touch inside the SeekArc
     */
    private boolean mTouchInside = false;

    /**
     * Will the progress increase clockwise or anti-clockwise
     */
    private boolean mClockwise = true;


    public boolean mDetailedView = true;


    // Internal variables
    private int mArcRadius = 0;
    private float mProgressSweep = 0;
    private RectF mArcRect = new RectF();
    private RectF mArcLimit = new RectF();
    private RectF mArcRectText = new RectF();
    private RectF mArcLimitBound = new RectF();
    private Paint mArcPaint;
    private Paint mProgressPaint;
    private Paint mUserLimitProgressPaint;
    private Paint mDelimeterPaint;
    private Paint mDelimeterPaintPointValue;
    private Paint mProgressTextPaint;
    private Paint mUserLimitOutsideProgressPaint;
    private Paint mOuterTextPaint;
    private Paint mUserLimitTextPaint;
    private Paint mThumbeCircleTextPaint;
    private Paint mArcLimitPaint;
    private Paint mThumbOuterCirclePaint;
    private Paint mThumbInnerCirclePaint;
    private int mTranslateX;
    private int mTranslateY;
    private int mThumbXPos;
    private int mThumbYPos;
    private double mTouchAngle;
    private float mTouchIgnoreRadius;
    private OnSeekArcChangeListener mOnSeekArcChangeListener;
    private int width = 0;
    private int height = 0;
    private float cx;
    private float cy;
    public Canvas mCanvas;
    private boolean isFirstRun = true;
    private Path path;
    private int delimeterColor;
    private Paint mProgressLimitPaint;
    private boolean isTouched = false;
    private double mCurrentTemp;
    private double mDesireTemp;
    private float mLimitStartAngle;
    private float mLimitEndAngle;
    private float mPathStartAngle;
    private float angleProgress = 0.0f;
    private boolean mTouchOutSide = true;
    private float mTouchIgnoreRadiusOutSide;
    private CountDownTimer timer;
    private boolean isTimerFinished = false;


    private float userLimitStartPoint = 0;
    private float userLimitEndPoint = 0;
    private double originalDesireTemp = 0;
    private int originalTranslateX;
    private int originalTranslateY;
    private int originalThumbXPos;
    private int originalThumbYPos;
    private float newDesireTemp;
    private boolean inLimit;

    private int mProgressTextSize;
    private int mOuterTextSize;
    private int mUserLimitTextSize;
    private int mThumbCircleTextSize;
    private int mThumbOuterRadius;
    private int mThumbInnerRadius;
    private int mMarkerTextHeight;
    private int mStatusTextHeight;
    private int mMarkerTextWidthX;
    private int mMarkerTextWidthY;
    private int mStatusTextWidth;
    private int mStatusOutsideTextWidth;
    private int mTempTextWidth = 65;
    private int mThumbXPos2;
    private int mThumbYPos2;
    private int mStatusOutsideTextHeight;
    private int mTempTextHeight;
    private Paint mStatusTextPaint;
    private int mStatusTextSize;
    private int mThumbTextWidth;
    private int mSmallThumbRadius;
    private Paint mSmallThumbPaint;
    private int mThumbDifference;
    private int mThumbTextHeight;
    private Paint mThumbOuterLimitCirclePaint;
    private Paint mThumbeOuterLimitCircleTextPaint;
    private Paint mInnerCirclePaint;

    private double originalCurrentTemp = 0;
    private boolean isCurrBeyondLimit = false;
    private boolean isMoveStarted = false;
    private float mGapAngle = 7.5f;
    private double mMiddleAngle = 70;


    private double mBuildingLimitStartAngle = 50;
    private double mBuildingLimitEndAngle = 90;
    private double mLeftMarginAngle = 48;
    private int tempStartAngle = 0;
    private int green, blue, red;
    int arcDiameter;
    private Context context;

    private int[] gradientColors;
    private float[] gradientPositions;
    private Paint mOuterCirclePaint;

    //private float bTheImapactPoint;

    public interface OnSeekArcChangeListener {

        /**
         * Notification that the progress level has changed. Clients can use the
         * fromUser parameter to distinguish user-initiated changes from those
         * that occurred programmatically.
         *
         * @param seekArc  The SeekArc whose progress has changed
         * @param progress The current progress level. This will be in the range
         *                 0..max where max was set by
         *                 //   {@link //ProgressArc#setMax(int)}. (The default value for
         *                 max is 100.)
         * @param fromUser True if the progress change was initiated by the user.
         */
        void onProgressChanged(SeekArc seekArc, int progress, boolean fromUser);

        /**
         * Notification that the user has started a touch gesture. Clients may
         * want to use this to disable advancing the seekbar.
         *
         * @param seekArc The SeekArc in which the touch gesture began
         */
        void onStartTrackingTouch(SeekArc seekArc);

        /**
         * Notification that the user has finished a touch gesture. Clients may
         * want to use this to re-enable advancing the seekarc.
         *
         * @param seekArc The SeekArc in which the touch gesture began
         */
        void onStopTrackingTouch(SeekArc seekArc);
    }


    public SeekArc(Context context) {
        super(context);
        init(context, null, 0);
    }

    public SeekArc(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, R.attr.seekArcStyle);
    }

    public SeekArc(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init(context, attrs, defStyle);
    }

    public void init(Context context, AttributeSet attrs, int defStyle) {


        final Resources res = getResources();
        float density = context.getResources().getDisplayMetrics().density;

        // Defaults, may need to link this into theme settings
        int arcColor = res.getColor(R.color.transparent);
        int progressColor = res.getColor(R.color.colorPrimary);
        green = res.getColor(R.color.green);
        blue = res.getColor(R.color.blue);
        red = res.getColor(R.color.red);
        int userLimitProgressColor = res.getColor(R.color.transparent_white);
        int thumbOuterColor = res.getColor(R.color.white);
        int thumbInnerColor = res.getColor(R.color.transparent_black_dark);
        int circleInnerColor = res.getColor(R.color.inner_dark_circle);
        int circleOuterColor = res.getColor(R.color.transparent_black_dark);
        delimeterColor = res.getColor(R.color.transparent_black);
        int statusTempText = res.getColor(R.color.white);
        int outerTempText = res.getColor(R.color.white);
        int thumbOuterLimitColor = res.getColor(R.color.white);
        //int progressColor = Color.parseColor("22810767");
        int thumbHalfheight = 0;
        int thumbHalfWidth = 0;
        mThumb = res.getDrawable(R.drawable.seek_arc_control_selector);
        // Convert progress width to pixels for current density
        mProgressWidth = (int) (mProgressWidth * density);

        timer = new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                invalidate();
                isTimerFinished = true;
            }

        }.start();
        if (attrs != null) {
            // Attribute initialization
            final TypedArray a = context.obtainStyledAttributes(attrs,
                    R.styleable.SeekArc, defStyle, 0);

            Drawable thumb = a.getDrawable(R.styleable.SeekArc_thumb);
            if (thumb != null) {
                mThumb = thumb;
            }

            thumbHalfheight = (int) mThumb.getIntrinsicHeight() / 2;
            thumbHalfWidth = (int) mThumb.getIntrinsicWidth() / 2;
            mThumb.setBounds(-thumbHalfWidth, -thumbHalfheight, thumbHalfWidth,
                    thumbHalfheight);

            mMax = a.getInteger(R.styleable.SeekArc_max, mMax);
            mProgress = a.getInteger(R.styleable.SeekArc_progress, mProgress);
            mProgressWidth = (int) a.getDimension(
                    R.styleable.SeekArc_progressWidth, mProgressWidth);
            mArcWidth = (int) a.getDimension(R.styleable.SeekArc_arcWidth,
                    mArcWidth);
            mStartAngle = a.getInt(R.styleable.SeekArc_startAngle, mStartAngle);
            mSweepAngle = a.getInt(R.styleable.SeekArc_sweepAngle, mSweepAngle);
            mRotation = a.getInt(R.styleable.SeekArc_rotation, mRotation);
            mRoundedEdges = a.getBoolean(R.styleable.SeekArc_roundEdges,
                    mRoundedEdges);
            mTouchInside = a.getBoolean(R.styleable.SeekArc_touchInside,
                    mTouchInside);
            mTouchOutSide = a.getBoolean(R.styleable.SeekArc_touchInside,
                    mTouchOutSide);
            mClockwise = a.getBoolean(R.styleable.SeekArc_clockwise,
                    mClockwise);

            mProgressTextSize = (int) a.getDimension(R.styleable.SeekArc_progresstextsize, mProgressTextSize);
            mOuterTextSize = (int) a.getDimension(R.styleable.SeekArc_outertextsize, mOuterTextSize);
            mStatusTextSize = (int) a.getDimension(R.styleable.SeekArc_statustextsize, mStatusTextSize);
            mUserLimitTextSize = (int) a.getDimension(R.styleable.SeekArc_userlimittextsize, mUserLimitTextSize);
            mThumbCircleTextSize = (int) a.getDimension(R.styleable.SeekArc_thumbcircletextsize, mThumbCircleTextSize);
            mThumbOuterRadius = (int) a.getDimension(R.styleable.SeekArc_thumbouterradius, mThumbOuterRadius);
            mThumbInnerRadius = (int) a.getDimension(R.styleable.SeekArc_thumbinnerradius, mThumbInnerRadius);
            mMarkerTextHeight = (int) a.getDimension(R.styleable.SeekArc_markertextheight, mMarkerTextHeight);
            mStatusTextHeight = (int) a.getDimension(R.styleable.SeekArc_statustextheight, mStatusTextHeight);
            mMarkerTextWidthX = (int) a.getDimension(R.styleable.SeekArc_markertextwidthx, mMarkerTextWidthX);
            mMarkerTextWidthY = (int) a.getDimension(R.styleable.SeekArc_markertextwidthy, mMarkerTextWidthY);
            mStatusTextWidth = (int) a.getDimension(R.styleable.SeekArc_statustextwidth, mStatusTextWidth);
            mStatusOutsideTextWidth = (int) a.getDimension(R.styleable.SeekArc_statusoutsidetextwidth, mStatusOutsideTextWidth);
            mStatusOutsideTextHeight = (int) a.getDimension(R.styleable.SeekArc_statusoutsidetextheight, mStatusOutsideTextHeight);
            mTempTextWidth = (int) a.getDimension(R.styleable.SeekArc_temptextwidth, mTempTextWidth);
            mTempTextHeight = (int) a.getDimension(R.styleable.SeekArc_temptextheight, mTempTextHeight);
            mThumbTextWidth = (int) a.getDimension(R.styleable.SeekArc_thumbtextwidth, mThumbTextWidth);
            mThumbTextHeight = (int) a.getDimension(R.styleable.SeekArc_thumbtextheight, mThumbTextHeight);
            mSmallThumbRadius = (int) a.getDimension(R.styleable.SeekArc_smallthumbradius, mSmallThumbRadius);
            mThumbDifference = (int) a.getDimension(R.styleable.SeekArc_thumbdifference, mThumbDifference);
            a.recycle();
        }

        mProgress = (mProgress > mMax) ? mMax : mProgress;
        mProgress = (mProgress < 0) ? 0 : mProgress;

        mSweepAngle = (mSweepAngle > 360) ? 360 : mSweepAngle;
        //mSweepAngle = (mSweepAngle < 0) ? 0 : mSweepAngle;

        mStartAngle = (mStartAngle > 360) ? 0 : mStartAngle;
        mStartAngle = (mStartAngle < 0) ? 0 : mStartAngle;

        mInnerCirclePaint = new Paint();
        mInnerCirclePaint.setColor(circleInnerColor);
        mInnerCirclePaint.setAntiAlias(true);
        mInnerCirclePaint.setStyle(Paint.Style.FILL);

        mOuterCirclePaint = new Paint();
        mOuterCirclePaint.setColor(circleOuterColor);
        mOuterCirclePaint.setAntiAlias(true);
        mOuterCirclePaint.setStyle(Paint.Style.FILL);

        mArcPaint = new Paint();
        mArcPaint.setColor(arcColor);
        mArcPaint.setAntiAlias(true);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(mArcWidth);
        mArcPaint.setStrokeCap(Paint.Cap.ROUND);


        mProgressPaint = new Paint();
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        mProgressPaint.setStrokeWidth(5);
        mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
//      mProgressPaint.setAlpha(166);

        mUserLimitProgressPaint = new Paint();
        mUserLimitProgressPaint.setAntiAlias(true);
        mUserLimitProgressPaint.setStyle(Paint.Style.STROKE);
        mUserLimitProgressPaint.setStrokeWidth(mProgressWidth);
        mUserLimitProgressPaint.setStrokeCap(Paint.Cap.SQUARE);
        mUserLimitProgressPaint.setAlpha(150);

        mDelimeterPaint = new Paint();
        mDelimeterPaint.setColor(delimeterColor);
        mDelimeterPaint.setAntiAlias(true);
        //mDelimeterPaint.setStyle(Paint.Style.STROKE);
        mDelimeterPaint.setStrokeWidth(150);

        mDelimeterPaintPointValue = new Paint();
        mDelimeterPaintPointValue.setColor(delimeterColor);
        mDelimeterPaintPointValue.setAntiAlias(true);
        mDelimeterPaintPointValue.setStyle(Paint.Style.STROKE);
        mDelimeterPaintPointValue.setStrokeWidth(15);

        mProgressTextPaint = new Paint();
        mProgressTextPaint.setColor(Color.parseColor("#1192db"));
        mProgressTextPaint.setTextAlign(Paint.Align.LEFT);
        mProgressTextPaint.setAntiAlias(true);
        mProgressTextPaint.setStyle(Paint.Style.FILL);
        mProgressTextPaint.setTextSize(mProgressTextSize);

        mUserLimitOutsideProgressPaint = new Paint();
        mUserLimitOutsideProgressPaint.setColor(userLimitProgressColor);
        mUserLimitOutsideProgressPaint.setAntiAlias(true);
        mUserLimitOutsideProgressPaint.setStyle(Paint.Style.FILL);
        mUserLimitOutsideProgressPaint.setTextSize(mProgressTextSize);

        mOuterTextPaint = new Paint();
        mOuterTextPaint.setColor(outerTempText);
        mOuterTextPaint.setAntiAlias(true);
        mOuterTextPaint.setTextAlign(Paint.Align.LEFT);
        mOuterTextPaint.setStyle(Paint.Style.FILL);
        mOuterTextPaint.setTextSize(mOuterTextSize);

        mStatusTextPaint = new Paint();
        mStatusTextPaint.setColor(statusTempText);
        mStatusTextPaint.setAntiAlias(true);
        mStatusTextPaint.setTextAlign(Paint.Align.LEFT);
        mStatusTextPaint.setStyle(Paint.Style.FILL);
        mStatusTextPaint.setTextSize(mStatusTextSize);

        mUserLimitTextPaint = new Paint();
        mUserLimitTextPaint.setColor(progressColor);
        mUserLimitTextPaint.setAntiAlias(true);
        mUserLimitTextPaint.setTextAlign(Paint.Align.LEFT);
        mUserLimitTextPaint.setStyle(Paint.Style.FILL);
        mUserLimitTextPaint.setTextSize(mOuterTextSize);


        mThumbeCircleTextPaint = new Paint();
        mThumbeCircleTextPaint.setColor(thumbOuterColor);
        mThumbeCircleTextPaint.setAntiAlias(true);
        mThumbeCircleTextPaint.setStyle(Paint.Style.FILL);
        mThumbeCircleTextPaint.setTextSize(mThumbCircleTextSize);

        mProgressLimitPaint = new Paint();
        mProgressLimitPaint.setColor(userLimitProgressColor);
        mProgressLimitPaint.setAntiAlias(true);
        mProgressLimitPaint.setStyle(Paint.Style.STROKE);
        mProgressLimitPaint.setStrokeWidth(20);

        mArcLimitPaint = new Paint();
        mArcLimitPaint.setColor(userLimitProgressColor);
        mArcLimitPaint.setAntiAlias(true);
        mArcLimitPaint.setStyle(Paint.Style.STROKE);
        mArcLimitPaint.setStrokeWidth(5);

        mThumbOuterCirclePaint = new Paint();
        mThumbOuterCirclePaint.setColor(thumbOuterColor);
        mThumbOuterCirclePaint.setAntiAlias(true);
        mThumbOuterCirclePaint.setStyle(Paint.Style.FILL);


        mSmallThumbPaint = new Paint();
        mSmallThumbPaint.setColor(thumbOuterColor);
        mSmallThumbPaint.setAntiAlias(true);
        mSmallThumbPaint.setStyle(Paint.Style.FILL);
        mSmallThumbPaint.setAlpha(150);

        mThumbInnerCirclePaint = new Paint();
        mThumbInnerCirclePaint.setColor(thumbInnerColor);
        mThumbInnerCirclePaint.setAntiAlias(true);
        mThumbInnerCirclePaint.setStyle(Paint.Style.FILL);


        mThumbOuterLimitCirclePaint = new Paint();
        mThumbOuterLimitCirclePaint.setColor(thumbOuterLimitColor);
        mThumbOuterLimitCirclePaint.setAntiAlias(true);
        mThumbOuterLimitCirclePaint.setStyle(Paint.Style.FILL);

        mThumbeOuterLimitCircleTextPaint = new Paint();
        mThumbeOuterLimitCircleTextPaint.setColor(thumbOuterLimitColor);
        mThumbeOuterLimitCircleTextPaint.setAntiAlias(true);
        mThumbeOuterLimitCircleTextPaint.setStyle(Paint.Style.FILL);
        mThumbeOuterLimitCircleTextPaint.setTextSize(mThumbCircleTextSize);

        gradientColors = new int[]{blue,
                green,
                red};
        gradientPositions = new float[]{0, 0.5f, 1};
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Rect bounds = new Rect();
        if (getCurrentTemp() < mBuildingLimitStartAngle) {
            originalCurrentTemp = getCurrentTemp();
            setCurrentTemp(mBuildingLimitStartAngle);
        } else if (getCurrentTemp() > mBuildingLimitEndAngle) {
            originalCurrentTemp = getCurrentTemp();
            setCurrentTemp(mBuildingLimitEndAngle);
        }

        prepareAngle();
        width = getWidth();
        height = getHeight();
        cx = width / 2;
        cy = height / 2;
        mCanvas = canvas;
        // Draw the arcs
        //The basic arc

        canvas.drawCircle(cx, cy, (float) (mArcRadius / 1.06), mOuterCirclePaint);
        canvas.drawCircle(cx, cy, (float) (mArcRadius / 1.4), mInnerCirclePaint);
        // canvas.drawCircle(cx/2, cy/2, (float) (mArcRadius / send.13), mOuterCirclePaint);
        canvas.drawArc(mArcRect, getmPathStartAngle(), 300, false, mArcPaint);

        //Delimeters in the basic arc
        if (isDetailedView()) {
            for (float i = getmPathStartAngle(); i <= getmPathStartAngle() + 300; i = i + mGapAngle) {
                canvas.drawArc(mArcRect, i, 0.5f, false, mDelimeterPaint);
            }

            for (float i = getmPathStartAngle() + (mGapAngle / 2); i <= getmPathStartAngle() + 300 - (mGapAngle / 2); i = i + mGapAngle) {
                canvas.drawArc(mArcRect, i, 0.5f, false, mDelimeterPaintPointValue);
            }
        }

        int tempStartAngle = (int) (210 + mGapAngle * (getCurrentTemp() - mBuildingLimitStartAngle));
        if (tempStartAngle > 180)
            tempStartAngle = tempStartAngle - 360;

        float sweepAngle = 0.0f;
        if (mTouchAngle > 180)
            sweepAngle = (float) mTouchAngle - tempStartAngle - 360;
        else
            sweepAngle = (float) mTouchAngle - tempStartAngle;

        if (mTouchAngle == 0) {
            if (getDesireTemp() >= getUserLimitStartPoint() && getDesireTemp() <= getUserLimitEndPoint()) {
                mProgressPaint.setShader(new LinearGradient(0, 0, 0, getHeight(), green, blue, Shader.TileMode.MIRROR));
                canvas.drawArc(mArcRect, mAngleOffset + tempStartAngle, (float) ((getDesireTemp() - getCurrentTemp()) * mGapAngle), false, mProgressPaint);
                updateProgress((int) ((getDesireTemp() - mMiddleAngle) * mGapAngle), false);
            } else {

                canvas.drawArc(mArcRect, mAngleOffset + tempStartAngle, (float) ((getDesireTemp() - getCurrentTemp()) * mGapAngle), false, mUserLimitProgressPaint);


                if ((int) getDesireTemp() > (int) ((getDesireTemp() - mMiddleAngle) * mGapAngle)) {

                    mUserLimitProgressPaint.setShader(new LinearGradient(0, 0, 0, getHeight(), green, blue, Shader.TileMode.MIRROR));
                } else {
                    invalidate();
                    int[] gradients = {
                            blue, green, red
                    };
                    float[] positions = {0.25F, 0.55F, 0.85F};
                    Shader shader = new SweepGradient(cx, cy, gradients, positions);
                    mUserLimitProgressPaint.setShader(shader);
                    Matrix matrix = new Matrix();
                    matrix.preRotate(mAngleOffset + tempStartAngle - 30, cx, cy);
                    shader.setLocalMatrix(matrix);
                }

                updateProgress((int) ((getDesireTemp() - mMiddleAngle) * mGapAngle), false);
            }
        } else if (isTouched) {
            //     Log.d(AppConstant.LOG_TAG,"I m here 3:");
            mArcPaint.setAlpha(30);
            if (getDesireTemp() >= getUserLimitStartPoint() && getDesireTemp() <= getUserLimitEndPoint()) {
                mProgressPaint.setStrokeWidth(mProgressWidth);
                mUserLimitProgressPaint.setStrokeWidth(mProgressWidth);
                invalidate();
                canvas.drawArc(mArcRect, mAngleOffset + tempStartAngle, sweepAngle, false, mProgressPaint);
                mProgressPaint.setStrokeWidth(5);
                mUserLimitProgressPaint.setStrokeWidth(mProgressWidth);
                invalidate();
                //Log.d(TAG,"i m here in send");
            } else {
                mUserLimitProgressPaint.setStrokeWidth(mProgressWidth);
                invalidate();
                SharePref.showLog("seekarc", getDesireTemp() + " " + (int) ((getDesireTemp() - mMiddleAngle) * mGapAngle));
                if ((int) getDesireTemp() > (int) ((getDesireTemp() - mMiddleAngle) * mGapAngle)) {
                    mUserLimitProgressPaint.setShader(new LinearGradient(0, 0, 0, getHeight(), green, blue, Shader.TileMode.MIRROR));
                } else {
                    int[] gradients = {
                            blue, green, red
                    };
                    float[] positions = {0.25F, 0.55F, 0.85F};
                    Shader shader = new SweepGradient(cx, cy, gradients, positions);
                    mUserLimitProgressPaint.setShader(shader);
                    Matrix matrix = new Matrix();
                    matrix.preRotate(mAngleOffset + tempStartAngle - 30, cx, cy);
                    shader.setLocalMatrix(matrix);
                }
                canvas.drawArc(mArcRect, mAngleOffset + tempStartAngle, sweepAngle,
                        false, mUserLimitProgressPaint);
                mUserLimitProgressPaint.setStrokeWidth(5);
                // Log.d(TAG, "i m here in 2");
            }

        } else if (!isTouched) {
            /*Log.d(AppConstant.LOG_TAG,"I m here 4:");*/
            mArcPaint.setAlpha(0);
            invalidate();
            if (getDesireTemp() >= getUserLimitStartPoint() && getDesireTemp() <= getUserLimitEndPoint()) {

                mProgressPaint.setShader(new LinearGradient(0, 0, 0, getHeight(), green, blue, Shader.TileMode.MIRROR));
                canvas.drawArc(mArcRect, mAngleOffset + tempStartAngle, (float) ((getDesireTemp() - getCurrentTemp()) * mGapAngle), false, mProgressPaint);
                updateProgress((int) ((getDesireTemp() - mMiddleAngle) * mGapAngle), false);
            } else {
                canvas.drawArc(mArcRect, mAngleOffset + tempStartAngle, (float) ((originalDesireTemp - getCurrentTemp()) * mGapAngle), false, mUserLimitProgressPaint);
                updateProgress((int) ((getDesireTemp() - mMiddleAngle) * mGapAngle), false);
                angleProgress = (float) originalDesireTemp;
                if ((int) getDesireTemp() > (int) ((getDesireTemp() - mMiddleAngle) * mGapAngle)) {

                    mUserLimitProgressPaint.setShader(new LinearGradient(0, 0, 0, getHeight(), green, blue, Shader.TileMode.MIRROR));
                } else {
                    int[] gradients = {
                            blue, green, red
                    };
                    float[] positions = {0.25F, 0.55F, 0.85F};
                    Shader shader = new SweepGradient(cx, cy, gradients, positions);
                    mUserLimitProgressPaint.setShader(shader);
                    Matrix matrix = new Matrix();
                    matrix.preRotate(mAngleOffset + tempStartAngle - 30, cx, cy);
                    shader.setLocalMatrix(matrix);
                    mUserLimitProgressPaint.setShader(shader);
                }
            }
        }


        //canvas for user Arc limit
        if (isDetailedView() && isTouched) {
            // if (isMoveStarted) {
            //   Log.d(AppConstant.LOG_TAG,"I m here 5:");
            canvas.drawArc(mArcLimit, mAngleOffset + getLimitStartAngle(), getLimitSweepAngle() + 1, false, mArcLimitPaint);
            canvas.drawArc(mArcLimitBound, mAngleOffset + getLimitStartAngle(), 2, false, mProgressLimitPaint);
            canvas.drawArc(mArcLimitBound, mAngleOffset + getLimitEndAngle(), 2, false, mProgressLimitPaint);
            // }
        }


        if (mTouchAngle > 180 && mTouchAngle < 360) {
            angleProgress = (float) (((float) ((mTouchAngle - tempStartAngle) / mGapAngle) + getCurrentTemp()) - mLeftMarginAngle);
        } else {
            angleProgress = (float) ((float) ((mTouchAngle - tempStartAngle) / mGapAngle) + getCurrentTemp());
        }


        //Format to represent the angle progress
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.applyPattern("##.#");

        angleProgress = Float.parseFloat(decimalFormat.format(angleProgress));

        float d = angleProgress - (long) angleProgress;

        if (d > 0.25 && d < 0.5) {
            angleProgress = (float) (Math.round(angleProgress) + 0.5);
        } else {
            angleProgress = Math.round(angleProgress);
        }

        if (isFirstRun) {
            angleProgress = (float) getDesireTemp();
            originalDesireTemp = getDesireTemp();

            originalTranslateX = mTranslateX;
            originalTranslateY = mTranslateY;
            originalThumbXPos = mThumbXPos;
            originalThumbYPos = mThumbYPos;

        }


        if (!(isFirstRun || isTimerFinished)) {
            if (getDesireTemp() >= getUserLimitStartPoint() && getDesireTemp() <= getUserLimitEndPoint()) {


                if (angleProgress >= getUserLimitStartPoint() && angleProgress <= getUserLimitEndPoint()) {
                    originalDesireTemp = angleProgress;

                }
                String curTemp = "Desired";
                mStatusTextPaint.getTextBounds(curTemp, 0, curTemp.length(), bounds);
                canvas.drawText(curTemp, cx - (bounds.width() / 2), cy + mStatusTextHeight - (bounds.height() / 2), mStatusTextPaint);
                curTemp = Double.toString(originalDesireTemp);
                mProgressTextPaint.getTextBounds(curTemp, 0, curTemp.length(), bounds);
                canvas.drawText(curTemp, cx - (bounds.width() / 2), cy + mTempTextHeight - (bounds.height() / 2), mProgressTextPaint);

            } else if (!isTouched) {


                mDesireTemp = originalDesireTemp;
                /*if(originalDesireTemp >= getmBuildingLimitStartAngle() && originalDesireTemp <= getmBuildingLimitEndAngle()) {
                    canvas.drawText("Outside User Limit ", cx - mStatusOutsideTextWidth, cy + mStatusOutsideTextHeight, mUserLimitTextPaint);
                    canvas.drawText("" + originalDesireTemp, cx - mTempTextWidth, cy + mTempTextHeight, mUserLimitOutsideProgressPaint);
                }*/

            } else {

                mDesireTemp = angleProgress;

                if (angleProgress >= getmBuildingLimitStartAngle() && angleProgress <= getmBuildingLimitEndAngle()) {
                    String curTemp = "Outside User Limit";
                    mUserLimitTextPaint.getTextBounds(curTemp, 0, curTemp.length(), bounds);
                    canvas.drawText(curTemp, cx - (bounds.width() / 2), cy + mStatusOutsideTextHeight - (bounds.height() / 2), mUserLimitTextPaint);
                    curTemp = Double.toString(angleProgress);
                    mUserLimitOutsideProgressPaint.getTextBounds(curTemp, 0, curTemp.length(), bounds);
                    canvas.drawText(curTemp, cx - (bounds.width() / 2), cy + mTempTextHeight - (bounds.height() / 2), mUserLimitOutsideProgressPaint);
                }

            }


        }


        if (isTouched) {
            if (getDesireTemp() >= getUserLimitStartPoint() && getDesireTemp() <= getUserLimitEndPoint()) {
                String curTemp = "Desired";
                mStatusTextPaint.getTextBounds(curTemp, 0, curTemp.length(), bounds);
                canvas.drawText(curTemp, cx - (bounds.width() / 2), cy + mStatusTextHeight - (bounds.height() / 2), mStatusTextPaint);
                //canvas.drawText("Desired", cx - mStatusTextWidth, cy + mStatusTextHeight, mStatusTextPaint);
                curTemp = Double.toString(angleProgress);
                mProgressTextPaint.getTextBounds(curTemp, 0, curTemp.length(), bounds);
                canvas.drawText(curTemp, cx - (bounds.width() / 2), cy + mTempTextHeight - (bounds.height() / 2), mProgressTextPaint);

            } else if (angleProgress >= getmBuildingLimitStartAngle() && angleProgress <= getmBuildingLimitEndAngle()) {
                String curTemp = Double.toString(angleProgress);
                mUserLimitOutsideProgressPaint.getTextBounds(curTemp, 0, curTemp.length(), bounds);
                canvas.drawText("" + angleProgress, cx - (bounds.width() / 2), cy + mTempTextHeight - (bounds.height() / 2), mUserLimitOutsideProgressPaint);
                curTemp = "Outside User Limit";
                mUserLimitTextPaint.getTextBounds(curTemp, 0, curTemp.length(), bounds);
                canvas.drawText(curTemp, cx - (bounds.width() / 2), cy + mStatusOutsideTextHeight - (bounds.height() / 2), mUserLimitTextPaint);
            }
            mDesireTemp = angleProgress;


        }


        if (isFirstRun) {
            mProgressPaint.setStrokeWidth(5);
            mUserLimitProgressPaint.setStrokeWidth(5);
            String firstTemp;
            firstTemp = "Current";
            mStatusTextPaint.getTextBounds(firstTemp, 0, firstTemp.length(), bounds);
            canvas.drawText(firstTemp, cx - (bounds.width() / 2), cy + mStatusTextHeight - (bounds.height() / 2), mStatusTextPaint);
            if (isCurrBeyondLimit) {
                firstTemp = Double.toString(originalCurrentTemp);
                mProgressTextPaint.getTextBounds(firstTemp, 0, firstTemp.length(), bounds);
                canvas.drawText(firstTemp, cx - (bounds.width() / 2), cy + mTempTextHeight - (bounds.height() / 2), mProgressTextPaint);


            } else {
                firstTemp = Double.toString(getCurrentTemp());

                mProgressTextPaint.getTextBounds(firstTemp, 0, firstTemp.length(), bounds);
                canvas.drawText(firstTemp, cx - (bounds.width() / 2), cy + mTempTextHeight - (bounds.height() / 2), mProgressTextPaint);
            }


        }


        if (isTimerFinished) {

            String curTemp;
            curTemp = "Current";
            mStatusTextPaint.getTextBounds(curTemp, 0, curTemp.length(), bounds);
            canvas.drawText(curTemp, cx - (bounds.width() / 2), cy + mStatusTextHeight - (bounds.height() / 2), mStatusTextPaint);
            if (isCurrBeyondLimit) {

                curTemp = Double.toString(originalCurrentTemp);
                mProgressTextPaint.getTextBounds(curTemp, 0, curTemp.length(), bounds);
                canvas.drawText(curTemp, cx - (bounds.width() / 2), cy + mTempTextHeight - (bounds.height() / 2), mProgressTextPaint);

            } else {
                curTemp = Double.toString(getCurrentTemp());

                mProgressTextPaint.getTextBounds(curTemp, 0, curTemp.length(), bounds);
                canvas.drawText(curTemp, cx - (bounds.width() / 2), cy + mTempTextHeight - (bounds.height() / 2), mProgressTextPaint);
            }


        } else {
            invalidate();
        }


        //outer arc numbers from 50 - 90
        if (isTouched) {
            canvas.drawText("" + Math.round(getmBuildingLimitStartAngle()), cx - mMarkerTextWidthX, cy + mMarkerTextHeight, mStatusTextPaint);
            canvas.drawText("" + Math.round(getmBuildingLimitEndAngle()), cx + mMarkerTextWidthY, cy + mMarkerTextHeight, mStatusTextPaint);
            canvas.drawText("" + Math.round(getUserLimitStartPoint()), cy - getUserLimitStartPoint(), convertDpToPixel(35, getContext()), mStatusTextPaint);
            canvas.drawText("" + Math.round(getUserLimitEndPoint()), cy + getUserLimitEndPoint(), convertDpToPixel(25, getContext()), mStatusTextPaint);
        }


        //Thumb circle
        if (isDetailedView()) {
            if (isTouched) {
                mArcPaint.setAlpha(30);
                canvas.drawCircle(mTranslateX - mThumbXPos, mTranslateY - mThumbYPos, mSmallThumbRadius, mSmallThumbPaint);
                if (getDesireTemp() >= getUserLimitStartPoint() && getDesireTemp() <= getUserLimitEndPoint()) {
                    canvas.drawCircle(mTranslateX - mThumbXPos2, mTranslateY - mThumbYPos2, mThumbOuterRadius, mThumbOuterCirclePaint);
                    canvas.drawCircle(mTranslateX - mThumbXPos2, mTranslateY - mThumbYPos2, mThumbInnerRadius, mThumbInnerCirclePaint);
                    canvas.drawText("" + getDesireTemp(), mTranslateX - mThumbXPos2 - mThumbTextWidth, mTranslateY - mThumbYPos2 + mThumbTextHeight, mThumbeCircleTextPaint);
                } else {
                    if (getDesireTemp() > getmBuildingLimitStartAngle() && getDesireTemp() < getmBuildingLimitEndAngle()) {
                        canvas.drawCircle(mTranslateX - mThumbXPos2, mTranslateY - mThumbYPos2, mThumbOuterRadius, mThumbOuterLimitCirclePaint);
                        canvas.drawCircle(mTranslateX - mThumbXPos2, mTranslateY - mThumbYPos2, mThumbInnerRadius, mThumbInnerCirclePaint);
                        canvas.drawText("" + getDesireTemp(), mTranslateX - mThumbXPos2 - mThumbTextWidth, mTranslateY - mThumbYPos2 + mThumbTextHeight, mThumbeOuterLimitCircleTextPaint);
                    }
                }

            } else if (!isTouched) {
                invalidate();
                if (getDesireTemp() >= getUserLimitStartPoint() && getDesireTemp() <= getUserLimitEndPoint()) {
                    canvas.drawCircle(mTranslateX - mThumbXPos, mTranslateY - mThumbYPos, mThumbOuterRadius, mThumbOuterCirclePaint);
                    canvas.drawCircle(mTranslateX - mThumbXPos, mTranslateY - mThumbYPos, mThumbInnerRadius, mThumbInnerCirclePaint);
                    canvas.drawText("" + getDesireTemp(), mTranslateX - mThumbXPos - mThumbTextWidth, mTranslateY - mThumbYPos + mThumbTextHeight, mThumbeCircleTextPaint);

                } else {
                    canvas.drawCircle(originalTranslateX - originalThumbXPos, originalTranslateY - originalThumbYPos, mThumbOuterRadius, mThumbOuterCirclePaint);
                    canvas.drawCircle(originalTranslateX - originalThumbXPos, originalTranslateY - originalThumbYPos, mThumbInnerRadius, mThumbInnerCirclePaint);
                    angleProgress = (float) originalDesireTemp;
                    if (isFirstRun) {
                        canvas.drawText("" + getDesireTemp(), originalTranslateX - originalThumbXPos - mThumbTextWidth, originalTranslateY - originalThumbYPos + mThumbTextHeight, mThumbeCircleTextPaint);
                    } else {
                        canvas.drawText("" + originalDesireTemp, originalTranslateX - originalThumbXPos - mThumbTextWidth, originalTranslateY - originalThumbYPos + mThumbTextHeight, mThumbeCircleTextPaint);

                    }
                }


            }
        } else {

            canvas.drawCircle(mTranslateX - mThumbXPos, mTranslateY - mThumbYPos, mThumbOuterRadius, mThumbOuterCirclePaint);
            canvas.drawCircle(mTranslateX - mThumbXPos, mTranslateY - mThumbYPos, mThumbInnerRadius, mThumbInnerCirclePaint);
            if (isFirstRun) {
                canvas.drawText("" + getDesireTemp(), mTranslateX - mThumbXPos - mThumbTextWidth, mTranslateY - mThumbYPos + mThumbTextHeight, mThumbeCircleTextPaint);
            } else {
                canvas.drawText("" + originalDesireTemp, mTranslateX - mThumbXPos - mThumbTextWidth, mTranslateY - mThumbYPos + mThumbTextHeight, mThumbeCircleTextPaint);

            }


        }
    }

    public void prepareAngle() {
        mGapAngle = (float) (300 / (mBuildingLimitEndAngle - mBuildingLimitStartAngle));
        mMiddleAngle = mBuildingLimitStartAngle + ((mBuildingLimitEndAngle - mBuildingLimitStartAngle) / 2);
        mLeftMarginAngle = (mBuildingLimitEndAngle - mBuildingLimitStartAngle) * 1.2;
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        height = getDefaultSize(getSuggestedMinimumHeight(),
                heightMeasureSpec);
        final int width = getDefaultSize(getSuggestedMinimumWidth(),
                widthMeasureSpec);
        final int min = Math.min(width, height);
        float top = 0;
        float left = 0;
        int arcDiameter = 0;
        int arcStart = 0;


        mTranslateX = (int) (width * 0.5f);
        mTranslateY = (int) (height * 0.5f);


        arcDiameter = min - getPaddingLeft();
        mArcRadius = arcDiameter / 2;
        top = height / 2 - (arcDiameter / 2);
        left = width / 2 - (arcDiameter / 2);
        mArcRect.set(left, top, left + arcDiameter, top + arcDiameter);
        mArcLimit.set(left + 25, top + 25, left + arcDiameter - 25, top + arcDiameter - 25);
        mArcLimitBound.set(left + 20, top + 20, left + arcDiameter - 20, top + arcDiameter - 20);
        mArcRectText.set(left - 80, top - 80, left + arcDiameter + 80, top + arcDiameter + 80);

        arcStart = (int) mProgressSweep + 90;
        mThumbXPos = (int) (mArcRadius * Math.cos(Math.toRadians(arcStart)));
        mThumbYPos = (int) (mArcRadius * Math.sin(Math.toRadians(arcStart)));


        mThumbXPos2 = (int) ((mArcRadius + mThumbDifference) * Math.cos(Math.toRadians(arcStart)));
        mThumbYPos2 = (int) ((mArcRadius + mThumbDifference) * Math.sin(Math.toRadians(arcStart)));

        setTouchInSide(mTouchInside);
        setTouchOutSide(mTouchOutSide);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        isFirstRun = false;


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                invalidate();

                //  setDetailedView(false);
                //bTheImapactPoint = event.getX();
                SeekArcDataHelper.INSTANCE.setIsInSeekArcTouch(true);
                isMoveStarted = isThumbPressed(event.getX(), event.getY());
                onStartTrackingTouch();
                updateOnTouch(event);
                break;
            case MotionEvent.ACTION_MOVE:

                if (/*isTouched &&*/ isMoveStarted /* && (bTheImapactPoint - event.getX() != 0)*/) {
                    isTouched = true;

                    onStartTrackingTouch();
                    SeekArcDataHelper.INSTANCE.setIsInSeekArcTouch(true);
                    // setDetailedView(true);
                    getParent().requestDisallowInterceptTouchEvent(true);
                    updateOnTouch(event);
                    if (isTouched) {
                        isMoveStarted = true;
                        isTimerFinished = false;
                        timer.cancel();
                    } else {
                        invalidate();
                    }
                } else {
                    ignoreTouch(event.getX(), event.getY());
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                invalidate();
                //  setDetailedView(false);
                getParent().requestDisallowInterceptTouchEvent(false);
                timer.start();
                SeekArcDataHelper.INSTANCE.setIsInSeekArcTouch(false);
                if (isTouched) {
                    isTouched = false;
                    onStopTrackingTouch();
                    isMoveStarted = false;
                    setPressed(false);
                }


                break;
          /*  case MotionEvent.ACTION_CANCEL:
                invalidate();
                isTouched = false;
                SeekArcDataHelper.INSTANCE.setIsInSeekArcTouch(false);
                setDetailedView(false);
                if(isTouched)
                    onStopTrackingTouch();
                isMoveStarted = false;
				setPressed(false);
                break;*/
        }

        return true;
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (mThumb != null && mThumb.isStateful()) {
            int[] state = getDrawableState();
            mThumb.setState(state);
        }
        invalidate();

    }

    private void onStartTrackingTouch() {
        if (mOnSeekArcChangeListener != null) {
            mOnSeekArcChangeListener.onStartTrackingTouch(this);
        }


    }

    private void onStopTrackingTouch() {
        if (mOnSeekArcChangeListener != null) {
            mOnSeekArcChangeListener.onStopTrackingTouch(this);
        }
    }

    private void updateOnTouch(MotionEvent event) {
        if (!isMoveStarted && !isTouched) {
            /*boolean ignoreTouch = ignoreTouch(event.getX(), event.getY());
            if (ignoreTouch) {*/

            return;
            //}
        }


        mTouchAngle = getTouchDegrees(event.getX(), event.getY());

        if (mTouchAngle > RIGHT_BOUND && mTouchAngle < 180) {
            mTouchAngle = RIGHT_BOUND;
            isTouched = false;
            SeekArcDataHelper.INSTANCE.setIsInSeekArcTouch(false);
        }
        if (mTouchAngle > 180 && mTouchAngle < LEFT_BOUND) {
            mTouchAngle = LEFT_BOUND;
            isTouched = false;
            SeekArcDataHelper.INSTANCE.setIsInSeekArcTouch(false);
        }


        int progress = getProgressForAngle(mTouchAngle);
        setPressed(true);

        onProgressRefresh(progress, true);
    }

    private boolean isThumbPressed(float xpos, float ypos) {

        double curTouch = getTouchDegrees(xpos, ypos);
        int progress = getProgressForAngle(curTouch);
        int arcStart = (int) progress + 90;
        int curThumbXPos = (int) (mArcRadius * Math.cos(Math.toRadians(arcStart)));
        int curThumbYpos = (int) (mArcRadius * Math.sin(Math.toRadians(arcStart)));
/*
        Log.d(AppConstant.LOG_TAG,"curTouch   :"+curTouch);
        Log.d(AppConstant.LOG_TAG,"progress   :"+progress);
        Log.d(AppConstant.LOG_TAG,"arcStart   :"+arcStart);*/
        //return (((Math.abs(mThumbXPos - curThumbXPos) > 0) && (Math.abs(mThumbXPos - curThumbXPos) < 6)) || ((Math.abs(mThumbYPos - curThumbYpos) > 0) && (Math.abs(mThumbYPos - curThumbYpos) < 6)));
        double distance = (Math.sqrt(Math.pow((mThumbXPos2 - mThumbXPos), 2) + Math.pow((mThumbYPos2 - mThumbYPos), 2))) * 2;
        int x = (curThumbXPos - mThumbXPos);
        int y = (curThumbYpos - mThumbYPos);
        return ((distance > Math.abs((x + y))) && (distance > Math.abs((x - y))));
    }

    private boolean ignoreTouch(float xPos, float yPos) {
        boolean ignore = false;
        float x = xPos - mTranslateX;
        float y = yPos - mTranslateY;

        float touchRadius = (float) Math.sqrt(((x * x) + (y * y)));


      /*  Log.d(TAG,"mTouchIgnoreRadius :"+mTouchIgnoreRadius);
        Log.d(TAG,"mTouchIgnoreRadiusOutSide :"+mTouchIgnoreRadiusOutSide);
        Log.d(TAG,"touchRadius :"+touchRadius);*/
        if (touchRadius < mTouchIgnoreRadius || touchRadius > mTouchIgnoreRadiusOutSide) {
            ignore = true;
            isTouched = false;
            SeekArcDataHelper.INSTANCE.setIsInSeekArcTouch(false);


        }

      /*  Log.d(TAG, "ignore :" + ignore);
        Log.d(TAG, "isTouched :" + isTouched);*/
        return ignore;
    }

    private double getTouchDegrees(float xPos, float yPos) {
        float x = xPos - mTranslateX;
        float y = yPos - mTranslateY;
        double angle = Math.toDegrees(Math.atan2(y, x) + (Math.PI / 2) - Math.toRadians(mRotation));
        if (angle < 0) {
            angle = 360 + angle;
        }

        return angle;
    }

    private int getProgressForAngle(double angle) {
        int touchProgress = (int) Math.round(1 * angle);

        touchProgress = (touchProgress < 0) ? INVALID_PROGRESS_VALUE
                : touchProgress;
        touchProgress = (touchProgress > mMax) ? INVALID_PROGRESS_VALUE
                : touchProgress;

        return touchProgress;
    }

	/*private float valuePerDegree() {

        return (float) mMax / mSweepAngle;
	}*/

    private void onProgressRefresh(int progress, boolean fromUser) {
        updateProgress(progress, fromUser);
    }

    private void updateThumbPosition() {
        int thumbAngle = (int) (mStartAngle + mProgressSweep + mRotation + 90);
        //int thumbAngle = (int) (180 + dSweepAngle + mRotation + 90);
        mThumbXPos = (int) (mArcRadius * Math.cos(Math.toRadians(thumbAngle)));
        mThumbYPos = (int) (mArcRadius * Math.sin(Math.toRadians(thumbAngle)));

        mThumbXPos2 = (int) ((mArcRadius + mThumbDifference) * Math.cos(Math.toRadians(thumbAngle)));
        mThumbYPos2 = (int) ((mArcRadius + mThumbDifference) * Math.sin(Math.toRadians(thumbAngle)));
    }

    private void updateProgress(int progress, boolean fromUser) {


        // Log.d(TAG,"Update Prigress----============>>> "+progress);
        if (progress == INVALID_PROGRESS_VALUE) {
            return;
        }

        if (mOnSeekArcChangeListener != null) {
            mOnSeekArcChangeListener
                    .onProgressChanged(this, progress, fromUser);
        }

        progress = (progress > mMax) ? mMax : progress;
        if (progress > 180) {
            progress = progress - 360;
        }


        mProgress = progress;
        mProgressSweep = progress;

        updateThumbPosition();
        invalidate();
    }

    /**
     * Sets a listener to receive notifications of changes to the SeekArc's
     * progress level. Also provides notifications of when the user starts and
     * stops a touch gesture within the SeekArc.
     *
     * @param l The seek bar notification listener
     * @see //SeekArc.OnSeekBarChangeListener
     */
    public void setOnSeekArcChangeListener(OnSeekArcChangeListener l) {
        mOnSeekArcChangeListener = l;
    }

    public void setProgress(int progress) {
        updateProgress(progress, false);
    }

    public int getProgressWidth() {
        return mProgressWidth;
    }

    public void setProgressWidth(int mProgressWidth) {
        this.mProgressWidth = mProgressWidth;
        mProgressPaint.setStrokeWidth(mProgressWidth);
    }

    public int getArcWidth() {
        return mArcWidth;
    }

    public void setArcWidth(int mArcWidth) {
        this.mArcWidth = mArcWidth;
        mArcPaint.setStrokeWidth(mArcWidth);
        this.mProgressWidth = mArcWidth;
        mProgressPaint.setStrokeWidth(mArcWidth);
    }

    public int getArcRotation() {
        return mRotation;
    }

    public void setArcRotation(int mRotation) {
        this.mRotation = mRotation;
        updateThumbPosition();
    }

    public int getStartAngle() {
        return mStartAngle;
    }

    public void setStartAngle(int mStartAngle) {
        this.mStartAngle = mStartAngle;
        updateThumbPosition();
    }

    public int getSweepAngle() {
        return mSweepAngle;
    }

	/*public void setSweepAngle(int mSweepAngle) {
        this.mSweepAngle = mSweepAngle;
		updateThumbPosition();
	}

	public void setRoundedEdges(boolean isEnabled) {
		mRoundedEdges = isEnabled;
		if (mRoundedEdges) {
			mArcPaint.setStrokeCap(Paint.Cap.ROUND);
			mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
		} else {
			mArcPaint.setStrokeCap(Paint.Cap.SQUARE);
			mProgressPaint.setStrokeCap(Paint.Cap.SQUARE);
		}
	}*/

    public void setTouchInSide(boolean isEnabled) {
        int thumbHalfheight = (int) mThumb.getIntrinsicHeight() / 2;
        int thumbHalfWidth = (int) mThumb.getIntrinsicWidth() / 2;
        mTouchInside = isEnabled;
        if (mTouchInside) {
            mTouchIgnoreRadius = (float) mArcRadius / 4;

        } else {
            // Don't use the exact radius makes interaction too tricky
            mTouchIgnoreRadius = mArcRadius
                    - Math.min(thumbHalfWidth, thumbHalfheight);


        }
    }

    public void setTouchOutSide(boolean isEnabled) {
        int thumbHalfheight = (int) mThumb.getIntrinsicHeight() / 2;
        int thumbHalfWidth = (int) mThumb.getIntrinsicWidth() / 2;
        mTouchOutSide = isEnabled;
        if (mTouchOutSide) {
            mTouchIgnoreRadiusOutSide = (float) mArcRadius / 4;


        } else {
            // Don't use the exact radius makes interaction too tricky
            mTouchIgnoreRadiusOutSide = mArcRadius
                    + Math.min(thumbHalfWidth, thumbHalfheight);

        }
    }


    public double getCurrentTemp() {
        return mCurrentTemp;
    }

    public void setCurrentTemp(double CurrentTemp) {
        this.mCurrentTemp = CurrentTemp;

        if (getCurrentTemp() > getDesireTemp()) {
            int[] gradients = {
                    blue, green, red
            };
            float[] positions = {0.25F, 0.55F, 0.85F};
            Shader shader = new SweepGradient(cx, cy, gradients, positions);
            mUserLimitProgressPaint.setShader(shader);
            Matrix matrix = new Matrix();
            matrix.preRotate(mAngleOffset + tempStartAngle - 30, cx, cy);
            shader.setLocalMatrix(matrix);
            mProgressPaint.setShader(shader);
        } else if (getCurrentTemp() < getDesireTemp()) {
            mProgressPaint.setShader(new LinearGradient(0, 0, 0, getHeight(), green, blue, Shader.TileMode.MIRROR));
        }

    }

    public float getLimitStartAngle() {
        return this.mLimitStartAngle;
    }


    public void setLimitStartAngle(float LimitStartAngle) {
        setUserLimitStartPoint(LimitStartAngle);
        float tempLimitStartAngle = (float) (210 + mGapAngle * (LimitStartAngle - mBuildingLimitStartAngle));
        if (tempLimitStartAngle < 360) {
            this.mLimitStartAngle = tempLimitStartAngle;
        } else {
            this.mLimitStartAngle = tempLimitStartAngle - 360;
        }
        //LEFT_BOUND = mLimitStartAngle;
    }

    public float getLimitSweepAngle() {

        if (this.mLimitStartAngle < 180 && this.mLimitEndAngle < 180) {
            return this.mLimitEndAngle - this.mLimitStartAngle;
        } else if ((this.mLimitStartAngle > 180 && this.mLimitStartAngle < 360) && (this.mLimitEndAngle > 180 && this.mLimitEndAngle < 360)) {
            return this.mLimitEndAngle - this.mLimitStartAngle;
        }


        if (this.mLimitStartAngle == 360 || this.mLimitStartAngle == 0) {
            return Math.abs(0 + this.mLimitEndAngle);
        } else {
            float startLimitAngle = 360 - this.mLimitStartAngle;

            float ret = Math.abs(startLimitAngle + this.mLimitEndAngle);
            if (ret <= 360)
                return ret;
            else
                return ret - 360;
        }

    }


    public float getLimitEndAngle() {
        return this.mLimitEndAngle;
    }

    public void setLimitEndAngle(float LimitEndAngle) {
        setUserLimitEndPoint(LimitEndAngle);
        float tempLimitEndAngle = (float) (210 + mGapAngle * (LimitEndAngle - mBuildingLimitStartAngle));
        if (tempLimitEndAngle <= 360) {
            this.mLimitEndAngle = tempLimitEndAngle;
        } else {
            this.mLimitEndAngle = tempLimitEndAngle - 360;
        }
        //RIGHT_BOUND = mLimitEndAngle;
    }

    public float getUserLimitStartPoint() {
        return userLimitStartPoint;
    }

    public void setUserLimitStartPoint(float userLimitStartPoint) {
        this.userLimitStartPoint = userLimitStartPoint;
    }

    public float getUserLimitEndPoint() {
        return userLimitEndPoint;
    }

    public void setUserLimitEndPoint(float userLimitEndPoint) {
        this.userLimitEndPoint = userLimitEndPoint;
    }

    public float getmPathStartAngle() {
        return mPathStartAngle;
    }

    public void setmPathStartAngle(float mPathStartAngle) {
        this.mPathStartAngle = mPathStartAngle;
    }


    public double getDesireTemp() {
        return mDesireTemp;
    }

    public void setDesireTemp(double DesireTemp) {

        this.mDesireTemp = DesireTemp;
    }

    public boolean isDetailedView() {
        return mDetailedView;
    }

    public void setDetailedView(boolean isDetailedView) {
        this.mDetailedView = isDetailedView;
    }


    public double getmBuildingLimitStartAngle() {
        return Math.round(mBuildingLimitStartAngle);
    }

    public void setmBuildingLimitStartAngle(double mBuildingLimitStartAngle) {
        this.mBuildingLimitStartAngle = mBuildingLimitStartAngle;
    }

    public double getmBuildingLimitEndAngle() {
        return Math.round(mBuildingLimitEndAngle);
    }

    public void setmBuildingLimitEndAngle(double mBuildingLimitEndAngle) {
        this.mBuildingLimitEndAngle = mBuildingLimitEndAngle;
    }


    public boolean isCurrBeyondLimit() {
        return isCurrBeyondLimit;
    }

    public void setIsCurrBeyondLimit(boolean isCurrBeyondLimit) {
        this.isCurrBeyondLimit = isCurrBeyondLimit;
    }


}

}
