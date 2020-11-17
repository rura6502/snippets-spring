package io.github.rura6502.basic;

import static org.hamcrest.Matchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest
@AutoConfigureRestDocs
public class StudentControllerTest extends ApiDocumentUtils {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objMapper;

  @MockBean
  private StudentService studentSrv;

  @Test
  public void test_save_new_student() throws Exception {
    
    Student newStudent = new Student(10, "HONG", Type.F, LocalDate.now());

    given(studentSrv.save(newStudent)).willReturn(newStudent);

    ResultActions result = mockMvc.perform(
      post("/api/student", newStudent)
        .content(objMapper.writeValueAsString(newStudent))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
    );

    result.andExpect(status().isOk())
                .andDo(document("{ClassName}/{methodName}"
                                            , getDocumentRequest()
                                            , getDocumentResponse()
                                            , requestFields(
                                              fieldWithPath("id").type(JsonFieldType.NUMBER).description("아이디")
                                              , fieldWithPath("name").type(JsonFieldType.STRING).description("이름")
                                              , fieldWithPath("type").type(JsonFieldType.STRING).description("결과 타입")
                                              , fieldWithPath("birthday").type(JsonFieldType.STRING).description("결과 메일")
                                              
                                            )
                                            , responseFields(
                                              fieldWithPath("id").type(JsonFieldType.NUMBER).description("결과 아이디")
                                              , fieldWithPath("name").type(JsonFieldType.STRING).description("결과 이름")
                                              , fieldWithPath("type").type(JsonFieldType.STRING).description("결과 타입")
                                              , fieldWithPath("birthday").type(JsonFieldType.STRING).description("결과 메일")
                                            )));
  }

  

  
}
