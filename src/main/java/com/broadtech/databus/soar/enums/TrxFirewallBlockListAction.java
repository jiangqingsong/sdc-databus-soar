package com.broadtech.databus.soar.enums;

/**
 * @Author: leo.j
 * @desc: 天融信防火墙静态黑名单操作
 * @Date: 2022/3/22 8:17 下午
 */
public enum TrxFirewallBlockListAction {
    _ENABLE(1, "启用"),
    _DISABLE(2, "禁用"),
    _DELETE(3, "删除"),
    ;
    private Integer actionId;
    private String actionDesc;

    TrxFirewallBlockListAction(Integer actionId, String actionDesc) {
        this.actionId = actionId;
        this.actionDesc = actionDesc;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public String getActionDesc() {
        return actionDesc;
    }

    public void setActionDesc(String actionDesc) {
        this.actionDesc = actionDesc;
    }
}
