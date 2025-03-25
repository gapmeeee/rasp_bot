package com.dstu.ChatBot.Entity;

import java.util.Objects;

public class Student {
    private Long chatId;
    private String username;
    private String fio;
    private String akademGroup;
    private Integer kurs;
    private String facult;
    private Integer studentId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return kurs == student.kurs && studentId == student.studentId  && Objects.equals(chatId, student.chatId) && Objects.equals(username, student.username) && Objects.equals(fio, student.fio) && Objects.equals(akademGroup, student.akademGroup) && Objects.equals(facult, student.facult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, username, fio, akademGroup, kurs, facult, studentId);
    }

    private Student() {
    }

    public Student(Long chatId, String username) {
        this.chatId = chatId;
        this.username = username;
    }

    public Student(Long chatId, String username, String fio, String akademGroup, Integer kurs, String facult, Integer studentId) {
        this.chatId = chatId;
        this.username = username;
        this.fio = fio;
        this.akademGroup = akademGroup;
        this.kurs = kurs;
        this.facult = facult;
        this.studentId = studentId;
    }


    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getAkademGroup() {
        return akademGroup;
    }

    public void setAkademGroup(String akademGroup) {
        this.akademGroup = akademGroup;
    }

    public Integer getKurs() {
        if (kurs == null){
            return 0;
        }
        return kurs;
    }

    public void setKurs(Integer kurs) {
        if(kurs == null){
            this.kurs = 0;
        }
        else{ this.kurs = kurs;}

    }

    public String getFacult() {
        return facult;
    }

    public void setFacult(String facult) {
        this.facult = facult;
    }

    public Integer getStudentId() {
        if(studentId==null){
            return 0;
        }
        return studentId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "chatId=" + chatId +
                ", username='" + username + '\'' +
                ", fio='" + fio + '\'' +
                ", akademGroup='" + akademGroup + '\'' +
                ", kurs=" + kurs +
                ", facult='" + facult + '\'' +
                ", studentId=" + studentId +
                '}';
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}