package com.kenny.demo.oslodemowithjavajpa.clientfeign.feign;

import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonRequest;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonResponse;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.customer.BaseAccountList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${client.biz.url}", name = "${client.biz.name}")
public interface BizFeignClient {

    @PostMapping("/v1/biz/customer/get/base_account_list")
    CommonResponse<BaseAccountList.Out> getBaseAccountListByCstno(CommonRequest<BaseAccountList.In> in);
}
