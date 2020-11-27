package example.spring.jsf.transformer;

import java.util.ArrayList;
import java.util.List;

import example.spring.jsf.dao.UserEntity;
import example.spring.jsf.dao.UserListEntity;
import example.spring.jsf.model.User;
import example.spring.jsf.model.UserList;

/**
 * Transformer used to convert between the resource and data
 * store representations. This is used to decouple the API
 * endpoints from the data store.
 */
public class UserTransformer {

	/**
	 * Transform from the resource representation to the data store representation
	 * 
	 * @param user Data model representing the resource
	 * @return entity Data model representing the data store
	 */
	public UserEntity transform(User user) {
		UserEntity entity = new UserEntity();
		entity.setId(user.getId());
		entity.setFirstName(user.getFirstName());
		entity.setLastName(user.getLastName());
		entity.setHouseNumber(user.getHouseNumber());
		entity.setStreet(user.getStreet());
		entity.setCity(user.getCity());
		entity.setPostcode(user.getPostcode());

		return entity;
	}

	/**
	 * Transform from the data store representation to the resource representation
	 * 
	 * @param entityList Data model representing the data store
	 * @return userList Data model representing the resource
	 */
	public UserList transform(UserListEntity entityList) {
		List<User> users = new ArrayList<>();
		for(UserEntity entity : entityList.getUserList()) {
			users.add(transform(entity));
		}

		UserList userList = new UserList();
		userList.setUserList(users);

		return userList;
	}

	/**
	 * Transform from the data store representation to the resource representation
	 * 
	 * @param entity Data model representing the data store
	 * @return user Data model representing the resource
	 */
	public User transform(UserEntity entity) {
		User user = new User();
		user.setId(entity.getId());
		user.setFirstName(entity.getFirstName());
		user.setLastName(entity.getLastName());
		user.setHouseNumber(entity.getHouseNumber());
		user.setStreet(entity.getStreet());
		user.setCity(entity.getCity());
		user.setPostcode(entity.getPostcode());

		return user;
	}
}
