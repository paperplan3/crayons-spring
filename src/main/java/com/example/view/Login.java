package com.example.view;

import org.springframework.context.ApplicationContext;

import com.vaadin.annotations.Theme;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import com.example.*;
import com.example.controller.LoginFormListener;


public class Login extends VerticalLayout {

   
    private static final long serialVersionUID = 1L;
    private TextField username = new TextField("User:");
    private PasswordField password = new PasswordField("Password:");
    private Button loginButton = new Button("Login");

    public Login() {
        this.loginViewBuilder();
        LoginFormListener loginFormListener = getLoginFormListener();
        loginButton.addClickListener(loginFormListener);

    }

    public void loginViewBuilder() {
       
        username.setWidth("300px");
        username.setRequired(true);
        username.setInputPrompt("Your username");

        password.setWidth("300px");
        password.setRequired(true);
        password.setValue("");
        password.setNullRepresentation("");


        
        addComponents(username,password,loginButton);
        setMargin(true);
        setSpacing(true);
    }

    public LoginFormListener getLoginFormListener() {
        MyUI ui = (MyUI) UI.getCurrent();
        ApplicationContext context = ui.getApplicationContext();
        return context.getBean(LoginFormListener.class);
    }

    public TextField getTxtLogin() {
        return username;
    }

    public PasswordField getTxtPassword() {
        return password;
    }

}
