package com.hfk.android.gestures.dsl;

public class RangeTypeSelector {

	public DistanceRangeStartSelector milliMeters(int value)
	{
		DistanceRangeStartSelector result = new DistanceRangeStartSelector();
		return result;
	}
	
	public TimeRangeStartSelector seconds(int value)
	{
		TimeRangeStartSelector result = new TimeRangeStartSelector();
		return result;
	}
}
