import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

/**
 * Created by raul on 8/9/2016.
 */
public class ReplyCellFactory extends ListCell<ChanReply> {

    public ReplyCellFactory() {}

    @Override
    public void updateItem(ChanReply item, boolean empty)
    {
        super.updateItem(item, empty);
        super.setPrefHeight(200);
        super.setPrefWidth(500);

        if(item != null)
        {
            String name = item.getName();
            String time = item.getNow();
            long id = item.getCommendID();
            String comment = item.getComment();

            String cellText = name + " " + time + " No. " + String.valueOf(id) + "\n"
                            + comment;

            Label label = new Label(cellText);
            label.setWrapText(true);

            setGraphic(label);

        }
        else
            setText("");

    }
}
