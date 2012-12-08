package com.hfk.android.gestures;

import com.hfk.android.ScreenVector;

import android.view.MotionEvent;

public class GestureEvent {
	public GestureEvent(MotionEvent event)
	{
		androidEvent = event;
		position = new ScreenVector((int)androidEvent.getX(), (int)androidEvent.getY());
	}
	
	public ScreenVector getPosition()
	{
		return position;
	}
	
	public long getTime()
	{
		return androidEvent.getEventTime();
	}
	
	ScreenVector position;
	MotionEvent androidEvent;
}
