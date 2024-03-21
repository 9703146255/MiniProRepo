package in.thiru.service;

import in.thiru.binding.DashboardResponse;
import in.thiru.entity.Counsellor;

public interface ICounsellerService {
	
	public String saveCounsellor(Counsellor c);
	 
	public Counsellor loginCheck(String email, String pwd);

	public boolean recoverPwd(String email);

	public DashboardResponse getDashboardInfo(Integer cid);

}
