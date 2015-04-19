<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/meta.jsp"%>
<link href="${ctx}/resources/bootstrap/css/bootstrap.css"
	rel="stylesheet">
	<title>民航不安全事件分析与预警系统</title>
</head>
<body>
	<div class="container">
		<div class="table-responsive ">
			<table class="table table-bordered" data-show-columns="true">
				<caption>
					<s:if test="dim==1">
				【<s:property value="dimtype" />】:<s:property value="dimvalue" />
					</s:if>
					<s:elseif test="dim==2">
				【<s:property value="dimtype1" />】:<s:property value="dimvalue1" />
				&nbsp;	【<s:property value="dimtype2" />】:<s:property
							value="dimvalue2" />
					</s:elseif>
					（
					<s:property value="day" />
					）
				</caption>
				<tr>
					<th>标题</th>
					<th>上报日期</th>
					<th>发生地区</th>
					<th>发生地点</th>
					<th>航空器机型</th>
					<!-- 					<th>机型大类</th> -->
					<th>航空器机号</th>
					<th>航空器使用单位</th>
					<!-- 					<th>事件后果</th> -->
					<th>事件等级</th>
					<th>等级大类</th>
					<th>事件条款</th>
					<th>条款大类</th>
					<th>责任单位</th>
					<th>事件类型</th>
					<th>类型大类</th>
					<th>事件原因</th>
					<th>原因大类</th>
					<!-- 					<th>原因因素</th> -->
					<th>风险值</th>
					<th>飞行阶段</th>
					<th>事件性质</th>
					<!-- 					<th>最后起飞点</th> -->
					<!-- 					<th>计划目的地</th> -->
				</tr>
				<s:iterator value="totals" id="t">
				 
				<tr>
					<td><s:property value="biaoti" /></td>
					<td><s:date name="shangbaoriqi" format="yyyy-MM-dd" /></td>
					<td><s:property value="fashengdiqu" /> </td>
					<td><s:property value="fashengdidian" /></td>
					<td><s:property value="hangkongqijixing" /> </td>
					<%-- 					<td><%=t.getJixingdalei()%></td> --%>
					<td><s:property value="hangkongqijihao" /> </td>
					<td><s:property value="hangkongqishiyongdanwei" /> </td>
					<%-- 					<td><%=t.getShijianhouguo()%></td> --%>
					<td><s:property value="shijiandengji" /> </td>
					<td><s:property value="dengjidalei" /> </td>
					<td><s:property value="shijiantiaokuan" /> </td>
					<td><s:property value="tiaokuandalei" />  </td>
					<td><s:property value="zerendanwei" /> </td>
					<td><s:property value="shijianleixing" />  </td>
					<td><s:property value="leixingdalei" />  </td>
					<td><s:property value="shijianyuanyin" /> </td>
					<td><s:property value="yuanyindalei" />  </td>
					<%-- 					<td><%=t.getYuanyinyinsu()%></td> --%>
					<td><s:property value="fengxianzhi" />  </td>
					<td><s:property value="feixingjieduan" /> </td>
					<td><s:property value="shijianxingzhi" />  </td>
					<%-- 					<td><%=t.getZuihouqifeidian()%></td> --%>
					<%-- 					<td><%=t.getJihuamudidi()%></td> --%>
				</tr>
			</s:iterator>
			</table>

		</div>
	</div>
</body>
</html>
