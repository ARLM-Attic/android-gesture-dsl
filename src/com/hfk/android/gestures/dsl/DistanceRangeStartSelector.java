package com.hfk.android.gestures.dsl;

import android.view.View;

import com.hfk.android.gestures.CheckWithinDistanceOfRegisteredPointCondition;
import com.hfk.android.gestures.IGestureCondition;
import com.hfk.android.gestures.TouchHandler;

public class DistanceRangeStartSelector {
	public DistanceRangeStartSelector(int distanceRange, View parentView)
	{
		range = distanceRange;
		view = parentView;
	}
	
	public IGestureCondition fromTouchDown()
	{
		return new CheckWithinDistanceOfRegisteredPointCondition(view, range, TouchHandler.ActionDownPos);
	}
	
	public IGestureCondition fromMove()
	{
		return  new CheckWithinDistanceOfRegisteredPointCondition(view, range, TouchHandler.ActionMovePos);
	}
	
	public IGestureCondition fromTouchUp()
	{
		return  new CheckWithinDistanceOfRegisteredPointCondition(view, range, TouchHandler.ActionUpPos);
	}
	
	private int range;
	private View view;
}
