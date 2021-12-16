package tb;

import jira.MyJiraClient;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class BotStarter {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        MyJiraClient myJiraClient = new MyJiraClient(Constants.username, Constants.password,Constants.url);
        try
        {
            telegramBotsApi.registerBot(new Bot());
        }catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
