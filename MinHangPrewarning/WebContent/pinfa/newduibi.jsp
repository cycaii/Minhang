<%@include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <%@include file="/common/meta.jsp"%>
<title>新建对比基准</title>
<!-- <style type="text/css"> -->
/* #body { */
/* 	margin-top: 50px; */
/* 	margin-right: auto; */
/* 	margin-left: auto; */
/* } */
<!-- </style> -->
</head>

<body>
<table id="body" width="401" border="0">
  <tr>
    <td width="395" height="39">新建对比基准</td>
  </tr>
  <tr>
    <td height="60"><table width="200" border="0">
      <tr>
        <td>统计类型</td>
        <td><SELECT style="width:74px">  </SELECT></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="63"><table width="377" border="0">
      <tr>
        <td width="68">起始时间</td>
        <td width="28">年</td>
        <td width="79"><SELECT style="width:74px">  </SELECT></td>
        <td width="82">序号</td>
        <td width="98"><SELECT style="width:74px">  </SELECT></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="377" border="0">
      <tr>
        <td width="68" height="42">结束时间</td>
        <td width="28">年</td>
        <td width="79"><SELECT style="width:74px">  </SELECT></td>
        <td width="82">序号</td>
        <td width="98"><SELECT style="width:74px">  </SELECT></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="58"><table width="200" border="0">
      <tr>
        <td>创建名称</td>
        <td><SELECT style="width:74px">  </SELECT></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="200" border="0">
      <tr>
        <td><input type="button" value="确定" /></td>
        <td><input type="button" value="重置" /></td>
        <td><input type="button" value="返回" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
