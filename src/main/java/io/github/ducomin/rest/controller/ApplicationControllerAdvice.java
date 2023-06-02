package io.github.ducomin.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.github.ducomin.exception.RegraNegocioException;
import io.github.ducomin.rest.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(RegraNegocioException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleRegraNegocioException(RegraNegocioException ex){
		String mensagemErro = ex.getMessage();
		return new ApiErrors(mensagemErro);
	}
}