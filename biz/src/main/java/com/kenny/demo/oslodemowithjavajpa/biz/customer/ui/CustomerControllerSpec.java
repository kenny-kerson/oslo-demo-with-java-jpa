package com.kenny.demo.oslodemowithjavajpa.biz.customer.ui;

import com.kenny.demo.oslodemowithjavajpa.biz.customer.ui.dto.BaseAccountList;
import com.kenny.demo.oslodemowithjavajpa.biz.common.dto.CommonRequest;
import com.kenny.demo.oslodemowithjavajpa.biz.common.dto.CommonResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

public interface CustomerControllerSpec {

    @PostMapping(
            value = "/v1/biz/customer/get/base_account_list",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    CommonResponse<BaseAccountList.Out> getBaseAccountListByCstno(CommonRequest<BaseAccountList.In> in);
}
