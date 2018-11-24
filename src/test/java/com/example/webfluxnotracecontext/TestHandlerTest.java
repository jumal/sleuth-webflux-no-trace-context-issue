package com.example.webfluxnotracecontext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.ServerRequest;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static reactor.core.publisher.Mono.just;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class TestHandlerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private TestRepository repository;

    @Captor
    private ArgumentCaptor<TestEntity> entityArgumentCaptor;

    /**
     * @verifies set the context
     * @see TestHandler#save(ServerRequest)
     */
    @Test
    public void save_should_set_the_context() {
        // given
        TestEntity entity = new TestEntity("field1");
        given(repository.save(any())).willReturn(just(entity));

        // when
        webClient
                .post().uri("/test")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .header("X-Field2", "field2")
                .body(just(entity), TestEntity.class)
                .exchange();

        // then
        verify(repository).save(entityArgumentCaptor.capture());
        TestEntity saved = entityArgumentCaptor.getValue();
        assertThat(saved.getField2(), is("field2"));
    }
}
