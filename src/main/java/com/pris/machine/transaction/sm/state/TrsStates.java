/**
 * 
 */
package com.pris.machine.transaction.sm.state;

/**
 * @author LO
 *
 */
public enum TrsStates {
	DRAFT("00", "草稿"),
	DRAFT_REG_WAITING("01", "草稿，注册申请"),
	REGISTERED("02", "已注册"),
	REG_REL_WAITING("03", "已注册，发布申请"),
	REG_CNC_WAITING("04", "已注册，注销申请"),
	// DRAFT5("05", "新版本拒绝发布"),
	// DRAFT7("07", "新版本未发布，调用信息变更"),
	RELEASED("08", "已发布"),
	REL_MNT_CHANGING("09", "已发布，管理信息变更"),
	// DRAFT10("10", "已覆盖"),
	REL_ABL_WAITING("11", "已发布，废止申请"),
	ABOLISHED("12", "已废止"),
	ABL_CNC_WAITING("13", "已废止，注销申请"),
	CANCELLED("14", "已注销"),
	VERSION_REL_WAITING("15", "新版本未发布，内容变更中"),
	VERSION_CANCELLED("16", "新版本未发布"),
	REL_CNT_CHANGING("17", "已发布，内容变更申请"),
	CHOICE("choice","选择伪态")
	// DRAFT18("18", "已变更"),
	// DRAFT19("19", "已变更，管理信息变更"),
	// DRAFT20("20", "已变更，废止申请"),
	// DRAFT21("21", "已变更，内容变更申请")
	;
	private String name;
	private String code;

	private TrsStates(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
