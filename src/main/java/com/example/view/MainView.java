package com.example.view;

import com.example.MyUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class MainView extends VerticalLayout  {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public MainView(MyUI ui) {
     this.mainViewBuilder();
    }
    
    private void mainViewBuilder() {
      final TextField field = new TextField();
      final TextField field2 = new TextField();
      field.setCaption("vField");
      field2.setCaption("hField");

      Button button = new Button("vClick");
      Button button2 = new Button("hClick2");

      button.addClickListener(e -> Notification.show("Hallo lambda"));

      HorizontalLayout hlayout = new HorizontalLayout();
      hlayout.setMargin(true);
      hlayout.addComponents(field2, button2);
      hlayout.setComponentAlignment(button2, Alignment.BOTTOM_RIGHT);

      addComponents(hlayout);
    }
}
