
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.List;

//import net.htmlparser.jericho.*;


public class Main extends Application {

    Stage window;
    Scene scene;
    static List<ChanThread> threads;
    static List<ChanReply> replies;
    //static List<String> threadCom;
    final static String url = "https://a.4cdn.org/g/catalog.json";
    final static String repliesURL = "https://a.4cdn.org/g/thread/55996728.json";

    public static void main (String[] args) {

        if(ReadJSONFile.readJSONThreads(url))
        {
            threads = ReadJSONFile.getThreads();
        }
        else
        {
            threads = null;
        }

        if(ReadJSONFile.readJSONReplies(repliesURL))
            replies = ReadJSONFile.getReplyList();
        else
            replies = null;



        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception
    {
        window = stage;
        window.setTitle("4Chan");

        //StackPane layout = new StackPane();
        BorderPane layout = new BorderPane();

        final ListView<ChanThread> threadListView = new ListView<ChanThread>();
        final ListView<ChanReply> replyListView = new ListView<ChanReply>();

        //populate threadListView
        threadListView.setCellFactory(new Callback<ListView<ChanThread>, ListCell<ChanThread>>() {
            @Override
            public ListCell<ChanThread> call(ListView<ChanThread> chanThreadListView) {
                return new CatalogCellFactory();
            }
        });

        //populate replyListView
        replyListView.setCellFactory(new Callback<ListView<ChanReply>, ListCell<ChanReply>>() {
            @Override
            public ListCell<ChanReply> call(ListView<ChanReply> chanReplyListView) {
                return new ReplyCellFactory();
            }
        });

        //add items to threadListView
        ObservableList<ChanThread> threadListItems = FXCollections.observableList(threads);
        threadListView.setItems(threadListItems);

        //add items to replyListView
        ObservableList<ChanReply> replyLisItems = FXCollections.observableList(replies);
        replyListView.setItems(replyLisItems);


        //position threadListView on left of window layout
        BorderPane.setAlignment(threadListView, Pos.TOP_LEFT);
        BorderPane.setMargin(threadListView, new Insets(12, 12, 12, 12));
        layout.setLeft(threadListView);

        //position replyListView on center of window layout
        BorderPane.setAlignment(replyListView, Pos.CENTER);
        BorderPane.setMargin(replyListView, new Insets(12, 12, 12, 12));
        layout.setCenter(replyListView);


        scene = new Scene(layout, 1400, 650);
        window.setScene(scene);
        window.show();
    }
}
