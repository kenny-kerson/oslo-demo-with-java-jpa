package com.kenny.demo.oslodemowithjavajpa.biz.account.dep.ui;

import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonRequest;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonResponse;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.account.DepAccountInfo;
import com.kenny.demo.oslodemowithjavajpa.common.code.StatusCode;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepAccountController implements DepAccountControllerSpec {

    /**
     * 수신계좌 정보조회
     */
    @Override
    public CommonResponse<DepAccountInfo.Out> getDepAccountInfo(final CommonRequest<DepAccountInfo.In>input, final String acno ) {
        // TODO 수신계좌 정보조회 로직추가 필요!!
        return CommonResponse.<DepAccountInfo.Out>builder()
                .guid(input.getGuid())
                .cstno(input.getCstno())
                .status(StatusCode.OK.getCode())
                .dataBody(
                        DepAccountInfo.Out.builder()
                                .acno(input.getDataBody().getAcno())
                                .firstDivisionCode("01")
                                .acnoStcd("00")
                                .acnoName("수신계좌")
                                .build()
                )
                .build();
    }
}
