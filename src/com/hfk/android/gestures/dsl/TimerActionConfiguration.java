package com.hfk.android.gestures.dsl;

import com.hfk.android.gestures.DoInstallTimer;
import com.hfk.android.gestures.DoMultipleAction;
import com.hfk.android.gestures.IGestureAction;

public class TimerActionConfiguration {
	public TimerActionConfiguration(int timeOutValue)
	{
		timeOut = timeOutValue;
	}
	
	public DoInstallTimer Do(IGestureAction action)
	{
		return new DoInstallTimer(action, timeOut);
	}
	
	public DoInstallTimer Do(IGestureAction action1, IGestureAction action2)
	{
		return new DoInstallTimer(new DoMultipleAction(action1, action2), timeOut);
	}
	
	private int timeOut;
}
