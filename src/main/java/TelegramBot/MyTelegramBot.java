package TelegramBot;


import org.checkerframework.checker.units.qual.C;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTelegramBot extends TelegramLongPollingBot {
    private Map<Long, TodoItem> todoItemStep = new HashMap<>();

    @Override
    public String getBotUsername() {
        return "beckend_bot";
    }

    @Override
    public String getBotToken() {
        return "5071713432:AAEMy61II8gSBDnoz0C3Jd80CGn6LFxxg9g";
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {


        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        String responceText = "";
        String responceText1 = "";
        System.out.println(update);
        System.out.println(message);
        if (message != null) {
            User user = message.getFrom();
            long chatId = user.getId();
            sendMessage.setChatId(user.getId().toString());
            String text = message.getText();
            responceText1 = text;
            System.out.println(text);
//        String [] commands = text.split("/");
//        String command = commands[1];
            Converter converter = new Converter();
            if (text.equalsIgnoreCase("/start")) {
                sendMessage.setText("<b>" + "<i>" + "<u>" + text + "</u>" + "</i>" + "</b>");
                sendMessage.setParseMode("HTML");
                responceText = "<b>" + "<i>" + "<u>" + "Welcome" + "</u>" + "</i>" + "</b> " + user.getFirstName() + "\n" + "<b>" + "<i>" + "Здравствуйте \n если вы хотите  просто красивые шрифты напишите  message \n    если хотите узнать многофункционалбность нашего бота то нажмите на   /menu  " + "</i>" + "</b> ";
                sendMessage.setReplyMarkup(startKeyboard());
            } else if (text.equalsIgnoreCase("/menu")) {
                sendMessage.setText("<b>" + "<i>" + text + "</i>" + "</b>");
                sendMessage.setParseMode("HTML");
                responceText = "\uD835\uDCDC\uD835\uDCEE\uD835\uDCF7\uD835\uDCFE\n ";
                sendMessage.setReplyMarkup(menuKeyboard());
            } else if (todoItemStep.containsKey(message.getChatId())) {
                execute(converter.CurrencyStart(text,chatId,message.getMessageId(),todoItemStep));
            }

            sendMessage.setText(responceText);


        } else if (update.hasCallbackQuery()) {
            // responceText="Пожалуйста напиши текст для шрифта ";
            // sendMessage.setText(responceText);
            User user = update.getCallbackQuery().getFrom();
            long chatId = user.getId();
            CallbackQuery callbackQuery = update.getCallbackQuery();
            message = callbackQuery.getMessage();
            String text1 = message.getText();
            String command = callbackQuery.getData();
            sendMessage.setChatId(user.getId().toString());
            if (command != null) {
                if (command.startsWith("/curency")) {
                    CurencyMenu curencyMenu = new CurencyMenu();
                    execute(curencyMenu.CurrencyStart(command, chatId));
                } else if (command.startsWith("/help")) {
                    String msg = """
                             <b><i> Здравствуйте 
                            Вы здесь можете узнать курс валют других стран 
                             Если вы что-то не поняли или незнаете как использовать бот перейдите по этосылке  [ YOUTUBE.com ]https://www.youtube.com/watch?v=kUxN6QfXfWc  ko'ring.
                            Или по указаной ниже сылки </i></b>""";
                    sendMessage.setText(msg);
                    sendMessage.setParseMode("HTML");
                    sendMessage.setChatId(String.valueOf(chatId));
                    execute(sendMessage);
                } else if (command.startsWith("/info")) {
                    String str = """
                            <b><i>  Вы можете узнать курс валют разных дркгих развитых городов 
                              /start  =>   вы можете вернуться в начало 
                              /menu   =>   страница меню  
                              /help  =>  вы можете узнать как использовать бот 
                              /info  =>   что может сделать этот бот </i></b>""";
                    sendMessage.setText(str);
                    sendMessage.setParseMode("HTML");
                    sendMessage.setChatId(String.valueOf(chatId));
                    execute(sendMessage);
                } else if (command.startsWith("/convert")) {
                    Converter converter=new Converter();
                    execute(converter.CurrencyStart(command,chatId,message.getMessageId(),todoItemStep));
                }
            }
        } else {
            sendMessage.setText("<b>" + "<i>" + "Sorry not found " + "</i>" + "</b>");
        }
        sendMessage.setText(responceText);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private InlineKeyboardMarkup menuKeyboard() {
        InlineKeyboardButton button1 = InlineButtonUtil.button(" \uD83D\uDCB2  \uD835\uDCD2\uD835\uDCFE\uD835\uDCFB\uD835\uDCEE\uD835\uDCF7\uD835\uDCEC\uD835\uDD02 \uD83D\uDCB2 ", "/curency/menu");
        InlineKeyboardButton button2 = InlineButtonUtil.button(" \uD83D\uDCF2  \uD835\uDCF1\uD835\uDCEE\uD835\uDCF5\uD835\uDCF9  ", "/help");
        InlineKeyboardButton button3 = InlineButtonUtil.button("ℹ️   \uD835\uDCF2\uD835\uDCF7\uD835\uDCEF\uD835\uDCF8  ", "/info");
        InlineKeyboardButton button4 = InlineButtonUtil.button("  \uD83D\uDCB2    \uD835\uDCD2\uD835\uDCF8\uD835\uDCF7\uD835\uDCFF\uD835\uDCEE\uD835\uDCFB\uD835\uDCFD ", "/convert/menu");

        List<List<InlineKeyboardButton>> rowList = InlineButtonUtil.collection(InlineButtonUtil.row(button1),
                InlineButtonUtil.row(button2), InlineButtonUtil.row(button3), InlineButtonUtil.row(button4));

        return InlineButtonUtil.keyboard(rowList);
    }

    private ReplyKeyboardMarkup startKeyboard() {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(false);
        KeyboardRow FirstRow = new KeyboardRow();
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        FirstRow.add(new KeyboardButton("/start "));
        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add(new KeyboardButton("/menu  "));
        keyboardRowList.add(FirstRow);
        keyboardRowList.add(secondRow);
        keyboard.setKeyboard(keyboardRowList);
        return keyboard;
    }

    public Map<Long, TodoItem> getTodoItemStep() {
        return todoItemStep;
    }

    public void setTodoItemStep(Map<Long, TodoItem> todoItemStep) {
        this.todoItemStep = todoItemStep;
    }
}