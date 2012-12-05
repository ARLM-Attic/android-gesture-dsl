package com.hfk.android.gestures.dsl;

import com.hfk.android.gestures.TouchGesture;

public class GestureBuilder<View> {
	
	public GestureBuilder(View view)
	{
		this.view = view;
	}
	
	public NextGestureAfterCreate Create(TouchGesture gesture)
	{
		return new NextGestureAfterCreate(gesture);
	}
	
	public RangeTypeSelector within()
	{
		RangeTypeSelector result = new RangeTypeSelector();
		return result;
	}
	
	public View getView()
	{
		return view;
	}
	
	View view;
}
