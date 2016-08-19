import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

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

            String topLabelText = name + " " + time + " No. " + String.valueOf(id);

            Label topLabel = new Label(topLabelText);
            topLabel.setWrapText(true);

            Label centerLabel = new Label("\n " + comment);
            Text top = new Text();
            top.setText(topLabelText);

            TextArea center = new TextArea();
            center.setWrapText(true);
            center.setStyle("-fx-focus-color: transparent; -fx-text-box-border: transparent;");
            center.setEditable(false);
            center.setText(comment);

            //WebView webView = new WebView();
            //webView.getEngine().lofadContent(cellText);

            BorderPane cellLayout = new BorderPane();

            // add image in reply to left
            if(item.getImage() != null)
            {
                final ImageView iv = new ImageView();
                iv.setImage(item.getImage());
                iv.setFitWidth(100);
                iv.setPreserveRatio(true);

                //set imageView listener
                iv.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        //open new windows with image original size
                        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();

                        double sceneHeight;
                        double sceneWidth;
                        if(visualBounds.getHeight() < iv.getImage().getHeight())
                            sceneHeight = visualBounds.getHeight();
                        else
                            sceneHeight = iv.getImage().getHeight();

                        if(visualBounds.getWidth() < iv.getImage().getWidth())
                            sceneWidth = visualBounds.getWidth();
                        else
                            sceneWidth = iv.getImage().getWidth();

                        ImageView secondIV = new ImageView();
                        secondIV.setImage(iv.getImage());
                        secondIV.setFitWidth(sceneWidth);
                        secondIV.setFitHeight(sceneHeight);



                        StackPane stackPane = new StackPane();
                        stackPane.getChildren().addAll(secondIV);

                        Stage secondStage = new Stage();
                        secondStage.setTitle("Image");


                        secondStage.setScene(new Scene(stackPane, sceneWidth, sceneHeight));
                        secondStage.show();
                    }
                });

                cellLayout.setAlignment(iv, Pos.TOP_LEFT);
                cellLayout.setLeft(iv);
            }

            // add topLabel to layout
            cellLayout.setAlignment(top, Pos.TOP_LEFT);
            cellLayout.setTop(top);

            // add centerLabel to layout
            cellLayout.setAlignment(center, Pos.TOP_LEFT);
            cellLayout.setCenter(center);


            setGraphic(cellLayout);

        }
        else
            setText("");

    }
}
