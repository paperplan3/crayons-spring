package com.example.view;

import java.util.List;

import com.ejt.vaadin.loginform.DefaultVerticalLoginForm;
import com.ejt.vaadin.loginform.LoginForm.LoginEvent;
import com.ejt.vaadin.loginform.LoginForm.LoginListener;
import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@SpringUI
@Theme("valo")
public class MainView extends VerticalLayout implements View {

    public MainView() {

        this.demoMainView();

    }

    public void demoMainView() {

        
//        final TextField field = new TextField();
//        final TextField field2 = new TextField();
//        field.setCaption("vField");
//        field2.setCaption("hField");
//
//        Button button = new Button("vClick");
//        Button button2 = new Button("hClick2");
//
//        button.addClickListener(e -> Notification.show("Hallo lambda"));
//
//        HorizontalLayout hlayout = new HorizontalLayout();
//        hlayout.setMargin(true);
//        hlayout.addComponents(field2, button2);
//        hlayout.setComponentAlignment(button2, Alignment.BOTTOM_RIGHT);
//
//        VerticalLayout vlayout = new VerticalLayout();
//        vlayout.addComponents(field, button);
//        vlayout.setMargin(true);
//        vlayout.setSpacing(true);
       
       
     

        TextField user;
        PasswordField password;
        Button loginButton = new Button("Login");
        loginButton.addClickListener(e -> Notification.show("Hallo"));
        
        user = new TextField("User:");
        user.setWidth("300px");
        user.setRequired(true);
        user.setInputPrompt("Your username");

        password = new PasswordField("Password:");
        password.setWidth("300px");
        password.setRequired(true);
        password.setValue("");
        password.setNullRepresentation("");

        VerticalLayout fields = new VerticalLayout(user, password, loginButton);
        fields.setCaption("Please login to access the application");
        fields.setSpacing(true);
        fields.setMargin(new MarginInfo(true, true, true, false));
        fields.setSizeUndefined();

        VerticalLayout uiLayout = new VerticalLayout(fields);
        uiLayout.setSizeFull();
        uiLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
        setStyleName(Reindeer.LAYOUT_BLUE);
      
        
        addComponents(uiLayout);
        setMargin(true);
        setSpacing(true);
    }

    

    @Override
    public void enter(ViewChangeEvent event) {
        Notification.show("Welcome to Demo");
    }

}
