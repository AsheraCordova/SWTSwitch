//start - license
/*******************************************************************************
 * Copyright (c) 2025 Ashera Cordova
 *
 * This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *******************************************************************************/
//end - license
package com.ashera.myswitch;
//start - imports

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import r.android.content.Context;


import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import androidx.core.view.*;
import static com.ashera.common.DisposeUtil.*;

import r.android.os.Build;
import r.android.view.View;
import r.android.text.*;

import com.ashera.core.IFragment;
import com.ashera.converter.*;

import r.android.annotation.SuppressLint;

import com.ashera.layout.*;
import com.ashera.plugin.*;
import com.ashera.widget.bus.*;
import com.ashera.widget.*;
import com.ashera.widget.bus.Event.*;
import com.ashera.widget.IWidgetLifeCycleListener.EventId;
import com.ashera.widget.IWidgetLifeCycleListener.EventId.*;


import static com.ashera.widget.IWidget.*;
//end - imports
import r.android.widget.CompoundButton;

@SuppressLint("NewApi")
public class SwitchStyledLabelImpl extends BaseWidget implements IDrawable, IHasMultiNativeWidgets, ICustomMeasureHeight, ICustomMeasureWidth{
	//start - body
	public final static String LOCAL_NAME = "SwitchStyledLabel"; 
	public final static String GROUP_NAME = "Switch";

	protected org.eclipse.nebula.widgets.opal.switchbutton.SwitchButton switchButton;
	protected r.android.widget.Switch measurableView;	
	
		@SuppressLint("NewApi")
		final static class Font extends AbstractEnumToIntConverter{
		private Map<String, Integer> mapping = new HashMap<>();
				{
				mapping.put("monospace",  0x3);
				mapping.put("normal",  0x0);
				mapping.put("sans",  0x1);
				mapping.put("serif",  0x2);
				}
		@Override
		public Map<String, Integer> getMapping() {
				return mapping;
				}

		@Override
		public Integer getDefault() {
				return 0;
				}
				}
		@SuppressLint("NewApi")
		final static class TextStyle  extends AbstractBitFlagConverter{
		private Map<String, Integer> mapping = new HashMap<>();
				{
				mapping.put("bold", 0x1);
				mapping.put("italic", 0x2);
				mapping.put("normal", 0x0);
				}
		@Override
		public Map<String, Integer> getMapping() {
				return mapping;
				}

		@Override
		public Integer getDefault() {
				return 0;
				}
				}
		@SuppressLint("NewApi")
		final static class TintMode extends AbstractEnumToIntConverter{
		private Map<String, Integer> mapping = new HashMap<>();
				{
				mapping.put("add",  0x1);
				mapping.put("multiply",  0x2);
				mapping.put("screen",  0x3);
				mapping.put("src_atop",  0x4);
				mapping.put("src_in",  0x5);
				mapping.put("src_over",  0x6);
				}
		@Override
		public Map<String, Integer> getMapping() {
				return mapping;
				}

		@Override
		public Integer getDefault() {
				return 0;
				}
				}
		@SuppressLint("NewApi")
		final static class Ellipsize extends AbstractEnumToIntConverter{
		private Map<String, Integer> mapping = new HashMap<>();
				{
				mapping.put("end",  0x3);
				mapping.put("marquee",  0x4);
				mapping.put("middle",  0x2);
				mapping.put("none",  0x0);
				mapping.put("start",  0x1);
				}
		@Override
		public Map<String, Integer> getMapping() {
				return mapping;
				}

		@Override
		public Integer getDefault() {
				return 0;
				}
				}
		@SuppressLint("NewApi")
		final static class MarqueeRepeatLimit extends AbstractEnumToIntConverter{
		private Map<String, Integer> mapping = new HashMap<>();
				{
				mapping.put("marquee_forever",  0xffffffff);
				}
		@Override
		public Map<String, Integer> getMapping() {
				return mapping;
				}

		@Override
		public Integer getDefault() {
				return 0;
				}
		@Override
		public boolean supportsIntAlso() {
			return true;
		}
				}
	
	@Override
	public void loadAttributes(String attributeName) {
		ViewImpl.register(attributeName);


		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("swtText").withType("resourcestring"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("swtSelection").withType("boolean"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("swtTextForSelect").withType("resourcestring"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("swtTextForUnselect").withType("resourcestring"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("swtRound").withType("boolean"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("swtBorderColor").withType("color"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("swtFocusColor").withType("color"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("swtSelectedForegroundColor").withType("color"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("swtSelectedBackgroundColor").withType("color"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("swtUnselectedForegroundColor").withType("color"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("swtUnselectedBackgroundColor").withType("color"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("swtButtonBorderColor").withType("color"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("swtButtonBackgroundColor1").withType("color"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("swtButtonBackgroundColor2").withType("color"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("swtGap").withType("int"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("swtArc").withType("int"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("onCheckedChange").withType("string"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("checked").withType("boolean"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("textOn").withType("resourcestring"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("textOff").withType("resourcestring"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("switchPadding").withType("dimension"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("switchMinWidth").withType("dimension"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("trackTint").withType("colorstate"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("thumbTint").withType("colorstate"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("swtInsideMarginX").withType("dimension"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("swtInsideMarginY").withType("dimension"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("text").withType("resourcestring").withUiFlag(UPDATE_UI_REQUEST_LAYOUT));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("textColor").withType("colorstate"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("gravity").withType("gravity").withUiFlag(UPDATE_UI_REQUEST_LAYOUT));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("textSize").withType("dimensionsp").withUiFlag(UPDATE_UI_REQUEST_LAYOUT));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("padding").withType("dimension"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("paddingBottom").withType("dimension"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("paddingRight").withType("dimension"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("paddingLeft").withType("dimension"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("paddingStart").withType("dimension"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("paddingEnd").withType("dimension"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("paddingTop").withType("dimension"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("paddingHorizontal").withType("dimension"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("paddingVertical").withType("dimension"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("minLines").withType("int"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("lines").withType("int"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("maxLines").withType("int"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("minWidth").withType("dimension"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("minHeight").withType("dimension"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("maxWidth").withType("dimension"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("maxHeight").withType("dimension"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("height").withType("dimension"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("width").withType("dimension"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("maxEms").withType("int"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("minEms").withType("int"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("ems").withType("int"));
		ConverterFactory.register("SwitchStyledLabel.font", new Font());
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("typeface").withType("SwitchStyledLabel.font").withUiFlag(UPDATE_UI_REQUEST_LAYOUT));
		ConverterFactory.register("SwitchStyledLabel.textStyle", new TextStyle());
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("textStyle").withType("SwitchStyledLabel.textStyle").withUiFlag(UPDATE_UI_REQUEST_LAYOUT));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("fontFamily").withType("font").withUiFlag(UPDATE_UI_REQUEST_LAYOUT));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("drawableStart").withType("drawable").withUiFlag(UPDATE_UI_REQUEST_LAYOUT));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("drawableEnd").withType("drawable").withUiFlag(UPDATE_UI_REQUEST_LAYOUT));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("drawableTop").withType("drawable").withUiFlag(UPDATE_UI_REQUEST_LAYOUT));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("drawableBottom").withType("drawable").withUiFlag(UPDATE_UI_REQUEST_LAYOUT));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("drawablePadding").withType("dimension").withOrder(1).withUiFlag(UPDATE_UI_REQUEST_LAYOUT));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("drawableTint").withType("colorstate").withOrder(-10));
		ConverterFactory.register("SwitchStyledLabel.tintMode", new TintMode());
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("drawableTintMode").withType("SwitchStyledLabel.tintMode").withOrder(-10));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("maxLength").withType("int").withUiFlag(UPDATE_UI_REQUEST_LAYOUT));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("enabled").withType("boolean"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("editable").withType("boolean"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("singleLine").withType("boolean"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("textAllCaps").withType("boolean").withOrder(-1).withUiFlag(UPDATE_UI_REQUEST_LAYOUT));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("scrollHorizontally").withType("boolean").withUiFlag(UPDATE_UI_REQUEST_LAYOUT));
		ConverterFactory.register("SwitchStyledLabel.ellipsize", new Ellipsize());
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("ellipsize").withType("SwitchStyledLabel.ellipsize"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("startOrStopMarquee").withType("boolean"));
		ConverterFactory.register("SwitchStyledLabel.marqueeRepeatLimit", new MarqueeRepeatLimit());
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("marqueeRepeatLimit").withType("SwitchStyledLabel.marqueeRepeatLimit"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("firstBaselineToTopHeight").withType("dimension").withUiFlag(UPDATE_UI_REQUEST_LAYOUT));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("lastBaselineToBottomHeight").withType("dimension").withUiFlag(UPDATE_UI_REQUEST_LAYOUT));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("textFormat").withType("resourcestring").withOrder(-1));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("textAppearance").withType("string").withUiFlag(UPDATE_UI_REQUEST_LAYOUT).withStylePriority(1));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("drawableIconSize").withType("dimension").withOrder(-1).withUiFlag(UPDATE_UI_REQUEST_LAYOUT));
	WidgetFactory.registerConstructorAttribute(localName, new WidgetAttribute.Builder().withName("swtTextStyle").withType("string"));
		loadCustomAttributes(attributeName);
	}
	
	public SwitchStyledLabelImpl() {
		super(GROUP_NAME, LOCAL_NAME);
	}
	public  SwitchStyledLabelImpl(String localname) {
		super(GROUP_NAME, localname);
	}
	public  SwitchStyledLabelImpl(String groupName, String localname) {
		super(groupName, localname);
	}

		
	public class SwitchStyledLabelExt extends r.android.widget.Switch implements ILifeCycleDecorator{
		private MeasureEvent measureFinished = new MeasureEvent();
		private OnLayoutEvent onLayoutEvent = new OnLayoutEvent();
		private List<IWidget> overlays;
		public IWidget getWidget() {
			return SwitchStyledLabelImpl.this;
		}

		public SwitchStyledLabelExt() {
			super(SwitchStyledLabelImpl.this);
			
		}
		
		@Override
		public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


	        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			IWidgetLifeCycleListener listener = (IWidgetLifeCycleListener) getListener();
			if (listener != null) {
			    measureFinished.setWidth(getMeasuredWidth());
			    measureFinished.setHeight(getMeasuredHeight());
				listener.eventOccurred(EventId.measureFinished, measureFinished);
			}
		}
		
		@Override
		protected void onLayout(boolean changed, int l, int t, int r, int b) {
			super.onLayout(changed, l, t, r, b);
			ViewImpl.setDrawableBounds(SwitchStyledLabelImpl.this, l, t, r, b);
			if (!isOverlay()) {
			ViewImpl.nativeMakeFrame(asNativeWidget(), l, t, r, b);
			nativeMakeFrameForChildWidget(l, t, r, b);
			}
			replayBufferedEvents();
	        ViewImpl.redrawDrawables(SwitchStyledLabelImpl.this);
	        overlays = ViewImpl.drawOverlay(SwitchStyledLabelImpl.this, overlays);
			
			IWidgetLifeCycleListener listener = (IWidgetLifeCycleListener) getListener();
			if (listener != null) {
				onLayoutEvent.setB(b);
				onLayoutEvent.setL(l);
				onLayoutEvent.setR(r);
				onLayoutEvent.setT(t);
				onLayoutEvent.setChanged(changed);
				listener.eventOccurred(EventId.onLayout, onLayoutEvent);
			}
			
			if (isInvalidateOnFrameChange() && isInitialised()) {
				SwitchStyledLabelImpl.this.invalidate();
			}
		}	
		
		@Override
		public void execute(String method, Object... canvas) {
			
		}

		public void updateMeasuredDimension(int width, int height) {
			setMeasuredDimension(width, height);
		}


		@Override
		public ILifeCycleDecorator newInstance(IWidget widget) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setAttribute(WidgetAttribute widgetAttribute,
				String strValue, Object objValue) {
			throw new UnsupportedOperationException();
		}		
		

		@Override
		public List<String> getMethods() {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public void initialized() {
			throw new UnsupportedOperationException();
		}
		
        @Override
        public Object getAttribute(WidgetAttribute widgetAttribute) {
            throw new UnsupportedOperationException();
        }
        @Override
        public void drawableStateChanged() {
        	super.drawableStateChanged();
        	if (!isWidgetDisposed()) {
        		ViewImpl.drawableStateChanged(SwitchStyledLabelImpl.this);
        	}
        }
        private Map<String, IWidget> templates;
    	@Override
    	public r.android.view.View inflateView(java.lang.String layout) {
    		if (templates == null) {
    			templates = new java.util.HashMap<String, IWidget>();
    		}
    		IWidget template = templates.get(layout);
    		if (template == null) {
    			template = (IWidget) quickConvert(layout, "template");
    			templates.put(layout, template);
    		}
    		
    		IWidget widget = template.loadLazyWidgets(SwitchStyledLabelImpl.this.getParent());
			return (View) widget.asWidget();
    	}   
        
    	@Override
		public void remeasure() {
    		if (getFragment() != null) {
    			getFragment().remeasure();
    		}
		}
    	
        @Override
		public void removeFromParent() {
        	SwitchStyledLabelImpl.this.getParent().remove(SwitchStyledLabelImpl.this);
		}
        @Override
        public void getLocationOnScreen(int[] appScreenLocation) {
        	org.eclipse.swt.widgets.Control control = (org.eclipse.swt.widgets.Control) asNativeWidget();
			appScreenLocation[0] = control.toDisplay(0, 0).x;
        	appScreenLocation[1] = control.toDisplay(0, 0).y;
        }
        @Override
        public void getWindowVisibleDisplayFrame(r.android.graphics.Rect displayFrame){
        	org.eclipse.swt.widgets.Shell shell = ((org.eclipse.swt.widgets.Control)asNativeWidget()).getShell();
        	displayFrame.left = shell.toDisplay(0, 0).x ;
			displayFrame.top = shell.getShell().toDisplay(0, 0).y ;
        	displayFrame.bottom = displayFrame.top + shell.getClientArea().height;
        	displayFrame.right = displayFrame.left + shell.getBounds().width;
        	
        }
        @Override
		public void offsetTopAndBottom(int offset) {
			super.offsetTopAndBottom(offset);
			ViewImpl.nativeMakeFrame(asNativeWidget(), getLeft(), getTop(), getRight(), getBottom());
		}
		@Override
		public void offsetLeftAndRight(int offset) {
			super.offsetLeftAndRight(offset);
			ViewImpl.nativeMakeFrame(asNativeWidget(), getLeft(), getTop(), getRight(), getBottom());
		}
		@Override
		public void setMyAttribute(String name, Object value) {
			if (name.equals("state0")) {
				setState0(value);
				return;
			}
			if (name.equals("state1")) {
				setState1(value);
				return;
			}
			if (name.equals("state2")) {
				setState2(value);
				return;
			}
			if (name.equals("state3")) {
				setState3(value);
				return;
			}
			if (name.equals("state4")) {
				setState4(value);
				return;
			}
			SwitchStyledLabelImpl.this.setAttribute(name, value, !(value instanceof String));
		}
        @Override
        public void setVisibility(int visibility) {
            super.setVisibility(visibility);
            org.eclipse.swt.widgets.Control control = ((org.eclipse.swt.widgets.Control)asNativeWidget());
            if (!control.isDisposed()) {
            	control.setVisible(View.VISIBLE == visibility);
            }
            
        }
		  public int getBorderPadding(){
		    return SwitchStyledLabelImpl.this.getBorderPadding();
		  }

		  public int getLineHeight(){
		    return SwitchStyledLabelImpl.this.getLineHeight();
		  }

		  public int getBorderWidth(){
		    return SwitchStyledLabelImpl.this.getBorderWidth();
		  }
		  public int getLineHeightPadding(){
		    return SwitchStyledLabelImpl.this.getLineHeightPadding();
		  }
        @Override
        public int nativeMeasureWidth(java.lang.Object uiView) {
        	return ViewImpl.nativeMeasureWidth(uiView);
        }
        
        @Override
        public int nativeMeasureHeight(java.lang.Object uiView, int width) {
        	return ViewImpl.nativeMeasureHeight(uiView, width);
        }
        @Override
        public int computeSize(float width) {
        	return nativeMeasureHeight(switchButton, (int) width);
    	}
		@Override
		public java.lang.String getText() {
			return (String) getMyText();
		}

        
    	public void setState0(Object value) {
    		ViewImpl.setState(SwitchStyledLabelImpl.this, 0, value);
    	}
    	public void setState1(Object value) {
    		ViewImpl.setState(SwitchStyledLabelImpl.this, 1, value);
    	}
    	public void setState2(Object value) {
    		ViewImpl.setState(SwitchStyledLabelImpl.this, 2, value);
    	}
    	public void setState3(Object value) {
    		ViewImpl.setState(SwitchStyledLabelImpl.this, 3, value);
    	}
    	public void setState4(Object value) {
    		ViewImpl.setState(SwitchStyledLabelImpl.this, 4, value);
    	}
        	public void state0() {
        		ViewImpl.state(SwitchStyledLabelImpl.this, 0);
        	}
        	public void state1() {
        		ViewImpl.state(SwitchStyledLabelImpl.this, 1);
        	}
        	public void state2() {
        		ViewImpl.state(SwitchStyledLabelImpl.this, 2);
        	}
        	public void state3() {
        		ViewImpl.state(SwitchStyledLabelImpl.this, 3);
        	}
        	public void state4() {
        		ViewImpl.state(SwitchStyledLabelImpl.this, 4);
        	}
                        
        public void stateYes() {
        	ViewImpl.stateYes(SwitchStyledLabelImpl.this);
        	
        }
        
        public void stateNo() {
        	ViewImpl.stateNo(SwitchStyledLabelImpl.this);
        }
     
	
	}	@Override
	public Class getViewClass() {
		return SwitchStyledLabelExt.class;
	}

	@Override
	public IWidget newInstance() {
		return new SwitchStyledLabelImpl(groupName, localName);
	}
	
	@SuppressLint("NewApi")
	@Override
	public void create(IFragment fragment, Map<String, Object> params) {
		super.create(fragment, params);
		measurableView = new SwitchStyledLabelExt();
		nativeCreate(params);	
		ViewImpl.registerCommandConveter(this);
	}

	@Override
	@SuppressLint("NewApi")
	public void setAttribute(WidgetAttribute key, String strValue, Object objValue, ILifeCycleDecorator decorator) {
		Object nativeWidget = asNativeWidget();
		ViewImpl.setAttribute(this,  key, strValue, objValue, decorator);
		
		switch (key.getAttributeName()) {
			case "swtText": {
				


		 switchButton.setText((String)objValue);



			}
			break;
			case "swtSelection": {
				


		 switchButton.setSelection((boolean)objValue);



			}
			break;
			case "swtTextForSelect": {
				


		 switchButton.setTextForSelect((String)objValue);



			}
			break;
			case "swtTextForUnselect": {
				


		 switchButton.setTextForUnselect((String)objValue);



			}
			break;
			case "swtRound": {
				


		 switchButton.setRound((boolean)objValue);



			}
			break;
			case "swtBorderColor": {
				


		 switchButton.setBorderColor((Color)objValue);



			}
			break;
			case "swtFocusColor": {
				


		 switchButton.setFocusColor((Color)objValue);



			}
			break;
			case "swtSelectedForegroundColor": {
				


		 switchButton.setSelectedForegroundColor((Color)objValue);



			}
			break;
			case "swtSelectedBackgroundColor": {
				


		 switchButton.setSelectedBackgroundColor((Color)objValue);



			}
			break;
			case "swtUnselectedForegroundColor": {
				


		 switchButton.setUnselectedForegroundColor((Color)objValue);



			}
			break;
			case "swtUnselectedBackgroundColor": {
				


		 switchButton.setUnselectedBackgroundColor((Color)objValue);



			}
			break;
			case "swtButtonBorderColor": {
				


		 switchButton.setButtonBorderColor((Color)objValue);



			}
			break;
			case "swtButtonBackgroundColor1": {
				


		 switchButton.setButtonBackgroundColor1((Color)objValue);



			}
			break;
			case "swtButtonBackgroundColor2": {
				


		 switchButton.setButtonBackgroundColor2((Color)objValue);



			}
			break;
			case "swtGap": {
				


		 switchButton.setGap((int)objValue);



			}
			break;
			case "swtArc": {
				


		 switchButton.setArc((int)objValue);



			}
			break;
			case "onCheckedChange": {
				


		setOnChecked(objValue);



			}
			break;
			case "checked": {
				


		setChecked(objValue);



			}
			break;
			case "textOn": {
				


		setTextOn(objValue);



			}
			break;
			case "textOff": {
				


		setTextOff(objValue);



			}
			break;
			case "switchPadding": {
				


		measurableView.setSwitchPadding((int) objValue);



			}
			break;
			case "switchMinWidth": {
				


		measurableView.setMinWidth((int) objValue);



			}
			break;
			case "trackTint": {
				


		setTrackTint(objValue);



			}
			break;
			case "thumbTint": {
				


		setThumbTint(objValue);



			}
			break;
			case "swtInsideMarginX": {
				


		setInsideMarginX(objValue);



			}
			break;
			case "swtInsideMarginY": {
				


		setInsideMarginY(objValue);



			}
			break;
			case "text": {
				


		setMyText(objValue);



			}
			break;
			case "textColor": {
				


		setTextColor(objValue);



			}
			break;
			case "gravity": {
				


		setGravity(objValue);



			}
			break;
			case "textSize": {
				


		setMyTextSize(objValue);



			}
			break;
			case "padding": {
				


		setPadding(objValue);



			}
			break;
			case "paddingBottom": {
				


		setPaddingBottom(objValue);



			}
			break;
			case "paddingRight": {
				


		setPaddingRight(objValue);



			}
			break;
			case "paddingLeft": {
				


		setPaddingLeft(objValue);



			}
			break;
			case "paddingStart": {
				


		setPaddingStart(objValue);



			}
			break;
			case "paddingEnd": {
				


		setPaddingEnd(objValue);



			}
			break;
			case "paddingTop": {
				


		setPaddingTop(objValue);



			}
			break;
			case "paddingHorizontal": {
				


		setPaddingHorizontal(objValue);



			}
			break;
			case "paddingVertical": {
				


		setPaddingVertical(objValue);



			}
			break;
			case "minLines": {
				


		 setMinLines(objValue); 



			}
			break;
			case "lines": {
				


		 setLines(objValue); 



			}
			break;
			case "maxLines": {
				


		 setMaxLines(objValue); 



			}
			break;
			case "minWidth": {
				


		 setMinWidth(objValue); 



			}
			break;
			case "minHeight": {
				


		 setMinHeight(objValue); 



			}
			break;
			case "maxWidth": {
				


		 setMaxWidth(objValue); 



			}
			break;
			case "maxHeight": {
				


		 setMaxHeight(objValue); 



			}
			break;
			case "height": {
				


		 setHeight(objValue); 



			}
			break;
			case "width": {
				


		 setWidth(objValue); 



			}
			break;
			case "maxEms": {
				


		 setMaxEms(objValue); 



			}
			break;
			case "minEms": {
				


		 setMinEms(objValue); 



			}
			break;
			case "ems": {
				


		 setEms(objValue); 



			}
			break;
			case "typeface": {
				


		setTypeFace(objValue, strValue);



			}
			break;
			case "textStyle": {
				


		setTextStyle(objValue);



			}
			break;
			case "fontFamily": {
				


		setFontFamily(objValue, strValue);



			}
			break;
			case "drawableStart": {
				


		 setDrawableLeft(objValue); 



			}
			break;
			case "drawableEnd": {
				


		 setDrawableRight(objValue); 



			}
			break;
			case "drawableTop": {
				


		 setDrawableTop(objValue); 



			}
			break;
			case "drawableBottom": {
				


		 setDrawableBottom(objValue); 



			}
			break;
			case "drawablePadding": {
				


		 setDrawablePadding(objValue); 



			}
			break;
			case "drawableTint": {
				


		setDrawableTint(objValue);



			}
			break;
			case "drawableTintMode": {
				


		setDrawableTintMode(strValue);



			}
			break;
			case "maxLength": {
				


		 setMaxLength(objValue); 



			}
			break;
			case "enabled": {
				


		 setEnabled(objValue);



			}
			break;
			case "editable": {
				


		 setEnabled(objValue);



			}
			break;
			case "singleLine": {
				


		 //



			}
			break;
			case "textAllCaps": {
				


		 setTextAllCaps(objValue);



			}
			break;
			case "scrollHorizontally": {
				


		 setScrollHorizontally(objValue);



			}
			break;
			case "ellipsize": {
				


		 setEllipsize(objValue, strValue);



			}
			break;
			case "startOrStopMarquee": {
				


		 startOrStopMarquee(objValue);



			}
			break;
			case "marqueeRepeatLimit": {
				


		 setMarqueeRepeatLimit(objValue);



			}
			break;
			case "firstBaselineToTopHeight": {
				


		setFirstBaselineToTopHeight(objValue);



			}
			break;
			case "lastBaselineToBottomHeight": {
				


		setLastBaselineToBottomHeight(objValue);



			}
			break;
			case "textFormat": {
				


		setTextFormat(objValue);



			}
			break;
			case "textAppearance": {
				


		ViewImpl.setStyle(this, objValue);



			}
			break;
			case "drawableIconSize": {
				


		setDrawableIconSize(objValue);



			}
			break;
		default:
			break;
		}
		postSetAttribute(key, strValue, objValue, decorator);
	}
	
	@Override
	@SuppressLint("NewApi")
	public Object getAttribute(WidgetAttribute key, ILifeCycleDecorator decorator) {
		Object nativeWidget = asNativeWidget();
		Object attributeValue = ViewImpl.getAttribute(this, nativeWidget, key, decorator);
		if (attributeValue != null) {
			return attributeValue;
		}
		switch (key.getAttributeName()) {
			case "swtText": {
return switchButton.getText();				}
			case "swtSelection": {
return switchButton.getSelection();				}
			case "swtTextForSelect": {
return switchButton.getTextForSelect();				}
			case "swtTextForUnselect": {
return switchButton.getTextForUnselect();				}
			case "swtRound": {
return switchButton.isRound();				}
			case "swtBorderColor": {
return switchButton.getBorderColor();				}
			case "swtFocusColor": {
return switchButton.getFocusColor();				}
			case "swtSelectedForegroundColor": {
return switchButton.getSelectedForegroundColor();				}
			case "swtSelectedBackgroundColor": {
return switchButton.getSelectedBackgroundColor();				}
			case "swtUnselectedForegroundColor": {
return switchButton.getUnselectedForegroundColor();				}
			case "swtUnselectedBackgroundColor": {
return switchButton.getUnselectedBackgroundColor();				}
			case "swtButtonBorderColor": {
return switchButton.getButtonBorderColor();				}
			case "swtButtonBackgroundColor1": {
return switchButton.getButtonBackgroundColor1();				}
			case "swtButtonBackgroundColor2": {
return switchButton.getButtonBackgroundColor2();				}
			case "swtGap": {
return switchButton.getGap();				}
			case "swtArc": {
return switchButton.getArc();				}
			case "checked": {
return getChecked();				}
			case "textOn": {
return getTextOn();				}
			case "textOff": {
return getTextOff();				}
			case "switchPadding": {
return measurableView.getSwitchPadding();				}
			case "switchMinWidth": {
return measurableView.getMinWidth();				}
			case "trackTint": {
return getTrackTint();				}
			case "thumbTint": {
return getThumbTint();				}
			case "swtInsideMarginX": {
return getInsideMarginX();				}
			case "swtInsideMarginY": {
return getInsideMarginY();				}
			case "text": {
return getMyText();				}
			case "textColor": {
return getTextColor();				}
			case "gravity": {
return getGravity();				}
			case "textSize": {
return getTextSize();				}
			case "paddingBottom": {
return getPaddingBottom();				}
			case "paddingRight": {
return getPaddingRight();				}
			case "paddingLeft": {
return getPaddingLeft();				}
			case "paddingStart": {
return getPaddingStart();				}
			case "paddingEnd": {
return getPaddingEnd();				}
			case "paddingTop": {
return getPaddingTop();				}
			case "minLines": {
return getMinLines();				}
			case "maxLines": {
return getMaxLines();				}
			case "minWidth": {
return getMinWidth();				}
			case "minHeight": {
return getMinHeight();				}
			case "maxWidth": {
return getMaxWidth();				}
			case "maxHeight": {
return getMaxHeight();				}
			case "height": {
return getHeight();				}
			case "width": {
return getWidth();				}
			case "maxEms": {
return getMaxEms();				}
			case "minEms": {
return getMinEms();				}
			case "drawablePadding": {
return this.getDrawablePadding();				}
			case "ellipsize": {
return getEllipsize();				}
			case "marqueeRepeatLimit": {
return getMarqueeRepeatLimit();				}
			case "firstBaselineToTopHeight": {
return getFirstBaselineToTopHeight();				}
			case "lastBaselineToBottomHeight": {
return getLastBaselineToBottomHeight();				}
		}
		
		return null;
	}
	
	@Override
	public Object asWidget() {
		return measurableView;
	}

	

	private void setChecked(Object objValue) {
		switchButton.setSelection((boolean)objValue);
		measurableView.setChecked((boolean)objValue);
	}

	
	private Object getChecked() {
		return switchButton.getSelection();
	}
	
	private final class CheckedListener implements org.eclipse.swt.events.SelectionListener {
		@Override
		public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
			if (measurableView.isEnabled()) {
				measurableView.toggle();
			}
		}

		@Override
		public void widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent e) {

		}
	}	
	private CheckedListener checkedListener;
	private void setOnChecked(Object objValue) {
		if (checkedListener == null) {
			checkedListener = new CheckedListener();
			switchButton.addSelectionListener(checkedListener);
		}
		
		CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
		if (objValue instanceof String) {
			onCheckedChangeListener = new OnCheckedChangeListener(this, (String) objValue);
		} else {
			onCheckedChangeListener = (CompoundButton.OnCheckedChangeListener) objValue;
		}
		measurableView.setOnCheckedChangeListener(onCheckedChangeListener);

	}

	


	private void setTextFormat(Object objValue) {
		applyAttributeCommand("text", CommonConverters.command_textformatter, new String[] {"textFormat"}, true, (String) objValue);
	}
	


	private Composite wrapperComposite;

	private void setScrollHorizontally(Object objValue) {
		measurableView.setHorizontallyScrolling(objValue != null && (Boolean) objValue);
		
	}
	
	//start - text
	//start - code1
	private void setMyText(Object objValue) {
		String text = (String) objValue;
		if (text == null) {
			text = "";
		}
		
		if (html) {
			handleHtmlText(text);
		} else {
			nativeSetText(text);
		}
	}

    //end - code1
	private Object getMyText() {
		return styledText.getText();
	}
	

	private void nativeSetText(String value) {
		styledText.setText(value);
	}
	//end - text

    @Override
    public Object asNativeWidget() {
        return wrapperComposite;
    }

    
	private void nativeCreateLabel(Map<String, Object> params) {
		if (isHtmlSupported()) {
			initHtml(params);
		}
		Composite parent = (Composite) ViewImpl.getParent(this);
		nativeCreateLabel(parent, params);
	}

	private void nativeCreateLabel(Composite parent, Map<String, Object> params) {
		wrapperComposite = new DrawableComposite(parent, this, getStyle(params, fragment));
		wrapperComposite.setLayout(new com.ashera.common.AbsoluteLayout());
		
		int textStyle = getStyle("swtTextStyle", org.eclipse.swt.SWT.WRAP, params, fragment) & ~org.eclipse.swt.SWT.MULTI;
		styledText = createStyledText(wrapperComposite, textStyle);
		
		computeLineHeight();
		styledText.addDisposeListener((e) -> {
        	disposeAll(newFont);
        	cancelTimer();
        });
		registerForAttributeCommandChain("text");
		registerForAttributeCommandChain("drawableStart");
		registerForAttributeCommandChain("drawableTop");
		registerForAttributeCommandChain("drawableBottom");
		registerForAttributeCommandChain("drawableEnd");
	}

	//start - html
	private boolean html;
	private boolean escapeHtml;
	private Map<String, Object> htmlConfig;
	private void initHtml(Map<String, Object> params) {
		if (params.containsKey("html")) {
			html = params.get("html").equals("true");
			if (html) {
				htmlConfig = new HashMap<>();
			} else {
				if (escapeHtml) {
					applyAttributeCommand("text", CommonConverters.command_escapehtml, new String[] {}, true);
				}
			}
		}
	}
	//end - html

    //start - constgravity
	private static final int TEXT_ALIGN_CENTER = org.eclipse.swt.SWT.CENTER;
	private static final int TEXT_ALIGN_LEFT = org.eclipse.swt.SWT.LEFT;
	private static final int TEXT_ALIGN_RIGHT = org.eclipse.swt.SWT.RIGHT;
	//end - constgravity
    //start - gravity
    private void setGravity(Object objValue) {
        int value = (int) objValue;
        measurableView.setGravity(value);
        int major = value & GravityConverter.VERTICAL_GRAVITY_MASK;
        updateTextAlignment();

        switch (major) {
        case GravityConverter.TOP:
            setVerticalAligmentTop();
            break;
        case GravityConverter.BOTTOM:
            setVerticalAligmentBottom();
            break;
        case GravityConverter.CENTER_VERTICAL:
            setVerticalAligmentCenter();
            break;
        default:
        	// default is vertical top
        	setVerticalAligmentTop();
            break;
        }

    }

	private void updateTextAlignment() {
		r.android.text.Layout.Alignment minor = measurableView.getAlignmentOfLayout();
		boolean isRtl = false;
		boolean hasTextDirection = measurableView.getRawTextDirection() != 0;
		if (hasTextDirection ) {
			r.android.text.TextDirectionHeuristic heuristic =  measurableView.getTextDirectionHeuristic();
			String text = (String) getMyText();
			isRtl = heuristic.isRtl(text, 0, text.length());
		}

        switch (minor) {
        case ALIGN_LEFT:
        	setHorizontalAligmentLeft();
        	break;
        case ALIGN_NORMAL:
        	if (hasTextDirection) {
        		if (isRtl) {
        			setHorizontalAligmentRight();
        		} else {
        			setHorizontalAligmentLeft();
        		}
        	}  else {
        		setHorizontalAligmentLeft();
        	}
            break;
        case ALIGN_RIGHT:
        	setHorizontalAligmentRight();
        	break;
        case ALIGN_OPPOSITE:
        	if (hasTextDirection) {
        		if (isRtl) {
        			setHorizontalAligmentLeft();
        		} else {
        			setHorizontalAligmentRight();
        		}
        	} else {
        		setHorizontalAligmentRight();
        	}
            break;
        case ALIGN_CENTER:
            setHorizontalAligmentCenter();
            break;
        default:
        	// default is horizontal left
        	setHorizontalAligmentLeft();
            break;
        }
	}
    
	
	private Object getGravity() {
		com.ashera.view.BaseMeasurableView.VerticalAligment verticalAligment = measurableView.getVerticalAligment();
		if (verticalAligment == null) {
			verticalAligment = com.ashera.view.BaseMeasurableView.VerticalAligment.top;
		}
		int gravityVertical = 0;
		switch (verticalAligment) {
        case top:
        	gravityVertical = GravityConverter.TOP;
            break;
        case middle:
        	gravityVertical = GravityConverter.CENTER_VERTICAL;
            break;
        case bottom:
        	gravityVertical = GravityConverter.BOTTOM;
            break;
        default:
            //
            break;
        }
		int aligment = getTextAlignment();
		int gravitHorizontal = 0;
		switch (aligment) {
        case TEXT_ALIGN_CENTER:
        	gravitHorizontal = GravityConverter.CENTER_HORIZONTAL;
            break;
        case TEXT_ALIGN_LEFT:
        	gravitHorizontal = GravityConverter.LEFT;
            break;
        case TEXT_ALIGN_RIGHT:
        	gravitHorizontal = GravityConverter.RIGHT;
            break;
        default:
            break;
		}
		int gravity = gravitHorizontal | gravityVertical;
		return gravity;
	}
	
	public void onRtlPropertiesChanged(int layoutDirection) {
		if (measurableView.getRawTextAlignment() != 0 || measurableView.getRawLayoutDirection() != 0) {
			updateTextAlignment();
		}
	}

	//end - gravity
	//start - aligment
    //start - valign
	private void setVerticalAligmentCenter() {
		measurableView.setVerticalAligment(com.ashera.view.BaseMeasurableView.VerticalAligment.middle);
	}

	private void setVerticalAligmentBottom() {
		measurableView.setVerticalAligment(com.ashera.view.BaseMeasurableView.VerticalAligment.bottom);
	}

	private void setVerticalAligmentTop() {
		measurableView.setVerticalAligment(com.ashera.view.BaseMeasurableView.VerticalAligment.top);
	}
	//end - valign

    private int getTextAlignment() {
		return styledText.getAlignment();
	}
	private void setHorizontalAligmentCenter() {
		styledText.setAlignment(TEXT_ALIGN_CENTER);
	}

	private void setHorizontalAligmentRight() {
		styledText.setAlignment(org.eclipse.swt.SWT.RIGHT);
	}

	private void setHorizontalAligmentLeft() {
		styledText.setAlignment(org.eclipse.swt.SWT.LEFT);
	}
	//end - aligment
	
	public void nativeRequestLayout() {
		wrapperComposite.requestLayout();
	}
	
	//start - paddingcopy
	private Object getPaddingBottom() {
		return measurableView.getPaddingBottom();
	}
	
	private Object getPaddingTop() {
		return measurableView.getPaddingTop();
	}

	private Object getPaddingRight() {
		return measurableView.getPaddingRight();
	}
	
	private Object getPaddingLeft() {
		return measurableView.getPaddingLeft();
	}
	
	private Object getPaddingEnd() {
		return getPaddingRight();
	}
	
	private Object getPaddingStart() {
		return getPaddingLeft();
	}
	
    private void setPaddingVertical(Object objValue) {
    	setPaddingBottom(objValue);
    	setPaddingTop(objValue);
    }

    private void setPaddingHorizontal(Object objValue) {
    	setPaddingRight(objValue);
    	setPaddingLeft(objValue);
    }

	private void setPaddingTop(Object objValue) {
		ViewImpl.setPaddingTop(objValue, measurableView);
	}

	private void setPaddingEnd(Object objValue) {
		ViewImpl.setPaddingRight(objValue, measurableView);
	}

	private void setPaddingStart(Object objValue) {
		ViewImpl.setPaddingLeft(objValue, measurableView);
	}

	private void setPaddingLeft(Object objValue) {
		ViewImpl.setPaddingLeft(objValue, measurableView);
	}

	private void setPaddingRight(Object objValue) {
		ViewImpl.setPaddingRight(objValue, measurableView);
	}

	private void setPaddingBottom(Object objValue) {
		ViewImpl.setPaddingBottom(objValue, measurableView);
	}

    private void setPadding(Object objValue) {
    	setPaddingBottom(objValue);
    	setPaddingTop(objValue);
    	setPaddingRight(objValue);
    	setPaddingLeft(objValue);
    }

    //end - paddingcopy
    //start - font
    //start - variables
    private org.eclipse.swt.graphics.Font newFont;
	private static final int ITALIC_FONT_TRAIT = org.eclipse.swt.SWT.ITALIC;
	private static final int BOLD_FONT_TRAIT = org.eclipse.swt.SWT.BOLD;
	private static final int NORMAL_FONT_TRAIT = org.eclipse.swt.SWT.NORMAL;
	//end - variables
	//start - code3
    private Map<String, com.ashera.model.FontDescriptor> fontDescriptors ;

    private void setTypeFace(Object objValue, String strValue) {
        setFontFamily(objValue, strValue);
    }
    
    private void setFontFamily(Object objValue, String strValue) {
        if (objValue instanceof Integer) {
            objValue = PluginInvoker.convertFrom(ConverterFactory.get(CommonConverters.font), null, strValue, fragment);
        }
        this.fontDescriptors = (Map<String, com.ashera.model.FontDescriptor>) objValue;
		int style = nativeGetFontStyle();
		int height = nativeGetFontSize();
        
        String weight = "400";
        if ((style & BOLD_FONT_TRAIT) != 0) {
            weight = "700";
        }
        String fontStyle = "normal";
        if ((style & ITALIC_FONT_TRAIT) != 0) {
            fontStyle = "italic";
        }
        com.ashera.model.FontDescriptor fontDescriptor = fontDescriptors.get(fontStyle + "_" + weight);
        nativeSetCustomFont(height, fontDescriptor);
    }

    
    private void setTextStyle(Object objValue) {
        int value = (int)objValue;

        if (fontDescriptors != null) {
            int height = nativeGetFontSize();
            
            String weight = "400";
            if ((value & 0x1) != 0) {
                weight = "700";
            }
            String fontStyle = "normal";
            if ((value & 0x2) != 0) {
                fontStyle = "italic";
            }
            com.ashera.model.FontDescriptor fontDescriptor = fontDescriptors.get(fontStyle + "_" + weight);
            nativeSetCustomFont(height, fontDescriptor);
        } else {
            int style = NORMAL_FONT_TRAIT; 
            if ((value & 0x1) != 0) {
                style = style | BOLD_FONT_TRAIT;
            }
            if ((value & 0x2) != 0) {
                style = style | ITALIC_FONT_TRAIT;
            }       
            nativeSetFontStyle(style);
        }

    }
	//end - code3
    //start - nativefont
    private FontData[] getFontData() {
    	FontData[] fontData = this.newFont == null ? styledText.getFont().getFontData() : this.newFont.getFontData();
    	return fontData;
    }
	private int nativeGetFontSize() {
		FontData[] fontData = getFontData();
        int height = fontData[0].getHeight();
		return height;
	}

	private int nativeGetFontStyle() {
		FontData[] fontData = getFontData();
        int style = fontData[0].getStyle();
		return style;
	}

	private void nativeSetCustomFont(int height, com.ashera.model.FontDescriptor fontDescriptor) {
		final org.eclipse.swt.graphics.Font newFont = new org.eclipse.swt.graphics.Font(Display.getDefault(), fontDescriptor.getName(), height, fontDescriptor.getStyle());
		setFont(newFont);
	}

	private void nativeSetFontStyle(int style) {
		FontData[] fontData = getFontData();
		for(int i = 0; i < fontData.length; ++i) {
		    fontData[i].setStyle(style);
		}

		final org.eclipse.swt.graphics.Font newFont = new org.eclipse.swt.graphics.Font(Display.getDefault(), fontData);
		setFont(newFont);
	}

    private void setFont(final org.eclipse.swt.graphics.Font newFont) {
        disposeAll(this.newFont);
        this.newFont = newFont;
        setMyFont(newFont);
    }

    private void setMyTextSize(Object objValue) {
        FontData[] fontData = getFontData();
        for(int i = 0; i < fontData.length; ++i) {
            float fontSize = ((float) objValue) * getFragment().getRootActivity().getScaleFactor();
			fontData[i].setHeight((int) fontSize);
        }

        final org.eclipse.swt.graphics.Font newFont = new org.eclipse.swt.graphics.Font(Display.getDefault(), fontData);
        setFont(newFont);
    }
    
	
	private Object getTextSize() {
		return getFontData()[0].getHeight();
	}
    //end - nativefont
    
	
	private void setTextColor(Object objValue) {
		if (objValue instanceof r.android.content.res.ColorStateList) {
			r.android.content.res.ColorStateList colorStateList = (r.android.content.res.ColorStateList) objValue;
			measurableView.setTextColor(colorStateList);
			objValue = measurableView.getCurrentTextColor();
		}
		
		styledText.setForeground((Color)ViewImpl.getColor(objValue));
	}

	private Object getTextColor() {
		return measurableView.getTextColors();
	}
    //end - font
    
    private void loadCustomAttributes(String attributeName) {
    }
    
    //start - drawable
    private Label drawableTop;
    private Label drawableBottom;
    private Label drawableRight;
	
    @Override
	public void drawableStateChanged() {
    	super.drawableStateChanged();
		drawableStateChange(drawableBottom, measurableView.getBottomDrawable(), "drawableBottom");
		drawableStateChange(drawableLeft, measurableView.getLeftDrawable(), "drawableStart");
		drawableStateChange(drawableRight, measurableView.getRightDrawable(), "drawableEnd");
		drawableStateChange(drawableTop, measurableView.getTopDrawable(), "drawableTop");
		
		if (measurableView.getTextColors() != null) {
			setTextColor(measurableView.getCurrentTextColor());
		}
		
		if (drawableTint != null && drawableTint.isStateful()) {
			setDrawableTint(drawableTint);
		}
		drawableStateChangedAdditional();
	}
    
	private void drawableStateChange(Label mydrawable, r.android.graphics.drawable.Drawable dr, String attribute) {
		if (mydrawable != null) {
			final int[] state = measurableView.getDrawableState();
			
			if (dr != null && dr.isStateful() && dr.setState(state)) {
				int width = mydrawable.getBounds().width;
				int height = mydrawable.getBounds().height;
				if (width != 0 && height != 0) { 
					Image image = (Image) dr.getDrawable();
					if (image.getImageData().width != width && image.getImageData().height != height) {
						Image resizedImage = com.ashera.common.ImageUtils.resize(image, width, height, 
								new com.ashera.common.ImageUtils.ResizeOptions.Builder().initFromAttr(this, attribute).build());
						image.dispose();
						image = resizedImage;
						dr.setDrawable(resizedImage);
						fragment.addDisposable(resizedImage);
					}
					mydrawable.setImage(image);
				}
			}
		}
	}
    //start - leftdrawable
	private void setDrawableTintMode(Object value) {
		applyAttributeCommand("drawableStart", "tintColor", "drawableTintMode", value);
		applyAttributeCommand("drawableEnd", "tintColor", "drawableTintMode", value);
		applyAttributeCommand("drawableTop", "tintColor", "drawableTintMode", value);
		applyAttributeCommand("drawableBottom", "tintColor", "drawableTintMode", value);
	}

	//start - iconsize
	private void setDrawableIconSize(Object objValue) {
		applyAttributeCommand("drawableStart", "drawableIconSize", new String[] {"drawableIconSize"}, true, objValue);
		applyAttributeCommand("drawableEnd", "drawableIconSize", new String[] {"drawableIconSize"}, true, objValue);
		applyAttributeCommand("drawableTop", "drawableIconSize", new String[] {"drawableIconSize"}, true, objValue);
		applyAttributeCommand("drawableBottom", "drawableIconSize", new String[] {"drawableIconSize"}, true, objValue);
	}
	//end - iconsize
	private r.android.content.res.ColorStateList drawableTint; 
	private void setDrawableTint(Object objValue) {
		if (objValue instanceof r.android.content.res.ColorStateList) {
			r.android.content.res.ColorStateList colorStateList = (r.android.content.res.ColorStateList) objValue;
			this.drawableTint = colorStateList;
			objValue = drawableTint.getColorForState(measurableView.getDrawableState(), r.android.graphics.Color.RED);
		}
		
		Object color = ViewImpl.getColor(objValue);

		applyAttributeCommand("drawableStart", "tintColor", "drawableTint", color);
		applyAttributeCommand("drawableEnd", "tintColor", "drawableTint", color);
		applyAttributeCommand("drawableTop", "tintColor", "drawableTint", color);
		applyAttributeCommand("drawableBottom", "tintColor", "drawableTint", color);
		
	}
	private void applyAttributeCommand(String sourceName, String commandName, String attribute, Object value) {
		applyAttributeCommand(sourceName, commandName, new String[] {attribute}, true, value);
	}
	
    private Label drawableLeft;

	private r.android.graphics.drawable.Drawable getDrawable(Object objValue) {
		r.android.graphics.drawable.Drawable drawable = new r.android.graphics.drawable.Drawable();
		drawable.setMinimumWidth(getImageWidth(objValue));
		drawable.setMinimumHeight(getImageHeight(objValue));
		return drawable;
	}
	
	private int getImageHeight(Object objValue) {
		return ((Image) objValue).getBounds().height;
	}
	
	private int getImageWidth(Object objValue) {
		return ((Image) objValue).getBounds().width;
	}

    //end - leftdrawable
    
	private void setDrawableLeft(Object objValue) {
		if (objValue instanceof r.android.graphics.drawable.Drawable) {
			r.android.graphics.drawable.Drawable drawable = (r.android.graphics.drawable.Drawable) objValue;
			
			if (drawable.hasDrawable()) {
				if (drawableLeft == null) {
					// create left
					this.drawableLeft = new Label(wrapperComposite, org.eclipse.swt.SWT.NONE);
					drawableLeft.addDisposeListener((e) -> disposeAll(drawableLeft.getImage()));
				}
				
				measurableView.setLeftDrawable(drawable);
				if (!drawable.isRecycleable()) {
					disposeAll(drawableLeft.getImage());
				}
				setImageOrColorOnDrawable(drawableLeft, drawable, drawable.getTintColor(), drawable.getTintMode(), "drawableStart");
			}
		} else {
			measurableView.setLeftDrawable(null);
			// null case
			if (drawableLeft != null) {
				drawableLeft.dispose();
				drawableLeft = null;
			}
		}
	}

	private void setDrawableRight(Object objValue) {		
		if (objValue instanceof r.android.graphics.drawable.Drawable) {
			r.android.graphics.drawable.Drawable drawable = (r.android.graphics.drawable.Drawable) objValue;

			if (drawable.hasDrawable()) {
				if (drawableRight == null) {
					// create right
					this.drawableRight = new Label(wrapperComposite, org.eclipse.swt.SWT.NONE);
					drawableRight.addDisposeListener((e) -> disposeAll(drawableRight.getImage()));
				}
	
				measurableView.setRightDrawable(drawable);
				if (!drawable.isRecycleable()) {
					disposeAll(drawableRight.getImage());
				}
				setImageOrColorOnDrawable(drawableRight,  drawable, drawable.getTintColor(), drawable.getTintMode(), "drawableEnd");
			}
		} else {
			measurableView.setRightDrawable(null);
			// null case
			if (drawableRight != null) {
				drawableRight.dispose();
				drawableRight = null;
			}
		}
	}
	
	private void setImageOrColorOnDrawable(Label control, r.android.graphics.drawable.Drawable drawable, Object tintColor, String tintMode, String attribute) {
		Object imageOrColor = drawable.getDrawable();
		if (imageOrColor instanceof Color) {
			control.setBackground((Color)imageOrColor);
		} else {
			control.setBackground(null);
		}
		if (imageOrColor instanceof Image) {
			if (tintColor != null) {
				if (tintColor instanceof r.android.content.res.ColorStateList) {
					tintColor = ((r.android.content.res.ColorStateList)tintColor).getColorForState(measurableView.getDrawableState(), r.android.graphics.Color.RED);
					tintColor = ViewImpl.getColor(tintColor);
				}
				imageOrColor = com.ashera.common.ImageUtils.tintImage((Image) imageOrColor, (Color) tintColor, tintMode);
				fragment.addDisposable(imageOrColor);
			}
			
			if (drawable.getMinimumWidth() != 0 && drawable.getMinimumHeight() != 0 
					&& ((Image) imageOrColor).getImageData().width != drawable.getMinimumWidth() && ((Image) imageOrColor).getImageData().height != drawable.getMinimumHeight()) {
				imageOrColor = com.ashera.common.ImageUtils.resize((Image) imageOrColor, drawable.getMinimumWidth(), drawable.getMinimumHeight(), 
						new com.ashera.common.ImageUtils.ResizeOptions.Builder().initFromAttr(this, attribute).build());
				fragment.addDisposable(imageOrColor);
			}
			control.setImage((Image) imageOrColor);
		} else {
			control.setImage(null);
		}
	}

	private void setDrawableBottom(Object objValue) {
				if (objValue instanceof r.android.graphics.drawable.Drawable) {
			r.android.graphics.drawable.Drawable drawable = (r.android.graphics.drawable.Drawable) objValue;
			if (drawable.hasDrawable()) {
				measurableView.setBottomDrawable(drawable);
			
				if (drawableBottom == null) {
					// create bottom
					this.drawableBottom = new Label(wrapperComposite, org.eclipse.swt.SWT.NONE);
					drawableBottom.addDisposeListener((e) -> disposeAll(drawableBottom.getImage()));
				}
				
				if (!drawable.isRecycleable()) {
					disposeAll(drawableBottom.getImage());
				}
				setImageOrColorOnDrawable(drawableBottom, drawable, drawable.getTintColor(), drawable.getTintMode(), "drawableStart");
			} else {
				measurableView.setBottomDrawable(null);
				// null case
				if (drawableBottom != null) {
					drawableBottom.dispose();
					drawableBottom = null;
				}
			}
		}
	}

	private void setDrawableTop(Object objValue) {
		if (objValue instanceof r.android.graphics.drawable.Drawable) {
			r.android.graphics.drawable.Drawable drawable = (r.android.graphics.drawable.Drawable) objValue;

			if (drawable.hasDrawable()) {
				if (drawableTop == null) {
					// create top
					this.drawableTop = new Label(wrapperComposite, org.eclipse.swt.SWT.NONE);
					drawableTop.addDisposeListener((e) -> disposeAll(drawableTop.getImage()));
				}
				
	
				measurableView.setTopDrawable(drawable);
				if (!drawable.isRecycleable()) {
					disposeAll(drawableTop.getImage());
				}
				setImageOrColorOnDrawable(drawableTop,  drawable, drawable.getTintColor(), drawable.getTintMode(), "drawableTop");
			} else {
				measurableView.setTopDrawable(null);
				// null case
				if (drawableTop != null) {
					drawableTop.dispose();
					drawableTop = null;
				}
			}
		}
	}

	private void setDrawablePadding(Object objValue) {
		measurableView.setDrawablePadding((int) objValue);
	}
	
	private Object getDrawablePadding() {
		return measurableView.getDrawablePadding();
	}
	
	@Override
	public Control getControl() {
		return styledText;
	}

	@Override
	public Control getDrawableTop() {
		return drawableTop;
	}

	@Override
	public Control getDrawableLeft() {
		return drawableLeft;
	}

	@Override
	public Control getDrawableRight() {
		return drawableRight;
	}

	@Override
	public Control getDrawableBottom() {
		return drawableBottom;
	}
    //end - drawable
	
    //start - maxminheight
    private Object getMinHeight() {
        return measurableView.getMinHeight();
    }

    private Object getMinWidth() {
        return measurableView.getMinWidth();
    }
    
    private void setEms(Object objValue) {
        setMinEms(objValue);
        setMaxEms(objValue);
    }
    
    
    public int getMaxEms() {
        return measurableView.getMaxEms();
    }
    public int getMinEms() {
        return measurableView.getMinEms();
    }

    private void setMinEms(Object objValue) {
    	measurableView.setMinEms((int) objValue);
        addMinMaxListener();
    }
    
    public int getMinLines() {
        return measurableView.getMinLines();
    }
    
    public int getMaxLines() {
        return measurableView.getMaxLines();
    }

    private void setMaxEms(Object objValue) {
    	measurableView.setMaxEms((int) objValue);
        addMinMaxListener();
    }

    private void setWidth(Object objValue) {
        setMinWidth(objValue);
        setMaxWidth(objValue);
    }

    private void setHeight(Object objValue) {
        setMinHeight(objValue);
        setMaxHeight(objValue);
    }

    private void setMaxLines(Object objValue) {
    	measurableView.setMaxLines((int) objValue);
        addMinMaxListener();
    }

    private void setLines(Object objValue) {
        setMinLines(objValue);
        setMaxLines(objValue);
    }

    private void setMinLines(Object objValue) {
    	measurableView.setMinLines((int) objValue);
        addMinMaxListener();
    
    }
    
    private void setMaxHeight(Object objValue) {
    	measurableView.setMaxHeight((int) objValue);
        addMinMaxListener();
    }

    private void setMaxWidth(Object objValue) {
    	measurableView.setMaxWidth((int) objValue);
        addMinMaxListener();
    }

    public int getMaxWidth() {
        return measurableView.getMaxWidth();
    }

    public int getMaxHeight() {
        return measurableView.getMaxHeight();
    }
    
    
    private void setMinHeight(Object objValue) {
    	measurableView.setMinHeight((int) objValue);
        addMinMaxListener();
    }

    private void setMinWidth(Object objValue) {
    	measurableView.setMinWidth((int) objValue);
        addMinMaxListener();
    }

    
    private Object getWidth() {
        return measurableView.getWidth();
    }

    private int getHeight() {
        return measurableView.getHeight();
    }

    
    //end - maxminheight
    
    //start - autosize
	private int getAutoSizeTextType(r.android.widget.TextView measurableView) {
		return measurableView.getAutoSizeTextType();
	}

	private void setAutoSizeTextTypeInternal(int autoTextType) {
		removeResizeListener();
        
		if (measurableView.isAutoSizeTextTypeUniform(autoTextType)) {
			measurableView.setUpAutoSizeTextTypeUniform(autoSizeMin, autoSizeMax, autoSizeGranular);
            addAutoResizeListener();
        } else {
        	measurableView.clearAutoSizeTypeConfiguration();
        }
	}
	
	private void setAutoSizePresetSizes(Object objValue) {
		measurableView.setAutoSizeTextTypeUniformWithPresetSizes((int[]) objValue, 0);
		
	}

	@com.google.j2objc.annotations.WeakOuter
	class PostMeasureHandler extends com.ashera.widget.bus.EventBusHandler {
		private boolean onlyOnce;
		public PostMeasureHandler(String type) {
			super(type);
		}

		@Override
		protected void doPerform(Object payload) {
			if (!onlyOnce || measurableView.isLayoutRequested()) {
				measurableView.autoResizeText();
				onlyOnce = true;
			}
		}
	}
	
	private PostMeasureHandler postMeasureHandler;
	private final String POST_MEASURE_EVENT = StandardEvents.postMeasure.toString();
    private void addAutoResizeListener() {
    	if (postMeasureHandler == null) {
    		postMeasureHandler = new PostMeasureHandler(POST_MEASURE_EVENT);
			fragment.getEventBus().on(POST_MEASURE_EVENT, postMeasureHandler);
    	}
	}

	private void removeResizeListener() {
		if (postMeasureHandler != null) {
			fragment.getEventBus().off(postMeasureHandler);
			postMeasureHandler = null;
		}
	}
    
    //end - autosize

	//start - maxlength
	private void setMaxLength(Object objValue) {
		if (!html) {
			applyAttributeCommand("text", CommonConverters.command_maxlength, new String[] {"maxLength"}, true, objValue);
		} else {
			htmlConfig.put("maxLength", objValue);
		}
	}
    //end - maxlength

	
    //start - enabled
	private void setEnabled(Object objValue) {
		styledText.setEnabled((boolean) objValue);
	}
	//end - enabled

	//start - textallcaps
	//start - code2

	private void setTextAllCaps(Object objValue) {
		if (!html) {
			applyAttributeCommand("text", CommonConverters.command_uppercase, new String[] {"textAllCaps"}, (boolean) objValue);
		} else {
			htmlConfig.put("textAllCaps", objValue);
		}
	}
	//end - code2

	//end - textallcaps

	//start - nativewidgets
	@Override
    public List<Object> getNativeWidgets() {
		return java.util.Arrays.asList(wrapperComposite, styledText, drawableBottom, drawableLeft, drawableTop, drawableRight).
        		stream().filter((p) -> p != null).collect(java.util.stream.Collectors.toList());
    }
	//end - nativewidgets
    
    //start - password
  	private void setPassword(Object objValue) {
  		if (!html) {
  			applyAttributeCommand("text", CommonConverters.command_password, new String[] {"password"}, (boolean) objValue);
  		} else {
  			htmlConfig.put("password", objValue);
  		}
  	}
  	//end - password
  	

  	//start - dimenmeasure
	@Override
	public int measureHeight(int width) {
		int wrapperCompositeHeight = wrapperComposite.computeSize(width, org.eclipse.swt.SWT.DEFAULT).y;
		return styledText.computeSize(width, org.eclipse.swt.SWT.DEFAULT).y + wrapperCompositeHeight;
	}

	@Override
	public int measureWidth() {
		int wrapperCompositeWidth = wrapperComposite.computeSize(org.eclipse.swt.SWT.DEFAULT, org.eclipse.swt.SWT.DEFAULT).y;
		return styledText.computeSize(org.eclipse.swt.SWT.DEFAULT, org.eclipse.swt.SWT.DEFAULT).x + wrapperCompositeWidth + getSwitchButtonWidth();
	}
	//end - dimenmeasure
	
	
    //start - ellipsize
	private void setEllipsize(Object objValue, String strValue) {
		styledText.setData("ellipsize", (int) objValue);

		cancelTimer();
		if (strValue.equals("marquee")) {
			startTimer();	
			
		}
		if (isInitialised()) {
			styledText.redraw();
		}
	}

	private Object getEllipsize() {
		return styledText.getData("ellipsize");
	}
	//end - ellipsize

	//start - marquee
	//start - widgetmarquee
    private MarqueeTask marqueeTask;
	private int marqueeRepeatLimit;
    private MarqueeCommandConverter marqueeCommandConverter;
	
    private int calcNumberOfWhiteSpaces() {
		if (!isLabelMeasured()) {
			return 0;
		}

		float blankSpaceWidth = ((Number) getTextSize()).floatValue();
		int width = getLabelWidth()/3;
		int numberOfBlankSpaces = (int) Math.ceil(width / (blankSpaceWidth * 1f));
		return numberOfBlankSpaces;
	}
	
	private void cancelTimer() {
		cancelNativeTimer();
		if (marqueeTask != null) {
			marqueeTask.cancel();
		}
		if (marqueeCommandConverter != null) {
			marqueeCommandConverter.updateArgs(0, 0);
		}
	}
	private void startTimer() {
		if (canMarquee()) {
			addDeallocHandler();
			if (marqueeTask != null) {
				marqueeTask.restart();
			} else {
				String direction = ViewImpl.isRTLLayoutDirection(this) ? "reverse" : "forward";
				marqueeTask = new MarqueeTask(direction);
			}
			
			schedule();
		}
	}

	@com.google.j2objc.annotations.WeakOuter
	private final class MarqueeTask implements Runnable {
		private int slideLength = 0;
		private int cycles = 0;
		private boolean cancel = false;
		private String direction;
		
		
		private MarqueeTask(String direction) {
			this.direction = direction;
		}

		public void cancel() {
			cancel = true;
		}

		public void restart() {
			cancel = false;
			slideLength = 0;
			cycles = 0;
		}

		@Override
		public void run() {
			int numberOfBlankSpaces = calcNumberOfWhiteSpaces();
			if (marqueeCommandConverter == null) {
				marqueeCommandConverter = (MarqueeCommandConverter) getAttributeCommand("text", CommonConverters.command_marquee, numberOfBlankSpaces, 0, direction);
			}
			slideLength = marqueeCommandConverter.getOffset();
			if (numberOfBlankSpaces > 0) {
				String str = (String) getMyText();
				if (slideLength == str.length()) {
					slideLength = 0;
					cycles++;
				}
				if ((marqueeRepeatLimit > 0 && cycles >= marqueeRepeatLimit)) {
					cancelTimer();
					return;
				}
				slideLength++;

				executeOnMainThread(() -> {
						if (!cancel) {
							applyAttributeCommand("text", CommonConverters.command_marquee, new String[] {"ellipsize"}, true, numberOfBlankSpaces, slideLength, direction);
							schedule();
						}
					
				});
			} else {
				schedule();
			}
		}
	}
	
	private Object getMarqueeRepeatLimit() {
		return marqueeRepeatLimit;
	}
	
	private void setMarqueeRepeatLimit(Object objValue) {
		this.marqueeRepeatLimit = (int) objValue;
	}
	

	private void startOrStopMarquee(Object objValue) {
		cancelTimer();
		if (objValue != null && (boolean) objValue) {
			startTimer();
		}
	}
	
	private int getLabelWidth() {
		if (measurableView.isIgnoreDrawableHeight()) {
			return measurableView.getMeasuredWidth() - measurableView.getPaddingLeft() - measurableView.getPaddingRight(); 
		}
		return measurableView.getMeasuredWidth() - measurableView.getCompoundPaddingRight() - measurableView.getCompoundPaddingLeft();
	}

	private boolean isLabelMeasured() {
		return !isDisposed() && getLabelWidth() > 0;
	}
	//end - widgetmarquee
	private void addDeallocHandler() {
		
	}
//	java.util.concurrent.ScheduledExecutorService scheduledExecutorService;
//	java.util.Timer timer;
	private void schedule() {
		Display.getDefault().timerExec(100, marqueeTask);
//		scheduledExecutorService = java.util.concurrent.Executors.newScheduledThreadPool(1);
//		scheduledExecutorService.schedule(task, 100, java.util.concurrent.TimeUnit.MILLISECONDS);
//		if (timer == null) {
//			timer = new java.util.Timer();
//			timer.scheduleAtFixedRate(new java.util.TimerTask() {
//	
//				@Override
//				public void run() {
//					task.run();
//				}
//			}, 0, 100);
//		}
	}
	
	private void executeOnMainThread(Runnable run) {
//		if (!cLabel.isDisposed()) {
//			Display.getDefault().asyncExec(run);
//		}
		run.run();
	}
	

	private void cancelNativeTimer() {
//		timer.cancel();
//		timer = null;
	}


	private boolean isDisposed() {
		return styledText.isDisposed();
	}

	private boolean canMarquee() {
		return (styledText.getStyle() & org.eclipse.swt.SWT.WRAP) == 0 && !html;
	}
	//end - marquee

	//start - baseline
	private void setFirstBaselineToTopHeight(Object objValue) {
		com.ashera.model.FontMetricsDescriptor fontMetrics = PluginInvoker.getFontMetrics(getFont());
		int firstBaselineToTopHeight = (int) objValue;

		final int fontMetricsTop;
        if (getIncludeFontPadding()) {
            fontMetricsTop = fontMetrics.top;
        } else {
            fontMetricsTop = fontMetrics.ascent;
        }

        // TODO: Decide if we want to ignore density ratio (i.e. when the user changes font size
        // in settings). At the moment, we don't.
        if (firstBaselineToTopHeight > Math.abs(fontMetricsTop)) {
            final int paddingTop = firstBaselineToTopHeight - (-fontMetricsTop);
            measurableView.setPadding((int) getPaddingLeft(), paddingTop, (int) getPaddingRight(), (int) getPaddingBottom());
        }
	}
	
	
	private boolean getIncludeFontPadding() {
		return false;
	}

	private Object getFirstBaselineToTopHeight() {
		com.ashera.model.FontMetricsDescriptor fontMetrics = PluginInvoker.getFontMetrics(getFont());
		return (int) getPaddingTop() - fontMetrics.top;
	}
	
	
	private void setLastBaselineToBottomHeight(Object objValue) {
		com.ashera.model.FontMetricsDescriptor fontMetrics = PluginInvoker.getFontMetrics(getFont());
		int lastBaselineToBottomHeight = (int) objValue;

        final int fontMetricsBottom;
        if (getIncludeFontPadding()) {
            fontMetricsBottom = fontMetrics.bottom;
        } else {
            fontMetricsBottom = fontMetrics.descent;
        }

        // TODO: Decide if we want to ignore density ratio (i.e. when the user changes font size
        // in settings). At the moment, we don't.

        if (lastBaselineToBottomHeight > Math.abs(fontMetricsBottom)) {
            final int paddingBottom = lastBaselineToBottomHeight - fontMetricsBottom;
            measurableView.setPadding((int) getPaddingLeft(), (int) getPaddingTop(), (int) getPaddingRight(), paddingBottom);
        }		
	}
	
	private Object getLastBaselineToBottomHeight() {
		com.ashera.model.FontMetricsDescriptor fontMetrics = PluginInvoker.getFontMetrics(getFont());
		return (int) getPaddingBottom() + fontMetrics.bottom;
	}
	//end - baseline

	private org.eclipse.swt.graphics.Font getFont() {
		return styledText.getFont();
	}
	


	protected org.eclipse.swt.custom.StyledText styledText;

	private void nativeCreate(Map<String, Object> params) {
		nativeCreateLabel(params);
		switchButton = new org.eclipse.nebula.widgets.opal.switchbutton.SwitchButton(wrapperComposite, getStyle(params, fragment));
		updateIntrinsicBounds();
		measurableView.setGravity(r.android.view.Gravity.CENTER_VERTICAL);
	}

	private void updateIntrinsicBounds() {
		measurableView.setIntrinsicWidth(getSwitchButtonWidth());
		measurableView.setIntrinsicHeight(getSwitchButtonHeight());
	}
	
	private void setTextOff(Object objValue) {
		switchButton.setTextForUnselect((String)objValue); 
	}

	private void setTextOn(Object objValue) {
		switchButton.setTextForSelect((String)objValue); 		
	}

	private Object getTextOff() {
		return switchButton.getTextForUnselect();
	}

	private Object getTextOn() {
		return switchButton.getTextForSelect();

	}
	
	private void setInsideMarginY(Object objValue) {
		switchButton.setInsideMargin((int) getInsideMarginX(), (int) objValue);
	}

	private void setInsideMarginX(Object objValue) {
		switchButton.setInsideMargin((int) objValue, (int) getInsideMarginY());		
	}

	
	private Object getInsideMarginY() {
		return switchButton.getInsideMargin().y;
	}

	private Object getInsideMarginX() {
		return switchButton.getInsideMargin().x;
	}
	
	
	private void postSetAttribute(WidgetAttribute key, String strValue, Object objValue,
			ILifeCycleDecorator decorator) {
		if (isInitialised()) {
			switchButton.redraw();
		}

		switch (key.getAttributeName()) {
		case "text":
			measurableView.setText((String) objValue);
			break;
		case "textOff":
		case "textOn":
		case "swtText":
		case "swtTextForSelect":
		case "swtTextForUnselect":
			updateIntrinsicBounds();
			if (isInitialised()) {
				measurableView.requestLayout();
			}
			break;

		case "drawableTop":
		case "drawableBottom":
		case "drawableLeft":
		case "drawableRight":
		case "drawableStart":
		case "drawableEnd":
			if (isInitialised()) {
				addClickListenerToSyncCheckbox();
			}
			break;
		case "editable":
			measurableView.setEnabled((boolean) objValue);
		case "enabled":
			switchButton.setEnabled((boolean) objValue);
			break;
		default:
			break;
		}

	}
	
    private int autoSizeMin = -1;
    private int autoSizeMax = -1;
    private int autoSizeGranular = -1;
    
	
	private void nativeMakeFrameForChildWidget(int l, int t, int r, int b) {
		
		r.android.graphics.Rect switchBounds = measurableView.getSwitchBounds();
		switchButton.setBounds(switchBounds.left, switchBounds.top, switchBounds.right - switchBounds.left, switchBounds.bottom - switchBounds.top);
	}
	
    private int getSwitchButtonWidth() {
		return switchButton.computeSize(org.eclipse.swt.SWT.DEFAULT, org.eclipse.swt.SWT.DEFAULT).x;
	}
    
    private int getSwitchButtonHeight() {
		return switchButton.computeSize(org.eclipse.swt.SWT.DEFAULT, org.eclipse.swt.SWT.DEFAULT).y;
	}
    
	@Override
	public void initialized() {
		super.initialized();
		
		addClickListenerToSyncCheckbox();
	}

	private void addClickListenerToSyncCheckbox() {
		ViewImpl.setOnListener(this, org.eclipse.swt.SWT.MouseDown, org.eclipse.swt.SWT.MouseDown + "Chk", (event) -> {
			if (measurableView.isEnabled()) {
				boolean isChecked = (boolean)getChecked();
				switchButton.setSelection(!isChecked);
				
				if (checkedListener != null) {
					checkedListener.widgetSelected(null);
				}
				switchButton.redraw();
			}
		});
	}
	
	
	private void setThumbTint(Object objValue) {
		measurableView.setThumbTintList((r.android.content.res.ColorStateList) objValue);

		switchButton.setUnselectedBackgroundColor((Color) ViewImpl.getColor(measurableView.getThumbTintList().getColorForState(
				measurableView.getDrawableState(), r.android.graphics.Color.RED)));
		switchButton.setSelectedBackgroundColor((Color) ViewImpl.getColor(measurableView.getThumbTintList().getColorForState(
				measurableView.getDrawableState(), r.android.graphics.Color.RED)));
	}
	

	private void setTrackTint(Object objValue) {
		measurableView.setTrackTintList((r.android.content.res.ColorStateList) objValue);
		switchButton.setButtonBackgroundColor1((Color) ViewImpl.getColor(measurableView.getTrackTintList().getColorForState(
				measurableView.getDrawableState(), r.android.graphics.Color.RED)));
		
	}
	
	
	private Object getThumbTint() {
		return measurableView.getThumbTintList();
	}

	private Object getTrackTint() {
		return measurableView.getTrackTintList();
	}
	

	private void drawableStateChangedAdditional() {
		if (measurableView.getTrackTintList() != null) {
			setTrackTint(measurableView.getTrackTintList());
		}
		
		if (measurableView.getThumbTintList() != null) {
			setThumbTint(measurableView.getThumbTintList());
		}
		
		switchButton.redraw();
	}
	


    

    private org.eclipse.swt.custom.StyledText createStyledText(Composite wrapperComposite, int style) {
        org.eclipse.swt.custom.StyledText styledText = new org.eclipse.swt.custom.StyledText(wrapperComposite, style) {
        	@Override
        	public void setText(java.lang.String text) {
        		int maxLength = getTextLimit();
        		if (maxLength > 0 && text.length() > maxLength) {
        			text = text.substring(0, maxLength);
        		}
        		super.setText(text);
        	}
        };;
        styledText.setBackground(Display.getDefault().getSystemColor(org.eclipse.swt.SWT.COLOR_TRANSPARENT));
        disableTextBox(styledText);
        return styledText;
    }

    
	private void setJustificationMode(Object objValue) {
		if (((int) objValue) == 0x1) {
			styledText.setJustify(true);
		} else {
			styledText.setJustify(false);
		}
	}
	

    private void setMyFont(org.eclipse.swt.graphics.Font newFont) {
        org.eclipse.swt.custom.StyleRange styleRange = new org.eclipse.swt.custom.StyleRange();
        styleRange.start = 0;
        styleRange.font = newFont;
        styleRange.length = styledText.getText().length();
        styledText.setStyleRange(styleRange);
	}
    
    //start - linespacing
	private float mSpacingAdd;
	private float mSpacingMult;
	private void setLineSpacingMultiplier(Object objValue) {
		mSpacingMult = (float) objValue;
		setLineSpacing();
	}

	private void setLineSpacingExtra(Object objValue) {
		mSpacingAdd = (float) objValue;
		setLineSpacing();
	}
	
	private Object getLineSpacingMultiplier() {
		return mSpacingMult;
	}

	private Object getLineSpacingExtra() {
		return mSpacingAdd;
	}
	//end - linespacing

	private void setLineSpacing() {
		styledText.setLineSpacingProvider(new org.eclipse.swt.custom.StyledTextLineSpacingProvider() {
			@Override
			public Integer getLineSpacing(int lineIndex) {
				return  Math.round((mSpacingAdd + (mSpacingMult * styledText.getLineHeight())));
			}
			
		});
	}
	
	private Object getJustificationMode() {
		return styledText.getJustify() ?  0x1 : 0x0;
	}
	
	@Override
	public int getBaseLine() {
		int baseline = styledText.getBaseline(styledText.getCharCount()) + measurableView.getPaddingTop();
		return baseline;
	}

	
	private void setTextIsSelectable(Object objValue) {
		styledText.setEnabled((boolean) objValue); 
		
	}

	private Object getTextIsSelectable() {
		return styledText.getEnabled();
	}
	
	private void computeLineHeight() {
	}

	


	

	private void nohup() {
		drawableStateChangedForTextLinkColor();
	}

    private void addMinMaxListener() {
        
    }

	private void disableTextBox(org.eclipse.swt.custom.StyledText styledText) {
		styledText.setEditable(false);
        styledText.setEnabled(false);
        styledText.setForeground(Display.getCurrent().getSystemColor(org.eclipse.swt.SWT.COLOR_BLACK));
	}

	
	private void setSingleLine(Object objValue) {
		styledText.setWordWrap(!(boolean) objValue);
	}
	
	
	public int getBorderPadding() {
		return 0;
	}
	
	public int getLineHeightPadding() {
		return 0;
	}
	
	public int getLineHeight() {
		return styledText.getLineHeight();
	}
	
	public int getBorderWidth() {
		return wrapperComposite.getBorderWidth();
	}
	
	private void handleHtmlText(String value) {
		com.ashera.attributedtext.AttributedString attributedString = com.ashera.parser.html.Html.fromHtml(value, htmlConfig, fragment);
		styledText.setText(attributedString.getText());

		java.util.Collection<org.eclipse.swt.custom.StyleRange> styleRanges = (java.util.Collection<org.eclipse.swt.custom.StyleRange>) attributedString.get();
		styledText.addPaintObjectListener(new org.eclipse.swt.custom.PaintObjectListener() {
		    public void paintObject(org.eclipse.swt.custom.PaintObjectEvent event) {
		      GC gc = event.gc;
		      org.eclipse.swt.custom.StyleRange style = event.style;
		      
				if (style.data != null) {
					Map<String, Object> data = (Map<String, Object>) style.data;
					Image image = (Image) data.get("image");
					if (image != null) {
						int x = event.x;
						int y = event.y + event.ascent - style.metrics.ascent;
						gc.drawImage(image, x, y);
					}
				}
		    }
		  });
		for (org.eclipse.swt.custom.StyleRange styleRange : styleRanges) {
			styledText.setStyleRange(styleRange);

			if (styleRange.data != null) {
				Map<String, Object> data = (Map<String, Object>) styleRange.data;

				int lineNumber = styledText.getLineAtOffset(styleRange.start);
				int lineCount = styledText.getLineAtOffset(styleRange.start + styleRange.length) - lineNumber;
				
				if (lineCount == 0) {
					lineCount = 1;
				}
				for (String key : data.keySet()) {
					switch (key) {
					case "lineAlignment":
						styledText.setLineAlignment(lineNumber, lineCount, (int) data.get(key));
						break;
					case "bullet":
						styledText.setLineBullet(lineNumber, lineCount, (org.eclipse.swt.custom.Bullet) data.get(key));
						break;
					default:
						break;
					}
				}
			}
		}
	}
	
	private boolean isHtmlSupported() {
		return true;
	}
	
	


	private int autoLink;
	private Object getAutoLink() {
		return autoLink;
	}
	
	
	private void setAutoLink(Object objValue) {
		this.autoLink = (int) objValue;
		applyAttributeCommand("text", "autoLink", new String[] {"autoLink"}, true, "mask", autoLink);		
	}
	
	private boolean linksClickable;
	private void setLinksClickable(Object objValue) {
		this.linksClickable = (boolean) objValue;
		applyAttributeCommand("text", "autoLink", new String[] {"autoLink"}, true, "linksClickable", linksClickable);	
	}
	
	
	private Object getLinksClickable() {
		return linksClickable;
	}
	

 
	private void setTextColorLink(Object objValue) {
		if (objValue instanceof r.android.content.res.ColorStateList) {
			r.android.content.res.ColorStateList colorStateList = (r.android.content.res.ColorStateList) objValue;
			measurableView.setLinkTextColor(colorStateList);
			objValue = measurableView.getPaint().linkColor;
		}
		if (html) {
			htmlConfig.put("textColorLink", ViewImpl.getColor(objValue));
		}
		
		if (isInitialised()) {
			rerunCommandOnSource("text", "statechange");
		}
	}
	
	private void drawableStateChangedForTextLinkColor() {
		if (measurableView.getLinkTextColors() != null && measurableView.getLinkTextColors().isStateful()) {
			setTextColorLink(measurableView.getLinkTextColors());
		}
	}	

	

	@SuppressLint("NewApi")
private static class OnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener, com.ashera.widget.IListener{
private IWidget w; private View view; private String strValue; private String action;
public String getAction() {return action;}
public OnCheckedChangeListener(IWidget w, String strValue)  {
this.w = w; this.strValue = strValue;
}
public OnCheckedChangeListener(IWidget w, String strValue, String action)  {
this.w = w; this.strValue = strValue;this.action=action;
}
public void onCheckedChanged (CompoundButton buttonView, 
                boolean isChecked){
    
	if (action == null || action.equals("onCheckedChange")) {
		// populate the data from ui to pojo
		w.syncModelFromUiToPojo("onCheckedChange");
	    java.util.Map<String, Object> obj = getOnCheckedChangeEventObj(buttonView,isChecked);
	    String commandName =  (String) obj.get(EventExpressionParser.KEY_COMMAND_NAME);
	    
	    // execute command based on command type
	    String commandType = (String)obj.get(EventExpressionParser.KEY_COMMAND_TYPE);
		switch (commandType) {
		case "+":
		    if (EventCommandFactory.hasCommand(commandName)) {
		    	 EventCommandFactory.getCommand(commandName).executeCommand(w, obj, buttonView,isChecked);
		    }

			break;
		default:
			break;
		}
		
		if (obj.containsKey("refreshUiFromModel")) {
			Object widgets = obj.remove("refreshUiFromModel");
			com.ashera.layout.ViewImpl.refreshUiFromModel(w, widgets, true);
		}
		if (w.getModelUiToPojoEventIds() != null) {
			com.ashera.layout.ViewImpl.refreshUiFromModel(w, w.getModelUiToPojoEventIds(), true);
		}
		if (strValue != null && !strValue.isEmpty() && !strValue.trim().startsWith("+")) {
		    com.ashera.core.IActivity activity = (com.ashera.core.IActivity)w.getFragment().getRootActivity();
		    if (activity != null) {
		    	activity.sendEventMessage(obj);
		    }
		}
	}
    return;
}//#####

public java.util.Map<String, Object> getOnCheckedChangeEventObj(CompoundButton buttonView,boolean isChecked) {
	java.util.Map<String, Object> obj = com.ashera.widget.PluginInvoker.getJSONCompatMap();
    obj.put("action", "action");
    obj.put("eventType", "checkedchange");
    obj.put("fragmentId", w.getFragment().getFragmentId());
    obj.put("actionUrl", w.getFragment().getActionUrl());
    obj.put("namespace", w.getFragment().getNamespace());
    
    if (w.getComponentId() != null) {
    	obj.put("componentId", w.getComponentId());
    }
    
    PluginInvoker.putJSONSafeObjectIntoMap(obj, "id", w.getId());
     
        PluginInvoker.putJSONSafeObjectIntoMap(obj, "isChecked", isChecked);
    
    // parse event info into the map
    EventExpressionParser.parseEventExpression(strValue, obj);
    
    // update model data into map
    w.updateModelToEventMap(obj, "onCheckedChange", (String)obj.get(EventExpressionParser.KEY_EVENT_ARGS));
    return obj;
}
}

	
	@Override
	public void setId(String id){
		if (id != null && !id.equals("")){
			super.setId(id);
			measurableView.setId((int) quickConvert(id, "id"));
		}
	}
	
    @Override
    public void setVisible(boolean b) {
        ((View)asWidget()).setVisibility(b ? View.VISIBLE : View.GONE);
    }
	public int getStyle(String key, int initStyle, Map<String, Object> params, IFragment fragment) {
		if (params == null) {
    		return initStyle;
    	}
    	Object style = params.get(key);
		if (style == null) {
			return initStyle;
		}
		int convertFrom = (int) ConverterFactory.get("swtbitflag").convertFrom(style.toString(), null, fragment);
		return convertFrom;
	}
	
	public int getStyle(Map<String, Object> params, IFragment fragment) {
		return getStyle("swtStyle", org.eclipse.swt.SWT.NONE, params, fragment);
	}
	
	public int getStyle(int initStyle, Map<String, Object> params, IFragment fragment) {
		return getStyle("swtStyle", initStyle, params, fragment);
	}
 
    @Override
    public void requestLayout() {
    	if (isInitialised()) {
    		ViewImpl.requestLayout(this, asNativeWidget());
    		
    	}
    }
    
    @Override
    public void invalidate() {
    	if (isInitialised()) {
			ViewImpl.invalidate(this, asNativeWidget());

    	}
    }
	public boolean isWidgetDisposed() {
		return switchButton.isDisposed();
	}

	
		//end - body

}
