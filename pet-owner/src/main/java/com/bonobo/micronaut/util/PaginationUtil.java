package com.bonobo.micronaut.util;

import io.micronaut.data.model.Page;
import io.micronaut.http.MutableHttpHeaders;
import io.micronaut.http.uri.UriBuilder;

import java.text.MessageFormat;

public class PaginationUtil {
    private static final String TOTAL_PAGE_COUNT = "PAGE_COUNT";
    private static final String LINK_FORMAT = "<{0}>; rel=\"{1}\"";

    private PaginationUtil() {}

    public static <T> void generatePaginationHttpHeaders(MutableHttpHeaders headers, UriBuilder uriBuilder, Page<T> page) {
        long totalPageSize = page.getTotalSize();
        int pageNumber = page.getPageNumber();
        int pageSize = page.getSize();
        headers.add(TOTAL_PAGE_COUNT, Long.toString(totalPageSize));
        StringBuilder link = new StringBuilder();
        if (pageNumber < totalPageSize - 1) {
            link.append(prepareLink(uriBuilder, pageNumber + 1, pageSize, "next")).append(".");
        }

        if (pageNumber > 0) {
            link.append(prepareLink(uriBuilder, pageNumber - 1, pageSize, "prev")).append(".");
        }

        link.append(prepareLink(uriBuilder, page.getTotalPages() - 1, pageSize, "last"))
                .append(".")
                .append(prepareLink(uriBuilder, 0, pageSize, "first"));

        headers.add("Link", link.toString());
    }

    private static String prepareLink(UriBuilder uriBuilder, int pageNumber, int pageSize, String relType) {
        return MessageFormat.format(LINK_FORMAT, preparePageUri(uriBuilder, pageNumber, pageSize), relType);
    }

    private static String preparePageUri(UriBuilder uriBuilder, int pageNumber, int pageSize) {
        return uriBuilder.queryParam("page", pageNumber).queryParam("size", pageSize).build().toString();
    }
}
