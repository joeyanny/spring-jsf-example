package example.spring.jsf.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

import example.spring.jsf.exception.ServiceException;
import example.spring.jsf.model.User;
import example.spring.jsf.model.UserList;
import example.spring.jsf.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private static final long ID = 1;
    private static final User USER = new User();
    private static final UserList USER_LIST = new UserList();
    private static final String ADD_USER_PAGE = "useradd?faces-redirect=true";
    private static final String USER_DETAILS_PAGE = "userdisplay?faces-redirect=true";
    private static final String LIST_USERS_PAGE = "userlistview?faces-redirect=true";
    private static final String ERROR_PAGE = "error";

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController controller;

    @DisplayName("Load new user page")
    @Test
    public void testAddNewUser() {
        assertEquals(ADD_USER_PAGE, controller.addUser());
    }

    @DisplayName("Load view user page")
    @Test
    public void testViewUserDetails() throws ServiceException {
        when(userService.get(ID)).thenReturn(USER);
        assertEquals(USER_DETAILS_PAGE, controller.viewUser(ID));
        assertEquals(USER, controller.getUser());
    }

    @DisplayName("Load view user page errors")
    @Test
    public void testViewUserDetailsErrors() throws ServiceException {
        when(userService.get(ID)).thenThrow(new ServiceException("Unit test error", null));
        assertEquals(ERROR_PAGE, controller.viewUser(ID));
    }

    @DisplayName("Load view users page")
    @Test
    public void testViewUsers() throws ServiceException {
        when(userService.getAll()).thenReturn(USER_LIST);
        assertEquals(LIST_USERS_PAGE, controller.viewUserList());
        assertEquals(USER_LIST, controller.getUserList());
    }

    @DisplayName("Load view users page errors")
    @Test
    public void testViewUsersErrors() throws ServiceException {
        when(userService.getAll()).thenThrow(new ServiceException("Unit test error", null));
        assertEquals(ERROR_PAGE, controller.viewUserList());
    }

    @DisplayName("Save user")
    @Test
    public void testSaveUser() throws ServiceException {
        controller.setUser(USER);
        doNothing().when(userService).save(USER);
        when(userService.getAll()).thenReturn(USER_LIST);

        String result = controller.saveUser();

        assertEquals(LIST_USERS_PAGE, result);
        assertEquals(USER_LIST, controller.getUserList());
        verify(userService).save(USER);
    }

    @DisplayName("Save user errors")
    @Test
    public void testSaveUserErrors() throws ServiceException {
        controller.setUser(USER);
        doThrow(new ServiceException("Unit test error", null)).when(userService).save(USER);
        assertEquals(ERROR_PAGE, controller.saveUser());
        verify(userService).save(USER);
    }

    @DisplayName("Update user")
    @Test
    public void testUpdateUser() throws ServiceException {
    	controller.setUser(USER);
        doNothing().when(userService).update(USER);
        when(userService.getAll()).thenReturn(USER_LIST);

        String result = controller.updateUser();

        assertEquals(LIST_USERS_PAGE, result);
        assertEquals(USER_LIST, controller.getUserList());
        verify(userService).update(USER);
    }

    @DisplayName("Update user errors")
    @Test
    public void testUpdateUserErrors() throws ServiceException {
    	controller.setUser(USER);
        doThrow(new ServiceException("Unit test error", null)).when(userService).update(USER);
        assertEquals(ERROR_PAGE, controller.updateUser());
        verify(userService).update(USER);
    }

    @DisplayName("Delete user")
    @Test
    public void testDeleteUser() throws ServiceException {
        doNothing().when(userService).delete(ID);
        when(userService.getAll()).thenReturn(USER_LIST);

        String result = controller.deleteUser(ID);

        assertEquals(LIST_USERS_PAGE, result);
        assertEquals(USER_LIST, controller.getUserList());
        verify(userService).delete(ID);
    }

    @DisplayName("Delete user errors")
    @Test
    public void testDeleteUserErrors() throws ServiceException {
        doThrow(new ServiceException("Unit test error", null)).when(userService).delete(ID);
        assertEquals(ERROR_PAGE, controller.deleteUser(ID));
        verify(userService).delete(ID);
    }
}
