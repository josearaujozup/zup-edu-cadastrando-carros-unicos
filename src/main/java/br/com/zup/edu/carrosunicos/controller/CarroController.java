package br.com.zup.edu.carrosunicos.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.edu.carrosunicos.model.Carro;
import br.com.zup.edu.carrosunicos.model.CarroRepository;

@RestController
@RequestMapping("/carros")
public class CarroController {
	
	private final CarroRepository repository;

	public CarroController(CarroRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CarroDTO request, UriComponentsBuilder uriComponentsBuilder){
		
		if(repository.existsByPlaca(request.getPlaca()) || repository.existsByChassi(request.getChassi())) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Carro já existe no sistema");
		}
		
        Carro carro = request.toModel();

        repository.save(carro);

        URI location = uriComponentsBuilder.path("/carros/{id}")
                .buildAndExpand(carro.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
	
	@ExceptionHandler
	public ResponseEntity<?> handleUniqueConstraintErrors(ConstraintViolationException e){
		
		Map<String, Object> body = Map.of(
				"message", "carro já existente no sistema",
				"timestamp", LocalDateTime.now()
		);
		
		return ResponseEntity.unprocessableEntity().body(body);
	}
	
}
