package br.com.zup.edu.carrosunicos.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.zup.edu.carrosunicos.model.Carro;

public class CarroDTO {
	
	@NotBlank
    private String marca;
	
    @NotBlank
    private String modelo;
    
    @NotNull
    @Min(2010)
    @Max(2099)
    private Integer ano;

    @NotBlank
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
    private String placa;
	
    @NotBlank
    @Size(min = 17, max = 17)
    private String chassi;

	public CarroDTO(@NotBlank String marca, @NotBlank String modelo, @NotNull @Min(2010) @Max(2099) Integer ano,
			@NotBlank @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}") String placa,
			@NotBlank @Size(min = 17, max = 17) String chassi) {
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.placa = placa;
		this.chassi = chassi;
	}
	
	public CarroDTO() {
		
	}
	
	

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public Integer getAno() {
		return ano;
	}

	public String getPlaca() {
		return placa;
	}

	public String getChassi() {
		return chassi;
	}

	public Carro toModel() {
		return new Carro(marca, modelo, ano, placa, chassi);
	}
	
}
