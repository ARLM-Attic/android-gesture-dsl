package com.hfk.android;

import com.hfk.android.actions.ShowMessageAction;
import com.hfk.android.conditions.OnRectangleCondition;
import com.hfk.android.gestures.IGestureAction;
import com.hfk.android.gestures.IGestureCondition;
import com.hfk.android.gestures.TouchGesture;
import com.hfk.android.gestures.dsl.GestureBuilder;

public class DoubleClickOnRectangleGesture extends GestureBuilder<AndroidGestureDSLView> {
	
	public DoubleClickOnRectangleGesture(AndroidGestureDSLView view)
	{
		super(view);
	}
	
	public TouchGesture create()
	{
		TouchGesture gesture = new TouchGesture("DoubleClickOnRectangleGesture");
		
		this.Create(gesture).TouchDown()
				.If(OnRectangle())
			.AndNext().CanMove()
				.If(within().milliMeters(2).fromTouchDown(1))
			.AndNext().TouchUp()
				.Do1(nothing())
			.AndNext().TouchDown()
				.Do1(nothing())
			.AndNext().CanMove()
				.If(within().milliMeters(2).fromTouchDown(2))
			.AndNext().TouchUp()
				.If(within().seconds(1).fromTouchDown(1))
					.Do2(ShowMessage("You doubleclicked on the rectangle"))
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