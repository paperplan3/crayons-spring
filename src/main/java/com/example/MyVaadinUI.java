package com.example;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.example.view.MainView;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.WrappedHttpSession;
import com.vaadin.server.WrappedSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

@SpringUI
@Theme("valo")
public class MyVaadinUI extends UI {



  private ApplicationContext context;

  @Override
  protected void init(VaadinRequest request) {
    
   
 
      getPage().setTitle("Demo");
      
      
      setContent( new MainView());

  }

  private UserService getUserService(VaadinRequest request) {
    WrappedSession session = request.getWrappedSession();
    HttpSession httpSession = ((WrappedHttpSession) session).getHttpSession();
    ServletContext servletContext = httpSession.getServletContext();
    context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

    return (UserService) context.getBean("userService");
  }
}
