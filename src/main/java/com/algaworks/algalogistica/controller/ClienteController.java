package com.algaworks.algalogistica.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalogistica.domain.model.Cliente;

@RestController //Isto diz ao spring que esta classe é um componente spring e diz ainda que ela pode receber pedidos ou requests.
public class ClienteController {
	
	@GetMapping("/clientes") // Esta anotacao diz ao spring que ela deve esperar receber um pedido do tipo GET e que o nome desse recurso será /clientes.
	public List<Cliente> listar() {
		var c1 =  new Cliente();
		c1.setId(1L);
		c1.setNome("Edilson Alexandre Cuamba");
		c1.setEmail("edilsoncuamba@gmail.com");
		c1.setTelefone("842473772");
		
		var c2 =  new Cliente();
		c2.setId(1L);
		c2.setNome("Alcidio Cuamba");
		c2.setEmail("alcidiocuamba@gmail.com");
		c2.setTelefone("822473772");
		
		return Arrays.asList(c1, c2);
	}
}
