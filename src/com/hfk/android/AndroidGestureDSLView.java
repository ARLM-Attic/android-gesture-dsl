package com.hfk.android;

import com.hfk.android.gestures.TouchHandler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class AndroidGestureDSLView extends View {

	public AndroidGestureDSLView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	    touchHandler = new TouchHandler();
	    
	    ClickOnRectangleGesture clickOnRectangleBuilder = new ClickOnRectangleGesture(this);
	    touchHandler.addGesture(clickOnRectangleBuilder.create());
	}
    
    public boolean onTouchEvent(MotionEvent motion)   { 
    	
    	touchHandler.onTouchEvent(motion);
    	
    	invalidate();
    	return true;  	
    }   

	protected void onDraw(Canvas canvas) {
    	Paint paint = new Paint();
    	paint.setColor(Color.GREEN);
		canvas.drawRect(getRect(), paint);
	}
	
	public boolean IsOnRectangle(ScreenVector position)
	{
		float centerX = this.getWidth()/2;
		float centerY = this.getHeight()/2;
		
		float size = this.getWidth()/4;
		
		Rect rect = getRect();

		if(rect.contains(position.x, position.y) )
		{
			return true;
		}
		
		return false;
	}
	
	public void showMessage(String message)
	{
		Toast msg = Toast.makeText(AndroidGestureDSLView.this.getContext(), "onClick", Toast.LENGTH_SHORT);
		msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2);
		msg.show();		
	}
	
	private Rect getRect()
	{
		int centerX = this.getWidth()/2;
		int centerY = this.getHeight()/2;
		
		int size = this.getWidth()/4;
		
		return new Rect(centerX - (size/2), centerY - (size/2), centerY + (size/2), centerY + (size/2));
	}
    
    TouchHandler touchHandler;
}
