package com.hfk.android.gestures;

public abstract class GestureConditionBase<T> implements IGestureCondition {

	//public GestureConditionBase(T view, TouchGesture gesture) {
	public GestureConditionBase(T view) {
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
