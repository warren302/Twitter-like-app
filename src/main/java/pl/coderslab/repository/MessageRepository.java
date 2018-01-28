package pl.coderslab.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.entity.Message;
import pl.coderslab.entity.User;

public interface MessageRepository extends JpaRepository<Message, Long>{

	Collection<Message> findAllBySenderIdOrAddresseeId(long senderId, long addresseeId);
	Collection<Message> findAllBySenderOrAddressee(User sender, User Addressee);
	
	
}
