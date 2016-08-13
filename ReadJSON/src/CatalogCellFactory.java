import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

/**
 * Created by raul on 8/8/2016.
 *
 * Class that creates a ListView
 * When a list request a cell, this class automatically calls updateItem.
 */
public class CatalogCellFactory extends ListCell<ChanThread> {

    public CatalogCellFactory() {}

    @Override
    protected void updateItem(ChanThread item, boolean empty)
    {

        super.updateItem(item, empty);
        super.setPrefHeight(200);
        super.setPrefWidth(300);

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
