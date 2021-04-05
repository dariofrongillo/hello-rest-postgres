package it.helloworlddbms.demo.dao;

import it.helloworlddbms.demo.entity.HelloMessage;
import org.springframework.data.repository.CrudRepository;

public interface HelloMessageRepository extends CrudRepository<HelloMessage, String> {

}
