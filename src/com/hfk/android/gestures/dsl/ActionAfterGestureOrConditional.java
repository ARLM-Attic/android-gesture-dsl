package com.hfk.android.gestures.dsl;

import com.hfk.android.gestures.DoInvalidateGestureGestureAction;
import com.hfk.android.gestures.GestureEvent;
import com.hfk.android.gestures.IGestureAction;
import com.hfk.android.gestures.IGestureCondition;
import com.hfk.android.gestures.IfThenClause;
import com.hfk.android.gestures.TouchEvent;
import com.hfk.android.gestures.TouchGesture;

public class ActionAfterGestureOrConditional<NextGesture>  {
	
	public ActionAfterGestureOrConditional(Class<NextGesture> aClass, TouchGesture gstr, TouchEvent vnt)
	{
		refClass = aClass;
		gesture = gstr;
		event = vnt;
	}
	
	public NextGestureOrConditional<NextGesture> Do1(IGestureAction action)
	{
		IfThenClause condition = new IfThenClause(
						new IGestureCondition()
						{
							public boolean checkCondition(GestureEvent motion, TouchGesture gesture)
							{
								return true;
							}
						}
				);
		condition.setThenAction(action);
		
		event.conditionList.add(condition);

		NextGestureOrConditional<NextGesture> result = new NextGestureOrConditional<NextGesture>(refClass, gesture, event);
		
		return result;
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
}
