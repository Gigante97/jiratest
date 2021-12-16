package tb;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import jira.MyJiraClient;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class BotController {
    private static final Logger LOGGER = Logger.getLogger(BotController.class);

        public static SendMessage doSomeAction(Message message) {
            MyJiraClient myJiraClient = new MyJiraClient(Constants.username, Constants.password,Constants.url);
            String messageText =message.getText();
            switch (messageText){
                case "/start":
                    return createAnswer(message,myJiraClient.countIssues());
                case "/run":
                        int min =1;
//                    Timer timer = new Timer();
//                    timer.schedule(new TimerTask() {
//                        @Override
//                        public void run() {
                          return   answer(message,myJiraClient.checkCritical());
//                        }
//                    },0,1000*60*min);
                default:
                    return answer(message,"Здравствуйте");
            }

        }


    private static SendMessage createAnswer(Message message, String answerMsg)
    {

            return answer(message, answerMsg);

//
    }

//    private static Boolean isAccessibleToUser(Integer id)
//    {
//        String fromFile = readFromFile(id);
//        return fromFile != null;
//    }

    private static SendMessage answer(Message message, String text)
    {
        SendMessage sMessage = new SendMessage();
        sMessage.setChatId(message.getChatId());

        sMessage.setText(text);
        return sMessage;
    }

//    private static String readFromFile(Integer userid)
//    {
//        Properties jdbcProp = new Properties();
//        String current = null;
//        try
//        {
//            FileInputStream inputStream = new FileInputStream(System.getenv("CATALINA_HOME") + "/" + "webapps" + "/" + "bot_users.properties");
//            jdbcProp.load(inputStream);
//            current = jdbcProp.getProperty(userid.toString());
//            inputStream.close();
//        }
//        catch ( IOException e )
//        {
//            LOGGER.error(e);
//        }
//        return current;
//    }
    private static SendMessage errorAnswer(Message message)
    {
        SendMessage sMessage = new SendMessage();
        sMessage.setChatId(message.getChatId());
        sMessage.setText("Constants.ERROR_MSG");
        return sMessage;
    }



}
