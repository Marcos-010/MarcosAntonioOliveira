package marcosantonio.service;

import java.util.List;

import marcosantonio.domain.Profissao;

public interface Profissaoservice {
	
	void salvar(Profissao profissao);

	void editar(Profissao profissao);
	
	void excluir(Long id);
	
	Profissao buscarPorId(Long id);
	
	List<Profissao> buscarTodos();

	boolean profissaoTemCliente(Long id);
}