package com.hfk.android.gestures;

public abstract class GestureConditionBase<T> implements IGestureCondition {

	public GestureConditionBase(T view) {
		touchView = view;
	}

	public T getTouchedView()
	{
		return touchView;
	}
	
	private T touchView;
}
