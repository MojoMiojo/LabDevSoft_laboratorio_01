package com.projetomatricula.controller;

import com.projetomatricula.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public abstract class BaseController<T> {

	protected CrudService<T> service;

	@Autowired
	public BaseController(CrudService<T> service) {
		this.service = service;
	}
}
