package com.hfk.android.gestures.dsl;

import com.hfk.android.gestures.DoInvalidateGestureGestureAction;
import com.hfk.android.gestures.GestureEvent;
import com.hfk.android.gestures.IGestureCondition;
import com.hfk.android.gestures.IfThenClause;
import com.hfk.android.gestures.TouchEvent;
import com.hfk.android.gestures.TouchGesture;

public class NextGestureAfterTouchDown {
	
	public NextGestureAfterTouchDown(TouchGesture gstr)
	{
		gesture = gstr;
	}
	
	public ActionAfterGestureOrConditional<NextGestureAfterMove> Move()
	{
	    TouchEvent moveEvent = new TouchEvent();
	    moveEvent.event = TouchEvent.TOUCH_MOVE;
	    
	    gesture.addEvent(moveEvent);
		
		ActionAfterGestureOrConditional<NextGestureAfterMove> result = new ActionAfterGestureOrConditional<NextGestureAfterMove>(NextGestureAfterMove.class, gesture, moveEvent);		
		return result;
	}
	
	public ActionAfterGestureOrConditional<NextGestureAfterTouchUp> TouchUp()
	{
		int size = gesture.size();
		if(size == 0)
		{
			// If no other events exist, 
			//	we insert a TOUCH_DOWN which is always accepted. You can not have a TOUCH_UP without having had a TOUCH_DOWN
		    TouchEvent upEvent = new TouchEvent();
		    upEvent.event = TouchEvent.TOUCH_DOWN;
			IfThenClause condition = new IfThenClause(
					new IGestureCondition()
					{
						public boolean checkCondition(GestureEvent motion, TouchGesture gesture)
						{
							return true;
						}
					}
			);
			upEvent.conditionList.add(condition);
		    gesture.addEvent(upEvent);
			
			//	we insert a TOUCH_MOVE which always invalidates.
		    TouchEvent moveEvent = new TouchEvent();
		    moveEvent.event = TouchEvent.TOUCH_MOVE;
		    gesture.addEvent(moveEvent);
		}
		else
		{		
			TouchEvent previousEvent = gesture.getEvent(size - 1);
			// If no TOUCH_MOVE exists, then insert one which, when executed, invalidates the gesture
			if(previousEvent.event != TouchEvent.TOUCH_MOVE)
			{
			    TouchEvent moveEvent = new TouchEvent();
			    moveEvent.event = TouchEvent.TOUCH_MOVE;
				IfThenClause condition = new IfThenClause(
						new IGestureCondition()
						{
							public boolean checkCondition(GestureEvent motion, TouchGesture gesture)
							{
								return true;
							}
						}
				);
				condition.setThenAction(new DoInvalidateGestureGestureAction());
				moveEvent.conditionList.add(condition);
			    gesture.addEvent(moveEvent);
			}
		}
		
	    TouchEvent upEvent = new TouchEvent();
	    upEvent.event = TouchEvent.TOUCH_UP;
	    
	    gesture.addEvent(upEvent);
		
		ActionAfterGestureOrConditional<NextGestureAfterTouchUp> move = new ActionAfterGestureOrConditional<NextGestureAfterTouchUp>(NextGestureAfterTouchUp.class, gesture, upEvent);
		return move;
	}
	
	TouchGesture gesture;

}
