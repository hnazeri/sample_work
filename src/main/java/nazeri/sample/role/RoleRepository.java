package nazeri.sample.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Query(value="SELECT * FROM roles WHERE roles.name = :name",nativeQuery=true)
	public Role findByName(String name);
	
}
