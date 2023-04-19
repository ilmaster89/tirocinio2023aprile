package f2.tirocinio.social.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import f2.tirocinio.social.models.Post;

public interface PostDao extends JpaRepository<Post, Integer> {

	@Query(value = "select * from post limit 10", nativeQuery = true)
	List<Post> tenPosts();

	@Query(value = "select post.* from post join commenti on commenti.post_id = post.id where post.utente_id = :userId and commenti.visto = 0 group by post.id", nativeQuery = true)
	List<Post> notifications(Integer userId);
}
