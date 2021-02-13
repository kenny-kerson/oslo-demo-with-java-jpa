package com.kenny.demo.oslodemowithjavajpa.biz.account.dep.ui;

import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonResponse;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.account.AccountInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface DepAccountControllerSpec {

    @GetMapping("/v1/biz/account/dep/info/{acno}")
     CommonResponse<AccountInfo.Out> getDepAccountInfo(@PathVariable String acno);
}
