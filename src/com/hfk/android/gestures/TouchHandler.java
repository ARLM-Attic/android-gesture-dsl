package com.hfk.android.gestures;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.graphics.Point;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;

public class TouchHandler {
	
	public static String TouchHandler = "TOUCH_HANDLER";
	
	public static String ActionDownPos = "ACTION_DOWN_POSITION";
	public static String ActionDownTime = "ACTION_DOWN_TIME";
	
	public static String ActionMovePos = "ACTION_MOVE_POSITION";
	public static String ActionMoveTime = "ACTION_MOVE_TIME";
	
	public static String ActionUpPos = "ACTION_UP_POSITION";
	public static String ActionUpTime = "ACTION_UP_TIME";
	
	public TouchHandler()
	{
		handler = new Handler();
	}
	
	public void addGesture(TouchGesture gesture)
	{
		gesture.addContext(TouchHandler, this);
		gestureList.add(gesture);
	}
	
	public void invalidateTimer() {
		if(timerAction != null)
		{
			handler.removeCallbacks(timerAction);
			timerAction = null;
		}
	}
	
	public void installTimer(IGestureAction action, int timeOut, final TouchGesture gesture) {
		timerGestureAction = action;
		timerAction = new Runnable()
		{
			public void run() {
				timerGestureAction.executeAction(lastMotionEvent, gesture);
			}
		};
		handler.postDelayed(timerAction, timeOut);
	}

	public void onTouchEvent(MotionEvent androidMotion)   {
		lastMotionEvent = new GestureEvent(androidMotion);
		
    	int action = androidMotion.getActionMasked();
    	
    	GestureEvent motion = new GestureEvent(androidMotion);
      	
		switch (action) {
    	case MotionEvent.ACTION_DOWN:
    	case MotionEvent.ACTION_POINTER_DOWN:
    		for(TouchGesture eventOrder : gestureList)
    		{
    			eventOrder.addContext(ActionDownPos, motion.getPosition());
    			eventOrder.addContext(ActionDownTime, motion.getTime());
	    		if(eventOrder.isValid() && eventOrder.current().event == TouchEvent.TOUCH_DOWN)
	    		{
	    			for(IfThenClause condition: eventOrder.current().conditionList)
	    			{
	    				condition.Execute(motion, eventOrder);
	    				if(!eventOrder.isValid())
	    				{
	    					break;
	    				}
	    			}
	    			
					eventOrder.currentIsExecuted();
	    			eventOrder.moveNext();
	    		}
    		}

    		break;
    	case MotionEvent.ACTION_MOVE:
    		for(TouchGesture eventOrder : gestureList)
    		{
    			boolean isValid = false;
    			int event = eventOrder.current().event;
	    		if(eventOrder.isValid() && eventOrder.current().event == TouchEvent.TOUCH_MOVE)
	    		{
	    			isValid = true;
	    			for(IfThenClause condition: eventOrder.current().conditionList)
	    			{
	    				condition.Execute(motion, eventOrder);
	    				if(!eventOrder.isValid())
	    				{
	    					break;
	    				}
	    			}

					eventOrder.currentIsExecuted();
	    			// Do not remove MOVE events because there will probably be a series
	    			//	of these events and otherwise only one would be accepted
	    			//eventOrder.remove(event);
	    		}

	    		if(!isValid)
	    			eventOrder.invalidate();
    		}

    		break;
    	case MotionEvent.ACTION_UP:
    	case MotionEvent.ACTION_POINTER_UP:
    		for(TouchGesture eventOrder : gestureList)
    		{
    			eventOrder.addContext(ActionUpPos, motion.getPosition());
    			eventOrder.addContext(ActionUpTime, motion.getTime());
				if(eventOrder.isValid() && (eventOrder.current().event == TouchEvent.TOUCH_MOVE) && !eventOrder.current().isOptional && !eventOrder.isCurrentExecuted())
				{
					eventOrder.invalidate();
				}
				if(eventOrder.isValid() && (eventOrder.current().event == TouchEvent.TOUCH_MOVE) && (eventOrder.current().isOptional || eventOrder.isCurrentExecuted()))
				{
					eventOrder.moveNext();
				}
	    		if(eventOrder.isValid() && eventOrder.current().event == TouchEvent.TOUCH_UP)
	    		{
	    			TouchEvent event = eventOrder.current();
	    			
	    			for(IfThenClause condition: eventOrder.current().conditionList)
	    			{
	    				condition.Execute(motion, eventOrder);
	    				if(!eventOrder.isValid())
	    				{
	    					break;
	    				}
	    			}
	    			
					eventOrder.currentIsExecuted();
	    			eventOrder.moveNext();
	    		}
    		}

    		break;
    	}
		
		boolean canReset = true;
		for(TouchGesture eventOrder : gestureList)
		{
			if(eventOrder.isValid() && !eventOrder.isCompleted())
				canReset = false;
		}
		
		if(canReset)
		{
			for(TouchGesture eventOrder : gestureList)
			{
				eventOrder.reset();
				eventOrder.addContext(TouchHandler, this);
			}
		}

	}
	
	private List<TouchGesture> gestureList = new ArrayList<TouchGesture>();
	private Handler handler;
	private Runnable timerAction;
	private IGestureAction timerGestureAction = null;
	private IGestureCondition timerGestureCondition = null;
	private GestureEvent lastMotionEvent;
}
