package example.spring.jsf.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import example.spring.jsf.exception.ServiceException;
import example.spring.jsf.model.User;
import example.spring.jsf.model.UserList;
import example.spring.jsf.service.UserService;

@Component
@ManagedBean
@RequestScoped
public class UserController {

    private static final Logger LOG = LogManager.getLogger(UserController.class);

    private UserService userService;

    @ManagedProperty(value="#{user}")
    private User user;

    @ManagedProperty(value="#{userList}")
    private UserList userList;

    public String addUser() {
        user = new User();
        return "useradd?faces-redirect=true";
    }

    public String viewUser(long id) {
        try {
            user = userService.get(id);
            if (user == null) {
                LOG.info("Rendering error page: User not found [" + id + "]");
                return "error";
            }

            LOG.info("Rendering view page: User found [" + id + "]");
            return "userdisplay?faces-redirect=true";
        } catch (ServiceException e) {
            LOG.error("Rendering error page: Error encountered while getting user: " + e.getMessage());
            return "error";
        }
    }

    public String viewUserList() {
        try {
            userList = userService.getAll();
            return "userlistview?faces-redirect=true";
        } catch (ServiceException e) {
            LOG.error("Rendering error page: Error encountered while getting all users: " + e.getMessage());
            return "error";
        }
    }

    public String saveUser() {
        try {
            userService.save(user);
            LOG.info("Redirecting to user list page: User successfully saved");
            return viewUserList();
        } catch (ServiceException e) {
            LOG.error("Rendering error page: Error encountered while saving user: " + e.getMessage());
            return "error";
        }
    }

    public String updateUser() {
        try {
            userService.update(user);
            LOG.info("Redirecting to user list page: User successfully updated [" + user.getId() + "]");
            return viewUserList();
        } catch (ServiceException e) {
            LOG.error("Rendering error page: Error encountered while updating user: " + e.getMessage());
            return "error";
        }
    }

    public String deleteUser(long id) {
        try {
            userService.delete(id);
            LOG.info("Redirecting to user list page: User successfully deleted [" + id + "]");
            return viewUserList();
        } catch (ServiceException e) {
            LOG.error("Rendering error page: Error encountered while deleting user: " + e.getMessage());
            return "error";
        }
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
