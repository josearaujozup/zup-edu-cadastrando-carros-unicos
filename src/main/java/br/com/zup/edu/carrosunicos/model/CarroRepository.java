package br.com.zup.edu.carrosunicos.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long>{
	public boolean existsByPlaca(String placa);
	public boolean existsByChassi(String chassi);
}
