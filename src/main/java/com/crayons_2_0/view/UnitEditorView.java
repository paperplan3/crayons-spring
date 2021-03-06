package com.crayons_2_0.view;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.ResourceBundle;

import com.crayons_2_0.component.UnitEditor;
import com.crayons_2_0.controller.UnitEditor2;
import com.crayons_2_0.service.LanguageService;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.event.Transferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.DropTarget;
import com.vaadin.event.dd.TargetDetails;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.dd.VerticalDropLocation;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.DragAndDropWrapper.DragStartMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class UnitEditorView extends VerticalLayout implements View {
	
	public static final String VIEW_NAME = "Unit Editor LevinNeu";   // Todo: Change Name!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	private final SortableLayout canvas;
    ResourceBundle lang = LanguageService.getInstance().getRes();
    
    private UnitEditor2 unitEditor;

	
	public UnitEditorView() {
		//this.listener = (CourseEditorListener) clickListener;
        setSizeFull();
        addStyleName("editor");
        addStyleName(ValoTheme.DRAG_AND_DROP_WRAPPER_NO_HORIZONTAL_DRAG_HINTS);
        /*
        setModal(true);
        setCloseShortcut(KeyCode.ESCAPE, null);
        setResizable(false);
        setClosable(false);
        */
        setHeight(90.0f, Unit.PERCENTAGE);
        
        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        
        this.unitEditor = new UnitEditor2();
        
        Component selectUnit = buildSelectUnit();
        content.addComponent(selectUnit);
        
        Component palette = buildPalette();
        content.addComponent(palette);
        content.setComponentAlignment(palette, Alignment.TOP_CENTER);

        canvas = new SortableLayout();
        canvas.setWidth(100.0f, Unit.PERCENTAGE);
        canvas.addStyleName("canvas");
        content.addComponent(canvas);
        content.setExpandRatio(canvas, 1);
        addComponent(content);
        content.addComponent(buildFooter());
	}
	
	private Component buildSelectUnit() {
		// Link: https://demo.vaadin.com/sampler/#ui/data-input/multiple-value/combo-box
		HorizontalLayout layout = new HorizontalLayout();
		
		ComboBox comboBox = new ComboBox("Select your Unit");
		comboBox.setInputPrompt(unitEditor.getCurrentUnit().getTitle());
		
		for (com.crayons_2_0.component.Unit tmpUnit : unitEditor.getCourse().getUnits2()) {
			comboBox.addItem(tmpUnit.getTitle());
		}
		//TODO REACTION
		layout.addComponent(comboBox);
		return layout;
	}

	public void setTitle(final String title) {
        canvas.setTitle(title);
    }

    private Component buildPalette() {
        HorizontalLayout paletteLayout = new HorizontalLayout();
        paletteLayout.setSpacing(true);
        paletteLayout.setWidthUndefined();
        paletteLayout.addStyleName("palette");

        paletteLayout.addComponent(buildPaletteItem(PaletteItemType.TEXT));
        paletteLayout.addComponent(buildPaletteItem(PaletteItemType.MULTIPLECHOICE));
        paletteLayout.addComponent(buildPaletteItem(PaletteItemType.IMAGE));

        paletteLayout.addLayoutClickListener(new LayoutClickListener() {
            @Override
            public void layoutClick(final LayoutClickEvent event) {
                if (event.getChildComponent() != null) {
                    PaletteItemType data = (PaletteItemType) ((DragAndDropWrapper) event
                            .getChildComponent()).getData();
                    addWidget(data, null);
                }
            }
        });

        return paletteLayout;
    }
    
    private Component buildFooter() {
        HorizontalLayout footer = new HorizontalLayout();
        footer.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
        footer.setWidth(100.0f, Unit.PERCENTAGE);

        Button ok = new Button(lang.getString("Save"));
        ok.addStyleName(ValoTheme.BUTTON_PRIMARY);
        ok.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                //close();
                Notification success = new Notification(
                        lang.getString("CourseSavedSuccessfully"));
                success.setDelayMsec(2000);
                success.setStyleName("bar success small");
                success.setPosition(Position.BOTTOM_CENTER);
                success.show(Page.getCurrent());

            }
        });
        ok.focus();
        footer.addComponent(ok);
        footer.setComponentAlignment(ok, Alignment.TOP_CENTER);
        return footer;
    }

    private Component buildPaletteItem(final PaletteItemType type) {
        Label caption = new Label(type.getIcon().getHtml() + type.getTitle(),
                ContentMode.HTML);
        caption.setSizeUndefined();

        DragAndDropWrapper ddWrap = new DragAndDropWrapper(caption);
        ddWrap.setSizeUndefined();
        ddWrap.setDragStartMode(DragStartMode.WRAPPER);
        ddWrap.setData(type);
        return ddWrap;
    }

    public void addWidget(final PaletteItemType paletteItemType,
            final Object prefillData) {
        canvas.addComponent(paletteItemType, prefillData);
    }

    public final class SortableLayout extends CustomComponent {

        private VerticalLayout layout;
        private final DropHandler dropHandler;
        private TextField titleLabel;
        private DragAndDropWrapper placeholder;

        public SortableLayout() {
            layout = new VerticalLayout();
            setCompositionRoot(layout);
            layout.addStyleName("canvas-layout");

            titleLabel = new TextField();
            titleLabel.addStyleName("title");
            SimpleDateFormat df = new SimpleDateFormat();
            df.applyPattern("M/dd/yyyy");

            titleLabel.addValueChangeListener(new ValueChangeListener() {
                @Override
                public void valueChange(final ValueChangeEvent event) {
                    String t = titleLabel.getValue();
                    if (t == null || t.equals("")) {
                        t = " ";
                    }
                    //
                    //listener.titleChanged(t, UnitEditor.this);
                }
            });
            layout.addComponent(titleLabel);

            dropHandler = new ReorderLayoutDropHandler();

            Label l = new Label(lang.getString("DragItemsHere"));
            l.setSizeUndefined();

            placeholder = new DragAndDropWrapper(l);
            placeholder.addStyleName("placeholder");
            placeholder.setDropHandler(new DropHandler() {

                @Override
                public AcceptCriterion getAcceptCriterion() {
                    return AcceptAll.get();
                }

                @Override
                public void drop(final DragAndDropEvent event) {
                    Transferable transferable = event.getTransferable();
                    Component sourceComponent = transferable
                            .getSourceComponent();

                    if (sourceComponent != layout.getParent()) {
                        Object type = ((AbstractComponent) sourceComponent)
                                .getData();
                        addComponent((PaletteItemType) type, null);
                    }
                }
            });
            layout.addComponent(placeholder);
        }

        public void setTitle(final String title) {
            titleLabel.setValue(title);
        }

        public void addComponent(final PaletteItemType paletteItemType,
                final Object prefillData) {
            if (placeholder.getParent() != null) {
                layout.removeComponent(placeholder);
            }
            
            layout.addComponent(
                    new WrappedComponent(createComponentFromPaletteItem(
                            paletteItemType, prefillData)), 1);
                            
        }
        
        private Component createComponentFromPaletteItem(
                final PaletteItemType type, final Object prefillData) {
            Component result = null;
            if (type == PaletteItemType.TEXT) {
                /*result = new InlineTextEditor(
                        prefillData != null ? String.valueOf(prefillData)
                                : null);*/
            } else if (type == PaletteItemType.IMAGE) {
                //result = new TopTenMoviesTable();
            } else if (type == PaletteItemType.MULTIPLECHOICE) {
                //result = new TopSixTheatersChart();
            } else if (type == PaletteItemType.TRANSACTIONS) {
                //result = new TransactionsListing(
                       // (Collection<Transaction>) prefillData);
            }
    
            return result;
        }
       

        private class WrappedComponent extends DragAndDropWrapper {

            public WrappedComponent(final Component content) {
                super(content);
                setDragStartMode(DragStartMode.WRAPPER);
            }

            @Override
            public DropHandler getDropHandler() {
                return dropHandler;
            }

        }

        private class ReorderLayoutDropHandler implements DropHandler {

            @Override
            public AcceptCriterion getAcceptCriterion() {
                 //return new SourceIs()
                return AcceptAll.get();
            }

            @Override
            public void drop(final DragAndDropEvent dropEvent) {
                Transferable transferable = dropEvent.getTransferable();
                Component sourceComponent = transferable.getSourceComponent();

                TargetDetails dropTargetData = dropEvent.getTargetDetails();
                DropTarget target = dropTargetData.getTarget();

                if (sourceComponent.getParent() != layout) {
                    Object paletteItemType = ((AbstractComponent) sourceComponent)
                            .getData();
                   
                    AbstractComponent c = new WrappedComponent(
                            createComponentFromPaletteItem(
                                    (PaletteItemType) paletteItemType, null));
                                    
                    int index = 0;
                    Iterator<Component> componentIterator = layout.iterator();
                    Component next = null;
                    while (next != target && componentIterator.hasNext()) {
                        next = componentIterator.next();
                        if (next != sourceComponent) {
                            index++;
                        }
                    }

                    if (dropTargetData.getData("verticalLocation").equals(
                            VerticalDropLocation.TOP.toString())) {
                        index--;
                        if (index <= 0) {
                            index = 1;
                        }
                    }

                    layout.addComponent(c, index);
                }

                if (sourceComponent instanceof WrappedComponent) {
                    // find the location where to move the dragged component
                    boolean sourceWasAfterTarget = true;
                    int index = 0;
                    Iterator<Component> componentIterator = layout.iterator();
                    Component next = null;
                    while (next != target && componentIterator.hasNext()) {
                        next = componentIterator.next();
                        if (next != sourceComponent) {
                            index++;
                        } else {
                            sourceWasAfterTarget = false;
                        }
                    }
                    if (next == null || next != target) {
                        // component not found - if dragging from another layout
                        return;
                    }

                    // drop on top of target?
                    if (dropTargetData.getData("verticalLocation").equals(
                            VerticalDropLocation.MIDDLE.toString())) {
                        if (sourceWasAfterTarget) {
                            index--;
                        }
                    }

                    // drop before the target?
                    else if (dropTargetData.getData("verticalLocation").equals(
                            VerticalDropLocation.TOP.toString())) {
                        index--;
                        if (index <= 0) {
                            index = 1;
                        }
                    }

                    // move component within the layout
                    layout.removeComponent(sourceComponent);
                    layout.addComponent(sourceComponent, index);
                }
            }
        }

    }

    public interface CourseEditorListener {
        void titleChanged(String newTitle, UnitEditor editor);
    }

    
    // TODO Change Language!
    
    public enum PaletteItemType {
        TEXT("Text Block", FontAwesome.FONT), MULTIPLECHOICE("MultipleChoice",
                FontAwesome.CHECK_SQUARE_O), IMAGE("Image",
                FontAwesome.IMAGE), TRANSACTIONS("Latest transactions",
                null);

        private final String title;
        private final FontAwesome icon;

        PaletteItemType(final String title, final FontAwesome icon) {
            this.title = title;
            this.icon = icon;
        }

        public String getTitle() {
            return title;
        }

        public FontAwesome getIcon() {
            return icon;
        }

    }
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
