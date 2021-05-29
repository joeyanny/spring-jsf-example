package example.spring.jsf.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import example.spring.jsf.dao.UserDAO;
import example.spring.jsf.dao.UserEntity;
import example.spring.jsf.dao.UserListEntity;
import example.spring.jsf.exception.ServiceException;
import example.spring.jsf.model.User;
import example.spring.jsf.model.UserList;
import example.spring.jsf.transformer.UserTransformer;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private static final long ID = 1;
    private static User USER = new User();
    private static UserEntity ENTITY = new UserEntity();

    @Mock
    private UserDAO dao;

    @Mock
    private UserTransformer transformer;

    @InjectMocks
    private UserService service;

    @DisplayName("Get all users")
    @Test
    public void testGetAllUsers() throws Exception {
        UserListEntity entities = new UserListEntity();
        UserList users = new UserList();

        when(dao.getUserList()).thenReturn(entities);
        when(transformer.transform(entities)).thenReturn(users);

        assertEquals(users, service.getAll());
        verify(dao).getUserList();
        verify(transformer).transform(entities);
    }

    @DisplayName("Error when getting all users")
    @Test
    public void testGetAllUsersErrors() throws Exception {
        when(dao.getUserList()).thenThrow(new SQLException());
        assertThrows(ServiceException.class, () -> service.getAll());
    }

    @DisplayName("Get a user")
    @Test
    public void testGetUser() throws Exception {
        when(dao.getUser(ID)).thenReturn(ENTITY);
        when(transformer.transform(ENTITY)).thenReturn(USER);

        assertEquals(USER, service.get(ID));
        verify(transformer).transform(ENTITY);
    }

    @DisplayName("Error when getting a user")
    @Test
    public void testGetUserErrors() throws Exception {
        when(dao.getUser(ID)).thenThrow(new SQLException());
        assertThrows(ServiceException.class, () -> service.get(ID));
    }

    @DisplayName("Save user")
    @Test
    public void testSaveUser() throws Exception {
        when(transformer.transform(USER)).thenReturn(ENTITY);
        doNothing().when(dao).saveUser(ENTITY);
        service.save(USER);
        verify(dao).saveUser(ENTITY);
    }
 
    @DisplayName("Error when saving a user")
    @Test
    public void testSaveUserErrors() throws Exception {
        when(transformer.transform(USER)).thenReturn(ENTITY);
        doThrow(new SQLException()).when(dao).saveUser(ENTITY);
        assertThrows(ServiceException.class, () -> service.save(USER));
    }

    @DisplayName("Update user")
    @Test
    public void testUpdateUser() throws Exception {
        when(transformer.transform(USER)).thenReturn(ENTITY);
        doNothing().when(dao).updateUser(ENTITY);
        service.update(USER);
        verify(dao).updateUser(ENTITY);
    }

    @DisplayName("Error when updating a user")
    @Test
    public void testUpdateUserErrors() throws Exception {
        when(transformer.transform(USER)).thenReturn(ENTITY);
        doThrow(new SQLException()).when(dao).updateUser(ENTITY);
        assertThrows(ServiceException.class, () -> service.update(USER));
    }

    @DisplayName("Delete user")
    @Test
    public void testDeleteUser() throws Exception {
        doNothing().when(dao).deleteUser(ID);
        service.delete(ID);
        verify(dao).deleteUser(ID);
    }

    @DisplayName("Error when deleting a user")
    @Test
    public void testDeleteUserErrors() throws Exception {
        doThrow(new SQLException()).when(dao).deleteUser(ID);
        assertThrows(ServiceException.class, () -> service.delete(ID));
    }
}
