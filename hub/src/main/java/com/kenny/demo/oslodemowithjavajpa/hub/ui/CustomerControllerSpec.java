package com.kenny.demo.oslodemowithjavajpa.hub.ui;

import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonRequest;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonResponse;
import com.kenny.demo.oslodemowithjavajpa.hub.ui.dto.AllAccountListDto;
import org.springframework.web.bind.annotation.PostMapping;

public interface CustomerControllerSpec {

    @PostMapping("/v1/hub/customer/all_account_list")
    CommonResponse<AllAccountListDto.Out> getAllAccountList(CommonRequest<AllAccountListDto.In> input );
}
