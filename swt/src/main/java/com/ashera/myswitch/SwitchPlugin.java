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

import com.ashera.widget.WidgetFactory;

public class SwitchPlugin  {
    public static void initPlugin() {
    	//start - widgets
        WidgetFactory.register(new com.ashera.myswitch.SwitchImpl());
        WidgetFactory.register(new com.ashera.myswitch.SwitchStyledLabelImpl());
        //end - widgets
    }
}
