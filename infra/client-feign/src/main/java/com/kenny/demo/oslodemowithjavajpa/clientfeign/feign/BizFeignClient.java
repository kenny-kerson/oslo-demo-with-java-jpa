package com.kenny.demo.oslodemowithjavajpa.clientfeign.feign;

import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonRequest;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonResponse;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.account.AccountInfo;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.account.DepAccountInfo;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.account.LoanAccountInfo;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.customer.BaseAccountList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        url = "${feign.biz.url}",
        name = "${feign.biz.name}"
)
public interface BizFeignClient {

    @PostMapping("/v1/biz/customer/get/base_account_list")
    CommonResponse<BaseAccountList.Out> getBaseAccountListByCstno(CommonRequest<BaseAccountList.In> in);

    @GetMapping("/v1/biz/account/dep/info/{acno}")
    CommonResponse<AccountInfo.Out> getDepAccountInfo(@PathVariable("acno") String acno);

    @GetMapping("/v1/biz/account/loan/info/{acno}")
    CommonResponse<AccountInfo.Out> getLoanAccountInfo(@PathVariable("acno") String acno);
}
