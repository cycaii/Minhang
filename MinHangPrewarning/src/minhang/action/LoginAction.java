/**
 * 
 */
package minhang.action;

import minhang.dao.UserDao;
import minhang.entity.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author cyc
 * 
 */
public class LoginAction extends ActionSupport {
	private String username;
	private String password;
	private String errinfo = "";
	private UserDao userDao = new UserDao();

	public String execute() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		if (username == null || "".endsWith(username)) {
			errinfo = "用户名不能为空！";
			return "fail";
		}
		if (password == null || "".endsWith(password)) {
			errinfo = "密码不能为空！";
			return "fail";
		}
		User user = userDao.login(username, password);
		if (user == null) {
			errinfo = "用户名或密码不正确";
			return "fail";
		}
		
		ctx.getSession().put("username", username);
		return SUCCESS;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getErrinfo() {
		return errinfo;
	}

	public void setErrinfo(String errinfo) {
		this.errinfo = errinfo;
	}

}
