package in.thiru.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.thiru.binding.DashboardResponse;
import in.thiru.entity.Counsellor;
import in.thiru.entity.StudentEnq;
import in.thiru.repo.CounsellorRepo;
import in.thiru.repo.StudentEnqRepo;
import in.thiru.util.EmailUtils;

@Service
public class CounsellerServiceImpl implements ICounsellerService {

	@Autowired
	private CounsellorRepo counsellorRepo;
	
	@Autowired
	private StudentEnqRepo studentEnqRepo;
	
	
	
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public String saveCounsellor(Counsellor c) {
		
		
		//verify duplicate email or not
		Counsellor obj = counsellorRepo.findByEmail(c.getEmail());
		if(obj != null)
		{
			return "Duplicate Email Id";
		}
		

		Counsellor counsellorObj = counsellorRepo.save(c);

		if (counsellorObj.getCid() != null) {
			return "Registration Success";
		}

		return "Registration Failed";
	}

	@Override
	public Counsellor loginCheck(String email, String pwd) {

		return counsellorRepo.findByEmailAndPswrd(email, pwd);
		
	}

	@Override
	public boolean recoverPwd(String email) {
		Counsellor findByEmail = counsellorRepo.findByEmail(email);
		
		//if the give email id is not available than return invalied email
		if(findByEmail == null)
		{
			return false;
		}
		String subject="Recover password- THIRUMALESH-IT";
		String password = findByEmail.getPswrd();
		String body = "<h1 style='color: #3498db;'>your account password is</h1>" +
		              "<p style='color: #2ecc71;'>Your Password: " + password + "</p>";

		
		
		
		return emailUtils.sendEmail(subject, body, email);
	}

	@Override
	public DashboardResponse getDashboardInfo(Integer cid) {
		
		//list of all enquiries we will get
		List<StudentEnq> findByCid = studentEnqRepo.findByCid(cid);
		
		 List<StudentEnq> enrolledEnq = findByCid.stream().filter(s->s.getCourseStatus().equals("Enrolled")).collect(Collectors.toList());
		
		
		DashboardResponse dresp=new DashboardResponse();
		dresp.setTotalEnq(findByCid.size());
		dresp.setEnrolledEnq(enrolledEnq.size());
		dresp.setLostEnq(findByCid.size()-enrolledEnq.size());
	
		return dresp;
	}

}
