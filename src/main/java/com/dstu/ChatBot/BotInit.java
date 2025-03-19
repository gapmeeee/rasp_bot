package com.dstu.ChatBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import com.dstu.ChatBot.service.TelegramBot;

@Component
public class BotInit {

     TelegramBot telegramBot;
    public BotInit(TelegramBot telegramBot) { // Внедряем зависимость через конструктор
        this.telegramBot = telegramBot;
    }
    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException{
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(telegramBot);
        } catch (TelegramApiException e){
            System.out.println(e);
        }
    }
}
