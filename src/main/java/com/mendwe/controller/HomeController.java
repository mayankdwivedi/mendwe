package com.mendwe.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import com.mendwe.DAO.MendWeDAOStd;
import com.mendwe.DAO.UserTest;
import com.mendwe.model.Posts;
import com.mendwe.service.MendWeServiceImpl;
import com.mendwe.service.MendWeServiceStd;
import com.mendwe.utility.JedisFactory;

@Controller
public class HomeController {

	UserTest test;
	private static final Logger logger = Logger.getLogger(HomeController.class);

	@Autowired
	private MendWeDAOStd mendWeDAOStd;
	
//	@Autowired
	private MendWeServiceStd serviceStd=new MendWeServiceImpl();

	@Autowired
	public HomeController(UserTest test) {
		this.test = test;
	}

	@RequestMapping(value = "/home.html", method = RequestMethod.GET)
	public ModelAndView openourprofilePage(HttpSession httpSession) {
		// ModelAndView md=new ModelAndView();
		// return new ModelAndView("ourprofile","mdList","mdList");
		logger.info("going to open a home page");
		System.out.println("from System.out.println()");

		String username = (String) httpSession.getAttribute("username");
		Set<String> friendRequestList = mendWeDAOStd
				.friendRequestNotification(username);
		ModelAndView modelAndView = new ModelAndView("ourprofile");
		modelAndView.addObject("friendList", friendRequestList);
		/*
		 * test.setTable(); System.out.println("seccess table ");
		 */
		logger.debug("friend Request List" + friendRequestList);

		Jedis jedis = JedisFactory.getInstance().getJedisPool().getResource();
		Set<String> friendList = jedis.smembers("FRIEND_SET:" + username);

		Map<Integer, Map<String, String>> postMap = new HashMap<Integer,Map<String, String>>();
		Map<String,String> postInfoMap;

/*		String postInfo;
*/		logger.debug("friend Set" + friendList);
		String postId = jedis.get("POST_IDS");
		int maxPostId = Integer.parseInt(postId);
		
		System.out.println("####################maximum postId is" + maxPostId);
		for (Integer i = maxPostId; i >=1 ; i--) {
			String postedByID = jedis.hget("POST_ALL:" + i, "postedby");
			if ((friendList.contains(postedByID))
					|| (username.equals(postedByID))) {
				String StotalLikes="";
				Set<String> likedBy=jedis.smembers("LIKE_POST:"+i);
				int totalLikes=likedBy.size();
				StotalLikes=StotalLikes+totalLikes;

				String postText = jedis.hget("POST_ALL:" + i, "text");
				String postDate = jedis.hget("POST_ALL:" + i, "posteddate");
/*				postInfo = null;
*/				System.out.println("postedBy" + postedByID);
				System.out.println("postText" + postText);
				postInfoMap=new HashMap<String,String>();
				
				  postInfoMap.put("username",postedByID); 
				  postInfoMap.put("postdate","no Date");/*Date is to be send to proper format*/
				  postInfoMap.put("postText",postText);
				  postInfoMap.put("StotalLikes",StotalLikes);
				  
/*				postInfo = postedByID + "/" + postDate + "/" + postText;
*/				/*
				 * jsonObject.put("postedBy",postedByID);
				 * jsonObject.put("postedDate", postDate);
				 * jsonObject.put("postedText",postText);
				 */

				postMap.put(i, postInfoMap);
				System.out.println("#########################Map this time is"
						+ postMap);
			}
		}
		modelAndView.addObject("postMap", postMap);

		/* City Lights */
		String city, text;
		List<String> cLightsList = new ArrayList<String>();
		String cLightsId = jedis.get("CITYLIGHTS_IDS");
		int maxcLightsId = Integer.parseInt(cLightsId);
		String currentCity = jedis.hget("USER_ALL:" + username, "currentCity");
		for (int i = 1; i <= maxcLightsId; i++) {
			city = jedis.hget("CITYLIGHTS_ALL:" + i, "city");
			if (currentCity.equals(city)) {
				text = jedis.hget("CITYLIGHTS_ALL:" + i, "text");
				cLightsList.add(text);
			}
		}
		
		modelAndView.addObject("cLightsList", cLightsList);

		/* Get News */
		Map<String,String> newsMap=jedis.hgetAll("NEWS_ALL");
		modelAndView.addObject("newsMap", newsMap);
		
		return modelAndView;
	}

	@RequestMapping(value = "/friendlist.html", method = RequestMethod.GET)
	public ModelAndView openfriendlistPage(HttpServletRequest request) {
		Jedis jedis = JedisFactory.getInstance().getJedisPool().getResource();

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		Set<String> friendList = jedis.smembers("FRIEND_SET:" + username);

		Set<String> friendFriendList = new HashSet<String>();

		Iterator iterator = friendList.iterator();
		Map<String, String> friendAndMutual = new HashMap<String, String>();
		while (iterator.hasNext()) {
			String nextFriend = (String) iterator.next();
			friendFriendList = jedis.smembers("FRIEND_SET:" + nextFriend);
			friendFriendList.retainAll(friendList);
			int mutualFriends = friendFriendList.size();
			friendAndMutual.put(nextFriend, (Integer.toString(mutualFriends)));
		}

		return new ModelAndView("friendlist", "friendAndMutual",
				friendAndMutual);

		/* return "friendlist"; */
	}

	@RequestMapping(value = "/billu.html", method = RequestMethod.GET)
	public String sendFriendRequest() {

		return "billu";
	}

	@RequestMapping(value = "/sendfrndreq.html", method = RequestMethod.GET)
	public String friendRequestSent(HttpSession session,
			final RedirectAttributes redirectAttributes,
			@RequestParam("unameid") String unameid) {
		logger.debug("sendfrndreq request handler");
		logger.debug((String) session.getAttribute("username")
				+ "  sends friend request to " + unameid);
		/*
		 * redirectAttributes.addFlashAttribute("username",
		 * (String)session.getAttribute("username"));
		 * redirectAttributes.addFlashAttribute("friendusername","billu");
		 * redirectAttributes
		 * .addFlashAttribute(" message","has sent friend request to ");
		 */

		mendWeDAOStd.friendRequestEntryPoint(unameid,
				(String) session.getAttribute("username"));
		return "billu";
	}

	@RequestMapping(value = "/accept.html", method = RequestMethod.GET)
	public String acceptFriendRequest(
			@RequestParam("fusername") String fusername, HttpSession session) {
		// ModelAndView md=new ModelAndView();
		/*
		 * return new
		 * ModelAndView("otherprofilenotf","mdList",test.getUserInfo());
		 */
		mendWeDAOStd.acceptFriendRequest(
				(String) session.getAttribute("username"), fusername);
		return "ourprofile";
	}

	@RequestMapping(value = "/reject.html", method = RequestMethod.GET)
	public String rejectFriendRequest() {
		ModelAndView md = new ModelAndView();
		/*
		 * return new
		 * ModelAndView("otherprofilenotf","mdList",test.getUserInfo());
		 */
		return "ourprofile";
	}

	@RequestMapping(value = "/otherprofilenotf.html", method = RequestMethod.GET)
	public ModelAndView openotherprofilenotfPage(@RequestParam("username") String username,HttpServletRequest request) {
		Map<String,String> userProp=new HashMap<String, String>();
		System.out.println("******************Username for Profile page is********************"+username);
		Jedis jedis = JedisFactory.getInstance().getJedisPool().getResource();
        Map<String,String> userInfo=jedis.hgetAll("USER_ALL:"+username);
        Set<String> friendList=jedis.smembers("FRIENDSET:"+username);
        HttpSession session = request.getSession();
		String loggedInUser = (String) session.getAttribute("username");
		boolean isUserFriend=friendList.contains(loggedInUser);
        String isUserFriendString=""+isUserFriend;
		
		userProp.put("username", username);
		userProp.put("name", userInfo.get("firstName")+" "+userInfo.get("lastName"));
		userProp.put("mailId", userInfo.get("mailid"));
		userProp.put("currentCity", userInfo.get("currentCity"));
		userProp.put("isFriend", isUserFriendString);
		
		/*
		 * return new
		 * ModelAndView("otherprofilenotf","mdList",test.getUserInfo());
		 */
		return new ModelAndView("otherprofilenotf","userProp",userProp);
	}

	@RequestMapping(value = "/postS.html", method = RequestMethod.GET)
	public String setPost(@ModelAttribute("posts") Posts posts,
			BindingResult result, HttpServletRequest request,
			final RedirectAttributes redirectAttributes) {
		Jedis jedis = JedisFactory.getInstance().getJedisPool().getResource();

		Long postId = jedis.incr("POST_IDS");
		HttpSession session = request.getSession();
		String postedBy = (String) session.getAttribute("username");

		Map<String, String> postInfo = new HashMap<String, String>();

		postInfo.put("text", posts.getText());
		postInfo.put("postedby", postedBy);
		jedis.hmset("POST_ALL:" + postId, postInfo);
		return "ourprofile";
	}

	@RequestMapping(value = "/postsomething.html", method = RequestMethod.GET)
	public String setPost(@ModelAttribute("posts") Posts posts,
			BindingResult bindingResult, HttpServletRequest request) {
		Jedis jedis = JedisFactory.getInstance().getJedisPool().getResource();
		Pipeline pipeline = jedis.pipelined();

		HttpSession session = request.getSession();
		String postedBy = (String) session.getAttribute("username");

		Map<String, String> postInfo = new HashMap<String, String>();

		String cityLightsCheckBox = request.getParameter("cCheckBox");

		postInfo.put("postedby", postedBy);
		postInfo.put("text", posts.getText());
		postInfo.put("createddate", (posts.getCreatedDate()).toString());

		if (cityLightsCheckBox != null) {
			Long clightsId = jedis.incr("CITYLIGHTS_IDS");
			String currentCity = jedis.hget("USER_ALL:" + postedBy,
					"currentCity");
			postInfo.put("city", currentCity);
			jedis.hmset("CITYLIGHTS_ALL:" + clightsId, postInfo);
		} else {
			Long postId = jedis.incr("POST_IDS");
			jedis.hmset("POST_ALL:" + postId, postInfo);

		}
		pipeline.sync();

		JedisFactory.getInstance().returnJedisResource(jedis);
		return "redirect:home.html";
	}

	@RequestMapping(value = "/search.html", method = RequestMethod.GET)
	public ModelAndView searchMethod(HttpSession httpSession,HttpServletRequest request) {
		Jedis jedis = JedisFactory.getInstance().getJedisPool().getResource();
        
		
		String searchText=request.getParameter("searchBox");
		Set<String> totalkeys=jedis.keys("USER_ALL:*");
		System.out.println("########Total keys are#######"+totalkeys);
        int countUsers=totalkeys.size();
        Map <String,Map<String,String>> userMap=new HashMap<String,Map<String,String>>();
       /* Map <String,String> userInfo=new HashMap<String, String>();*/
        
       /* userInfo.put("name", "Mayank Dwivedi");
        userInfo.put("work", "JSS");
        userInfo.put("currentCity","Noida");
        userInfo.put("hometown", "Kanpur");
        
        userMap.put("may", userInfo);

        Map <String,String> userInfo1=new HashMap<String, String>();
        userInfo1.put("name", "Himanshu Dwivedi");
        userInfo1.put("work", "Jaypee");
        userInfo1.put("currentCity","Noida");
        userInfo1.put("hometown", "Kanpur");
        
        userMap.put("him", userInfo1);*/
        
        Iterator iterator=totalkeys.iterator();
        while(iterator.hasNext()){
        	String usernameKey=(String) iterator.next();
        	 Map <String,String> userInfo=new HashMap<String, String>();
        	Map<String, String> userDbMap=jedis.hgetAll(usernameKey);
        	String[] userKeyArray=usernameKey.split(":");
        	String name=userDbMap.get("firstName")+" "+userDbMap.get("lastName");
        	
        	if(name.contains(searchText)){
        		userInfo.put("name",name);
        		userInfo.put("work","null");
        		userInfo.put("currentCity", userDbMap.get("currentCity"));
        		userInfo.put("hometown", "null");
        		userMap.put(userKeyArray[1], userInfo);
        	}
        }
        	
        
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^ Map value is"+userMap);
        
		return new ModelAndView("search","userMap",userMap);
	}

	
	@RequestMapping("/ajax")
    public ModelAndView helloAjaxTest() {
        return new ModelAndView("ajax", "message", "Crunchify Spring MVC with Ajax and JQuery Demo..");
    }
 
    @RequestMapping(value = "/ajaxtest", method = RequestMethod.GET)
    public @ResponseBody
    String getTime(@RequestParam(value = "postId") String postId,HttpServletRequest request) {
 
    	System.out.println("######################Post Id is##################### "+postId);
    	HttpSession session = request.getSession();
		String postedBy = (String) session.getAttribute("username");

		
    	
		Jedis jedis = JedisFactory.getInstance().getJedisPool().getResource();
		Pipeline pipeline = jedis.pipelined();
		String result="";
		/*Set<String> totalkeys=jedis.keys("USER_ALL:*");
        result=result+(totalkeys.size());*/
		boolean hasLiked= jedis.sismember("LIKE_POST:"+postId, postedBy);
		if(!hasLiked){
		jedis.sadd("LIKE_POST:"+postId, postedBy);}
		else{
			jedis.srem("LIKE_POST:"+postId, postedBy);

		}
		Set<String> likedBy=jedis.smembers("LIKE_POST:"+postId);
		int totalLikes=likedBy.size();
		pipeline.sync();
		result=result+totalLikes;
		

       logger.debug("Debug Message from CrunchifySpringAjaxJQuery Controller..");
        return result;
    }
    
//  For uploading File
    @RequestMapping(value = "/fileUpload.html", method = RequestMethod.POST)
	public String fileUpload() {
    	serviceStd.uploadFile();
//    return "ourprofile";
    	return "redirect:home.html";
    }
}
