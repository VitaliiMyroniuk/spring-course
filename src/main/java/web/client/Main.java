package web.client;

import beans.dtos.UserDTO;
import web.client.rest.RestClient;

public class Main {

	public static void main(String[] args) {
		RestClient restClient = new RestClient();

		UserDTO user1 = restClient.getUserById(1);
		System.out.println(user1);
		System.out.println(user1.getEmail());

		UserDTO userDTO = new UserDTO();
		userDTO.setEmail("test@test.com");
		userDTO.setName("Test");
		userDTO.setBirthday("2000-05-20");
		userDTO.setPassword("1234");
		restClient.createUser(userDTO);

		UserDTO user2 = restClient.getUserById(2);
		System.out.println(user2);
	}

}
