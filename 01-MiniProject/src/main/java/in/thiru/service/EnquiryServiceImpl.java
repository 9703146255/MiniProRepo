package in.thiru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.thiru.binding.SearchCriteria;
import in.thiru.entity.StudentEnq;
import in.thiru.repo.StudentEnqRepo;

@Service
public class EnquiryServiceImpl implements IEnquiryService {

	@Autowired
	private StudentEnqRepo studentEnqRepo;

	@Override
	public boolean addEnq(StudentEnq se) {

		StudentEnq student = studentEnqRepo.save(se);
		
		//if null is there in db return false
		//if null value is not there(some value(id) is there) than we should return true

		return student.getEnqId() != null;
	}

	@Override
	public List<StudentEnq> getEnquiries(Integer cid, SearchCriteria s) {

		return null;
	}

}
