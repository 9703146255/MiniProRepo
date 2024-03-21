package in.thiru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.thiru.binding.DashboardResponse;
import in.thiru.entity.Counsellor;
import in.thiru.service.ICounsellerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorsController {
	
	@Autowired
	private ICounsellerService counsellorService;
	
	
	@GetMapping("/")
	public String index(Model model) // display login page
	{
		model.addAttribute("counsellor", new Counsellor());
		return "loginView";
	}
	
	@PostMapping("/login")
	public String handleLogin(Counsellor c, Model model,HttpServletRequest req)
	{
		Counsellor obj = counsellorService.loginCheck(c.getEmail(), c.getPswrd());
		if(obj == null)
		{
			model.addAttribute("errMsg", "Invalid Credentials");
			
			return "loginView";
		}
		
		
		HttpSession session = req.getSession(true);
		session.setAttribute("CID", obj.getCid());
		
		
		
		
		
		return "redirect:dashboard";
	}
	
	@GetMapping("/dashboard")
	public String buildDashboard(Model model,HttpServletRequest req)
	{
		//get data for dashboard
		
		HttpSession session = req.getSession(false);
		Object obj = session.getAttribute("CID");
		Integer cid=(Integer)obj;
		
		
		DashboardResponse dashboardInfo = counsellorService.getDashboardInfo(cid);
		
		model.addAttribute("dashboard", dashboardInfo);
		
		
		return "dashboardView";
	}

	

	@GetMapping("/register")
	public String regPage(Model model) // display signup page
	{
		model.addAttribute("counsellor", new Counsellor());
		return "regView";
	}
	
	@PostMapping("/register")
	public String handleRegistration(Counsellor c, Model model)
	{
		String saveCounsellor = counsellorService.saveCounsellor(c);
		model.addAttribute("msg", saveCounsellor);
		return "regView";
	}
	
	

	@GetMapping("/forgotPwd")
	public String recoverPwdPage(Model model) // display fpwd page
	{
		return "forgotPwrdView";                 //only one field is there so no need empty object
	}
	
	@GetMapping("/recoverpwd")
	public String handleForgotPwd(@RequestParam("email") String email, Model model)
	{
		
		boolean recoverPwd = counsellorService.recoverPwd(email);
		if(recoverPwd)
		{
			model.addAttribute("succMsg", "Pwd sent to your email");
		}
		else
		{
			model.addAttribute("errMsg", "Invalid Email");
		}
		
		return "forgotPwrdView";
	}
	

}
