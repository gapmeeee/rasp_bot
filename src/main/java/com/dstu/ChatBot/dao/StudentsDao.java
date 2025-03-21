package com.dstu.ChatBot.dao;

import com.dstu.ChatBot.Data.ConnectionManager;
import com.dstu.ChatBot.Entity.Student;
import com.dstu.ChatBot.exception.DaoException;

import java.sql.SQLException;

public class StudentsDao {
    private final static  StudentsDao INSTANCE = new StudentsDao();

    private final static String SAVE_SQL = """
            INSERT INTO dstu.students (chat_id, fio, akadem_group, kurs, facult, username,student_id)
            VALUES (?,?,?,?,?,?,?)
            """;

    private final static String DELETE_SQL = """
            DELETE FROM dstu.students
            WHERE id = ?
            """;

    public static StudentsDao getInstance(){
        return INSTANCE;
    }
    private  StudentsDao(){}

    public Student save (Student student){
        try(var connection = ConnectionManager.open();
        var statement = connection.prepareStatement(SAVE_SQL)){

            statement.setLong(1, student.getChatId());
            statement.setString(2, student.getFio());
            statement.setString(3, student.getAkademGroup());
            statement.setInt(4, student.getKurs());
            statement.setString(5, student.getFacult());
            statement.setString(6, student.getUsername());
            statement.setInt(7, student.getStudentId());

            statement.executeUpdate();
            var keys = statement.getGeneratedKeys();
            if (keys.next()) {
                student.setId(keys.getLong("id"));
            }
            return student;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public boolean delete(Long id){
        try(var connection = ConnectionManager.open();
        var statement = connection.prepareStatement(DELETE_SQL)){
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}
