import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;

/**
 * Created by raul on 8/8/2016.
 *
 * Class that creates a ListView
 * When a list request a cell, this class automatically calls updateItem.
 */
public class CatalogCellFactory extends ListCell<ChanThread> {

    private static final int CELL_WIDTH = 300;
    private static final int CELL_HEIGHT = 200;

    public CatalogCellFactory() {}

    @Override
    protected void updateItem(ChanThread item, boolean empty)
    {

        super.updateItem(item, empty);
        super.setPrefHeight(CELL_HEIGHT);
        super.setPrefWidth(CELL_WIDTH);

        if(item != null)
        {
            //textProperty().bind(item.getThreadComment());
            long id = item.getThreadID();
            String subject = item.getThreadSubject().getValue();
            String comment = item.getThreadComment().getValue();
            //System.out.println(item.getThreadID());
            //System.out.println(item.getThreadSubject().getValue());
            //System.out.println(item.getThreadComment().getValue());
            //System.out.println();
            String cellText = String.valueOf(id) + "\n"
                            + subject
                            + comment;
            Label label = new Label(cellText);
            label.setWrapText(true);
            setGraphic(label);

        }
        else
            textProperty().bind(new SimpleStringProperty(""));

    }

    
}
