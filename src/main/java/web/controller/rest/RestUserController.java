package web.controller.rest;

import beans.converters.DtoConverter;
import beans.dtos.UserDTO;
import beans.models.User;
import beans.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/rest/users")
public class RestUserController {

	@Resource
	private UserService userService;
	@Resource
	private DtoConverter<User, UserDTO> userConverter;

	@RequestMapping(value = "/{id}", method = GET)
	public ResponseEntity<UserDTO> getUserById(@PathVariable long id) {
		User user = userService.getById(id);
		if (user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		UserDTO userDTO = userConverter.convertToDto(user);
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

	@RequestMapping(method = POST)
	public ResponseEntity createUser(@RequestBody UserDTO userDTO) {
		User user = userConverter.convertToEntity(userDTO);
		userService.register(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(method = PUT)
	public ResponseEntity updateUser(@RequestBody UserDTO userDTO) {
		User user = userService.getUserByEmail(userDTO.getEmail());
		if (user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		// Not implemented at service layer

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = DELETE)
	public ResponseEntity deleteUser(@PathVariable long id) {
		User user = userService.getById(id);
		if (user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		userService.remove(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
