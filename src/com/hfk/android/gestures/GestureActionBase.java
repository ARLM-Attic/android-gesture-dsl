package com.hfk.android.gestures;

import android.view.MotionEvent;

public abstract class GestureActionBase<T> implements IGestureAction {

	//public GestureActionBase(T view, TouchGesture gesture) {
	public GestureActionBase(T view) {
		touchView = view;
		//touchGesture = gesture;
	}

	public T getTouchedView()
	{
		return touchView;
	}
	
//	public boolean contextExists(String key)
//	{
//		return touchGesture.contextExists(key);
//	}
//	
//	public void addContext(String key, Object data)
//	{
//		touchGesture.addContext(key, data);
//	}
//	
//	public Object getContext(String key)
//	{
//		return touchGesture.getContext(key);
//	}
	
	private T touchView;
	//private TouchGesture touchGesture;
}
