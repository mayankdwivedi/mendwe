package com.mendwe.DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import com.mendwe.enumerations.Relationship;
import com.mendwe.enumerations.Religion;
import com.mendwe.enumerations.Sex;
import com.mendwe.enumerations.VegNonVeg;
import com.mendwe.model.User;
import com.mendwe.utility.SessionFactoryUtility;



public class UserTest {
	private static final Logger logger = Logger.getLogger(UserTest.class);

	public void setTable(){
		SessionFactory fact=SessionFactoryUtility.getSessionFactory();
		Session session=fact.openSession();
		session.beginTransaction();
		User user=new User();
		user.setAge(35);
		user.setBlogId("google.com");
		user.setCollege("kanpur");
		user.setCurrentAddress("delhi");
		user.setHomeTown("up");
		user.setMailId("abc1@gmail.com");
		user.setMobilenum(435435);
	//	user.setName("Amit");
		user.setOrganization("gmail");
		user.setRelation(Relationship.divorced);
		user.setReligion(Religion.hindu);
		user.setSchool("saraswati shishu mandir");
		user.setSex(Sex.Male);
		
		user.setVegNonVeg(VegNonVeg.veg);
		
		session.save(user);
		session.getTransaction().commit();
		session.close();
		logger.info("success");
	}
	
	public List<User> getUserInfo(){
		SessionFactory fact=SessionFactoryUtility.getSessionFactory();
		Session session=fact.openSession();
		session.beginTransaction();
		Query query   =session.createQuery("from User where id=1");
		List<User> list=query.list();
		session.close();
		return list;
	}
	
	public List<User> getFriendList(Integer uId){
		SessionFactory fact=SessionFactoryUtility.getSessionFactory();
		Session session=fact.openSession();
		session.beginTransaction();
		Query query   =session.createSQLQuery("select friendID from friendlist where userId ="+uId);
		
		List list=query.list();
		
		List<User> friendList=new ArrayList<User>();
		
		Iterator it=list.iterator();
		while(it.hasNext())
		{
			int friendId=(Integer) it.next();
			Query query1   =session.createQuery("from User where id="+friendId);
            friendList.add((User) query1.list().get(0));
            int mutualFriend=getMutualFriends(uId,friendId);
		}
				
		//System.out.println(list);
		session.close();
		return friendList;
	}

	public int getMutualFriends(int userId,int friendId){
		
		SessionFactory fact=SessionFactoryUtility.getSessionFactory();
		Session session=fact.openSession();
		session.beginTransaction();
		Query query1   =session.createSQLQuery("select friendID from friendlist where userId ="+userId);
		Query query2   =session.createSQLQuery("select friendID from friendlist where userId ="+friendId);

		List list1=query1.list();
		List list2=query2.list();
		
		list1.retainAll(list2);
		
		return list1.size();
	}
	
	
}
