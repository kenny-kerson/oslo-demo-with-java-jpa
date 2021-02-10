package com.kenny.demo.oslodemowithjavajpa.hub.ui;

import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonRequest;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonResponse;
import com.kenny.demo.oslodemowithjavajpa.hub.ui.dto.AllAccountListDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface CustomerControllerSpec {

    @PostMapping(
            value = "/v1/hub/customer/all_account_list/cf",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    CommonResponse<AllAccountListDto.Out> getAllAccountListByCf(CommonRequest<AllAccountListDto.In> input ) throws InterruptedException, ExecutionException, TimeoutException;

    @PostMapping(
            value = "/v1/hub/customer/all_account_list/webflux",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    CommonResponse<AllAccountListDto.Out> getAllAccountListByWebFlux(CommonRequest<AllAccountListDto.In> input ) throws InterruptedException, ExecutionException, TimeoutException;
}
