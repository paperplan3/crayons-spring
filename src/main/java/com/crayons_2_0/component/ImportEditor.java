package com.crayons_2_0.component;

import java.util.ResourceBundle;

import com.crayons_2_0.service.LanguageControl;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class ImportEditor implements Button.ClickListener {
    ResourceBundle lang = LanguageControl.getInstance().getRes();

    Button export = new Button(lang.getString("Export"));
   
    @Override
    public void buttonClick(ClickEvent event) {      
        for (Window window: UI.getCurrent().getWindows()) window.close();
        Notification success = new Notification(
                "file is exported successfully");
        success.setDelayMsec(2000);
        success.setStyleName("bar success small");
        success.setPosition(Position.BOTTOM_CENTER);
        success.show(Page.getCurrent());
    }
}
