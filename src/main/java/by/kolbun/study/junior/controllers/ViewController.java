package by.kolbun.study.junior.controllers;

import by.kolbun.study.junior.Nav;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ViewController {

	@GetMapping(Nav.ROOT_PATH)
	public String loginPage() {

		return Nav.LOGIN;
	}

	@GetMapping(Nav.INDEX_PATH)
	public String indexPage() {

		return Nav.INDEX;
	}

	@PostMapping(Nav.INDEX_PATH)
	public String indexContent() {

		return Nav.INDEX_CONTENT;
	}
}
