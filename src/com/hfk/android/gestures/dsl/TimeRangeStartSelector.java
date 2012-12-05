package com.hfk.android.gestures.dsl;

import android.view.View;

import com.hfk.android.gestures.IGestureCondition;

public class TimeRangeStartSelector {
	public TimeRangeStartSelector(int distanceRange, View parentView)
	{
		range = distanceRange;
		view = parentView;
	}
	
	public IGestureCondition fromTouchDown()
	{
		return  null;
	}
	
	public IGestureCondition fromMove()
	{
		return  null;
	}
	
	public IGestureCondition fromTouchUp()
	{
		return  null;
	}
	
	private int range;
	private View view;
}
