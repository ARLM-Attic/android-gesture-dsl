package com.hfk.android.actions;

import java.util.List;

import com.hfk.android.AndroidGestureDSLView;
import com.hfk.android.gestures.GestureActionBase;
import com.hfk.android.gestures.GestureEvent;
import com.hfk.android.gestures.TouchGesture;

public class ShowMessageAction extends GestureActionBase<AndroidGestureDSLView> {

	public ShowMessageAction(AndroidGestureDSLView view, String message) {
		super(view);
		
		this.message = message;
	}
	
	@Override
	public void executeAction(GestureEvent motion, TouchGesture gesture) {
		getTouchedView().showMessage(message);
	}
	
	String message;
}
