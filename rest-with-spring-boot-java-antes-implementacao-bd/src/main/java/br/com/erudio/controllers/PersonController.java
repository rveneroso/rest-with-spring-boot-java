package br.com.erudio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.model.Person;
import br.com.erudio.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonServices service;
	///private PersonServices service = new PersonServices();
	/*
	* O método abaixo não possui o atributo value simplesmente porque nenhum parãmetro é necessário para sua execução. Na prática, o método abaixo será chamado quando for feita a chamadas GET à
	* http://localhost:8080/person
	* Lembrando que essa classe mapeia todas as chamadas que se iniciam com /person (vide anotação @RequestMapping("/person") acima do nome da classe). Portanto, em não sendo informado nenhum
	* parâmetro após o /person em chamddas GET o método findAll() será chamado.
	 */
	@RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findAll() {
		return service.findAll();
	}
	
	@RequestMapping(value = "/{id}",
			method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Person findById(@PathVariable(value = "id") String id) {
		return service.findById(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Person update(@RequestBody Person person) {
		return service.update(person);
	}


	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable(value = "id") String id) {
		service.delete(id);
	}

	/*
	 * Aqui temos o caso em que o parâmetro que o método recebe não vem via Path Param nem via Request Param: o parâmetro virá no corpo da chamada. Tanto é que esse método atende a requisições
	 * POST. Então, o que se espera é que no corpo da mensagem venha um objeto do tipo Person.
	 * Outra observação importante é que esse método, além de produzir um resultado em JSON (produces = MediaType.APPLICATION_JSON_VALUE), ele também consome um objeto JSON
	 * (consumes = MediaType.APPLICATION_JSON_VALUE).
	 */
	@RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,	produces = MediaType.APPLICATION_JSON_VALUE)
	public Person create(@RequestBody Person person) {
		return service.create(person);
	}
}