package tw.SianHao.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

	@GetMapping(path = "/login")
	public String login() {
		return "login";
	}
	
	@GetMapping(path = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("member");
		return "login";
	}

	@GetMapping(path = "/message/board")
	public String messageBoard() {
		return "messageBoard";
	}
	
	@GetMapping(path="/message/new")
	public String newMessagePage() {
		return "insertMessage";
	}

}

