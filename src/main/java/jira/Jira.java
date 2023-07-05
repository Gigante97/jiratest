package jira;

import com.atlassian.jira.rest.client.api.domain.Issue;
import net.rcarz.jiraclient.*;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Jira {



    public static void main(String[] args) throws JiraException, ExecutionException, InterruptedException {
        String url = "";
        String username = "";
        String password = "";
        String issueKey ="GISMTOPS-949";

        Map<String,String> m1 = new HashMap();

        m1.put("GISMTOPS-949", "test");
        m1.put("GISMTOPS-777", "test1");

        MyJiraClient myJiraClient = new MyJiraClient(username, password,url);
        for (Map.Entry<String, String> pair : m1.entrySet()) {
            Issue issue = myJiraClient.getIssue(pair.getKey());
            myJiraClient.addComment(issue, pair.getValue());
        }
//        Issue issue = myJiraClient.getIssue(issueKey);
//        myJiraClient.addComment(issue,"test");








    }

}
