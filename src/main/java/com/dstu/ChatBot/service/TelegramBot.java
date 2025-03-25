package com.dstu.ChatBot.service;

import com.dstu.ChatBot.BotConfig;
import com.dstu.ChatBot.Entity.Student;
import com.dstu.ChatBot.dao.StudentsDao;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
public class TelegramBot extends TelegramLongPollingBot{

    StudentsDao studentsDao = StudentsDao.getInstance();

    private final BotConfig botConfig;
    public TelegramBot(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    private final  String botToken = "7591037463:AAELkHvNLvy0-c3Qq0REQgyLJ0idh8JXMu0";


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            switch (messageText){
                case "/start":
                    System.out.print(update.getMessage().getChat().getFirstName());
                startCommandReceived(chatId, update.getMessage().getChat().getUserName());
            }
        }
    }

    private void startCommandReceived(long chatId, String firstName) {

        String answer = "Здорова братишка "+ firstName + " скажи в какой ты группе";
        Student student = new Student(chatId, firstName);
        if(studentsDao.findById(chatId).isEmpty()){
            studentsDao.save(student);
        }

        sendMessage(chatId, answer);
    }
    private void sendMessage(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        try {
            execute(message);
        }
        catch (TelegramApiException e){
            System.out.print(e);
        }
    }


    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }
}
