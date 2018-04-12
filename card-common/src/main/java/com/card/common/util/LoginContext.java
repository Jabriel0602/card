package com.card.common.util;

import com.google.common.base.Strings;
import lombok.Data;

public class LoginContext {

	private static final ThreadLocal<SessionInfo> SESSION_INFO = new ThreadLocal<SessionInfo>() {
		@Override
		protected SessionInfo initialValue() {
			return new SessionInfo();
		}
	};

	public static Boolean isLogin() {
		if (Strings.isNullOrEmpty(getUserPIN())) {
			return false;
		}
		return true;
	}

	@Data
	public static class SessionInfo {
		private String userPin;
		private String userName;
		private String ip;
	}

	public static SessionInfo getSessionInfo() {
		return SESSION_INFO.get();
	}

	public static void setUserName(String userName) {
		SESSION_INFO.get().setUserName(userName);
	}

	public static void getUserName() {
		SESSION_INFO.get().getUserName();
	}

	public static void setUserPIN(String userPIN) {
		SESSION_INFO.get().setUserPin(userPIN);
	}

	public static String getUserPIN() {
		return SESSION_INFO.get().getUserPin();
	}

	public static String getIP() {
		return SESSION_INFO.get().getIp();
	}

	public static void setIP(String IP) {
		SESSION_INFO.get().setIp(IP);
	}

	public static void clear() {
		SESSION_INFO.remove();
	}
}