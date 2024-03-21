package in.thiru.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.thiru.binding.SearchCriteria;
import in.thiru.entity.StudentEnq;
import in.thiru.service.IEnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {

	@Autowired
	private IEnquiryService enquiryService;

	@GetMapping("/addenquiry")
	public String enqPage(Model model) {

		model.addAttribute("enq", new StudentEnq());

		return "addEnqView";
	}

	@PostMapping("/addenquiry")
	public String addEnquiry(@ModelAttribute("enq") StudentEnq enq, Model model,HttpServletRequest req) {
		boolean addEnq = enquiryService.addEnq(enq);
		
		//false means we will get existing session
		//true means new session id we will get
		HttpSession session = req.getSession(false);
		Object object = session.getAttribute("CID");
		Integer cid=(Integer)object;
		
		enq.setCid(cid);

		if (addEnq) {
			// succ message
			model.addAttribute("succMsg", "Enquiry added");
		} else {
			// error message
			model.addAttribute("errMsg", "Enquiry failed to added");
		}

		return "addEnqView";
	}

	@GetMapping("/enquiries")
	public String viewEnquiries(Model model) {

		List<StudentEnq> enquiriesList = enquiryService.getEnquiries(null, null);

		model.addAttribute("enquiries", enquiriesList);

		return "dispalyEnqViews";
	}

	@PostMapping("/filter-enquiries")
	public String filterEnquiries(SearchCriteria sc, Model model) {

		List<StudentEnq> enquiriesList = enquiryService.getEnquiries(null, null);

		model.addAttribute("enquiries", enquiriesList);

		return "dispalyEnqViews";
	}

}
