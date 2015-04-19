/**
 * 
 */
package minhang.action;

import minhang.entity.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author cyc
 *
 */
public class PinfaAction extends ActionSupport {

	
	public String show() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		String username = (String) ctx.get("username");
		if (username == null || username.equals("")) {
			return LOGIN;
		}
		 
		 
		
	 
		return SUCCESS;
	}

}
