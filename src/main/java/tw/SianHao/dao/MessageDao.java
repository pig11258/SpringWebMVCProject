package tw.SianHao.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.SianHao.model.Member;
import tw.SianHao.model.Message;

@Repository
@Transactional
public class MessageDao {

	@Autowired
	private SessionFactory sessionFactory;

	 public boolean checkLogin(String account,String pwd) {
			Session session = sessionFactory.openSession();
			
			String hqlstr = "from Member where account = :account and password=:pwd";
			Query<Member> query = session.createQuery(hqlstr,Member.class);
			query.setParameter("account", account).setParameter("pwd", pwd);
			
			Member member = query.uniqueResult();
			
			session.close();
			
		   if(member!=null) {
			   return true;
		   }
			return false;
		}
	
	 public Member getMember(String account,String pwd) {
		 Session session = sessionFactory.openSession();
			
			String hqlstr = "from Member where account = :account and password=:pwd";
			Query<Member> query = session.createQuery(hqlstr,Member.class);
			query.setParameter("account", account).setParameter("pwd", pwd);
			
			Member member = query.uniqueResult();
			
			session.close();
			return member;
	 }
	 
	
	public Member createMessage(Member member,String title,Date datePostDate,Date dateDeadLine,String content) {
		Session session = sessionFactory.openSession();
		Message message = new Message();
		message.setTitle(title);
		message.setPost_date(datePostDate);
		message.setDeadline(dateDeadLine);
		message.setContent(content);
		message.setMember(member);
		Set<Message> tempSet = member.getMessage();
		tempSet.add(message);
		member.setMessage(tempSet);
		session.save(message);
		session.close();
		return member;
	}
	


	public Message updateMes(Member member,Integer id, String title, Date postDate, Date deadLine, String content)  {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		

		Message oldMessage = session.get(Message.class, id);
		if (oldMessage != null) {
			oldMessage.setTitle(title);
			oldMessage.setContent(content);
			oldMessage.setPost_date(postDate);
			oldMessage.setDeadline(deadLine);
			oldMessage.setMember(member);
			System.out.println("更新完成");
			session.getTransaction().commit();
			session.close();
			return oldMessage;
		} else {
			System.out.println("更新失敗");
			session.getTransaction().rollback();
			session.close();
			return null;
		}
	}

	public Boolean delete(Integer id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Message message = session.get(Message.class, id);
		if (message == null) {
			System.out.println("刪除失敗");
			session.getTransaction().rollback();
			session.close();
			return false;
		} else {
			session.delete(message);
			session.getTransaction().commit();
			session.close();
			return true;
		}
	}

	public List<Message> findAll() {
		Session session = sessionFactory.openSession();
		String hqlStr = "from Message order by post_date DESC";
		Query<Message> query = session.createQuery(hqlStr, Message.class);
		List<Message> rsList = query.list();
		session.close();
		return rsList;
	}

	public Integer findPageTotal() {
		Session session = sessionFactory.openSession();
		String hqlStr = "from Message order by post_date DESC";
		Query<Message> query = session.createQuery(hqlStr, Message.class);
		List<Message> rsList = query.list();
		int size = rsList.size();
		int totalPage = ((size-1)/3)+1;
		session.close();
		return totalPage;
	}
	
	public List<Message> findPageFirst() {
		Session session = sessionFactory.openSession();
		String hqlStr = "from Message order by post_date DESC";
		Query<Message> query = session.createQuery(hqlStr, Message.class);
		query.setFirstResult(0);
		query.setMaxResults(3);
		List<Message> rsList = query.list();
		session.close();
		return rsList;
	}
	
	public List<Message> findPage(Integer pageNum) {
		Session session = sessionFactory.openSession();
		String hqlStr = "from Message order by post_date DESC";
		Query<Message> query = session.createQuery(hqlStr, Message.class);
		query.setFirstResult((pageNum-1)*3);
		query.setMaxResults(3);
		List<Message> rsList = query.list();
		session.close();
		return rsList;
	}
	
	public Message findOne(Integer id) {
		Session session = sessionFactory.openSession();
		String hqlstr = "from Message where id=:id ";
		Query<Message> query = session.createQuery(hqlstr, Message.class);
		query.setParameter("id", id);
		Message message = query.uniqueResult();
		session.close();
		if (message != null) {
			System.out.println("找到訊息");
			return message;
		} else {
			System.out.println("查詢失敗");
			return null;
		}
	}

}

