package com.kenny.demo.oslodemowithjavajpa.biz.account.loan.ui;

import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonResponse;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.account.AccountInfo;
import org.springframework.web.bind.annotation.GetMapping;

public interface LoanAccountControllerSpec {

    @GetMapping("/v1/biz/account/loan/info/{acno}")
    CommonResponse<AccountInfo.Out> getLoanAccountInfo(String acno);
}
