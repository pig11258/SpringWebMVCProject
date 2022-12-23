package tw.SianHao.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.SianHao.dao.MessageDao;
import tw.SianHao.model.Member;
import tw.SianHao.model.Message;

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageDao mDao;

	public boolean checkLogin(String account,String pwd) {
		return mDao.checkLogin(account,pwd);
	}
	
	public Member getMember(String account,String pwd) {
		return mDao.getMember(account, pwd);
	}
	
	public Member createMessage(Member member,String title,Date datePostDate,Date dateDeadLine,String content) {
		return mDao.createMessage(member,title,datePostDate,dateDeadLine,content);
	}
	

	public Message updateMes(Member member,Integer id, String title, Date postDate, Date deadLine, String content)
			throws ParseException {
		return mDao.updateMes(member,id, title, postDate, deadLine, content);
	}

	public Boolean delete(Integer id) {
		return mDao.delete(id);
	}
	
	public Integer findPageTotal() {
		return mDao.findPageTotal();
	}
	
	public List<Message> findPageFirst(){
		return mDao.findPageFirst();
	}

	public List<Message> findPage(Integer pageNum) {
		return mDao.findPage(pageNum);
	}
	
	public List<Message> findAll() {
		return mDao.findAll();
	}

	public Message findOne(Integer id) {
		return mDao.findOne(id);
	}

}

