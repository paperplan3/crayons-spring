package com.crayons_2_0.view;

import java.util.ResourceBundle;

import com.crayons_2_0.component.UnitEditor;
import com.crayons_2_0.controller.UserBibManager;
import com.crayons_2_0.mockup.Benutzeransicht;
import com.crayons_2_0.service.LanguageService;
import com.crayons_2_0.service.database.CourseService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.Version;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;
@SpringUI
public class UserlibraryView extends VerticalLayout implements View {

	
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final String VIEW_NAME = "Userlibrary";
    ResourceBundle lang = LanguageService.getInstance().getRes();
    
    public UserlibraryView() {
        VerticalLayout content = new VerticalLayout();

        setSizeFull();
        setSpacing(true);
        setMargin(true);

        addComponent(content);
        content.addComponent(buildTitle());
        content.addComponent(buildCoursesTabSheet());
    }
    
    private Component buildTitle() {
        Label title = new Label("Kursübersicht");
        title.addStyleName(ValoTheme.LABEL_H2);
        return title;
    }
    
    private Component buildCoursesTabSheet() {
        TabSheet coursesTabSheet = new TabSheet();
        coursesTabSheet.setSizeFull();
        coursesTabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        coursesTabSheet.addStyleName(ValoTheme.TABSHEET_CENTERED_TABS);
        coursesTabSheet.addComponent(buildCourseTab("Lineare Algebra"));
        return coursesTabSheet;
    }
  
    //TODO: Kurs aus Datenbank übergeben 
    private Component buildCourseTab(String title) {
        VerticalLayout tabContent = new VerticalLayout();
        tabContent.setCaption(title);
        tabContent.setSpacing(true);
        tabContent.setMargin(true);
        
        tabContent.addComponent(getCourseInformation());
        
        return tabContent;
    }
    
    private Component getCourseInformation() {
        VerticalLayout courseInformation = new VerticalLayout();
        
        TextArea courseDescription = new TextArea("Kursbeschreibung");
        courseDescription.setValue("Kursbeschreibung");
        courseDescription.setSizeFull();
        courseInformation.addComponent(courseDescription);
        
        Table testResults = new Table("Testergebnisse");
        testResults.addContainerProperty("Test", String.class, null);
        testResults.addContainerProperty("Score", Integer.class, 0);
        testResults.addItem(new Object[]{"Lektion 1", 100}, 1);
        testResults.addItem(new Object[]{"Lektion 2", 70}, 2);
        testResults.setSizeFull();
        testResults.setPageLength(testResults.size());
        courseInformation.addComponent(testResults);
        
        ProgressBar learningProgressBar = new ProgressBar(0.0f);
        learningProgressBar.setValue(0.3f);
        learningProgressBar.setSizeFull();
        courseInformation.addComponents(learningProgressBar);
        learningProgressBar.setCaption("Lernfortschritt");
        
        Component controlButtons = buildControlButtons();
        courseInformation.addComponent(controlButtons);
        courseInformation.setComponentAlignment(controlButtons, Alignment.BOTTOM_CENTER);
        
        return courseInformation;
    }
    
    private Component buildControlButtons() {
        HorizontalLayout controlButtons = new HorizontalLayout();
        controlButtons.setMargin(true);
        controlButtons.setSpacing(true);
        
        Button leaveCourse = new Button("Verlassen");
        controlButtons.addComponent(leaveCourse);
        controlButtons.setComponentAlignment(leaveCourse, Alignment.MIDDLE_LEFT);
        
        Button study = new Button("Lernen");
        controlButtons.addComponent(study);
        controlButtons.setComponentAlignment(study, Alignment.MIDDLE_RIGHT);
        
        /*graphEditor.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                UI.getCurrent().getNavigator().navigateTo(CourseEditorView.VIEW_NAME);
            }
        });*/
        
        return controlButtons;
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }  
}
