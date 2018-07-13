package org.ipph.spider.sipop.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SipopPatentFeePaidControllerTest {
	
private MockMvc mockMvc;
	
	@Autowired
    protected WebApplicationContext wac;
    
    @Before
    public void setUp() throws Exception {
    	mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
   
    @Test
    public void testSyncPatent() throws Exception {
    	Object[] appNumbers=new String[] {"CN200480016783.7","CN200430011881.4"};
    	
		String json=JSONObject.toJSONString(appNumbers);
		
		String responseString = 
			mockMvc.perform(MockMvcRequestBuilders.post("/patent/paidFee").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(json))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn().getResponse().getContentAsString();
		System.out.println("--------返回的json = " + responseString);
    }

}
