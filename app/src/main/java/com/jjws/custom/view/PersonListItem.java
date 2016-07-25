package com.jjws.custom.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.jjws.testanim.R;

/**
 * Created by sk on 16-7-18.
 */
public class PersonListItem extends RelativeLayout {


    public static final int []PERSON_STATE_MAN = {R.attr.person_state_man};

    private boolean mIsMan = false;

    public boolean isIsMan() {
        return mIsMan;
    }

    public void setIsMan(boolean mIsMan) {
        this.mIsMan = mIsMan;

        refreshDrawableState();
    }

    public PersonListItem(Context context) {
        super(context);
    }

    public PersonListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PersonListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {

        if(this.mIsMan) {
            int[] states = super.onCreateDrawableState(extraSpace + 1);

            mergeDrawableStates(states, PERSON_STATE_MAN);

            return states;
        }

        return super.onCreateDrawableState(extraSpace);
    }
}
