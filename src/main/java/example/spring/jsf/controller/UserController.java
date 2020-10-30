package example.spring.jsf.controller;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import example.spring.jsf.dao.UserDao;
import example.spring.jsf.model.User;
import example.spring.jsf.model.UserList;

@Component
@ManagedBean
@RequestScoped
public class UserController {

    @Autowired
    private UserDao userDao;

    @ManagedProperty(value="#{user}")
    @Autowired
    private User user;

    @ManagedProperty(value="#{userList}")
    @Autowired
    private UserList userList;

    public String addUser() {
        user = new User();
        return "useradd?faces-redirect=true";
    }

    public String viewUser(int id) throws SQLException {
        user = userDao.getUser(id);
        return "userdisplay?faces-redirect=true";
    }

    public String viewUserList() throws SQLException {
        userList = userDao.getUserList();
        return "userlistview?faces-redirect=true";
    }

    public String saveUser() throws SQLException {
        userDao.saveUser(user);
        return viewUserList();
    }

    public String updateUser() throws SQLException {
        userDao.updateUser(user);
        return viewUserList();
    }

    public String deleteUser(long id) throws SQLException {
        userDao.deleteUser(id);
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
}
