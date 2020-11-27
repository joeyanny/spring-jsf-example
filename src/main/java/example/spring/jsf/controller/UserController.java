package example.spring.jsf.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

import example.spring.jsf.exception.ServiceException;
import example.spring.jsf.model.User;
import example.spring.jsf.model.UserList;
import example.spring.jsf.service.UserService;

@Component
@ManagedBean
@RequestScoped
public class UserController {

    private UserService userService;

    @ManagedProperty(value="#{user}")
    private User user;

    @ManagedProperty(value="#{userList}")
    private UserList userList;

    public String addUser() {
        user = new User();
        return "useradd?faces-redirect=true";
    }

    public String viewUser(int id) throws ServiceException {
        user = userService.get(id);
        return "userdisplay?faces-redirect=true";
    }

    public String viewUserList() throws ServiceException {
        userList = userService.getAll();
        return "userlistview?faces-redirect=true";
    }

    public String saveUser() throws ServiceException {
        userService.save(user);
        return viewUserList();
    }

    public String updateUser() throws ServiceException {
        userService.update(user);
        return viewUserList();
    }

    public String deleteUser(long id) throws ServiceException {
        userService.delete(id);
        return viewUserList();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserList getUserList() {
        return userList;
    }

    public void setUserList(UserList userList) {
        this.userList = userList;
    }

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
