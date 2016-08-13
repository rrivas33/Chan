import java.util.ArrayList;
import java.util.List;

/**
 * Created by raul on 8/8/2016.
 *
 * Stores information about a reply in a ChantThread.
 */
public class ChanReply {

    private long commentID; //number of comment
    private String comment;
    private String name;    //name of replier
    private String now;     //time of reply
    private List<Long> references = new ArrayList<>();


    public ChanReply(long id, String com, String replier, String time)
    {
        commentID = id;
        comment = com;
        name = replier;
        now = time;
    }

    public void addReference(long ref)
    {
        references.add(ref);
    }

    public long getCommendID()
    {
        return commentID;
    }

    public String getComment()
    {
        return comment;
    }

    public List<Long> getReferences()
    {
        return references;
    }

    public String getName()
    {
        return name;
    }

    public String getNow()
    {
        return now;
    }

}
