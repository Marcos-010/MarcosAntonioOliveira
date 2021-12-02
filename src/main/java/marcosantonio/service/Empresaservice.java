package marcosantonio.service;

import java.util.List;

import marcosantonio.domain.Empresa;


public interface Empresaservice {
	
	void salvar(Empresa empresa);

	void editar(Empresa empresa);
	
	void excluir(Long id);
	
	Empresa buscarPorId(Long id);
	
	List<Empresa> buscarTodos();

	boolean empresaTemProfissoes(Long id);
}