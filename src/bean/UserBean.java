package bean;



import controller.LoginManager;
import model.User;

public class UserBean {
	
		private String username;
		private String password;
		private String email;
		
		
		public boolean validate() {
			
			boolean checked=false;
			LoginManager controller = LoginManager.getInstance();
			synchronized(controller) {
			User found = controller.login(this.username, this.password);
				if(found != null) {
				
					checked=true;
				}
			}
			return checked;
		}
		
		public boolean foundUser(){
			boolean uChecked=false;
			LoginManager controller = LoginManager.getInstance();
			synchronized(controller) {
			User found = controller.foundUser(this.username);
				if(found != null) {
				
					uChecked=true;
				}
				}
			return uChecked;
		}
		
	
	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }
	    public String getEmail() {
			return email;
		}
		
		public void setEmail(String email) {
			this.email= email;
			
		}
		public void reset() {
		    this.setEmail(null);
		    this.setPassword(null);
		    this.setUsername(null);
		}
	}



