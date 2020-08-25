package com.kenny.demo.oslodemowithjavajpa.biz.common;

import lombok.Builder;
import lombok.Getter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("스트림 기본 테스트")
class StreamTest {

    @Test
    @DisplayName("스트림 테스트케이스 1")
    void streamTest1() {
        System.out.println( "__KENNY__ streamTest1()");

        List<SampleDomain> accountBaseList = List.of(
                SampleDomain.builder().acno("333301").wholAccoDvcd("01").build(),
                SampleDomain.builder().acno("333302").wholAccoDvcd("01").build(),
                SampleDomain.builder().acno("333303").wholAccoDvcd("01").build(),
                SampleDomain.builder().acno("333304").wholAccoDvcd("01").build(),
                SampleDomain.builder().acno("333305").wholAccoDvcd("02").build(),
                SampleDomain.builder().acno("333306").wholAccoDvcd("02").build()
        );

        List<DepLoanAccountDomain> depLoanAccountList = new java.util.ArrayList<>(
                List.of(
                        DepLoanAccountDomain.builder().acno("333301").emergencyLoanYn("Y").minusLoanYn("N").loanClsStcd("00").build(),
                        DepLoanAccountDomain.builder().acno("333302").emergencyLoanYn("N").minusLoanYn("N").loanClsStcd("00").build(),
                        DepLoanAccountDomain.builder().acno("333303").emergencyLoanYn("N").minusLoanYn("N").loanClsStcd("00").build()
        ));

        assertThat(getEmergencyLoanExistYn(accountBaseList, depLoanAccountList)).isEqualTo("Y");

        depLoanAccountList.add(
                DepLoanAccountDomain.builder().acno("333304").emergencyLoanYn("N").minusLoanYn("N").loanClsStcd("07").build()
        );

        assertThat(getEmergencyLoanExistYn(accountBaseList, depLoanAccountList)).isEqualTo("N");
    }

    private String getEmergencyLoanExistYn(List<SampleDomain> list, List<DepLoanAccountDomain> depLoanAccountDomainList) {
        final List<DepLoanAccountDomain> depLoanAccountInfoList = list.parallelStream()
                .filter(el -> "01".equals(el.getWholAccoDvcd()))
                .map(el -> getDepLoanAccountInfo(el.getAcno(), depLoanAccountDomainList))
                .collect(Collectors.toList());

        final boolean emergencyLoanExist = depLoanAccountInfoList.parallelStream()
                .anyMatch(el -> "Y".equals(el.getEmergencyLoanYn()));

        final Optional<DepLoanAccountDomain> first = depLoanAccountInfoList.parallelStream()
                .filter(el -> emergencyLoanExist && "07".equals(el.getLoanClsStcd()))
                .findFirst();

        return first.map(el -> "N").orElse(emergencyLoanExist ? "Y" : "N");
    }

    @Getter
    @Builder
    private static class SampleDomain {
        private final String acno;
        private final String wholAccoDvcd;
    }

    @Getter
    @Builder
    private static class DepLoanAccountDomain {
        private final String acno;
        private final String emergencyLoanYn;
        private final String minusLoanYn;
        private final String loanClsStcd;
    }

    private DepLoanAccountDomain getDepLoanAccountInfo( final String acno, final List<DepLoanAccountDomain> depLoanAccountDomainList ) {
        return depLoanAccountDomainList.parallelStream()
                        .filter(el -> acno.equals(el.getAcno()))
                        .findAny()
                        .orElseGet(() -> DepLoanAccountDomain.builder().build())
        ;
    }

}
