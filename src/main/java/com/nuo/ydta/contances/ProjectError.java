package com.nuo.ydta.contances;

import com.nuo.ydta.exception.IError;

public enum ProjectError implements IError {
    /**
     * 0xxx 接口处理错误码
     */
    PARAM_SERIALNO_IS_ERROR("0000", "serialNo参数异常"),
    PARAM_ROLE_IS_ERROR("0001", "角色参数异常"),
    PARAM_PLAY_IS_ERROR("0002","剧本参数异常"),
    PARAM_CAMP_IS_ERROR("0003","阵营参数异常"),
    PARAM_NAME_IS_ERROR("0004","角色名参数异常"),
    PARAM_GENDER_IS_ERROR("0005","角色性别参数异常"),
    PARAM_ROLE_ID_IS_ERROR("0006","角色ID参数异常"),
    PARAM_ROLE_SUSPICION_IS_ERROR("0007","角色嫌疑值参数异常"),
    PARAM_DESC_IS_ERROR("0008","描述参数异常"),
    PARAM_ROLENAME_IS_ERROR("0009","角色姓名参数异常"),
    PARAM_NPC_ID_IS_ERROR("0010","NPC ID参数异常"),
    PARAM_NPC_DESC_IS_ERROR("0011","NPC描述参数异常"),
    PARAM_NPC_NAME_IS_ERROR("0012","NPC名字参数异常"),
    PARAM_NPC_ADDRESS_IS_ERROR("0013","NPC地址参数异常"),
    PARAM_FAVORABILITY_IS_NULL("0013","好感度参数异常"),
    PARAM_STAGE_NAME_IS_ERROR("0014","剧情名字参数异常"),
    PARAM_STAGE_ID_IS_ERROR("0015","当前修改的剧情不存在"),
    PARAM_CAMP_ID_IS_ERROR("0016","当前修改的阵营不存在"),
    PARAM_CAMP_DESC_IS_ERROR("0017","阵营名字参数异常"),
    PARAM_VOTE_ROUND_IS_ERROR("0018","投票轮数异常"),
    PARAM_PLAY_ID_IS_ERROR("0019","剧本ID异常"),
    PARAM_PLAY_STAGE_IS_ERROR("0020","剧本所属阶段异常"),
    PARAM_PLAY_ROLE_IS_ERROR("0021","剧本所属角色异常"),
    PARAM_PLAY_CONTENT_IS_ERROR("0022","剧本内容异常"),
    PARAM_STATUS_IS_EXCEPTION("0023","状态异常"),




    /**
     * 1xxx 业务处理错误码
     */
    ROLE_IS_NULL("1000","角色对象为空"),
    GET_ROLE_ERROR("1001","获取角色对象失败，角色ID不存在"),
    ROLE_CAMP_MODIFY_ERROR("1002","更改角色阵营失败，该角色不能再进行修改"),
    STAGE_IS_NULL("1003","阶段对象为空"),
    NPC_IS_NULL("1004","NPC对象为空"),
    CAMP_IS_NULL("1004","阵营对象为空"),
    VOTE_ERROR("1005","投票失败"),
    STATISTICS_IS_NULL("1006","投票对象为空"),
    VITAL_IS_ERROR("1007","统计异常"),
    PLAY_IS_NULL("1008","剧本为空"),
    TASK_IS_NULL("1008","任务为空"),
    CLEW_IS_NULL("1008","线索为空"),
    NOTICE_IS_NULL("1008","通知为空"),



    /**
     * 系统异常
     */
    SYSTEM_ERROR("6666","系统异常");

    private static final String NAMESPACE = "ydta";

    private final String errorCode;
    private final String errorMessage;

    ProjectError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getNamespace() {
        return NAMESPACE;
    }

    @Override
    public String getErrorCode() {
        return NAMESPACE.concat(".").concat(errorCode);
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
