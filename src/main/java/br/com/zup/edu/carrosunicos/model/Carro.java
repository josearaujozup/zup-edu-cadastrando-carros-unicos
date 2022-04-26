package br.com.zup.edu.carrosunicos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Entity
public class Carro {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private String marca;
	
	@Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
    private String placa;
	
	@Column(nullable = false, unique = true)
    private String chassi;

	public Carro(String marca, String modelo, Integer ano,
			@Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}") String placa, String chassi) {
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.placa = placa;
		this.chassi = chassi;
	}
	
	/**
     * @deprecated construtor para uso exclusivo do Hibernate
     */
    @Deprecated
	public Carro() {
		
	}

	public Long getId() {
		return id;
	}
	
}
