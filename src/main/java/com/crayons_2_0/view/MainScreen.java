package com.crayons_2_0.view;

import java.util.ResourceBundle;

import com.crayons_2_0.MyUI;
import com.crayons_2_0.controller.Menu;
import com.crayons_2_0.service.LanguageService;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

/**
 * Content of the UI when the user is logged in.
 * 
 * 
 */
@SpringUI
public class MainScreen extends HorizontalLayout {
    /**
     * 
     */
    private Menu menu;
    
    private ResourceBundle lang = LanguageService.getInstance().getRes();

    public MainScreen(MyUI ui) {

        setStyleName("main-screen");

        CssLayout viewContainer = new CssLayout();
        viewContainer.addStyleName("valo-content");
        viewContainer.setSizeFull();
        
        final Navigator navigator = new Navigator(ui, viewContainer);
        navigator.setErrorView(ErrorView.class);
        menu = new Menu(navigator);
        menu.addView(new AboutView(), AboutView.VIEW_NAME, lang.getString(AboutView.VIEW_NAME),
                FontAwesome.INFO_CIRCLE);
        menu.addView(new Authorlibrary(), Authorlibrary.VIEW_NAME, lang.getString(Authorlibrary.VIEW_NAME),
                FontAwesome.BOOK);
        menu.addView(new UserlibraryView(), UserlibraryView.VIEW_NAME, lang.getString(UserlibraryView.VIEW_NAME),
                FontAwesome.PENCIL);
        menu.addView(new Preferences(),Preferences.VIEW_NAME, lang.getString(Preferences.VIEW_NAME),
                FontAwesome.GEAR);
        
        // Adds Views to Navigator
        navigator.addView(CourseEditorView.VIEW_NAME, new CourseEditorView());
        //navigator.addView(UnitEditorView.VIEW_NAME, new UnitEditorView());
        navigator.addView(Uniteditor.VIEW_NAME, new Uniteditor());

        navigator.addViewChangeListener(viewChangeListener);
        addComponent(menu);
        addComponent(viewContainer);
        setExpandRatio(viewContainer, 1);
        setSizeFull();
        
    }

    // notify the view menu about view changes so that it can display which view
    // is currently active
    ViewChangeListener viewChangeListener = new ViewChangeListener() {

        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        @Override
        public boolean beforeViewChange(ViewChangeEvent event) {
            return true;
        }

        @Override
        public void afterViewChange(ViewChangeEvent event) {
            menu.setActiveView(event.getViewName());
        }

    };
}
