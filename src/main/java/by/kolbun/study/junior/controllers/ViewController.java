package by.kolbun.study.junior.controllers;

import by.kolbun.study.junior.Nav;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

	@RequestMapping(Nav.ROOT_PATH)
	public String loginPage() {

		return Nav.LOGIN;
	}

	@RequestMapping(Nav.INDEX_PATH)
	public String indexPage() {

		return Nav.INDEX;
	}
}
