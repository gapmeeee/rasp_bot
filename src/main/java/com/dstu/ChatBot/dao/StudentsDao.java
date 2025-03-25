package com.dstu.ChatBot.dao;

import com.dstu.ChatBot.Data.ConnectionManager;
import com.dstu.ChatBot.Entity.Student;
import com.dstu.ChatBot.dto.FilterStudent;
import com.dstu.ChatBot.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentsDao implements Dao<Long, Student>{
    private final static  StudentsDao INSTANCE = new StudentsDao();

    private final static String SAVE_SQL = """
            INSERT INTO dstu.students (chat_id, fio, akadem_group, kurs, facult, username,student_id)
            VALUES (?,?,?,?,?,?,?)
            """;

    private final static String DELETE_SQL = """
            DELETE FROM dstu.students
            WHERE id = ?
            """;

    private final static String FIND_ALL_SQL = """
            SELECT chat_id, fio, akadem_group, kurs, facult, username, student_id 
            FROM dstu.students 
            """;
    private static final String FIND_BY_ID_SQL = """
            SELECT chat_id, fio, akadem_group, kurs, facult, username, student_id 
            FROM dstu.students WHERE chat_id = ?; 
            """;
    private static final String FIND_BY_USERNAME_SQL = """
            SELECT chat_id, fio, akadem_group, kurs, facult, username, student_id 
            FROM dstu.students WHERE username = ?; 
            """;
    private static final String UPDATE_SQL = """
            UPDATE dstu.students 
            set fio = ?, akadem_group = ?, kurs = ?, facult = ?, username = ?, student_id = ?
            WHERE chat_id = ?;
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

    public Optional<Student> findById(Long chatId){
        try(var connection = ConnectionManager.open();
            var statement = connection.prepareStatement(FIND_BY_ID_SQL)){
            statement.setLong(1, chatId);
            var result = statement.executeQuery();
            Student student = null;
            if (result.next()){
                student = buildStudent(result);
            }
            return Optional.ofNullable(student);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Optional<Student> findByUsername(String username){
        try(var connection = ConnectionManager.open();
            var statement = connection.prepareStatement(FIND_BY_USERNAME_SQL)){
            statement.setString(1, username);
            var result = statement.executeQuery();
            Student student = null;
            if (result.next()){
                student = buildStudent(result);
            }
            return Optional.ofNullable(student);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Student buildStudent(ResultSet result) throws SQLException {
        return new Student(
                result.getLong("chat_id"),
                result.getString("username"),
                result.getString("fio"),
                result.getString("akadem_group"),
                result.getInt("kurs"),
                result.getString("facult"),
                result.getInt("student_id")
        );
    }

    @Override
    public boolean upadate(Student student) {
        try (var connection = ConnectionManager.open();
        var statement = connection.prepareStatement(UPDATE_SQL);){
            statement.setString(0, student.getFio());
            statement.setString(1, student.getAkademGroup());
            statement.setInt(2, student.getKurs());
            statement.setString(3, student.getFacult());
            statement.setString(4, student.getUsername());
            statement.setInt(5, student.getStudentId());
            return statement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Student> findAll(){
        try(var connection = ConnectionManager.open();
            var statement = connection.prepareStatement(FIND_ALL_SQL)){
            ResultSet resultSet = statement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()){
                students.add(buildStudent(resultSet));
            }
            return students;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Student> findAll(FilterStudent filterStudent){
        List<Object> parametrs = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();
        if(filterStudent.akademGroup()!=null){
            parametrs.add(filterStudent.akademGroup());
            whereSql.add("akadem_group = ?");
        }
        if(filterStudent.kurs()!=null){
            parametrs.add(filterStudent.kurs());
            whereSql.add("kurs = ?");
        }
        parametrs.add(filterStudent.limit());
        parametrs.add(filterStudent.offset());
        String where = whereSql.stream().collect(Collectors.joining(
                " AND ",
                parametrs.size() > 2 ? " WHERE " : " ",
                " LIMIT ? OFFSET ?"));
        try(var connection = ConnectionManager.open();
            var statement = connection.prepareStatement(FIND_ALL_SQL + where)){
            for (int i = 0; i < parametrs.size(); i++) {
                statement.setObject(i + 1, parametrs.get(i));
            }
            System.out.printf(statement.toString());
            List<Student> students = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                students.add(buildStudent(resultSet));
            }
            return students;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}
