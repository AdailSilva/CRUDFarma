package com.adailsilva.drogaria.domain;

public class FabricanteDTO {

	// atributos
	private Long idfabricante; // usando int o valor padrão é 0, seria zero padrão do java ou zero digitado?
								// não então usar. para que seja nulo no padrão usa-se outro inteiro.
	private String descricao;

	// métodos acessores Getters e Setters
	public Long getIdfabricante() {
		return idfabricante;
	}

	public void setIdfabricante(Long idfabricante) {
		this.idfabricante = idfabricante;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	// método toString para formatar a saída dos objetos do tipo da classe
	@Override
	public String toString() {
		return "FabricanteDTO [idfabricante=" + idfabricante + ", descricao=" + descricao + "]";
	}

}
