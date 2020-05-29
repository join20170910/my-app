package com.mycompany.app.common.utils.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.HttpServletResponse;

/**
 * 业务代码枚举
 *
 * @author Chill
 */
@Getter
@AllArgsConstructor
public enum ResultCode implements IResultCode {

	/**
	 * 操作成功
	 */
	SUCCESS(HttpServletResponse.SC_OK, "操作成功"),

	/**
	 * 业务异常
	 */
	FAILURE(HttpServletResponse.SC_BAD_REQUEST, "业务异常"),

	/**
	 * 请求未授权
	 */
	UN_AUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "请求未授权"),

	/**
	 * 404 没找到请求
	 */
	NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "404 没找到请求"),

	/**
	 * 消息不能读取
	 */
	MSG_NOT_READABLE(HttpServletResponse.SC_BAD_REQUEST, "消息不能读取"),

	/**
	 * 不支持当前请求方法
	 */
	METHOD_NOT_SUPPORTED(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "不支持当前请求方法"),

	/**
	 * 不支持当前媒体类型
	 */
	MEDIA_TYPE_NOT_SUPPORTED(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "不支持当前媒体类型"),

	/**
	 * 请求被拒绝
	 */
	REQ_REJECT(HttpServletResponse.SC_FORBIDDEN, "请求被拒绝"),

	/**
	 * 服务器异常
	 */
	INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "服务器异常"),

	/**
	 * 缺少必要的请求参数
	 */
	PARAM_MISS(HttpServletResponse.SC_BAD_REQUEST, "缺少必要的请求参数"),

	/**
	 * 请求参数类型错误
	 */
	PARAM_TYPE_ERROR(HttpServletResponse.SC_BAD_REQUEST, "请求参数类型错误"),

	/**
	 * 请求参数绑定错误
	 */
	PARAM_BIND_ERROR(HttpServletResponse.SC_BAD_REQUEST, "请求参数绑定错误"),

	/**
	 * 参数校验失败
	 */
	PARAM_VALID_ERROR(HttpServletResponse.SC_BAD_REQUEST, "参数校验失败"),

	MSG_HEAD_LENGTH_ERROR(HttpServletResponse.SC_BAD_REQUEST, "消息头长度异常"),
	MSG_BODY_LENGTH_ERROR(HttpServletResponse.SC_BAD_REQUEST, "消息内容长度异常"),
	PRO_BODY_BLOCK_LENGTH_ERROR(HttpServletResponse.SC_BAD_REQUEST, "协议指令内容长度异常"),
	MSG_ORDER_LENGTH_ERROR(HttpServletResponse.SC_BAD_REQUEST, "请求指令唯一码越界异常"),
	MSG_ORDER_BODY_ERROR(HttpServletResponse.SC_BAD_REQUEST, "485指令构造问题,请检查"),
	MSG_FILE_ERROR(HttpServletResponse.SC_BAD_REQUEST, "文件不存在,请检查"),
	MSG_OPERATION_GENERAL_ERROR(HttpServletResponse.SC_BAD_REQUEST, "操作失败,请重试"),
	MSG_NETWORK_CONNECTION_ERROR(HttpServletResponse.SC_BAD_REQUEST, "操作失败,确认网关与服务器网络通信是否正常"),
	MSG_GATEWAY_NAME_ERROR(HttpServletResponse.SC_BAD_REQUEST, "操作失败,确认网关编号与数据库配置一样"),

	;

	/**
	 * code编码
	 */
	final int code;
	/**
	 * 中文信息描述
	 */
	final String message;

}
