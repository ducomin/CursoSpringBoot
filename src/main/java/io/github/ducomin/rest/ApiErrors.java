package io.github.ducomin.rest;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErrors {

	@Getter
	private List<String> errors;

	public ApiErrors(String mensagemErro){
		this.errors = Arrays.asList(mensagemErro);
	}
}
