package com.kenny.demo.oslodemowithjavajpa.biz.customer.ui;

import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonRequest;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.CommonResponse;
import com.kenny.demo.oslodemowithjavajpa.clientfeign.dto.customer.BaseAccountList;
import com.kenny.demo.oslodemowithjavajpa.common.code.StatusCode;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController implements CustomerControllerSpec {

    /**
     * 고객별 기본계좌 목록조회
     */
    @Override
    public CommonResponse<BaseAccountList.Out> getBaseAccountListByCstno(@RequestBody CommonRequest<BaseAccountList.In> in) {

        // TODO 임시코드
        return CommonResponse.<BaseAccountList.Out>builder()
                .status(StatusCode.OK.getCode())
                .guid(in.getGuid())
                .cstno(in.getCstno())
                .dataBody(
                        BaseAccountList.Out.builder()
                                .representationAcno(in.getDataBody().getRepresentationAcno())
                                .grid01(
                                        List.of(
                                                BaseAccountList.Grid01.builder()
                                                        .acno("3333010000001")
                                                        .firstDivisionCode("01")
                                                        .build(),
                                                BaseAccountList.Grid01.builder()
                                                        .acno("3333010000002")
                                                        .firstDivisionCode("01")
                                                        .build(),
                                                BaseAccountList.Grid01.builder()
                                                        .acno("3333010000003")
                                                        .firstDivisionCode("01")
                                                        .build()
                                        )
                                )
                                .build()
                )
                .build();
    }
}
