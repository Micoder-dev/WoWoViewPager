package com.nightonke.wowoviewpager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.nightonke.wowoviewpager.Animation.ViewAnimation;
import com.nightonke.wowoviewpager.Enum.Ease;
import com.nightonke.wowoviewpager.Enum.Gearbox;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Weiping Huang at 23:56 on 2016/3/3
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 */

public class WoWoViewPager extends BaseViewPager {

    public static final String TAG = "WoWoViewPager";

    // Public attributes
    private Gearbox gearbox = Gearbox.Positive3;
    private boolean draggable = true;
    private int scrollDuration = -1;

    // Inner attributes
    private WoWoDirection direction = WoWoDirection.Unknown;
    private float lastOffset = -1;
    private int lastPage = -1;
    private boolean lastOffsetIsInteger = true;
    private boolean easeReverse = false;
    private ArrayList<ViewAnimation> viewAnimations = new ArrayList<>();
    private WoWoScroller scroller = null;

    // Scroll automatically
    private boolean autoScroll = false;
    private boolean touching = false;
    private boolean touchThenStop = false;
    private int delayPerPage = 1000;
    private Timer autoScrollTimer = null;
    private Handler autoScrollHandler;

    public WoWoViewPager(Context context) {
        super(context);
        init(context, null);
    }

    public WoWoViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public WoWoViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        initAttrs(context, attrs);
        initScroller();
    }

    private void initAttrs(Context context, AttributeSet attrs) {

    }

    private void initScroller() {
        try {
            Field mScroller;
            mScroller = BaseViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            scroller = new WoWoScroller(this.getContext(), gearbox.interpolator());
            scroller.setDuration(scrollDuration);
            mScroller.set(this, scroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will be invoked when the current page is scrolled, either as part
     * of a programmatically initiated smooth scroll or a user initiated touch scroll.
     * If you override this method you must call through to the superclass implementation
     * (e.g. super.onPageScrolled(position, offset, offsetPixels)) before onPageScrolled
     * returns.
     *
     * @param position Position index of the first page currently being displayed.
     *                 Page position+1 will be visible if positionOffset is nonzero.
     * @param offset Value from [0, 1) indicating the offset from the page at position.
     * @param offsetPixels Value in pixels indicating the offset from position.
     */
    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels) {
        super.onPageScrolled(position, offset, offsetPixels);
        innerPlayAnimations(position, offset);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return draggable && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_CANCEL) {
            touching = false;
        } else if (ev.getAction() == MotionEvent.ACTION_DOWN || ev.getAction() == MotionEvent.ACTION_MOVE) {
            stopAutoScrollTimer();
            if (touchThenStop) stopAutoScroll();
            touching = true;
        }
        return draggable && super.onTouchEvent(ev);
    }

    /**
     * Logic to play view animations.
     *
     * @param position Position of WoWoViewPager
     * @param offset Offset of WoWoViewPager
     */
    private void innerPlayAnimations(int position, float offset) {
        float nowOffset = position + offset;
        // Should be true when from page 1 offset 0 to page 0 offset 0.999
        // If the scroll duration is zero, then there may be from page 1 offset 0 to page 0 offset 0,
        // in this situation, it should not be true. Check the logic A
        boolean shouldForceAnimationToStartStateInNextPage = false;
        boolean inOrder = nowOffset > lastOffset;
        boolean reverse = nowOffset < lastOffset;
        boolean still = nowOffset == lastOffset;
        boolean pageChanged = pageChanged(position);
        if (still) return;
        if (offset == 0) checkWhetherAutoScroll(position);
        if (inOrder) direction = WoWoDirection.GoingRight;
        else if (reverse) direction = WoWoDirection.GoingLeft;
        if (pageChanged) {
            if (reverse) {
                if (offset != 0) shouldForceAnimationToStartStateInNextPage = true;  // Logic A
                easeReverse = true;
            } else easeReverse = false;
        }
        lastOffsetIsInteger = offset == 0;
        lastOffset = nowOffset;

        for (int i = 0; i < viewAnimations.size(); i++) {
            if (shouldForceAnimationToStartStateInNextPage) viewAnimations.get(i).forceAnimationToStartStateInNextPage(position + 1);
            viewAnimations.get(i).play(position, offset, direction == WoWoDirection.GoingRight, easeReverse);
            if (offset == 0 || (pageChanged && inOrder)) viewAnimations.get(i).forceAnimationToEndStateInPreviousPage(position - 1);
        }
    }

    /**
     * Whether the page of WoWoViewPager is changed.
     *
     * @param currentPage Current page
     * @return Changed?
     */
    private boolean pageChanged(int currentPage) {
        boolean pageChanged = false;
        if (lastPage != currentPage) pageChanged = true;
        lastPage = currentPage;
        return pageChanged;
    }

    /**
     * Check whether we should start the auto-scroll timer.
     * This method should be call when the view-pager offset is zero(at the start of a page).
     *
     * @param position Position
     */
    private void checkWhetherAutoScroll(int position) {
        if (position == getAdapter().getCount() - 1) stopAutoScroll();
        else if (autoScroll) {
            startAutoScrollTimer();
        }
    }

    /**
     * Start the auto-scroll timer.
     */
    private void startAutoScrollTimer() {
        if (!autoScroll || touching) return;
        if (autoScrollTimer != null) autoScrollTimer.cancel();
        autoScrollTimer = new Timer();
        autoScrollTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (autoScrollHandler != null) autoScrollHandler.obtainMessage().sendToTarget();
            }
        }, delayPerPage);
    }

    /**
     * Stop the auto-scroll timer.
     */
    private void stopAutoScrollTimer() {
        if (autoScrollTimer != null) autoScrollTimer.cancel();
    }

    /**
     * Add a ViewAnimation to WoWoViewPager.
     *
     * @param viewAnimation The new ViewAnimation
     * @return WoWoViewPager itself
     */
    public WoWoViewPager addAnimation(ViewAnimation viewAnimation) {
        viewAnimations.add(viewAnimation);
        return this;
    }

    /**
     * Create and add a ViewAnimation to WoWoViewPager.
     *
     * @param view Animation-playing-view
     * @return The new ViewAnimation
     */
    public ViewAnimation addAnimation(View view) {
        ViewAnimation viewAnimation = new ViewAnimation(view);
        viewAnimations.add(viewAnimation);
        return viewAnimation;
    }

    /**
     * After adding animations into WoWoViewPager, this method must be call.
     */
    public void ready() {
        lastOffset = -1;
        innerPlayAnimations(0, 0);
    }

    /**
     * Smoothly animating to next page.
     * 
     * @return Successfully or not
     */
    public boolean next() {
        if (lastOffsetIsInteger) {
            if (getCurrentItem() == getAdapter().getCount() - 1) return false;
            else {
                setCurrentItem(getCurrentItem() + 1, true);
                return true;
            }
        } else return false;
    }

    /**
     * Smoothly animating to previous page.
     * 
     * @return Successfully or not
     */
    public boolean previous() {
        if (lastOffsetIsInteger) {
            if (getCurrentItem() == 0) return false;
            else {
                setCurrentItem(getCurrentItem() - 1, true);
                return true;
            }
        } else return false;
    }

    /**
     * @return The {@link Gearbox} of the WoWoViewPager.
     */
    public Gearbox getGearbox() {
        return gearbox;
    }

    /**
     * Set the {@link Gearbox} of the WoWoViewPager.
     *
     * @param gearbox {@link Gearbox}
     */
    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
        initScroller();
    }

    /**
     * @return Whether the WoWoViewPager is draggable.
     */
    public boolean isDraggable() {
        return draggable;
    }

    /**
     * Set whether the WoWoViewPager is draggable.
     *
     * @param draggable Draggable or not
     */
    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
    }

    /**
     * @return The duration of swiping.
     */
    public int getScrollDuration() {
        return scrollDuration;
    }

    /**
     * Set the duration of swiping in ms
     * @param scrollDuration Duration in ms, -1 to reset
     */
    public void setScrollDuration(int scrollDuration) {
        this.scrollDuration = scrollDuration;
        if (scroller == null) initScroller();
        else scroller.setDuration(this.scrollDuration);
    }

    /**
     * Set ease type for all page animations in all view animations.
     *
     * @param ease Ease enum
     */
    public void setEase(Ease ease) {
        if (viewAnimations == null) return;
        for (ViewAnimation viewAnimation : viewAnimations) viewAnimation.setEase(ease);
    }

    /**
     * Set ease type for all page animations at a certain page in all view animations.
     *
     * @param ease Ease enum
     * @param page Certain page
     */
    public void setEase(Ease ease, int page) {
        if (viewAnimations == null) return;
        for (ViewAnimation viewAnimation : viewAnimations) viewAnimation.setEase(ease, page);
    }

    /**
     * Use same ease enum when swiping the view-pager back for all page animations in all view animations.
     *
     * @param useSameEaseEnumBack Use same ease enum when swiping the view-pager back
     */
    public void setUseSameEaseBack(boolean useSameEaseEnumBack) {
        if (viewAnimations == null) return;
        for (ViewAnimation viewAnimation : viewAnimations) viewAnimation.setUseSameEaseBack(useSameEaseEnumBack);
    }

    /**
     * Use same ease enum when swiping the view-pager back at a certain page in all view animations.
     *
     * @param useSameEaseEnumBack Use same ease enum when swiping the view-pager back
     * @param page Certain page
     */
    public void setUseSameEaseBack(boolean useSameEaseEnumBack, int page) {
        if (viewAnimations == null) return;
        for (ViewAnimation viewAnimation : viewAnimations) viewAnimation.setUseSameEaseBack(useSameEaseEnumBack, page);
    }

    public void startAutoScroll(boolean touchThenStop, int delayPerPage, int scrollDuration) {
        if (autoScrollHandler == null) autoScrollHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                next();
            }
        };

        stopAutoScrollTimer();
        autoScroll = true;
        this.touchThenStop = touchThenStop;
        this.delayPerPage = delayPerPage;
        setScrollDuration(scrollDuration);
        startAutoScrollTimer();


    }

    public void stopAutoScroll() {
        stopAutoScrollTimer();
        setScrollDuration(-1);
        autoScroll = false;
    }

}
