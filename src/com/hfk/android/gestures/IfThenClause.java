package com.hfk.android.gestures;

public class IfThenClause {

//	public IfThenClause(IGestureCondition condition, IGestureAction thenAction, IGestureAction elseAction)
//	{
//		this.condition = condition;
//		this.onTrueAction = thenAction;
//		this.onFalseAction = elseAction;
//	}
//
//	public IfThenClause(IGestureCondition condition, IGestureAction thenAction)
//	{
//		this.condition = condition;
//		this.onTrueAction = thenAction;
//		this.onFalseAction = null;
//	}

	public IfThenClause(IGestureCondition condition)
	{
		this.condition = condition;
		this.onTrueAction = null;
		this.onFalseAction = new DoInvalidateGestureGestureAction();
	}

//	public IfThenClause(IGestureAction thenAction)
//	{
//		this.condition = new IGestureCondition()
//		{
//			public boolean checkCondition(GestureEvent motion, TouchGesture gesture)
//			{
//				return true;
//			}
//		};
//		this.onTrueAction = thenAction;
//		this.onFalseAction = null;
//	}
	
	public void setThenAction(IGestureAction action)
	{
		this.onTrueAction = action;
	}
	
	public void setElseAction(IGestureAction action)
	{
		this.onFalseAction = action;
	}
	
	public boolean Execute(GestureEvent motion, TouchGesture gesture)
	{
		if(condition.checkCondition(motion, gesture))
		{
			if(onTrueAction != null)
			{
				onTrueAction.executeAction(motion, gesture);
			}
			return true;
		}
		else
		{
			if(onFalseAction != null)
			{
				onFalseAction.executeAction(motion, gesture);
			}
			return false;
		}
	}
	
	private IGestureCondition condition;
	private IGestureAction onTrueAction;
	private IGestureAction onFalseAction;
}
