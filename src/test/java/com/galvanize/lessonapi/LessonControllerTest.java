package com.galvanize.lessonapi;
import com.galvanize.lessonapi.domain.Lesson;
import com.galvanize.lessonapi.repository.LessonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import javax.transaction.Transactional;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LessonControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    LessonRepository lessonRepository;
    @Test
    @Transactional
    @Rollback
    public void testCreateLesson() throws Exception {
        MockHttpServletRequestBuilder request = post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"java\"}");

        this.mvc.perform(request)
                .andExpect(status().isCreated());

    }
    @Test
    @Transactional
    @Rollback
    public void testGetLesson() throws Exception {
        Lesson lesson = new Lesson();
        lesson.setTitle("Java");
        lessonRepository.save(lesson);

        MockHttpServletRequestBuilder request = get("/lessons")
                .contentType(MediaType.APPLICATION_JSON);
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(((int) lesson.getId()))));
    }
}
