package com.example.demo.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.impl.MemberServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(AutentificaController.class)
@ContextConfiguration
public class AutentificaControllerTest {

	@Autowired
	private WebApplicationContext context;
    @Autowired
    private AutentificaController controller;

    @Autowired
    private MockMvc mockMvc;

   @MockBean
    private MemberServiceImpl service;

   @Autowired
   private Filter springSecurityFilterChain;

   @Before
	public void setup() {
	   mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	   	}
   
    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void indexTest() throws Exception {
    	this.mockMvc.perform(get("/").with(user("user"))).andDo(print()).andExpect(status().isOk())
    	.andExpect(view().name("home"));
    }
    
    @Test
    public void homeTest() throws Exception {
    	this.mockMvc.perform(get("/home").with(user("user"))).andExpect(status().isOk())
    	.andExpect(view().name("home"));
    }
    
    @Test
    public void loginTest() throws Exception {
    	this.mockMvc.perform(get("/login").with(user("user").password("pass"))).andExpect(status().isOk())
    	.andExpect(view().name("login"));
    }
    
    @Test
    public void adminTest() throws Exception {
    	this.mockMvc.perform(get("/admin").with(user("user"))).andExpect(status().isOk())
    	.andExpect(view().name("admin"));
    }
    
    @Test
    public void myPageTest() throws Exception {
    	this.mockMvc.perform(get("/mypage").with(user("user"))).andExpect(status().isOk())
    	.andExpect(view().name("myPage"));
    }
    
    @Test
//    @WithUserDetails(value="user", userDetailsServiceBeanName="service")
    public void formLoginAuthenticatedTest() throws Exception {
       	ResultActions ac = this.mockMvc.perform(formLogin("/login").user("username","user").password("password","pass"));
       	ac.andExpect(authenticated());
       	
    }
    @Test
    public void formLoginUnAuthenticatedTest() throws Exception {
    	ResultActions ac = this.mockMvc.perform(formLogin("/login").user("username","user").password("password","invalid"));
       	ac.andExpect(unauthenticated());
   }
    
    
}
