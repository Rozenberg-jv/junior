package by.kolbun.study.junior.controllers.un;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter(value = AccessLevel.PRIVATE)
public class UnDto {

	private int amount;

	private int capacity;
}
