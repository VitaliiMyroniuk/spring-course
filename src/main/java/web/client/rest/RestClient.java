package web.client.rest;

import beans.dtos.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {

	private static final String REST_URI = "http://localhost:8090/rest";

	public UserDTO getUserById(long id) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = REST_URI + "/users/" + id;
		return restTemplate.getForObject(uri, UserDTO.class);
	}

	public void createUser(UserDTO userDTO) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = REST_URI + "/users";
		restTemplate.postForObject(uri, userDTO, ResponseEntity.class);
	}

}
