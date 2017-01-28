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

//@SpringUI
@Theme("valo")
public class Login extends VerticalLayout {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private TextField user = new TextField("User:");
    private PasswordField password = new PasswordField("Password:");
    private Button loginButton = new Button("Login");

    public Login() {

        this.loginViewBuilder();

    }

    public void loginViewBuilder() {

        user.setWidth("300px");
        user.setRequired(true);
        user.setInputPrompt("Your username");

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

        LoginFormListener loginFormListener = getLoginFormListener();

        loginButton.addClickListener(loginFormListener);

        addComponents(uiLayout);
        setMargin(true);
        setSpacing(true);
    }

    public LoginFormListener getLoginFormListener() {
        MyUI ui = (MyUI) UI.getCurrent();
        ApplicationContext context = ui.getApplicationContext();
        return context.getBean(LoginFormListener.class);
    }

    public TextField getTxtLogin() {
        return user;
    }

    public PasswordField getTxtPassword() {
        return password;
    }

}
