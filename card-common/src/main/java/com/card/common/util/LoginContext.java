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
		if (getUserId() == null) {
			return false;
		}
		return true;
	}

	public static class SessionInfo {
		private Long userId;
		private String userName;
		private String ip;

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}
	}

	public static SessionInfo getSessionInfo() {
		return SESSION_INFO.get();
	}

	public static void setUserName(String userName) {
		SESSION_INFO.get().setUserName(userName);
	}

	public static String getUserName() {
		//todo
//		return SESSION_INFO.get().getUserName();
		return "test";
	}

	public static void setUserId(Long userId) {
		SESSION_INFO.get().setUserId(userId);
	}

	public static Long getUserId() {
		//todo
//		return SESSION_INFO.get().getUserId();
		return 1066L;
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