package beans.converters.impl;

import beans.converters.DtoConverter;
import beans.dtos.UserDTO;
import beans.models.User;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;

@Component
public class UserDtoConverter implements DtoConverter<User, UserDTO> {

	@Resource
	private ConversionService conversionService;

	@Override
	public User convertToEntity(UserDTO UserDTO) {
		User user = new User();
		user.setEmail(UserDTO.getEmail());
		user.setName(UserDTO.getName());
		user.setBirthday(conversionService.convert(UserDTO.getBirthday(), LocalDate.class));
		user.setPassword(UserDTO.getPassword());
		return user;
	}

	@Override
	public UserDTO convertToDto(User user) {
		UserDTO UserDTO = new UserDTO();
		UserDTO.setEmail(user.getEmail());
		UserDTO.setName(user.getName());
		UserDTO.setPassword(user.getPassword());
		UserDTO.setBirthday(conversionService.convert(user.getBirthday(), String.class));
		return UserDTO;
	}

}
