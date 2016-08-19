import javafx.scene.image.Image;
import org.apache.commons.lang3.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raul on 6/20/2016.
 */
public class ReadJSONFile {

    private static List<ChanThread> threadList = new ArrayList<>();
    private static List<ChanReply> replyList = new ArrayList<>();

    public static boolean readJSONReplies (String url)
    {
        JSONParser parser = new JSONParser();
        try
        {

            URL threadURL = new URL(url);
            Reader in = new InputStreamReader(threadURL.openStream());

            //flush replyList
            replyList.clear();

            JSONObject thread = (JSONObject) parser.parse(in);
            JSONArray posts = (JSONArray) thread.get("posts");
            for(int i = 0; i < posts.size(); i++)
            {
                JSONObject post = (JSONObject) posts.get(i);

                //reply contains image
                Image image = null;
                String ext = (String) post.get("ext");
                if(ext != null && !ext.equals(".webm"))
                {
                    System.out.print(ext);
                    String imgURL = ChanURL.getImageURL("g", (long) post.get("tim"), ext);
                    image = new Image(imgURL, true);
                }


                long id = (long) post.get("no");
                String commentHTML = (String) post.get("com");
                String comment = cleanTagPerservingLineBreaks(commentHTML);
                String posterName = (String) post.get("name");
                String timeOfPost = (String) post.get("now");

                ChanReply reply = new ChanReply(id, comment, posterName, timeOfPost);
                reply.setImage(image);
                replyList.add(reply);

            }

        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean readJSONThreads(String url)
    {
        JSONParser parser = new JSONParser();
        try
        {

            URL gCatalog = new URL(url);
            Reader in = new InputStreamReader(gCatalog.openStream());

            //flush threadList
            threadList.clear();

            JSONArray pages = (JSONArray) parser.parse(in);
            for(int i = 0; i < pages.size(); i++)
            {
                JSONObject page = (JSONObject) pages.get(i);
                JSONArray threads = (JSONArray) page.get("threads");

                for(int j = 0; j < threads.size(); j++)
                {
                    JSONObject threadObject = (JSONObject) threads.get(j);

                    ChanThread thread;

                    //check if thread has subject field
                    if(threadObject.get("sub") != null)
                    {
                        //thread id
                        long threadID = (Long) threadObject.get("no");

                        //thread comment
                        String htmlComment = (String) threadObject.get("com");
                        String comment = cleanTagPerservingLineBreaks(htmlComment);
                        comment = StringEscapeUtils.unescapeHtml4(comment);

                        //thread subject
                        String htmlSubject = (String) threadObject.get("sub");
                        String subject = StringEscapeUtils.unescapeHtml3(htmlSubject);
                        subject.toUpperCase();

                        //create new thread and add to list
                        thread = new ChanThread(threadID, comment, subject);
                        threadList.add(thread);


                    }
                    else
                    {
                        //thread id
                        long threadID = (Long) threadObject.get("no");

                        //thread comment
                        String htmlComment = (String) threadObject.get("com");
                        String comment = cleanTagPerservingLineBreaks(htmlComment);
                        comment = StringEscapeUtils.unescapeHtml4(comment);

                        //create new thread and add to list
                        thread = new ChanThread(threadID, comment);
                        threadList.add(thread);



                    }
//                    //System.out.println(thread.get("sub"));
//                    String htmlTitle = (String) threadObject.get("com");
//                    String title = cleanTagPerservingLineBreaks(htmlTitle);
//                    title = StringEscapeUtils.unescapeHtml3(title);
//                    System.out.println(title);
//                    //System.out.println(htmlTitle);

                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static List<ChanThread> getThreads()
    {
        return threadList;
    }

    public static List<ChanReply> getReplyList() {return replyList; }

    private static String cleanTagPerservingLineBreaks(String html) {
        String result = "";
        if (html == null)
            return html;
        Document document = Jsoup.parse(html);
        document.outputSettings(new Document.OutputSettings()
                .prettyPrint(false));// makes html() preserve linebreaks and
        // spacing
        document.select("br").append("\\n");
        document.select("p").prepend("");
        result = document.html().replaceAll("\\\\n", "\n");
        result = Jsoup.clean(result, "", Whitelist.none(),
                new Document.OutputSettings().prettyPrint(false));

        result = result.replaceAll("&gt;", ">");
        //replace "&gt;" with ">"
//        int pos;
//        while((pos = result.indexOf("&gt;")) != -1)
//        {
//            result.replaceAll("&gt;", ">");
//        }
        return result;
    }
}
