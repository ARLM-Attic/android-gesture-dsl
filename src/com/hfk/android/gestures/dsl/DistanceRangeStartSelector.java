package com.hfk.android.gestures.dsl;

import android.view.View;

import com.hfk.android.gestures.CheckWithinDistanceOfRegisteredPointCondition;
import com.hfk.android.gestures.CheckWithinDistanceOfTouchEventCondition;
import com.hfk.android.gestures.IGestureCondition;

public class DistanceRangeStartSelector {
	public DistanceRangeStartSelector(int distanceRange, View parentView)
	{
		range = distanceRange;
		view = parentView;
	}
	
	public IGestureCondition fromTouchDown()
	{
		return new CheckWithinDistanceOfRegisteredPointCondition(view, range, "ACTION_DOWN");
	}
	
	public IGestureCondition fromMove()
	{
		return  new CheckWithinDistanceOfRegisteredPointCondition(view, range, "ACTION_MOVE");
	}
	
	public IGestureCondition fromTouchUp()
	{
		return  new CheckWithinDistanceOfRegisteredPointCondition(view, range, "ACTION_UP");
	}
	
	private int range;
	private View view;
}
