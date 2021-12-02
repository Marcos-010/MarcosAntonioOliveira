package marcosantonio.service;

import java.util.List;

import marcosantonio.domain.Cliente;

public interface Clienteservice {

    void salvar(Cliente cliente);

    void editar(Cliente cliente);

    void excluir(Long id);

    Cliente buscarPorId(Long id);

    List<Cliente> buscarTodos();
}