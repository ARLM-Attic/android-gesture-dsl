package com.hfk.android.gestures.dsl;

import com.hfk.android.gestures.DoMultipleAction;
import com.hfk.android.gestures.GestureEvent;
import com.hfk.android.gestures.IGestureAction;
import com.hfk.android.gestures.IGestureCondition;
import com.hfk.android.gestures.IfThenClause;
import com.hfk.android.gestures.TouchEvent;
import com.hfk.android.gestures.TouchGesture;

public class ActionAfterGestureOrConditionalContinuation<NextGesture>  {
	
	public ActionAfterGestureOrConditionalContinuation(Class<NextGesture> aClass, TouchGesture gstr, TouchEvent vnt, IfThenClause aCondition)
	{
		refClass = aClass;
		gesture = gstr;
		event = vnt;
		condition = aCondition;
	}
	
	public NextGestureOrConditional<NextGesture> Do3(IGestureAction action)
	{
		condition.setElseAction(action);

		NextGestureOrConditional<NextGesture> result = new NextGestureOrConditional<NextGesture>(refClass, gesture, event);
		
		return result;
	}
	
	public NextGestureOrConditional<NextGesture> Do3(IGestureAction action1, IGestureAction action2)
	{
		return Do3(new DoMultipleAction(action1, action2));
	}
	
	public NextGestureOrConditional<NextGesture> Do3(IGestureAction action1, IGestureAction action2, IGestureAction action3)
	{
		return Do3(new DoMultipleAction(action1, action2, action3));
	}
	
	public AfterConditional<NextGesture> If(IGestureCondition condition) 
	{
		event.conditionList.add(new IfThenClause(condition));
		
		AfterConditional<NextGesture> result = new AfterConditional<NextGesture>(refClass, gesture, event);
		
		return result;
	}
	
	private Class<NextGesture> refClass;
	private TouchGesture gesture;
	private TouchEvent event;
	private IfThenClause condition;
}
