package com.mendwe.DAO;

import java.util.Set;

import com.mendwe.model.User;

public interface MendWeDAOStd {

	public boolean registerFreshUser(User user);
	
	public void friendRequestEntryPoint(String friendname,String username);
	
    public Set<String> friendRequestNotification(String username);
    
    
    public void acceptFriendRequest(String username,String fusername);
    
    public void rejectFriendRequest(String username,String fusername);
}
