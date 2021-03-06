package com.kenny.demo.oslodemowithjavajpa.biz.customer.ui;

import com.kenny.demo.oslodemowithjavajpa.biz.common.dto.CommonRequest;
import com.kenny.demo.oslodemowithjavajpa.biz.common.dto.CommonResponse;
import com.kenny.demo.oslodemowithjavajpa.biz.customer.ui.dto.BaseAccountList;
import com.kenny.demo.oslodemowithjavajpa.common.code.StatusCode;
import com.kenny.demo.oslodemowithjavajpa.dbpostgresql.account.dep.domain.DepositAccountInfoMRepository;
import com.kenny.demo.oslodemowithjavajpa.dbpostgresql.customer.domain.CustomerBaseAccount;
import com.kenny.demo.oslodemowithjavajpa.dbpostgresql.customer.domain.CustomerInfoM;
import com.kenny.demo.oslodemowithjavajpa.dbpostgresql.customer.domain.CustomerInfoMRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomerController implements CustomerControllerSpec {

    // TODO 이거는 biz 모듈의 domain, infra 로직으로 변경 필요! 지금 로직은 테스트용!
    private final CustomerInfoMRepository customerInfoMRepository;

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
                                                        .build(),
                                                BaseAccountList.Grid01.builder()
                                                        .acno("3650010000001")
                                                        .firstDivisionCode("02")
                                                        .build(),
                                                BaseAccountList.Grid01.builder()
                                                        .acno("3650010000002")
                                                        .firstDivisionCode("02")
                                                        .build()
                                        )
                                )
                                .build()
                )
                .build();
    }
}
