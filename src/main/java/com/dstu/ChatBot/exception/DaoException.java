package com.dstu.ChatBot.exception;

import java.sql.SQLException;

public class DaoException extends RuntimeException{
    public DaoException(Throwable e) {
        super(e);
    }
}
