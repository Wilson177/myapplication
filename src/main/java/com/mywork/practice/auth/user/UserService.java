package com.mywork.practice.auth.user;

public interface UserService {
	String getUserDetails = "SELECT user_name, password FROM Users where user_name = :userName";
}
