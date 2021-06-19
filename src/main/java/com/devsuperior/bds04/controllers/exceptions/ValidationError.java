package com.devsuperior.bds04.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private final List<FieldValidationMessage> errors = new ArrayList<>();

	public List<FieldValidationMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		this.errors.add(new FieldValidationMessage(fieldName, message));
	}

}
