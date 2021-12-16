package jira;

import com.atlassian.jira.rest.client.api.domain.Issue;
import net.rcarz.jiraclient.*;

public class Jira {



    public static void main(String[] args) throws JiraException {
        String url = "https://jira.crpt.ru";
        String username = "k.dzhigante";
        String password = "450125Ad*-";
        String issueKey ="OPS-27074";

        MyJiraClient myJiraClient = new MyJiraClient(username, password,url);
//        Issue issue = myJiraClient.getIssue(issueKey);
//        System.out.println(issue.getDescription());
//        if (myJiraClient.checkCritical()==true) {
//            System.out.println("Есть задачи");
//        } else {
//            System.out.println("Нет задач");
//        }







    }

}
