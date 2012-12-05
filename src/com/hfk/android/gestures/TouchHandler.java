package com.hfk.android.gestures;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.graphics.Point;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;

public class TouchHandler {
	
	public TouchHandler()
	{
		handler = new Handler();
	}
	
	public void addGesture(TouchGesture gesture)
	{
		gestureList.add(gesture);
	}
	
	public void invalidateTimer() {
		if(timerAction != null)
		{
			handler.removeCallbacks(timerAction);
			timerAction = null;
		}
	}
	
	public void installTimer(IGestureCondition condition, IGestureAction action, int timeOut, final TouchGesture gesture) {
		timerGestureCondition = condition;
		timerGestureAction = action;
		timerAction = new Runnable()
		{
			public void run() {
				boolean doAction = true;
				if(timerGestureCondition != null)
				{
					if(!timerGestureCondition.checkCondition(lastMotionEvent, gesture))
					{
						doAction = false;
					}
				}
				if(doAction)
				{
					timerGestureAction.executeAction(lastMotionEvent, gesture);
				}
			}
		};
		handler.postDelayed(timerAction, timeOut);
	}

	public void onTouchEvent(MotionEvent androidMotion)   {
		lastMotionEvent = new GestureEvent(androidMotion);
		
    	int action = androidMotion.getActionMasked();
    	
    	GestureEvent motion = new GestureEvent(androidMotion);
      	
//    	int pointerIndex = motion.getActionIndex();
//    	int pointerId = motion.getPointerId(pointerIndex);
		
    	//boolean result = true;
		switch (action) {
    	case MotionEvent.ACTION_DOWN:
    	case MotionEvent.ACTION_POINTER_DOWN:
//			if(timerAction != null)
//			{
//				handler.removeCallbacks(timerAction);
//				timerAction = null;
//			}
    		for(TouchGesture eventOrder : gestureList)
    		{
    			eventOrder.addContext("ACTION_DOWN", motion.getPosition());
    			//boolean isValid = false;
	    		if(eventOrder.isValid() && eventOrder.current().event == TouchEvent.TOUCH_DOWN)
	    		{
	    			TouchEvent event = eventOrder.current();
//	    			if(event.condition != null)
//	    			{
//	    				if(event.condition.checkCondition(motion))
//	    				{
//	    					isValid = true;
//	    				}
//	    			}
//	    			else
//	    			{
//	    				isValid = true;
//	    			}
	    			
	    			for(IfThenClause condition: eventOrder.current().conditionList)
	    			{
	    				condition.Execute(motion, eventOrder);
	    				if(!eventOrder.isValid())
	    				{
	    					break;
	    				}
	    			}
	    			
	    			//if(isValid && event.action != null)
//	    			if(eventOrder.isValid())
//	    			{
//	    				//event.action.executeAction(motion);
//	    				if(event.timeEvent != null)
//	    				{
//	    					timerGestureCondition = event.timeEvent.condition;
//	    					timerGestureAction = event.timeEvent.action;
//	    					timerAction = new Runnable()
//	    					{
//	    						public void run() {
//	    							boolean doAction = true;
//	    							if(timerGestureCondition != null)
//	    							{
//		    							if(!timerGestureCondition.checkCondition(lastMotionEvent))
//		    							{
//		    								doAction = false;
//		    							}
//	    							}
//	    							if(doAction)
//	    							{
//	    								timerGestureAction.executeAction(lastMotionEvent);
//	    							}
//	    						}
//	    					};
//	    					handler.postDelayed(timerAction, 
//	    							event.timeEvent.timeOut);
//	    				}
//	    			}
					eventOrder.currentIsExecuted();
	    			eventOrder.moveNext();
	    		}
	    		
//	    		if(!isValid)
//	    			eventOrder.invalidate();
    		}

//    		EventData eventData = new EventData();
//    		eventData.x = event.getX(pointerIndex);
//    		eventData.y = event.getY(pointerIndex);
//    		eventDataMap.put(new Integer(pointerId), eventData);

    		break;
    	case MotionEvent.ACTION_MOVE:
//			if(timerAction != null)
//			{
//				handler.removeCallbacks(timerAction);
//				timerAction = null;
//			}
    		for(TouchGesture eventOrder : gestureList)
    		{
    			boolean isValid = false;
	    		if(eventOrder.isValid() && eventOrder.current().event == TouchEvent.TOUCH_MOVE)
	    		{
	    			isValid = true;
	    			boolean conditionisValid = false;
	    			TouchEvent event = eventOrder.current();
//	    			if(event.condition != null)
//	    			{
//	    				if(event.condition.checkCondition(motion))
//	    				{
//	    					conditionisValid = true;
//	    				}
//	    			}
//	    			else
//	    			{
//	    				conditionisValid = true;
//	    			}
	    			
	    			for(IfThenClause condition: eventOrder.current().conditionList)
	    			{
	    				conditionisValid = condition.Execute(motion, eventOrder);
	    				if(!eventOrder.isValid())
	    				{
	    					break;
	    				}
	    			}
	    			
	    			//if(conditionisValid && event.action != null)
//	    			if(conditionisValid)
//	    			{
//	    				//event.action.executeAction(motion);
//	    				if(event.timeEvent != null && lastMotionEvent != null)
//	    				{
//	    					timerGestureCondition = event.timeEvent.condition;
//	    					timerGestureAction = event.timeEvent.action;
//	    					timerAction = new Runnable()
//	    					{
//	    						public void run() {
//	    							boolean doAction = true;
//	    							if(timerGestureCondition != null)
//	    							{
//		    							if(!timerGestureCondition.checkCondition(lastMotionEvent))
//		    							{
//		    								doAction = false;
//		    							}
//	    							}
//	    							if(doAction)
//	    							{
//	    								timerGestureAction.executeAction(lastMotionEvent);
//	    							}
//	    						}
//	    					};
//	    					handler.postDelayed(timerAction, 
//	    							event.timeEvent.timeOut);
//	    				}
//	    			}
					eventOrder.currentIsExecuted();
	    			// Do not remove MOVE events because there will probably be a series
	    			//	of these events and otherwise only one would be accepted
	    			//eventOrder.remove(event);
	    		}

	    		if(!isValid)
	    			eventOrder.invalidate();
    		}

//    		for(int i = 0; i < event.getPointerCount(); i++)
//    		{
//    			int curPointerId = event.getPointerId(i);
//	    		if(eventDataMap.containsKey(new Integer(curPointerId)))
//	    		{
//	        		EventData moveEventData = eventDataMap.get(new Integer(curPointerId));
//	        		moveEventData.x = event.getX(i);
//	        		moveEventData.y = event.getY(i);
//	    		}
//			}

    		break;
    	case MotionEvent.ACTION_UP:
    	case MotionEvent.ACTION_POINTER_UP:
//			if(timerAction != null)
//			{
//				handler.removeCallbacks(timerAction);
//				timerAction = null;
//			}
    		for(TouchGesture eventOrder : gestureList)
    		{
    			//boolean isValid = false;
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
//	    			if(event.condition != null)
//	    			{
//	    				if(event.condition.checkCondition(motion))
//	    				{
//	    					isValid = true;
//	    				}
//	    			}
//	    			else
//	    			{
//	    				isValid = true;
//	    			}
	    			
	    			for(IfThenClause condition: eventOrder.current().conditionList)
	    			{
	    				condition.Execute(motion, eventOrder);
	    				if(!eventOrder.isValid())
	    				{
	    					break;
	    				}
	    			}
	    			
	    			//if(isValid && event.action != null)
//	    			if(eventOrder.isValid())
//	    			{
//	    				//event.action.executeAction(motion);
//	    				if(event.timeEvent != null && lastMotionEvent != null)
//	    				{
//	    					timerGestureCondition = event.timeEvent.condition;
//	    					timerGestureAction = event.timeEvent.action;
//	    					timerAction = new Runnable()
//	    					{
//	    						public void run() {
//	    							boolean doAction = true;
//	    							if(timerGestureCondition != null)
//	    							{
//		    							if(!timerGestureCondition.checkCondition(lastMotionEvent))
//		    							{
//		    								doAction = false;
//		    							}
//	    							}
//	    							if(doAction)
//	    							{
//	    								timerGestureAction.executeAction(lastMotionEvent);
//	    							}
//	    						}
//	    					};
//	    					handler.postDelayed(timerAction, event.timeEvent.timeOut);
//	    				}
//	    			}
					eventOrder.currentIsExecuted();
	    			eventOrder.moveNext();
	    		}

//	    		if(!isValid)
//	    			eventOrder.invalidate();
    		}

//    		eventDataMap.remove(new Integer(pointerId));

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
			}
		}

	}
	
	private List<TouchGesture> gestureList = new ArrayList<TouchGesture>();
	private Handler handler;
	private Runnable timerAction;
	private IGestureAction timerGestureAction = null;
	private IGestureCondition timerGestureCondition = null;
	private GestureEvent lastMotionEvent;
    
//    private Map<Integer, EventData> eventDataMap; 
//    
//    private class EventData{
//    	public float x;
//    	public float y;
//    }
    

}
