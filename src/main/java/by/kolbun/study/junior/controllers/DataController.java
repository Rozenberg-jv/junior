package by.kolbun.study.junior.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

	private static final String EXAMPLE =
			"Наверняка Вы помните еще со школы, что есть такое понятие, как \"константы\", то есть фиксированные значения, которые не должны меняться."
					+ "Например, число пи - это константа. Так вот достаточно часто при написании программ, необходимо использовать константу.\n\n"
					+ "Как сделать переменную константой?\n"
					+ "1. Для того, чтобы переменную сделать константой, необходимо использовать ключевое слово final.";

	@GetMapping("/contentdata")
	public ResponseEntity<?> getContentData() {

		return ResponseEntity.ok(EXAMPLE);
	}

}
