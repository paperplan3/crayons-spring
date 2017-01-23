package com.crayons_2_0.view;

import java.awt.List;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.springframework.security.core.userdetails.User;

import com.crayons.view.dagred3.Dagre;
import com.crayons.view.jointjs.GraphTry;
import com.crayons_2_0.model.MultipleChoice;
import com.crayons_2_0.service.Language;
import com.crayons_2_0.service.LanguageService;
import com.crayons_2_0.service.database.UserService;
import com.hs18.vaadin.addon.graph.GraphJSComponent;
import com.hs18.vaadin.addon.graph.listener.GraphJsLeftClickListener;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.Version;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;

@SpringUI
public class AboutView extends VerticalLayout implements View {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final String VIEW_NAME = "About";

    ResourceBundle lang = LanguageService.getInstance().getRes();
    private TextField txtUserLabel;

    public AboutView() {
        final Dagre graph = new Dagre();
        addComponent(graph);
        VerticalLayout aboutContent = new VerticalLayout();
        Label sz = new Label("");
        sz.setWidth( null );
        sz.setHeight ( "800px" );
        addComponent(sz);
        aboutContent.addComponent(sz);
       //aboutContent.setStyleName("about-content");

        // you can add Vaadin components in predefined slots in the custom
        // layout
        aboutContent.addComponent(buildGraph());
        aboutContent.addComponent(new MultipleChoice());
        aboutContent.addComponent(new Label(
                FontAwesome.INFO_CIRCLE.getHtml() + " This application is using Vaadin " + Version.getFullVersion(),
                ContentMode.HTML));

        setSizeFull();
        setStyleName("about-view");
        addComponent(aboutContent);
        setComponentAlignment(aboutContent, Alignment.BOTTOM_CENTER);
        TwinColSelect sample = new TwinColSelect();
        for (int i = 0; i < 6; i++) {
            sample.addItem(i);
            sample.setItemCaption(i, "Option " + i);
        }
        sample.setRows(6);
        sample.setNullSelectionAllowed(true);
        sample.setMultiSelect(true);
        sample.setImmediate(true);
        sample.setLeftColumnCaption("Available options");
        sample.setRightColumnCaption("Selected options");

        sample.addValueChangeListener(e -> Notification.show("Value changed:"));
        aboutContent.addComponent(sample);

        // NEW-DATENBANK----TEST--------------------------------------------------------------------
        /*
         * Button testDB = new Button("Teste Datenbank");
         * testDB.addClickListener(new ClickListener() {
         * 
         * @Override public void buttonClick(ClickEvent event) {
         * 
         * try { Statement statement =
         * JDBCConnection.getInstance().getStatement(); ResultSet set =
         * statement.executeQuery("SELECT * FROM realm.user");
         * 
         * while (set.next()) { Label levin = new Label(set.getString(1));
         * aboutContent.addComponent(levin); } } catch (DatabaseException |
         * SQLException e1) { e1.printStackTrace(); } Label levin = new
         * Label("Hier müssten die Namen erscheinen");
         * aboutContent.addComponent(levin);
         * 
         * } }); aboutContent.addComponent(testDB);
         */
        // -------- DATENBANK NEU
        // ----------------------------------------------------------------------

        TextField txtUserLabel = new TextField("User label: ");
        aboutContent.addComponent(txtUserLabel);

        Button btnAddNewUser = new Button("Add New User");
        // btnAddNewUser.addClickListener(new AddNewUserListener());
        aboutContent.addComponent(btnAddNewUser);

        // DatenBank ganzNeu

        Button dbTest = new Button("Teste Datenbank");
        dbTest.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                UserService userservice = new UserService();
                for (User user : userservice.findAll()) {
                    aboutContent.addComponent(new Label(user.getUsername()));
                }

            }
        });
        aboutContent.addComponent(dbTest);

        // -------------------------------------------------------------------------------

        Button buttonGerman = new Button(lang.getString("German"));
        buttonGerman.setIcon(FontAwesome.BEER);
        buttonGerman.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                LanguageService.getInstance().setCurrentLocale(Language.German);
                Page.getCurrent().reload();
            }
        });
        aboutContent.addComponent(buttonGerman);

        Button buttonEnglish = new Button(lang.getString("English"));
        buttonEnglish.setIcon(FontAwesome.COFFEE);
        buttonEnglish.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                LanguageService.getInstance().setCurrentLocale(Language.English);
                Page.getCurrent().reload();
            }
        });
        aboutContent.addComponent(buttonEnglish);

        // ---------------------------------------------------------------------------------

        GraphJSComponent graphJSComponent = new GraphJSComponent();
        graphJSComponent.setNodesSize(120, 50);
        graphJSComponent.setLeftClickListener(new GraphJsLeftClickListener() {

            @Override
            public void onLeftClick(String id, String type, String parentId) {
                System.out.println(id + " " + type + " " + parentId);
            }
        });
        graphJSComponent.setImmediate(true);

        String lhtml = "<div id='graph' class='graph' ></div>";// add
                                                               // style='overflow:scroll'
                                                               // if required
        Label graphLabel = new Label(lhtml, Label.CONTENT_XHTML);

        aboutContent.addComponent(graphLabel);
        aboutContent.addComponent(graphJSComponent);

        try {
            graphJSComponent.addNode("fruits", "Fruits I Like", "level 1", null, null);// Give
                                                                                       // parent
                                                                                       // id
                                                                                       // as
                                                                                       // null
                                                                                       // for
                                                                                       // root
                                                                                       // node
            graphJSComponent.getNodeProperties("fruits").put("title", "Fruits I Like");
            graphJSComponent.addNode("watermelon", "Watermelon", "level 2", null, "fruits");// first
                                                                                            // child
                                                                                            // of
                                                                                            // node
                                                                                            // with
                                                                                            // id
                                                                                            // fruits
            graphJSComponent.getNodeProperties("watermelon").put("title", "Its a very juicy fruit.");
            graphJSComponent.addNode("mango", "Mango", "level 2", null, "fruits");// second
                                                                                  // child
                                                                                  // of
                                                                                  // node
                                                                                  // with
                                                                                  // id
                                                                                  // fruits
            graphJSComponent.getNodeProperties("mango").put("title", "Katrina Kaif's favourite.");
            graphJSComponent.addNode("apple", "Apple", "level 2", null, "fruits");// third
                                                                                  // child
                                                                                  // of
                                                                                  // node
                                                                                  // with
                                                                                  // id
                                                                                  // fruits
            graphJSComponent.getNodeProperties("apple").put("title", "One apple a day, keeps the doctor away");
            graphJSComponent.getNodeProperties("apple").put("fill", "#F00");
            graphJSComponent.getNodeProperties("mango").put("fill", "yellow");

            graphJSComponent.addNode("5", "Hapoos", "level 3", null, "mango");// child
                                                                              // of
                                                                              // mango
                                                                              // node
            graphJSComponent.getNodeProperties("5").put("title", "One of the best mangos");

            graphJSComponent.addNode("6", "Green", "level 3", null, "watermelon");// child
                                                                                  // of
                                                                                  // watermelon
                                                                                  // node
            graphJSComponent.getNodeProperties("6").put("title", "Green from outside, red inside");
            graphJSComponent.getNodeProperties("6").put("fill", "#0F0");

            // Another Tree in the same graph
            graphJSComponent.addNode("fruitsnotlike", "Fruits I Dont Like", "level 1", null, null);// Give
                                                                                                   // parent
                                                                                                   // id
                                                                                                   // as
                                                                                                   // null
            graphJSComponent.getNodeProperties("fruitsnotlike").put("title", "Another tree in the same graph");
            graphJSComponent.addNode("lichy", "Lichy", "level 2", null, "fruitsnotlike");// first
                                                                                         // child
                                                                                         // of
                                                                                         // node
                                                                                         // with
                                                                                         // id
                                                                                         // fruitsnotlike
            graphJSComponent.getNodeProperties("lichy").put("title", "because its nto easy to eat it.");
            graphJSComponent.getNodeProperties("lichy").put("opacity", "0.2");
            graphJSComponent.addNode("redlichy", "Red Lichy", "level 3", null, "lichy");
            graphJSComponent.getNodeProperties("redlichy").put("title", "red lichy");
            graphJSComponent.refresh();// Call refresh after you are done with
                                       // your changes
        } catch (Exception e) {
            e.printStackTrace();
        } //

        // ------------------

    }

    private Component buildGraph() {
        final VerticalLayout layout = new VerticalLayout();
        //Joint.js
        final GraphTry diagram = new GraphTry();
        //Dagred3
        final Dagre graph = new Dagre();
        

        
        // now we build the layout.
        layout.setSpacing(true);
            layout.addComponent(diagram);
    

    return layout;

    }

    private void configureIntegerField(final TextField integerField) {
        integerField.setConverter(Integer.class);
        integerField.addValidator(new IntegerRangeValidator("only integer, 0-500", 0, 500));
        integerField.setRequired(true);
        integerField.setImmediate(true);
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }

    public TextField getTxtUserLabel() {
        return txtUserLabel;
    }
}
