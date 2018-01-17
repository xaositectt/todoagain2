package com.todo.todoagain;

import com.todo.todoagain.repository.TodoRepo;
import com.todo.todoagain.todo.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoagainApplication implements CommandLineRunner {

	@Autowired
	TodoRepo todoRepo;

	public static void main(String[] args) {
		SpringApplication.run(TodoagainApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		todoRepo.save(new Todo("cowshit", false, false));
		todoRepo.save(new Todo("bullcrap", false, false));
		todoRepo.save(new Todo("horseshit", false, false));
		todoRepo.save(new Todo("chickenshit", false, true));
		todoRepo.save(new Todo("dogshit", false, true));
		todoRepo.save(new Todo("donkeycrap", false, true));

	}
}
