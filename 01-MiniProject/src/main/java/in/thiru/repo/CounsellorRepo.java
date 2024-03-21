package in.thiru.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.thiru.entity.Counsellor;
@Repository
public interface CounsellorRepo extends JpaRepository<Counsellor, Integer>{
	
	
	public Counsellor findByEmailAndPswrd(String email, String password);
	public Counsellor findByEmail(String email);

}
