package com.dbtraining.reconx.dto;

public class PagedResponse<T> extends com.dbtraining.reconx.dto.common.PagedResponse<T> {
    public PagedResponse(java.util.List<T> items, int page, int size, long totalElements, int totalPages) {
        super(items, page, size, totalElements, totalPages);
    }

    public static <E, T> com.dbtraining.reconx.dto.common.PagedResponse<T> of(
            org.springframework.data.domain.Page<E> page, java.util.function.Function<E, T> mapper) {
        return com.dbtraining.reconx.dto.common.PagedResponse.of(page, mapper);
    }
}
