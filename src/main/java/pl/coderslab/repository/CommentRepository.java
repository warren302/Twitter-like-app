package pl.coderslab.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.entity.Comment;
import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	Collection<Comment> findAllByTweetOrderByCreatedDesc(Tweet tweet);
	Collection<Comment> findAllByTweetIdOrderByCreatedDesc(long id);
	
	long countByUser(User user);
	long countByUserId(long id);
	
	
}
