package tw.SianHao.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tw.SianHao.model.Member;
import tw.SianHao.model.Message;
import tw.SianHao.service.MessageService;

@Controller
public class MessageController {

	@Autowired
	private MessageService mService;

	@GetMapping(path = "/messageFirst")
	public String findFirst(Model m,HttpSession session) {
		List<Message> list = mService.findPageFirst();
		Integer pageTotal = mService.findPageTotal();
		Integer nowPage = 1;
		Member member = (Member) session.getAttribute("member");
		m.addAttribute("pageTotal", pageTotal);
		m.addAttribute("nowPage", nowPage);
		m.addAttribute("list", list);
		m.addAttribute("member", member);
		return "messageBoard";
	}
	
	@GetMapping(path = "/messagePage/{pageNum}")
	public String findPage(@PathVariable("pageNum") Integer pageNum, Model m,HttpSession session) {
		List<Message> list = mService.findPage(pageNum);
		Integer pageTotal = mService.findPageTotal();
		Member member = (Member) session.getAttribute("member");
		m.addAttribute("nowPage", pageNum);
		m.addAttribute("pageTotal", pageTotal);
		m.addAttribute("list", list);
		m.addAttribute("member", member);
		return "messageBoard";
	}
	
	@PostMapping(path = "/message/login")
	public String checkLogin(@RequestParam("account") String account,@RequestParam("password") String password,HttpSession session,Model m) {
		boolean result = mService.checkLogin(account, password);
		if(result) {
			Member member = mService.getMember(account, password);
			session.setAttribute("member", member);
			return "redirect:/messageFirst";
		}else {
			String str = "帳號或密碼錯誤";
			m.addAttribute("error",str);
			return "login";
		}
	}
	
	
	@PostMapping(path = "/message/create")
	public String createMessage(@RequestParam("title") String title,@RequestParam("deadLine") String deadLine,
			                    @RequestParam("postDate") String postDate,@RequestParam("content") String content
			                    ,HttpSession session) throws ParseException {
		String postDate2 = postDate.replace('T', ' ');
		Date datePostDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(postDate2+":59");
		String deadLine2 = deadLine.replace('T', ' ');
		Date dateDeadLine = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(deadLine2+":59");
		Member member = (Member) session.getAttribute("member");
		mService.createMessage(member,title,datePostDate,dateDeadLine,content);
		
		return "redirect:/messageFirst";
	}

	@GetMapping(path = "/message/delete/{id}")
	public String deleteMessage(@PathVariable("id") Integer id) {
		mService.delete(id);
		return "redirect:/messageFirst";
	}

	@GetMapping(path = "/message/update/{id}")
	public String updateMessagePage(@PathVariable("id") Integer id, Model m,HttpSession session) {
		Message message = mService.findOne(id);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date postDate = message.getPost_date();
		String formatPostDate = dateFormat.format(postDate);
		Date deadLine = message.getDeadline();
		String formatDeadLine = dateFormat.format(deadLine);
		Member member = (Member) session.getAttribute("member");
		
		m.addAttribute("member", member);
		m.addAttribute("message", message);
		m.addAttribute("formatPostDate", formatPostDate);
		m.addAttribute("formatDeadLine", formatDeadLine);
		return "updateMessagePage";
	}

	@GetMapping(path = "/message/update2")
	public String updateMessageAction(@RequestParam("id") Integer id,@RequestParam("title") String title, 
			                          @RequestParam("postDate") String postDate,@RequestParam("deadLine") String deadLine, 
			                          @RequestParam("content") String content,HttpSession session) throws ParseException {
		String postDate2 = postDate.replace('T', ' ');
		Date datePostDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(postDate2+":59");
		String deadLine2 = deadLine.replace('T', ' ');
		Date dateDeadLine = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(deadLine2+":59");
		Member member = (Member) session.getAttribute("member");
		mService.updateMes(member,id, title, datePostDate, dateDeadLine, content);
		return "redirect:/messageFirst";
	}


}

