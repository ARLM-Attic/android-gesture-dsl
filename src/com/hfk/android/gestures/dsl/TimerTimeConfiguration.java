package com.hfk.android.gestures.dsl;

public class TimerTimeConfiguration {
	public TimerActionConfiguration seconds(int seconds)
	{
		return new TimerActionConfiguration(seconds * 1000);
	}
}
