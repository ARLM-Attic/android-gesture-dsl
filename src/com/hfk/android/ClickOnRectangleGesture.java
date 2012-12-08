package com.hfk.android;

import com.hfk.android.conditions.OnRectangleCondition;
import com.hfk.android.actions.ShowMessageAction;
import com.hfk.android.gestures.IGestureAction;
import com.hfk.android.gestures.IGestureCondition;
import com.hfk.android.gestures.TouchGesture;
import com.hfk.android.gestures.dsl.GestureBuilder;

public class ClickOnRectangleGesture extends GestureBuilder<AndroidGestureDSLView> {
	
	public ClickOnRectangleGesture(AndroidGestureDSLView view)
	{
		super(view);
	}
	
	public TouchGesture create()
	{
		TouchGesture gesture = new TouchGesture("ClickOnCircleGesture");
		
		this.Create(gesture).TouchDown().If(OnRectangle())
			.AndNext().CanMove()
				.If(within().milliMeters(2).fromTouchDown())
			.AndNext().TouchUp()
				.If(within().seconds(1).fromTouchDown())
					.Do2(ShowMessage("You clicked on the circle"))
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
