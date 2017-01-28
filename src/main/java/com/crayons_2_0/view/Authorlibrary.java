package com.crayons_2_0.view;

import java.util.ResourceBundle;

import com.crayons_2_0.component.UnitEditor;
import com.crayons_2_0.component.UnitEditor.CourseEditorListener;
import com.crayons_2_0.component.UnitEditor;
import com.crayons_2_0.mockup.autorenbereich;
import com.crayons_2_0.service.LanguageService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
public class Authorlibrary extends VerticalLayout implements View, CourseEditorListener{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final String VIEW_NAME = "Authorlibrary";
    ResourceBundle lang = LanguageService.getInstance().getRes();

    public Authorlibrary() {
        //aboutContent.setStyleName("about-content");

        // you can add Vaadin components in predefined slots in the custom
        // layout

        setSizeFull();
        setStyleName("about-view");
        setSpacing(true);
        setMargin(true);

       // addComponent(aboutContent);
        //setComponentAlignment(aboutContent, Alignment.MIDDLE_CENTER);
        autorenbereich a = new autorenbereich();
        addComponent(a);
        
        setExpandRatio(a, 1f);
        addComponent(buildFooter());
    }
  
    private Component buildFooter() {
        HorizontalLayout footer = new HorizontalLayout();
        footer.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
        footer.setWidth(100.0f, Unit.PERCENTAGE);
        
        
        //TODO: Perstenz Kursentitäten usw....
        //Uniteditor courseEditor = new Uniteditor(this);
        
        Button CreateCourse = new Button(lang.getString("CreateNewCourse"));
        CreateCourse.addStyleName(ValoTheme.BUTTON_PRIMARY);
        CreateCourse.addClickListener(new ClickListener() {
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                
                //get courseeditor listener from db
                // Add it to the root component
                UI.getCurrent().addWindow(new UnitEditor());
                
                
                // fieldGroup.commit();
                // Updated user should also be persisted to database. But
                // not in this demo.

               
                // DashboardEventBus.post(new ProfileUpdatedEvent());

                /*
                 * try { //fieldGroup.commit(); // Updated user should also be
                 * persisted to database. But // not in this demo.
                 * 
                 * Notification success = new Notification(
                 * "Profile updated successfully"); success.setDelayMsec(2000);
                 * success.setStyleName("bar success small");
                 * success.setPosition(Position.BOTTOM_CENTER);
                 * success.show(Page.getCurrent());
                 * 
                 * //DashboardEventBus.post(new ProfileUpdatedEvent()); close();
                 * } catch (CommitException e) {
                 * Notification.show("Error while updating profile",
                 * Type.ERROR_MESSAGE); }
                 */

            }
        });
        CreateCourse.focus();
        footer.addComponent(CreateCourse);
        footer.setSpacing(true);
        footer.setComponentAlignment(CreateCourse, Alignment.TOP_CENTER);
        
        return footer;
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }


    @Override
    public void titleChanged(String newTitle, UnitEditor editor) {
        // TODO Auto-generated method stub
        
    }  
}
