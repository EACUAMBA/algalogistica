package com.algaworks.algalogistica.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalogistica.domain.model.Cliente;
import com.algaworks.algalogistica.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor //Gera um construtor com todos os argumentos, propriedades.
@RestController //Isto diz ao spring que esta classe é um componente spring e diz ainda que ela pode receber pedidos ou requests.
public class ClienteController {
	
//	@PersistenceContext // FAz o Spring injectar uma instancia do EntityManager
//	private EntityManager entityManager;
	
//	@Autowired // Estamos dizendo ao Spring para injectar uma instancia do ClienteRepository, mas essa forma não é boa porque dificulta na hora de fazer testes unitarios, por isso usamos construtores sem nenhuma anotacao, o spring ja sabe que deve injectar.
	private ClienteRepository clienteRepository;
	
	
	@GetMapping("/clientes") // Esta anotacao diz ao spring que ela deve esperar receber um pedido do tipo GET e que o nome desse recurso será /clientes.
	public List<Cliente> listar() {
		var clientes = this.clienteRepository.findByNomeContaining("n");
		return clientes;
	}
}
