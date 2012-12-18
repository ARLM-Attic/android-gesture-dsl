package com.hfk.android;

import com.hfk.android.actions.DragRectangleAction;
import com.hfk.android.actions.NoDraggingAction;
import com.hfk.android.actions.RegisterRectangleHitPointAction;
import com.hfk.android.actions.ShowMessageAction;
import com.hfk.android.conditions.OnRectangleCondition;
import com.hfk.android.gestures.IGestureAction;
import com.hfk.android.gestures.IGestureCondition;
import com.hfk.android.gestures.TouchGesture;
import com.hfk.android.gestures.dsl.GestureBuilder;

public class DragRectangleGesture extends GestureBuilder<AndroidGestureDSLView> {
	
	public DragRectangleGesture(AndroidGestureDSLView view)
	{
		super(view);
	}
	
	public TouchGesture create()
	{
		TouchGesture gesture = new TouchGesture("DragRectangleGesture");
		
		this.Create(gesture).TouchDown().If(OnRectangle())
				.Do2(RegisterRectangleHitPoint())
			.AndNext()
				.Move()
				.If(not(within().milliMeters(2).fromTouchDown(1)))
					.Do2(DragRectangle())
				.Else()
					.Do3(nothing())
			.AndNext()
				.TouchUp()
				.Do1(nothing())
		;

		
		return gesture;
	}
	
	IGestureCondition OnRectangle()
	{
		return new OnRectangleCondition(getView());
	}
	
	IGestureAction RegisterRectangleHitPoint()
	{
		return new RegisterRectangleHitPointAction(getView());
	}
	
	IGestureAction DragRectangle()
	{
		return new DragRectangleAction(getView());
	}
	
	IGestureAction NoDragging()
	{
		return new NoDraggingAction(getView());
	}

	IGestureAction ShowMessage(String message)
	{
		return new ShowMessageAction(getView(), message);
	}
}
