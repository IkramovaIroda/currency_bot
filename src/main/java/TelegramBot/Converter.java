package TelegramBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Converter {
    public SendMessage CurrencyStart(String text, long chatId,Integer messageId,Map<Long, TodoItem> todoItemStep) {
        MyTelegramBot myTelegramBot=new MyTelegramBot();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        String[] split = text.split("/");
        if(split.length>2) {
            if (split[2].equals("menu")) {
                sendMessage.setText("\uD835\uDCD2\uD835\uDCF8\uD835\uDCF7\uD835\uDCFF\uD835\uDCEE\uD835\uDCFB\uD835\uDCFD\uD835\uDCEE\uD835\uDCFB\n");
                sendMessage.setReplyMarkup(menuKeyboard());
            } else if (split[2].equals("RUB")) {
                sendMessage.setText("Send *Title* ");
                sendMessage.setParseMode("Markdown");

                TodoItem todoItem = new TodoItem();
                todoItem.setId(String.valueOf(messageId));
                todoItem.setUserId(chatId);
                todoItem.setTodoItemType(TodoItemType.RUB);

                todoItemStep.put(chatId, todoItem);
            }
            else if (split[2].equals("GBP")) {
                sendMessage.setText("Send *Title* ");
                sendMessage.setParseMode("Markdown");

                TodoItem todoItem = new TodoItem();
                todoItem.setId(String.valueOf(messageId));
                todoItem.setUserId(chatId);
                todoItem.setTodoItemType(TodoItemType.GBP);

                todoItemStep.put(chatId, todoItem);
            }
            else if (split[2].equals("ETH")) {
                sendMessage.setText("Send *Title* ");
                sendMessage.setParseMode("Markdown");

                TodoItem todoItem = new TodoItem();
                todoItem.setId(String.valueOf(messageId));
                todoItem.setUserId(chatId);
                todoItem.setTodoItemType(TodoItemType.ETH);

                todoItemStep.put(chatId, todoItem);
            }
            else if (split[2].equals("JPY")) {
                sendMessage.setText("Send *Title* ");
                sendMessage.setParseMode("Markdown");

                TodoItem todoItem = new TodoItem();
                todoItem.setId(String.valueOf(messageId));
                todoItem.setUserId(chatId);
                todoItem.setTodoItemType(TodoItemType.JPY);

                todoItemStep.put(chatId, todoItem);
            }
        }else {
            TodoItem todoItem = todoItemStep.get(chatId);
            sendMessage.setText(String.valueOf(Converter(String.valueOf(todoItem.getTodoItemType()),Integer.valueOf(split[0]))));
        }
        return sendMessage;
    }

    private InlineKeyboardMarkup menuKeyboard() {
        InlineKeyboardButton button1 = InlineButtonUtil.button("RUB => USD", "/convert/RUB", "\uD83D\uDCB2");
        InlineKeyboardButton button2 = InlineButtonUtil.button("GBP => USD", "/convert/GBP", "\uD83D\uDCB2");
        InlineKeyboardButton button3 = InlineButtonUtil.button("ETH => USD", "/convert/ETH", "\uD83D\uDCB2");
        InlineKeyboardButton button4 = InlineButtonUtil.button("JPY => USD", "/convert/JPY", "\uD83D\uDCB2");

        List<List<InlineKeyboardButton>> rowList = InlineButtonUtil.collection(
                InlineButtonUtil.row(button1), InlineButtonUtil.row(button2), InlineButtonUtil.row(button3), InlineButtonUtil.row(button4));

        return InlineButtonUtil.keyboard(rowList);
    }

    public static Double Converter(String type,Integer son) {
        Double d=0.0;
        if(type.equals("RUB")){
            d=son*0.01329;
        }else if(type.equals("GBP")){
            d=son*1.35765;
        }else if(type.equals("ETH")){
            d=son*300101.95;
        }else if(type.equals("JPY")){
            d=son*0.00866;
        }
        return d;
    }

}
