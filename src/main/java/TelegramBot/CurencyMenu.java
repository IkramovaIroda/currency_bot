package TelegramBot;

import com.google.gson.Gson;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Objects;

public class CurencyMenu {
    public SendMessage CurrencyStart(String text, long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        String[] split = text.split("/");
        if (split[2].equals("menu")) {
            sendMessage.setText("\uD835\uDCD2\uD835\uDCFE\uD835\uDCFB\uD835\uDCFB\uD835\uDCEE\uD835\uDCF7\uD835\uDCEC\uD835\uDD02 \uD835\uDCF6\uD835\uDCEE\uD835\uDCF7\uD835\uDCFE");
            sendMessage.setReplyMarkup(menuKeyboard());
        } else {
            sendMessage.setText(Objects.requireNonNull(Curency(split[2])));
        }
        return sendMessage;
    }

    private InlineKeyboardMarkup menuKeyboard() {
        InlineKeyboardButton button1 = InlineButtonUtil.button("    USD  ", "/curency/USD", "\uD83D\uDCB2");
        InlineKeyboardButton button2 = InlineButtonUtil.button("    Euro  ", "/curency/EUR", "\uD83D\uDCB2");
        InlineKeyboardButton button4 = InlineButtonUtil.button("    AUD  ", "/curency/AUD", "\uD83D\uDCB2");
        InlineKeyboardButton button6 = InlineButtonUtil.button("    ARS  ", "/curency/ARS", "\uD83D\uDCB2");
        InlineKeyboardButton button7 = InlineButtonUtil.button("    AMD  ", "/curency/AMD", "\uD83D\uDCB2");
        InlineKeyboardButton button8 = InlineButtonUtil.button("    GBP  ", "/curency/GBP", "\uD83D\uDCB2");
        InlineKeyboardButton button10 = InlineButtonUtil.button("    BDT  ", "/curency/BDT", "\uD83D\uDCB2");
        InlineKeyboardButton button11 = InlineButtonUtil.button("    BHD  ", "/curency/BHD", "\uD83D\uDCB2");
        InlineKeyboardButton button12 = InlineButtonUtil.button("    BYN  ", "/curency/BYN", "\uD83D\uDCB2");
        InlineKeyboardButton button13 = InlineButtonUtil.button("    BGN  ", "/curency/BGN", "\uD83D\uDCB2");
        InlineKeyboardButton button14 = InlineButtonUtil.button("   BND  ", "/curency/BND", "\uD83D\uDCB2");
        InlineKeyboardButton button15 = InlineButtonUtil.button("   HUF  ", "/curency/HUF", "\uD83D\uDCB2");
        InlineKeyboardButton button16 = InlineButtonUtil.button("   VES  ", "/curency/VES", "\uD83D\uDCB2");
        InlineKeyboardButton button17 = InlineButtonUtil.button("   VND  ", "/curency/VND", "\uD83D\uDCB2");
        InlineKeyboardButton button18 = InlineButtonUtil.button("   GEL  ", "/curency/GEL", "\uD83D\uDCB2");
        InlineKeyboardButton button19 = InlineButtonUtil.button("   DKK  ", "/curency/DKK", "\uD83D\uDCB2");
        InlineKeyboardButton button20 = InlineButtonUtil.button("   DZD  ", "/curency/DZD", "\uD83D\uDCB2");
        InlineKeyboardButton button21 = InlineButtonUtil.button("   ZAR  ", "/curency/ZAR", "\uD83D\uDCB2");
        InlineKeyboardButton button22 = InlineButtonUtil.button("   IDR  ", "/curency/IDR", "\uD83D\uDCB2");
        InlineKeyboardButton button23 = InlineButtonUtil.button("   JOD  ", "/curency/JOD", "\uD83D\uDCB2");
        InlineKeyboardButton button24 = InlineButtonUtil.button("   IQD  ", "/curency/IQD", "\uD83D\uDCB2");
        InlineKeyboardButton button25 = InlineButtonUtil.button("   ISK  ", "/curency/ISK", "\uD83D\uDCB2");
        InlineKeyboardButton button26 = InlineButtonUtil.button("   ILS  ", "/curency/ILS", "\uD83D\uDCB2");
        InlineKeyboardButton button27 = InlineButtonUtil.button("   KHR  ", "/curency/KHR", "\uD83D\uDCB2");
        InlineKeyboardButton button28 = InlineButtonUtil.button("   CAD  ", "/curency/CAD", "\uD83D\uDCB2");
        InlineKeyboardButton button9 = InlineButtonUtil.button("   AED  ", "/curency/AED", "\uD83D\uDCB2");
        InlineKeyboardButton button5 = InlineButtonUtil.button("   GBP  ", "/curency/GBP", "\uD83D\uDCB2");
        List<List<InlineKeyboardButton>> rowList = InlineButtonUtil.collection(
                InlineButtonUtil.row(button1, button2, button4), InlineButtonUtil.row(button20, button21, button22), InlineButtonUtil.row(button17, button18, button19), InlineButtonUtil.row(button5, button6, button7), InlineButtonUtil.row(button23, button24, button25), InlineButtonUtil.row(button11, button12, button13), InlineButtonUtil.row(button26, button27, button28), InlineButtonUtil.row(button14, button15, button16), InlineButtonUtil.row(button8, button9, button10));

        return InlineButtonUtil.keyboard(rowList);
    }

    public static String Curency(String CCy) {
        try {
            URL url = new URL("https://cbu.uz/oz/services/open_data/rates/json/");
            URLConnection connection = url.openConnection();
            InputStream stream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
            Gson gson = new Gson();
            Json[] userArray = gson.fromJson(String.valueOf(stringBuilder), Json[].class);
            for (Json currensyDTO : userArray) {
                if (currensyDTO.getG1().equals(CCy)) {
                    return "\nКурс валюты: " + currensyDTO.getG1()
                            + "\nRU:  " + currensyDTO.getG6()
                            + "\nUZ:  " + currensyDTO.getG7()
                            + "\nUZC:  " + currensyDTO.getG8()
                            + "\nEn:  " + currensyDTO.getG9()
                            + "\nСумма:  " + currensyDTO.getG4()
                            + "\nДата:  " + currensyDTO.getG5();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
}
