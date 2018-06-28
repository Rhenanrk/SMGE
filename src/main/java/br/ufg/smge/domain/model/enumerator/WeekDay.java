package br.ufg.smge.domain.model.enumerator;

public enum WeekDay {
	MONDAY("Segunda-feira"), TUESDAY("Terça-feira"), WEDNESDAY("Quarta-feira"), THURSDAY("Quinta-feira"), FRIDAY(
			"Sexta-feira");

	private final String dayName;

	WeekDay(String name) {
		this.dayName = name;
	}

	public String getDayName() {
		return dayName;
	}

}
