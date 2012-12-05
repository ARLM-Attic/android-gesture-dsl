package com.hfk.android.gestures.dsl;

import com.hfk.android.gestures.TouchEvent;
import com.hfk.android.gestures.TouchGesture;

public class NextGestureOrConditional<NextGesture> {
	
	public NextGestureOrConditional(Class<NextGesture> aClass, TouchGesture gstr, TouchEvent vnt)
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
		AfterConditional result = new AfterConditional(refClass, gesture, event);
		
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
