package com.dstu.ChatBot;

import com.dstu.ChatBot.Data.ConnectionManager;
import com.dstu.ChatBot.Entity.Student;
import com.dstu.ChatBot.dao.StudentsDao;
import com.dstu.ChatBot.dto.FilterStudent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ChatBotApplication {
//	CREATE TABLE dstu.students (
//	id SERIAL NOT NULL PRIMARY KEY,
//	chat_id int,
//	username int,
//	fio varchar(255),
//	student_id varchar(255),
//	akadem_group varchar(255),
//	kurs int,
//	facult varchar(50)
//
//				);
	public static void main(String[] args) throws IOException, InterruptedException, SQLException {
		SpringApplication.run(ChatBotApplication.class, args);
		var studentrDao = StudentsDao.getInstance();
//		Student student = new Student();
//		student.setChatId(12345L);
//		System.out.println(studentrDao.save(student));
//		System.out.println(studentrDao.delete( 21L));
////
//		String url = "https://edu.donstu.ru/api/raspGrouplist?year=2024-2025";
//
//		HttpClient client = HttpClient.newHttpClient();
//		HttpRequest request = HttpRequest.newBuilder()
//				.uri(URI.create(url))
//				.header("User-Agent", "Mozilla/5.0")  // Иногда нужен, чтобы имитировать браузер
//				.build();
//
//		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//		System.out.println("Ответ сервера: " + response.body().toString());
		System.out.println(studentrDao.findByUsername("gapmeeeeee"));
    }
}
