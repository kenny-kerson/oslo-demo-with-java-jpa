package com.kenny.demo.oslodemowithjavajpa.hub;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DemoTest {

    @Test
    void emptyStreamWithList() {
        // Given
        List<String> notEmptyList = Arrays.asList("abc", "def");
        List<String> emptyList = new ArrayList();

        // When & Then
        assertThat(notEmptyList.stream()).isNotNull();
        assertThat(notEmptyList.stream().findAny().orElse("널입니다")).isNotNull();
        assertThat(notEmptyList.stream().findFirst().orElse("널입니다")).isEqualTo("abc");

        assertThat(emptyList).isNotNull();
        assertThat(emptyList.stream()).isNotNull();
        assertThat(emptyList.stream().findAny().orElse("널입니다.")).isNotNull();
        assertThat(emptyList.stream().findAny().orElse(" ")).isEqualTo(" ");
    }
}
