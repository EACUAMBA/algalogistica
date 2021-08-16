package com.algaworks.algalogistica.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalogistica.domain.model.Cliente;
import com.algaworks.algalogistica.domain.repository.ClienteRepository;
import com.algaworks.algalogistica.domain.service.CatalogoClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor //Gera um construtor com todos os argumentos, propriedades.
@RestController //Isto diz ao spring que esta classe é um componente spring e diz ainda que ela pode receber pedidos ou requests.
@RequestMapping(path = "/clientes") // Dizendo ao Spring para levar para aqui pedidos /clientes
public class ClienteController {
	
//	@PersistenceContext // FAz o Spring injectar uma instancia do EntityManager
//	private EntityManager entityManager;
	
//	@Autowired // Estamos dizendo ao Spring para injectar uma instancia do ClienteRepository, mas essa forma não é boa porque dificulta na hora de fazer testes unitarios, por isso usamos construtores sem nenhuma anotacao, o spring ja sabe que deve injectar.
	private ClienteRepository clienteRepository;
	private CatalogoClienteService catalogoClienteService;
	
	
	@GetMapping// Esta anotacao diz ao spring que ela deve esperar receber um pedido do tipo GET e que o nome desse recurso será /clientes.
	public List<Cliente> listar() {
		var clientes = this.clienteRepository.findAll();
		return clientes;
	}
	
	@GetMapping("/{clienteId}") //Pegando o valor que é passado na url com a variavel clienteId, OBS: os nomes devem ser iguais, daqui e do parametro do metodo
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) { // @PathVariavel faz um binding com o valor passado na URL e o valor esperado no parametro do metodo. Retornando o ResponseEntity que pode ter um cliente no body.
		return this.clienteRepository.findById(clienteId)
//		.map(cliente -> ResponseEntity.ok(cliente))
				.map(ResponseEntity::ok)
		.orElse(ResponseEntity.notFound().build());//Pesquisando usando o ID no repositorio, retornando um optional, para caso não exista.
		
		
//		if(cliente.isPresent()) {
//			return ResponseEntity.ok(cliente.get()); // Retorna o repose e o codigo para aqui!
//		}
//		
//		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) //Isto diz ao Spring que as respostas devem ser com o status created
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return this.catalogoClienteService.salvar(cliente);
	}
	
	@PutMapping(path = "/{clienteId}")
	public ResponseEntity<Cliente> actualizar(@PathVariable Long clienteId, @RequestBody Cliente cliente){
		if(!this.clienteRepository.existsById(clienteId)){
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		Cliente clientePersistido = this.catalogoClienteService.actualizar(cliente);
		
		return ResponseEntity.ok(clientePersistido);
	}
	
	@DeleteMapping(path = "/{clienteId}")
	public ResponseEntity<Void> apagar(@PathVariable Long clienteId) { // Respondendo um responseentity personalizado que embrulha um objecto Void
		if(!this.clienteRepository.existsById(clienteId))
			return ResponseEntity.notFound().build();
		
		this.catalogoClienteService.apagar(clienteId);
		
		return ResponseEntity.noContent().build();
	}
}
