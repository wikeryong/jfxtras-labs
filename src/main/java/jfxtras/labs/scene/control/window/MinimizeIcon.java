/**
 * MinimizeIcon.java
 *
 * Copyright (c) 2011-2014, JFXtras
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the organization nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package jfxtras.labs.scene.control.window;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import jfxtras.labs.util.WindowUtil;

/**
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
public class MinimizeIcon extends WindowIcon {

    public static final String DEFAULT_STYLE_CLASS = "window-minimize-icon";
    
    private Window w;

    public MinimizeIcon(final Window w) {
        
        this.w = w;

        getStyleClass().setAll(DEFAULT_STYLE_CLASS);

        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {

               w.setMinimized(!w.isMinimized());
               
               if (w.isSelected()) {
                   minimizeSelectedWindows();
               }
            }
        });
    }
    
    // TODO move from skin to behavior class (a lot of other stuff here too)
    private void minimizeSelectedWindows() {
        for (SelectableNode sN : WindowUtil.
                getDefaultClipboard().getSelectedItems()) {

            if (sN == w
                    || !(sN instanceof Window)) {
                continue;
            }

            Window selectedWindow = (Window) sN;

            if (w.getParent().
                    equals(selectedWindow.getParent())) {
                
                selectedWindow.setMinimized(!selectedWindow.isMinimized());
            }
        } // end for sN
    }
}
