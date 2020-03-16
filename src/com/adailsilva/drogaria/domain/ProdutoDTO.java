package com.adailsilva.drogaria.domain;

public class ProdutoDTO {

	// atributos
	private Long idproduto;
	private String descricao;
	private Long quantidade;
	private Integer codigodebarras;
	private Double custo;
	private Double preco;
	private String fabricacao;
	private String vencimento;
	private String similar;
	private String observacao;

	// chave estrangeira por COMPOSIÇÃO (1 Produto pertence a 1 Fabricante)
	// Necessário criar um novo objeto sempre que se trabalho com composição
	// Ou seja sempre que criar um produtos um fabricante também será criado
	private FabricanteDTO fabricante = new FabricanteDTO();

	// métodos acessores Getters e Setters
	public Long getIdproduto() {
		return idproduto;
	}

	public void setIdproduto(Long idproduto) {
		this.idproduto = idproduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getCodigodebarras() {
		return codigodebarras;
	}

	public void setCodigodebarras(Integer codigodebarras) {
		this.codigodebarras = codigodebarras;
	}

	public Double getCusto() {
		return custo;
	}

	public void setCusto(Double custo) {
		this.custo = custo;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getFabricacao() {
		return fabricacao;
	}

	public void setFabricacao(String fabricacao) {
		this.fabricacao = fabricacao;
	}

	public String getVencimento() {
		return vencimento;
	}

	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}

	public String getSimilar() {
		return similar;
	}

	public void setSimilar(String similar) {
		this.similar = similar;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public FabricanteDTO getFabricante() {
		return fabricante;
	}

	public void setFabricante(FabricanteDTO fabricante) {
		this.fabricante = fabricante;
	}

	// método toString para formatar a saída dos objetos do tipo da classe
	@Override
	public String toString() {
		return "ProdutoDTO [idproduto=" + idproduto + ", descricao=" + descricao + ", quantidade=" + quantidade
				+ ", codigodebarras=" + codigodebarras + ", custo=" + custo + ", preco=" + preco + ", fabricacao="
				+ fabricacao + ", vencimento=" + vencimento + ", similar=" + similar + ", observacao=" + observacao
				+ ", fabricante=" + fabricante + "]";
	}

}