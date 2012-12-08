package com.hfk.android.gestures;

import android.view.MotionEvent;

public abstract class GestureActionBase<T> implements IGestureAction {

	public GestureActionBase(T view) {
		touchView = view;
	}

	public T getTouchedView()
	{
		return touchView;
	}
	
	private T touchView;
}
