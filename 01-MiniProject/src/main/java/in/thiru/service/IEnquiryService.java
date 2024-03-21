package in.thiru.service;

import java.util.List;

import in.thiru.binding.SearchCriteria;
import in.thiru.entity.StudentEnq;

public interface IEnquiryService {
	
	public boolean addEnq(StudentEnq se);

	public List<StudentEnq> getEnquiries(Integer cid, SearchCriteria s);

}
