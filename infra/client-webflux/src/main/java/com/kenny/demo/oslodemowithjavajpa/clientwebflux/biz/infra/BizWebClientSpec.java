package com.kenny.demo.oslodemowithjavajpa.clientwebflux.biz.infra;


import com.kenny.demo.oslodemowithjavajpa.clientwebflux.dto.CommonRequest;
import com.kenny.demo.oslodemowithjavajpa.clientwebflux.dto.CommonResponse;
import com.kenny.demo.oslodemowithjavajpa.clientwebflux.dto.account.AccountInfo;
import com.kenny.demo.oslodemowithjavajpa.clientwebflux.dto.customer.BaseAccountList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BizWebClientSpec {

    @PostMapping("/v1/biz/customer/get/base_account_list")
    Mono<CommonResponse<BaseAccountList.Out>> getBaseAccountListByCstno(CommonRequest<BaseAccountList.In> in);

    @GetMapping("/v1/biz/account/dep/info/{acno}")
    Mono<CommonResponse<AccountInfo.Out>> getDepAccountInfo(String acno);

    @GetMapping("/v1/biz/account/loan/info/{acno}")
    Mono<CommonResponse<AccountInfo.Out>> getLoanAccountInfo(String acno);
}
