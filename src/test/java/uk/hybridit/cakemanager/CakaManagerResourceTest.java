package uk.hybridit.cakemanager;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uk.hybridit.cakemanager.uk.waracle.cakemanager.entity.Cake;
import uk.hybridit.cakemanager.uk.waracle.cakemanager.entity.CakeManagerRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CakaManagerResource.class)
public class CakaManagerResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CakeManagerRepository cakeManagerRepository;

    String cakeJson = "{\"id\":10001,\"name\":\"Lemon cheesecake\",\"description\":\"A cheesecake made of lemon\",\"imageURL\":\"https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg\"}";

    private Optional<Cake> cake = Optional.of(new Cake(10001l,"Lemon cheesecake", "A cheesecake made of lemon", "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"));

    @Test
    public void retrieveStudent() throws Exception {
        {

            Mockito.when(
                    cakeManagerRepository.findById(Mockito.anyLong())).thenReturn(cake);

            RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                    "/cake/10001").accept(
                    MediaType.APPLICATION_JSON);

            MvcResult result = mockMvc.perform(requestBuilder).andReturn();

            System.out.println(result.getResponse().getContentAsString());
            String expected = "{id:10001}";

            JSONAssert.assertEquals(expected, result.getResponse()
                    .getContentAsString(), false);
        }
    }

    @Test
    public void createNewCake() throws Exception {
        Cake mockCake = new Cake(10001l,"Lemon cheesecake", "A cheesecake made of lemon", "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg");

        // studentService.addCourse to respond back with mockCourse
        Mockito.when(
                cakeManagerRepository.save(
                        Mockito.any(Cake.class))).thenReturn(mockCake);

        // Send course as body to /students/Student1/courses
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/cakes")
                .accept(MediaType.APPLICATION_JSON).content(cakeJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost/cakes%7B/10001%7D",
                response.getHeader(HttpHeaders.LOCATION));

    }

}