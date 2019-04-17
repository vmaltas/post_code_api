package com.postcodedistance.post.code.api;

import com.postcodedistance.post.code.api.controller.PostCodeApiController;
import com.postcodedistance.post.code.api.repository.PostCodeRepository;
import com.postcodedistance.post.code.api.service.impl.PostCodeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(PostCodeApiController.class)
@EnableJpaRepositories("com.postcodedistance.post.code.api.repository")
public class PostCodeApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostCodeServiceImpl postCodeService;

    @MockBean
    private PostCodeRepository postCodeRepository;

    @InjectMocks
    private PostCodeApiController postCodeApiController;



    @Test
    public void testGetDistanceSuccessful() throws Exception {

        mockMvc.perform(get("/postCodeApi/getDistance?firstUkPostalCode=AB24 1WS&secondUkPostalCode=AB24 1XD")
                .with(user("postCodeAdmin")))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"firstUkPostalCode\": {\n" +
                        "        \"postalCode\": \"AB24 1WS\",\n" +
                        "        \"latitude\": 57.17333145,\n" +
                        "        \"longitude\": -2.09524168\n" +
                        "    },\n" +
                        "    \"secondUkPostalCode\": {\n" +
                        "        \"postalCode\": \"AB24 1XD\",\n" +
                        "        \"latitude\": 57.1677984,\n" +
                        "        \"longitude\": -2.09463214\n" +
                        "    },\n" +
                        "    \"distance\": 0.6163433926108876,\n" +
                        "    \"distanceUnit\": \"km\"\n" +
                        "}"));
    }

    @Test
    public void testGetDistanceError() throws Exception {

        mockMvc.perform(get("/postCodeApi/getDistance?firstUkPostalCode=AAAA 1WS&secondUkPostalCode=BBBB 1XD")
                .with(user("postCodeAdmin")))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"firstUkPostalCode\": {\n" +
                        "        \"postalCode\": \"AB24 1WS\",\n" +
                        "        \"latitude\": 57.17333145,\n" +
                        "        \"longitude\": -2.09524168\n" +
                        "    },\n" +
                        "    \"secondUkPostalCode\": {\n" +
                        "        \"postalCode\": \"AB24 1XD\",\n" +
                        "        \"latitude\": 57.1677984,\n" +
                        "        \"longitude\": -2.09463214\n" +
                        "    },\n" +
                        "    \"distance\": 0.6163433926108876,\n" +
                        "    \"distanceUnit\": \"km\"\n" +
                        "}"));
    }

}
