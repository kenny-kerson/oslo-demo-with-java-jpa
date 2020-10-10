package com.kenny.demo.oslodemowithjavajpa.hub.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonRequest;
import com.kenny.demo.oslodemowithjavajpa.hub.common.IntegrationTest;
import com.kenny.demo.oslodemowithjavajpa.hub.ui.dto.AllAccountListDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest extends IntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("# 전계좌조회 : 정상")
    @Order(1)
    void getAllAccountList() throws Exception {
        // Given
        final CommonRequest<AllAccountListDto.In> input = CommonRequest.<AllAccountListDto.In>builder()
                .guid("20201010120000OSLO-DEMO-HUB000111001")
                .cstno("0000011000000331")
                .dataBody(
                        AllAccountListDto.In.builder()
                                .representationAcno("3333010000001")
                                .build()
                )
                .build();

        // When & Then
        mockMvc.perform(post("/v1/hub/customer/all_account_list")
                .content(objectMapper.writeValueAsString(input))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }
}