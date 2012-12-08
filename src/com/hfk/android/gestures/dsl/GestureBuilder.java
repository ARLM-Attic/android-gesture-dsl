package com.hfk.android.gestures.dsl;

import com.hfk.android.gestures.CheckNot;
import com.hfk.android.gestures.DoInvalidateRunningTimerAction;
import com.hfk.android.gestures.IGestureAction;
import com.hfk.android.gestures.IGestureCondition;
import com.hfk.android.gestures.TouchGesture;
import android.view.View;

public class GestureBuilder<V> {
	
	public GestureBuilder(V view)
	{
		this.view = view;
	}
	
	public NextGestureAfterCreate Create(TouchGesture gesture)
	{
		return new NextGestureAfterCreate(gesture);
	}
	
	public RangeTypeSelector within()
	{
		RangeTypeSelector result = new RangeTypeSelector((View)getView());
		return result;
	}
	
	public IGestureCondition not(IGestureCondition gestureCondition)
	{
		return new CheckNot(gestureCondition);
	}
	
	public IGestureAction nothing()
	{
		return null;
	}
	
	public TimerTimeConfiguration after()
	{
		return new TimerTimeConfiguration();
	}
	
	public DoInvalidateRunningTimerAction endCurrentTimer()
	{
		return new DoInvalidateRunningTimerAction();
	}
	
	public V getView()
	{
		return view;
	}
	
	V view;
}
