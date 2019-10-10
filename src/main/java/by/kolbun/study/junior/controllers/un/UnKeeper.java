package by.kolbun.study.junior.controllers.un;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class UnKeeper extends Thread {

	private int amount = 0;
	private int lastAmount = 0;

	private int income = 10;

	private final int capacity = 1000;

	private final SimpMessagingTemplate messagingTemplate;

	@Autowired
	public UnKeeper(SimpMessagingTemplate messagingTemplate) {

		this.messagingTemplate = messagingTemplate;
		this.setDaemon(true);
	}

	@Override
	public void run() {

		while (true) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			amount += income;

			if (amount > capacity)
				amount = capacity;

			if (amount < 0)
				amount = 0;

			if (lastAmount != amount) {
				messagingTemplate.convertAndSend("/chat/messages", new UnDto(amount, capacity));
				lastAmount = amount;
				System.out.printf("sending %d / %d\n", amount, capacity);
			}
		}
	}

	public void incIncome() {

		income += 10;
	}

	public void decIncome() {

		income -= 10;
	}

}
