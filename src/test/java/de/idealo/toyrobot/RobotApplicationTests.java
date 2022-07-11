package de.idealo.toyrobot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


@ExtendWith(SpringExtension.class)
@WebMvcTest(RobotController.class)
class RobotApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void robotMove() throws Exception {
		String mockRequest = "{\"instructions\":[\"POSITION 1 1 EAST\", \"FORWARD 2\", \"RIGHT\", \"FORWARD 2\"], \"grid\": {\"width\": 5, \"height\": 5} }";

		String expectedResponse = "{coordinates:{x: 3, y:3 }, direction: SOUTH}";

		mockMvc.perform(MockMvcRequestBuilders.post("/robot")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mockRequest))
				.andExpect(content().json(expectedResponse));
	}

	// Expect robot to reset if it moves out of limits
	@Test
	void robotOutOfLimit() throws Exception {
		String mockRequest = "{\"instructions\":[\"POSITION 1 1 EAST\", \"FORWARD 2\", \"RIGHT\", \"FORWARD 3\", \"FORWARD 3\"], \"grid\": {\"width\": 5, \"height\": 5} }";

		String expectedResponse = "{coordinates: {x:0, y:0}, direction: EAST}";

		MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post("/robot");

		mockMvc.perform(postRequest
				.contentType(MediaType.APPLICATION_JSON)
				.content(mockRequest))
				.andExpect(content().json(expectedResponse));
	}
}
