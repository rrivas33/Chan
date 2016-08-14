/**
 * Created by raul on 8/13/2016.
 */
public class ChanURL {

    public static String getBoardURL(String board)
    {
        // http(s)://a.4cdn.org/board/catalog.json
        return "https://a.4cdn.org/" + board + "/catalog.json";
    }

    public static String getThreadURL(String board, long threadNumber)
    {
        // http(s)://a.4cdn.org/board/thread/threadnumber.json
        return "https://a.4cdn.org/" + board + "/thread/" + threadNumber + ".json";

    }

    public static String getImageURL(String board, long tim, String extension)
    {
        // http(s)://i.4cdn.org/board/tim.ext
        return "https://i.4cdn.org/" + board + "/" + tim + extension;
    }
}
