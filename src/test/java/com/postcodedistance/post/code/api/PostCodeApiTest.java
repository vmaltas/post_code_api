package com.postcodedistance.post.code.api;

import com.postcodedistance.post.code.api.controller.PostCodeApiController;
import com.postcodedistance.post.code.api.service.PostCodeService;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(PowerMockRunner.class)
public class PostCodeApiTest {


    @Mock
    private PostCodeService postCodeService;


    @InjectMocks
    private PostCodeApiController postCodeApiController;

    private MockMvc mockMvc;


    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(postCodeApiController)
                .build();



    }

    @Test
    public void testGetDistance() throws Exception {



        this.mockMvc.perform(MockMvcRequestBuilders.get("/postCodeApi/getDistance?firstUkPostalCode=AB24 1WS&secondUkPostalCode=AB24 1XD")
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
