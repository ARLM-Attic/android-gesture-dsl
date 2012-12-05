package com.hfk.android.gestures.dsl;

import com.hfk.android.gestures.TouchEvent;
import com.hfk.android.gestures.TouchGesture;

public class AfterConditionalContinuation<NextGesture> {
	
	public AfterConditionalContinuation(Class<NextGesture> aClass, TouchGesture gstr, TouchEvent vnt)
	{
		refClass = aClass;
		gesture = gstr;
		event = vnt;
	}
	
	public NextGesture AndNext()
	{
		NextGesture result = getInstance(refClass);
		
		return result;
	}
	
	public AfterConditional<NextGesture> AndIf()
	{
		AfterConditional<NextGesture> result = new AfterConditional<NextGesture>(refClass, gesture, event);
		
		return result;
	}
	
	public ActionAfterGestureOrConditional<NextGesture> Else()
	{
		ActionAfterGestureOrConditional<NextGesture> result = new ActionAfterGestureOrConditional<NextGesture>(refClass, gesture, event);
		
		return result;
	}
	
	//http://stackoverflow.com/questions/2434041/instantiating-generics-type-in-java
	private NextGesture getInstance(Class<NextGesture> aClass)
	{
		try {
			return aClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private Class<NextGesture> refClass;
	private TouchGesture gesture;
	private TouchEvent event;
	
}
