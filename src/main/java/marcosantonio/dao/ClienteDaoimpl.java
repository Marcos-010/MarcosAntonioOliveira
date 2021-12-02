package marcosantonio.dao;

import org.springframework.stereotype.Repository;

import marcosantonio.domain.Cliente;

@Repository
public class ClienteDaoimpl extends AbstractDao<Cliente, Long> implements ClienteDao {

}