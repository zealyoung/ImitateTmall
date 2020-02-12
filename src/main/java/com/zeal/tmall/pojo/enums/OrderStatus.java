package com.zeal.tmall.pojo.enums;

public enum OrderStatus {

    WAITPAY(0, "waitPay","待支付"),
    WAITDELIVERY(1, "waitDelivery","待发货"),
    WAITCONFIRM(2, "waitConfirm", "待收货"),
    WAITREVIEW(3, "waitReview", "待评价"),
    FINISH(4,"finish", "已完成"),
    DELETE(5, "delete", "已删除");


    private int value;

    private String status;

    private String statusDesc;

    OrderStatus(int value, String status, String statusDesc){
        this.value = value;
        this.status = status;
        this.statusDesc = statusDesc;
    }

    public int getValue() {
        return value;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    /**
     * 根据status返回对应中文
     * @param status
     * @return
     */
    public static String getInfo(String status) {
        if(status != null) {
            for(OrderStatus entity : OrderStatus.values()){
                if(entity.getStatus().equals(status)){
                    return entity.getStatusDesc();
                }
            }
            return "未知";
        }
        return null;
    }
}
