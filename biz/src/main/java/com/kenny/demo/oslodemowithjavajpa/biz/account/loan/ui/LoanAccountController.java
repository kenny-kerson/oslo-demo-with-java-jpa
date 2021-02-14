package com.kenny.demo.oslodemowithjavajpa.biz.account.loan.ui;

import com.kenny.demo.oslodemowithjavajpa.biz.account.loan.ui.dto.AccountInfo;
import com.kenny.demo.oslodemowithjavajpa.biz.common.dto.CommonResponse;
import com.kenny.demo.oslodemowithjavajpa.common.code.StatusCode;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanAccountController implements LoanAccountControllerSpec {

    @Override
    public CommonResponse<AccountInfo.Out> getLoanAccountInfo(String acno) {
        // TODO 여신계좌정보조회 로직 추가 필요!!
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return CommonResponse.<AccountInfo.Out>builder()
                .status(StatusCode.OK.getCode())
                .guid("TBD")
                .cstno("TBD")
                .dataBody(
                        AccountInfo.Out.builder()
                                .acno(acno)
                                .firstDivisionCode("02")
                                .acnoStcd("00")
                                .acnoName("여신계좌")
                                .build()
                )
                .build();
    }
}
