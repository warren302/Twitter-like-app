package pl.coderslab.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.entity.Comment;
import pl.coderslab.entity.Tweet;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	Collection<Comment> findAllByTweetOrderByCreatedDesc(Tweet tweet);
	Collection<Comment> findAllByTweetIdOrderByCreatedDesc(long id);
	
	
	
}
