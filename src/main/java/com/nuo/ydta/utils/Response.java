package com.nuo.ydta.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nuo.ydta.exception.IError;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable {

    private static final long serialVersionUID = -6602365878131231511L;
    private Status status = Status.SUCCEED;
    private String errorCode;
    private String errorMessage;
    private String extMessage;
    private Integer code = 0;
    private Long total;
    /**
     * 以下4个字段：totalCount，pageIndex，pageSize，pageCount建议应用不再使用。
     * 使用total替换totalCount，另外三个字段建议不要返回。
     * pageIndex，pageSize，pageCount在客户端可以直接获取到不需要服务器段再进行计算并返回。此处列出仅为兼容老版本接口
     */
    private Long totalCount;
    private Long pageIndex;
    private Long pageSize;
    private Long pageCount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public Response() {

    }


    public Response(IError error) {
        this.errorCode = error.getErrorCode();
        this.errorMessage = error.getErrorMessage();
        this.status = Status.FAILED;
    }

    public Response(Long total) {
        this.total = total;
    }

    public Response(T data) {
        this.data = data;
    }

    public Response(T data, Long total) {
        this.data = data;
        this.total = total;
    }

    public Response(Long total, T data) {
        this.data = data;
        this.total = total;
    }

    public static Response create() {
        return new Response();
    }

    public static Response create(IError error) {
        Response response = new Response();
        response.errorCode = error.getErrorCode();
        response.errorMessage = error.getErrorMessage();
        response.status = Status.FAILED;
        return response;
    }

    public static Response create(Object data) {
        return new Response(data);
    }

    public static Response create(Long total) {
        return new Response(total);
    }

    public static Response create(Object data, Long total) {
        return new Response(data, total);
    }

    public static Response create(Long total, Object data) {
        return new Response(data, total);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getExtMessage() {
        return extMessage;
    }

    public void setExtMessage(String extMessage) {
        this.extMessage = extMessage;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 在客户端可以直接获取到不需要服务器段再进行计算并返回。此接口仅为兼容老版本接口
     *
     * @return
     */
    @Deprecated
    public Long getTotalCount() {
        return totalCount;
    }

    /**
     * 在客户端可以直接获取到不需要服务器段再进行计算并返回。此接口仅为兼容老版本接口
     *
     * @param totalCount
     */
    @Deprecated
    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 在客户端可以直接获取到不需要服务器段再进行计算并返回。此接口仅为兼容老版本接口
     *
     * @return
     */
    @Deprecated
    public Long getPageIndex() {
        return pageIndex;
    }

    /**
     * 在客户端可以直接获取到不需要服务器段再进行计算并返回。此接口仅为兼容老版本接口
     *
     * @param pageIndex
     */
    @Deprecated
    public void setPageIndex(Long pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * 在客户端可以直接获取到不需要服务器段再进行计算并返回。此接口仅为兼容老版本接口
     *
     * @return
     */
    @Deprecated
    public Long getPageSize() {
        return pageSize;
    }

    /**
     * 在客户端可以直接获取到不需要服务器段再进行计算并返回。此接口仅为兼容老版本接口
     *
     * @param pageSize
     */
    @Deprecated
    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 在客户端可以直接获取到不需要服务器段再进行计算并返回。此接口仅为兼容老版本接口
     *
     * @return
     */
    @Deprecated
    public Long getPageCount() {
        return pageCount;
    }

    /**
     * 在客户端可以直接获取到不需要服务器段再进行计算并返回。此接口仅为兼容老版本接口
     *
     * @param pageCount
     */
    @Deprecated
    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (getErrorCode() != null) {
            sb
                    .append("{status: ").append(getStatus())
                    .append(", errorCode : ").append(getErrorCode())
                    .append(", errorMessage : ").append(getErrorMessage())
                    .append(", extMessage : ").append(getExtMessage()).append("}");
        } else {
            sb.append("Succeed");
        }
        return sb.toString();
    }

    public enum Status {
        SUCCEED, FAILED
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

