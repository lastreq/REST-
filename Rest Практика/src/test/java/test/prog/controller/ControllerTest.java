package test.prog.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import test.prog.app.RestfulApplication;
import test.prog.model.Profile;
import test.prog.service.ProfileService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestfulApplication.class)

public class ControllerTest {
    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

    RestTemplate restTemplate = new RestTemplate();
    Profile profile = new Profile();
    @Autowired

    ProfileService profileService;

    @Before
    public void before() {

    }

    @Test
    public void testProfileGet() throws Exception {

        ResponseEntity<Profile> responseEntity =
                restTemplate.exchange("http://localhost:4002/profile/get/{id}", HttpMethod.GET, null,
                        new ParameterizedTypeReference<Profile>() {
                        });

        profile = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        log.info("Вывод записи полученной методом GET в тесте: ID:{} FirstName:{} LastName:{} Address:{} Age:{} Email:{} Country:{}", profile.getId(), profile.getFirstName(), profile.getLastName(), profile.getAddress());

    }

    @Test
    public void testProfilePost() {


        String url = "http://localhost:4002/profile/post";
        String requestJson = "{\"firstName\":\"Кириллл\",\"lastName\":\"Сувороввв\",\"address\":\"Ленина 11\",\"age\":23,\"email\":\"mytest@google.com\",\"country\":\"Россия\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        String answer = restTemplate.postForObject(url, entity, String.class);
        System.out.println(answer);

    }


    @Test
    public void testProfileDelete() {

        ResponseEntity<Profile> responseEntity =
                restTemplate.exchange("http://localhost:4002/profile/get/10", HttpMethod.GET, null,
                        new ParameterizedTypeReference<Profile>() {
                        });

        profile = responseEntity.getBody();

                restTemplate.exchange("http://localhost:4002/profile/delete/10", HttpMethod.DELETE, null,
                        new ParameterizedTypeReference<Profile>() {
                        });


        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        log.info("Вывод записи удаленной методом Delete в тесте: ID:{} FirstName:{} LastName:{} Address:{} Age:{} Email:{} Country:{}", profile.getId(), profile.getFirstName(), profile.getLastName(), profile.getAddress());

    }
    }

