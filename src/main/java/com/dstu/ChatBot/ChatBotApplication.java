package com.dstu.ChatBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootApplication
public class ChatBotApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(ChatBotApplication.class, args);

		String url = "https://edu.donstu.ru/api/raspGrouplist?year=2024-2025"; // Замените на найденный URL

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.header("User-Agent", "Mozilla/5.0")  // Иногда нужен, чтобы имитировать браузер
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println("Ответ сервера: " + response.body());
	}
}
