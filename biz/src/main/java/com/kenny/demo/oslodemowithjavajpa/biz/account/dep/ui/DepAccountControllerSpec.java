package com.kenny.demo.oslodemowithjavajpa.biz.account.dep.ui;

import com.kenny.demo.oslodemowithjavajpa.biz.account.dep.ui.dto.AccountInfo;
import com.kenny.demo.oslodemowithjavajpa.biz.common.dto.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface DepAccountControllerSpec {

    @GetMapping("/v1/biz/account/dep/info/{acno}")
    CommonResponse<AccountInfo.Out> getDepAccountInfo(@PathVariable String acno);
}
