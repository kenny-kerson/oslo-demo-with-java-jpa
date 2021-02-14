package com.kenny.demo.oslodemowithjavajpa.biz.account.loan.ui;

import com.kenny.demo.oslodemowithjavajpa.biz.account.loan.ui.dto.AccountInfo;
import com.kenny.demo.oslodemowithjavajpa.biz.common.dto.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface LoanAccountControllerSpec {

    @GetMapping("/v1/biz/account/loan/info/{acno}")
    CommonResponse<AccountInfo.Out> getLoanAccountInfo(@PathVariable String acno);
}
