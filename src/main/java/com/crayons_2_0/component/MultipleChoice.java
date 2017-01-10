package com.crayons_2_0.component;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.VerticalLayout;

import ch.qos.logback.core.subst.Token.Type;

public class MultipleChoice extends VerticalLayout {
    
    
    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    //public MultipleChoice(MCListener) usw
    public MultipleChoice(){
        
    
    OptionGroup sample = new OptionGroup("Multiple Choice Example");
    for (int i = 0; i < 5; i++) {
        sample.addItem(i);
        sample.setItemCaption(i, "Option " + i);
    }
    sample.select(2);
    sample.setNullSelectionAllowed(false);
    sample.setHtmlContentAllowed(true);
    sample.setImmediate(true);


    final CheckBox disableOptionsCheckBox = new CheckBox(
            "Some options disabled");
    disableOptionsCheckBox.setImmediate(true);
    disableOptionsCheckBox
            .addValueChangeListener(new ValueChangeListener() {
                @Override
                public void valueChange(final ValueChangeEvent event) {
                    final boolean value = (Boolean) event.getProperty()
                            .getValue();
                    sample.setItemEnabled(1, !value);
                    sample.setItemEnabled(3, !value);
                }
            });


    final CheckBox htmlCaptionsCheckBox = new CheckBox(
            "Captions with HTML content");
    htmlCaptionsCheckBox.setImmediate(true);
    htmlCaptionsCheckBox.addValueChangeListener(new ValueChangeListener() {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        @Override
        public void valueChange(final ValueChangeEvent event) {
            for (int i = 0; i < 5; i++) {
                sample.setItemCaption(i, "Option " + i);
            }

            if ((Boolean) event.getProperty().getValue()) {
                sample.setItemCaption(1, "<b>" + sample.getItemCaption(1)
                        + "</b>");
                sample.setItemCaption(2, "<u>" + sample.getItemCaption(2)
                        + "</u>");
            }
        }
    });

    /*TODO 
    sample.addValueChangeListener(e -> Notification.show("Value changed:",
            String.valueOf(e.getProperty().getValue()),
          Type.class));
    */
    addComponent(sample);
    
   
}
}
