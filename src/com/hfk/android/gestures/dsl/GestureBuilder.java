package com.hfk.android.gestures.dsl;

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
	
	public V getView()
	{
		return view;
	}
	
	V view;
}
