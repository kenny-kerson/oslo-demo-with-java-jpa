package com.kenny.demo.oslodemowithjavajpa.biz.customer.ui;

import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonRequest;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonResponse;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.customer.BaseAccountList;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

public interface CustomerControllerSpec {

    @PostMapping("/v1/biz/customer/get/base_account_list")
    CommonResponse<BaseAccountList.Out> getBaseAccountListByCstno(CommonRequest<BaseAccountList.In> in);
}
