package jp.co.rngd.ss.todo.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import jp.co.rngd.ss.todo.RngdServerSideTodoApplicationTestConfiguration;
import jp.co.rngd.ss.todo.models.AppUserModelRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Import(RngdServerSideTodoApplicationTestConfiguration.class)
public class AuthControllerTest {
    
    @Autowired
    private MockMvc authControllerMock;
    @Autowired
    private AuthController authController;
    @Autowired
    private AppUserModelRepository appUserModelRepository;
    
    @Before("execute")
    public void setup() {
        this.authControllerMock = MockMvcBuilders.standaloneSetup(authController).build();
    }
    
    @Test
    public void index_TODOブランドトップページが表示される() throws Exception {
        this.authControllerMock.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(
                model().attribute("title", is("RngdTodo"))
        )
        .andExpect(
                content().string(
                        containsString("タスクを自由に管理しよう")
                )
        )
        ;
    }
    
    @Test
    public void register_新規登録ページが表示される() throws Exception {
        this.authControllerMock.perform(get("/register"))
        .andExpect(status().isOk())
        .andExpect(
                model().attribute("title", is("新規登録"))
        )
        .andExpect(
                content().string(
                        containsString("TODOに新規登録")
                )
        );
        
    }
    
    @Test
    public void signup_ユーザの新規登録ができる() throws Exception {
        String userName = "TestUser@hoge.com";
        String password = "testuser";
        this.authControllerMock.perform(
                post("/signup")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .param("user", userName)
                .param("password", password)
                .param("password_confirm", password)
        )
        .andExpect(status().is3xxRedirection())
        .andExpect(
                view().name("redirect:/top")
        )
        ;
//        assertThat(appUserModelRepository.findByUsername(userName).getUsername(), is(userName));
    }
    
    @Test
    public void login_ログインページが表示される() throws Exception {
        this.authControllerMock.perform(
                get("/login")
        )
        .andExpect(
                status().isOk()
        )
        .andExpect(
                model().attribute("title", "ログイン")
        )
        .andExpect(
                content().string(
                        containsString("TODOにログイン")
                )
        );
    }
    
}
