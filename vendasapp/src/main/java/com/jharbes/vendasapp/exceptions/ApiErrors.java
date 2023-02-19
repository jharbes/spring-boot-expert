package com.jharbes.vendasapp.exceptions;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErrors {

	@Getter
	private List<String> errors;

	public ApiErrors(String messagemErro) {
		this.errors = Arrays.asList(messagemErro);
	}
}
