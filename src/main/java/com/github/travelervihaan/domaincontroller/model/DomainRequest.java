package com.github.travelervihaan.domaincontroller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.Expose;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "code",
        "code_description",
        "execution_time",
        "command_name",
        "uid",
        "result"
})
public class DomainRequest {
    @Expose
    @JsonProperty("code")
    private Integer code;

    @Expose
    @JsonProperty("code_description")
    private String code_description;

    @Expose
    @JsonProperty("execution_time")
    private Double execution_time;

    @Expose
    @JsonProperty("command_name")
    private String command_name;

    @Expose
    @JsonProperty("uid")
    private Integer uid;

    @Expose
    @JsonProperty("result")
    private Result result;

    @JsonProperty("code")
    public Integer getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(Integer code) {
        this.code = code;
    }

    @JsonProperty("code_description")
    public String getCode_description() {
        return code_description;
    }

    @JsonProperty("code_description")
    public void setCode_description(String code_description) {
        this.code_description = code_description;
    }

    @JsonProperty("execution_time")
    public Double getExecution_time() {
        return execution_time;
    }

    @JsonProperty("execution_time")
    public void setExecution_time(Double execution_time) {
        this.execution_time = execution_time;
    }

    @JsonProperty("command_name")
    public String getCommand_name() {
        return command_name;
    }

    @JsonProperty("command_name")
    public void setCommand_name(String command_name) {
        this.command_name = command_name;
    }

    @JsonProperty("uid")
    public Integer getUid() {
        return uid;
    }

    @JsonProperty("uid")
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @JsonProperty("result")
    public Result getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(Result result) {
        this.result = result;
    }

}