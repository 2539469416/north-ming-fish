package com.xfa.util;

public enum CodeEnum {
    /** 成功 */
    SUCCESS("200", "成功"),

    /** 操做失败 */
    ERROR("500", "操做失败"),

    /**非法信息**/
    ILLEGAL("999","非法信息"),

    /****/
    EMPTY("400","账号或密码为空")
    ;


    CodeEnum(String value, String msg){
        this.val = value;
        this.msg = msg;
    }

    public String val() {
        return val;
    }

    public String msg() {
        return msg;
    }

    private String val;
    private String msg;
}
