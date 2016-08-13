import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by raul on 6/20/2016.
 *
 * Store information of a 4chan thread and "all" its properties.
 */
public class ChanThread {

    private long threadID;
    private String comment;
    private String subject;

    public ChanThread(long threadID, String com)
    {
        this.threadID = threadID;
        this.comment = com;
        this.subject = "";
    }

    public ChanThread(long threadID, String com, String sub)
    {
        this.threadID = threadID;
        this.comment = com;
        this.subject = sub;
    }

    public long getThreadID()
    {
        return threadID;
    }

    public StringProperty getThreadComment()
    {
        return new SimpleStringProperty(comment);
    }

    //return string property so that listview cells update
    public StringProperty getThreadSubject()
    {
        return new SimpleStringProperty(subject);
    }

}
