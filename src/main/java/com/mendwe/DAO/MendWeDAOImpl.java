package com.mendwe.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import com.mendwe.model.User;
import com.mendwe.utility.JedisFactory;

public class MendWeDAOImpl implements MendWeDAOStd {

	private static final Logger logger = Logger
			.getLogger(MendWeDAOImpl.class);
	public boolean registerFreshUser(User user) {
		Jedis jedis = JedisFactory.getInstance().getJedisPool().getResource();
		Pipeline pipeline = jedis.pipelined();

		jedis.set("USERNAME_PASSWORD:" + user.getUsername(), user.getPassword());

		
		Map<String, String> userProperties = new HashMap<String, String>();

		userProperties.put("firstName", user.getFirstName());
		userProperties.put("lastName", user.getLastName());
		userProperties.put("username", user.getUsername());
		userProperties.put("password", user.getPassword());
		userProperties.put("mailid", user.getMailId());
		userProperties.put("currentCity",user.getCurrentAddress());
		

		/*
		 * WE HAVE TO DO THIS WAY FINALLY
		 */
		/*
		 * pipeline.hmset(Keys.USER_DATA.formated(String.valueOf(userId)),
		 * BeanUtils.toMap(user));
		 */
		jedis.hmset("USER_ALL:" + user.getUsername(), userProperties);
		pipeline.sync();
		
		//logger.debug("user with user id = "+jedis.hmget(key, fields));
		JedisFactory.getInstance().returnJedisResource(jedis);
		logger.debug("user successfully registered");
		return true;
	}
	
	public void friendRequestEntryPoint(String friendUserName,String username) {

		Jedis jedis = JedisFactory.getInstance().getJedisPool().getResource();
		Pipeline pipeline = jedis.pipelined();
		
		
		
		
		jedis.sadd("FRIEND_REQUESTS:" + friendUserName, username);

		
		logger.debug("Total friendrequests to "+friendUserName+ "   "+jedis.smembers("FRIEND_REQUESTS:" + friendUserName));

		jedis.hset("PENDING_FRIEND_REQUESTS:" + username,friendUserName,"pending" );

		logger.debug("total pending friend request for user = "+username+"  = "+jedis.hgetAll("PENDING_FRIEND_REQUESTS:"+username));

		pipeline.sync();
		
		//logger.debug("user with user id = "+jedis.hmget(key, fields));
		JedisFactory.getInstance().returnJedisResource(jedis);
		logger.debug("FRIEND request send successfully");
	
	
		
	}

	@Override
	public Set<String> friendRequestNotification(String username) {
		Jedis jedis = JedisFactory.getInstance().getJedisPool().getResource();
		
		Set<String> friendrequests=jedis.smembers("FRIEND_REQUESTS:"+username);
		JedisFactory.getInstance().returnJedisResource(jedis);

		return friendrequests;
	}

	@Override
	public void acceptFriendRequest(String username, String fusername) {
		Jedis jedis = JedisFactory.getInstance().getJedisPool().getResource();
		Pipeline pipeline = jedis.pipelined();
		
		
		
		jedis.srem("FRIEND_REQUESTS:" + username, fusername);
		
		logger.debug("freind request list "+jedis.smembers("FRIEND_REQUESTS:"+username)+" after accepting request of  = "+fusername);

		jedis.hdel("PENDING_FRIEND_REQUESTS:" + username,fusername);
		
		logger.debug("PENDING_FRIEND_REQUESTS map "+jedis.smembers("PENDING_FRIEND_REQUESTS:"+username )+" after accepting request of  = "+fusername);
		
		

		jedis.sadd("FRIEND_SET:" +username, fusername);
		jedis.sadd("FRIEND_SET:" +fusername,username);

		
		logger.debug("Total friends of  "+username+ "   "+jedis.smembers("FRIEND_SET:" + username+"  after accepting friend request of "+fusername));

		pipeline.sync();
		
		//logger.debug("user with user id = "+jedis.hmget(key, fields));
		JedisFactory.getInstance().returnJedisResource(jedis);
		logger.debug("FRIEND request accept successfully");
		
	}

	@Override
	public void rejectFriendRequest(String username, String fusername) {
		// TODO Auto-generated method stub
		Jedis jedis = JedisFactory.getInstance().getJedisPool().getResource();
		Pipeline pipeline = jedis.pipelined();
		
		
		jedis.srem("FRIEND_REQUESTS:" + username, fusername);
		
		logger.debug("freind request list "+jedis.smembers("FRIEND_REQUESTS:"+username)+" after accepting request of  = "+fusername);

		jedis.hset("PENDING_FRIEND_REQUESTS:" + username,fusername,"Rejected");
		
		logger.debug("PENDING_FRIEND_REQUESTS map "+jedis.smembers("PENDING_FRIEND_REQUESTS:"+username )+" after accepting request of  = "+fusername);

		
		logger.debug("Total friends of  "+username+ "   "+jedis.smembers("FRIEND_SET:" + username+"  after accepting friend request of "+fusername));

		pipeline.sync();
		
		//logger.debug("user with user id = "+jedis.hmget(key, fields));
		JedisFactory.getInstance().returnJedisResource(jedis);
		logger.debug("FRIEND request rejected successfully");
	}

	
	

}
