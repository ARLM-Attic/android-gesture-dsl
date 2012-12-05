package com.hfk.android.gestures;

import java.util.ArrayList;

public class TouchEvent {
	public static final int TOUCH_DOWN = 1;
	public static final int TOUCH_MOVE = 2;
	public static final int TOUCH_UP = 3;
	
	public int event;
	public boolean isOptional = false;
	//public IGestureAction action = null;
	//public IGestureCondition condition = null;
	public ArrayList<IfThenClause> conditionList = new ArrayList<IfThenClause>();
	
	//public TimeoutEvent timeEvent = null;
}
