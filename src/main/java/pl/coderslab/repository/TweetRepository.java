package pl.coderslab.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

	Collection<Tweet> findAllByUser(User user);
	Collection<Tweet> findAllByUserId(long id);
	long countByUser(User user);
	long countByUserId(long id);
	
//	@Query("Select t From Tweet t ORDER By t.created DESC")
	Collection<Tweet> findAllByOrderByCreatedDesc();
	
}
