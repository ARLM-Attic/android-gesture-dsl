package com.hfk.android;

import android.telephony.SignalStrength;

import com.hfk.android.actions.ShowMessageAction;
import com.hfk.android.conditions.OnRectangleCondition;
import com.hfk.android.gestures.IGestureAction;
import com.hfk.android.gestures.IGestureCondition;
import com.hfk.android.gestures.TouchGesture;
import com.hfk.android.gestures.dsl.GestureBuilder;

public class ClickAndDoubleClickOnRectangleGesture extends GestureBuilder<AndroidGestureDSLView> {
	
	public ClickAndDoubleClickOnRectangleGesture(AndroidGestureDSLView view)
	{
		super(view);
	}
	
	public TouchGesture create()
	{
		TouchGesture gesture = new TouchGesture("ClickAndDoubleClickOnRectangleGesture");
		
		this.Create(gesture).TouchDown()
				.If(OnRectangle())
			.AndNext().CanMove()
				.If(within().milliMeters(2).fromTouchDown(1))
			.AndNext().TouchUp()
				.Do1(after().seconds(1).Do(
						ShowMessage("You clicked on the rectangle"),
						gestureIsCompleted()))
			.AndNext().TouchDown()
				.Do1(endCurrentTimer())
			.AndNext().CanMove()
				.If(within().milliMeters(2).fromTouchDown(1))
			.AndNext().TouchUp()
				.If(within().seconds(2).fromTouchDown(1))
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
