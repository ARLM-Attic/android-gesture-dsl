package com.hfk.android;

import com.hfk.android.actions.ShowMessageAction;
import com.hfk.android.conditions.OnRectangleCondition;
import com.hfk.android.gestures.IGestureAction;
import com.hfk.android.gestures.IGestureCondition;
import com.hfk.android.gestures.TouchGesture;
import com.hfk.android.gestures.dsl.GestureBuilder;

public class LongClickOutsideRectangle extends GestureBuilder<AndroidGestureDSLView> {
	
	public LongClickOutsideRectangle(AndroidGestureDSLView view)
	{
		super(view);
	}
	
	public TouchGesture create()
	{
		TouchGesture gesture = new TouchGesture("LongClickOutsideRectangle");
		
		this.Create(gesture).TouchDown().If(not(OnRectangle()))
			.AndNext().CanMove()
				.If(within().milliMeters(2).fromTouchDown(1))
			.AndNext().TouchUp()
				.If(not(within().seconds(3).fromTouchDown(1)))
					.Do2(ShowMessage("You longclicked outside the rectangle"))
		;
		
		return gesture;
	}
	
	IGestureCondition OnRectangle()
	{
		return new OnRectangleCondition(getView());
	}

	IGestureAction ShowMessage(String message)
	{
		return new ShowMessageAction(getView(), message);
	}
}
