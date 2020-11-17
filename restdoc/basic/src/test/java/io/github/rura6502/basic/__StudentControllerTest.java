// package io.github.rura6502.basic;

// import static org.hamcrest.Matchers.is;
// import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
// import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
// import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
// import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
// import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
// import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
// import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
// import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import java.time.LocalDate;

// import com.fasterxml.jackson.databind.ObjectMapper;

// import org.junit.jupiter.api.Order;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
// import org.springframework.restdocs.payload.JsonFieldType;
// import org.springframework.test.web.servlet.MockMvc;


// @SpringBootTest
// @AutoConfigureMockMvc
// @AutoConfigureRestDocs(uriScheme = "https", uriHost="api.dev.iochord.com")
// public class __StudentControllerTest {
  

//   @Autowired
//   private MockMvc mockMvc;

//   @Autowired
//   private ObjectMapper objMapper;

//   @Test
//   @Order(1)
//   public void test_save_new_student() throws Exception {

//     Student newStudent = new Student(10, "HONG", Type.F, LocalDate.now());

//     this
//       .mockMvc
//       .perform(
//         post("/api/student")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON)
//         .content(objMapper.writeValueAsString(newStudent)))
//       .andExpect(status().isOk())
//       .andExpect(content().json(objMapper.writeValueAsString(newStudent)))
//       .andDo(
//         document(
//           "save-student"
//           , preprocessResponse(prettyPrint())
//           , requestFields(
//             fieldWithPath("id").type(JsonFieldType.NUMBER).description("student identity")
//             , fieldWithPath("name").type(JsonFieldType.STRING).description("student's name")
//             , fieldWithPath("type").type(JsonFieldType.VARIES).description("class student in")
//             , fieldWithPath("birthday").type(JsonFieldType.STRING).description("student birthday")
//           )
//           , responseFields(
//             fieldWithPath("id").type(JsonFieldType.NUMBER).description("student identity")
//             , fieldWithPath("name").type(JsonFieldType.STRING).description("student's name")
//             , fieldWithPath("type").type(JsonFieldType.VARIES).description("class student in")
//             , fieldWithPath("birthday").type(JsonFieldType.STRING).description("student birthday"))))
//             ;
//   }

//   @Test
//   @Order(2)
//   public void get_student_by_id() throws Exception {
//     this
//       .mockMvc
//       .perform(
//         RestDocumentationRequestBuilders
//           .get("/api/student/{id}", 10)
//           .contentType(MediaType.APPLICATION_JSON)
//           .accept(MediaType.APPLICATION_JSON))
//       .andExpect(status().isOk())
//       .andExpect(jsonPath("$.name", is("HONG")))
//       .andDo(print())
//       .andDo(
//         document(
//           "get-student"
//           , preprocessResponse(prettyPrint())
//           , pathParameters(
//             parameterWithName("id").description("student's identity"))
//           , responseFields(
//             fieldWithPath("id").type(JsonFieldType.NUMBER).description("student identity")
//             , fieldWithPath("name").type(JsonFieldType.STRING).description("student's name")
//             , fieldWithPath("type").type(JsonFieldType.STRING).description("class student in")
            
//             , fieldWithPath("birthday").type(JsonFieldType.STRING).description("student birthday"))))
//     ;
//   }

// }