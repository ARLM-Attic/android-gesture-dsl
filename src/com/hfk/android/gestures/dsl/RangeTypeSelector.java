package com.hfk.android.gestures.dsl;

import android.view.View;

public class RangeTypeSelector {
	
	public RangeTypeSelector(View parentView)
	{
		view = parentView;
	}

	public DistanceRangeStartSelector milliMeters(int range)
	{
		DistanceRangeStartSelector result = new DistanceRangeStartSelector(range, view);
		return result;
	}
	
	public TimeRangeStartSelector seconds(int range)
	{
		TimeRangeStartSelector result = new TimeRangeStartSelector(1000 * range, view);
		return result;
	}
	
	private View view;
}
