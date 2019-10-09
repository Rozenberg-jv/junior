package by.kolbun.study.junior.controllers.un;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UnController {

	private final UnKeeper unKeeper;

	@Autowired
	public UnController(UnKeeper unKeeper) {

		this.unKeeper = unKeeper;
	}

	@GetMapping("/un")
	public String unPage() {

		if (!unKeeper.isAlive())
			unKeeper.start();

		return "un";
	}

	/*@MessageMapping("/message")
	public void getUnData(String msg) {

		System.out.println("Message: " + msg);
	}

	@SendTo("/chat/messages")
	public UnDto sendUnData(int amount, int capacity) {

		System.out.printf("sending %d / %d\n", amount, capacity);
		return new UnDto(amount, capacity);
	}*/
}
