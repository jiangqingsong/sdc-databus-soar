package com.broadtech.databus.soar.pojo;

import lombok.Data;

/**
 * @Author: leo.j
 * @desc: 天融信防火墙病毒过滤参数实体类
 * @Date: 2022/4/21 10:42 上午
 */
@Data
public class AvPolicy {
    //策略名
    private String policyName;
    //策略描述
    private String policyDesc;
    //http协议开关
    private String httpSwitch;
    //http协议方向
    private String httpDirection;
    //http协议动作
    private String httpAction;
    //ftp协议开关
    private String ftpSwitch;
    //ftp协议方向
    private String ftpDirection;
    //ftp协议动作
    private String ftpAction;
    //smtp协议开关
    private String smtpSwitch;
    //smtp协议方向
    private String smtpDirection;
    //smtp协议动作
    private String smtpAction;
    //pop3协议开关
    private String pop3Switch;
    //pop3协议方向
    private String pop3Direction;
    //pop3协议动作
    private String pop3Action;
    //im协议开关
    private String imSwitch;
    //im协议方向
    private String imDirection;
    //im协议动作
    private String imAction;

    public AvPolicy(String policyName, String policyDesc, String httpSwitch, String httpDirection, String httpAction,
                    String ftpSwitch, String ftpDirection, String ftpAction, String smtpSwitch, String smtpDirection,
                    String smtpAction, String pop3Switch, String pop3Direction, String pop3Action, String imSwitch,
                    String imDirection, String imAction) {
        this.policyName = policyName;
        this.policyDesc = policyDesc;
        this.httpSwitch = httpSwitch;
        this.httpDirection = httpDirection;
        this.httpAction = httpAction;
        this.ftpSwitch = ftpSwitch;
        this.ftpDirection = ftpDirection;
        this.ftpAction = ftpAction;
        this.smtpSwitch = smtpSwitch;
        this.smtpDirection = smtpDirection;
        this.smtpAction = smtpAction;
        this.pop3Switch = pop3Switch;
        this.pop3Direction = pop3Direction;
        this.pop3Action = pop3Action;
        this.imSwitch = imSwitch;
        this.imDirection = imDirection;
        this.imAction = imAction;
    }
}
