package jira;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Comment;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;
import org.apache.http.client.methods.HttpPut;

import java.net.URI;
import java.util.concurrent.ExecutionException;

public class MyJiraClient {

    private String username;
    private String password;
    private String jiraUrl;
    private JiraRestClient restClient;

    public MyJiraClient(String username, String password, String jiraUrl) {
        this.username = username;
        this.password = password;
        this.jiraUrl = jiraUrl;
        this.restClient = getJiraRestClient();
    }
    private JiraRestClient getJiraRestClient() {
        return new AsynchronousJiraRestClientFactory()
                .createWithBasicHttpAuthentication(getJiraUri(), this.username, this.password);
    }
    private URI getJiraUri() {
        return URI.create(this.jiraUrl);
    }
    public Issue getIssue(String issueKey) {
        return restClient.getIssueClient().getIssue(issueKey).claim();
    }

    public void addComment(Issue issue, String text) throws ExecutionException, InterruptedException {
         restClient.getIssueClient().addComment(issue.getCommentsUri(),Comment.valueOf(text));
        IssueInput issueInput = new IssueInputBuilder()
                .setAssigneeName("d.khindy")
                .build();
         restClient.getIssueClient().updateIssue(issue.getKey(), issueInput).get();
    }

    public String checkCritical() {
         int result =0;
            Promise<SearchResult> total = restClient.getSearchClient().searchJql("project = Operations.Support AND type = Incident AND created >= 2020-10-01 AND resolution = Unresolved AND assignee = EMPTY AND status not in (Blocked, Closed) AND \"assigned on group\" = OSIS_Tier3 ORDER BY summary ASC, id DESC, due DESC, assignee DESC, created DESC, priority DESC, cf[11609] ASC");
            for (Issue issue : total.claim().getIssues()) {
                result++;

            }
            if (result != 0) {
                System.out.println("Их количество " + result);
                return  "Всего " + result + " незакрытых задач";

            }

            return "Задач нет";

    }

    public String countIssues() {
         int result =0;
        Promise<SearchResult> total=  restClient.getSearchClient().searchJql("project = Operations.Support AND type = Incident AND created >= 2020-10-01 AND resolution = Unresolved AND assignee = EMPTY AND status not in (Blocked, Closed) AND \"assigned on group\" = OSIS_Tier3  ORDER BY summary ASC, id DESC, due DESC, assignee DESC, created DESC, priority DESC, cf[11609] ASC");
        for (Issue issue : total.claim().getIssues()){
            result++;

        }
        if (result!=0){
            System.out.println("Их количество "+result);

            return "Всего " + result + " незакрытых задач";
        }
        return "Задач нет";
    }

}
