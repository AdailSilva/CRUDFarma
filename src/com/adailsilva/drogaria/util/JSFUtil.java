package com.adailsilva.drogaria.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {

	// o [growl] para exibir uma mensagem ele precisa que tenha um FacesMensagem no
	// Contexto
	// métodos estáticos para não ter que instanciá-los
	// JSF não entende String e sim FacesMessage
	// FacesMessage precisa basicamente de três parâmetros:
	// 1 - severity [severidade] == tipo do erro que podem ser:
	// info, warning, error e fatal.
	// 2 - summary [sumário] == resumo da mensagem.
	// 3 - detail [detalhe] == detalhe da mensagem.

	// adicionar o msg no contexto da aplicação
	// área de memória onde ficam armazenadas as informações referentes a aplicação
	// variável que irá apontar para o contexto da aplicação
	// vai pegar a área de memória temporária da aplicação e ser referenciada por
	// essa variável
	
	public static void adicionarMensagemSucesso(String mensagem) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, msg);
	}

	public static void adicionarMensagemErro(String mensagem) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, msg);
	}

}
