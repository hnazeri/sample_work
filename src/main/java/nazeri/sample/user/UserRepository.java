package nazeri.sample.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query(value="SELECT * FROM users WHERE users.email = :email",nativeQuery=true)
	public User findByEmail(String email);
	
}
