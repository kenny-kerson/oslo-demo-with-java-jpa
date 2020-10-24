package com.kenny.demo.oslodemowithjavajpa.biz.account.dep.ui;

import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonResponse;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.account.AccountInfo;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.account.DepAccountInfo;
import com.kenny.demo.oslodemowithjavajpa.common.code.StatusCode;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepAccountController implements DepAccountControllerSpec {

    /**
     * 수신계좌 정보조회
     * @return
     */
    @Override
    public CommonResponse<AccountInfo.Out> getDepAccountInfo(final String acno ) {
        // TODO 수신계좌 정보조회 로직추가 필요!!
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return CommonResponse.<AccountInfo.Out>builder()
                .guid("TBD")
                .cstno("TBD")
                .status(StatusCode.OK.getCode())
                .dataBody(
                        AccountInfo.Out.builder()
                                .acno(acno)
                                .firstDivisionCode("01")
                                .acnoStcd("00")
                                .acnoName("수신계좌")
                                .build()
                )
                .build();
    }
}
