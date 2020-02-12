/**
 * @Author: ZealYoung
 * @Time: 2020/2/11 5:20 下午
 * @Description:
 */
package com.zeal.tmall.pojo.enums;

public enum RoleAuthority {
    NORMAL(0,"NORMAL"),

    ADMIN(1,"ADMIN"),

    SUPER_ADMIN(2,"SUPER_ADMIN");

    private int code;

    private String value;

    RoleAuthority(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public static int getCode(String value){
        if(value != null){
            for(RoleAuthority entity : RoleAuthority.values()){
                if(value.equals(entity.getValue())){
                    return entity.getCode();
                }
            }
        }
        return -1;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
