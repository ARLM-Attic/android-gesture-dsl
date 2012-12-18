package com.hfk.android.gestures;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.graphics.Point;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;

public class TouchHandler {
	
	public static String TouchHandlerId = "TOUCH_HANDLER";
	
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
		gesture.addContext(TouchHandlerId, this);
		gestureList.add(gesture);
	}
	
	public static String getEventId(String dataKey, int index)
	{
		return dataKey + "_" + ((Integer)index).toString();
	}
	
	public void invalidateTimer() {
		if(timerAction != null)
		{
			handler.removeCallbacks(timerAction);
			timerAction = null;
		}
	}
	
	public void tryReset()
	{
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
				eventOrder.addContext(TouchHandlerId, this);
				
				touchDownCounter = 0;
				touchUpCounter = 0;
			}
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
			touchDownCounter++;
    		for(TouchGesture eventOrder : gestureList)
    		{
    			eventOrder.addContext(TouchHandler.getEventId(ActionDownPos, touchDownCounter), motion.getPosition());
    			eventOrder.addContext(TouchHandler.getEventId(ActionDownTime, touchDownCounter), motion.getTime());
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
			touchUpCounter++;
    		for(TouchGesture eventOrder : gestureList)
    		{
    			eventOrder.addContext(TouchHandler.getEventId(ActionUpPos, touchUpCounter), motion.getPosition());
    			eventOrder.addContext(TouchHandler.getEventId(ActionUpTime, touchUpCounter), motion.getTime());
				if(eventOrder.isValid() && (eventOrder.current().event == TouchEvent.TOUCH_MOVE) 
						&& !eventOrder.current().isOptional && !eventOrder.isCurrentExecuted())
				{
					eventOrder.invalidate();
				}
				if(eventOrder.isValid() && (eventOrder.current().event == TouchEvent.TOUCH_MOVE) 
						&& (eventOrder.current().isOptional || eventOrder.isCurrentExecuted()))
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
		
		tryReset();

	}
	
	private List<TouchGesture> gestureList = new ArrayList<TouchGesture>();
	private Handler handler;
	private Runnable timerAction;
	private IGestureAction timerGestureAction = null;
	private IGestureCondition timerGestureCondition = null;
	private GestureEvent lastMotionEvent;
	
	private int touchDownCounter = 0;
	private int touchUpCounter = 0;
}
