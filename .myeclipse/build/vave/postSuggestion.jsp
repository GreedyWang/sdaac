<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <table border="1">
   <thead></thead>
   <tbody>
   <tr><td rowspan="2">SDAAC VAVE�᰸��</td>
   <td>�᰸��Դ</td><td>�᰸���</td><td>�᰸״̬</td><td>�����޶�ʱ��</td><td>�汾</td></tr>
   <tr>
   <td></td>
   <td></td>
   <td></td>
   <td></td>
   <td></td></tr>
   
   <tr><td>�᰸��</td> <td></td> <td>�绰/����/��˾</td><td></td><td></td> </tr>
    <tr><td>�ռ���</td> <td></td> <td>�绰/����/��˾</td><td></td><td></td> </tr>
    
    <tr><td>��  ��</td></tr>
    
    <tr><td>�������</td><td>�����</td><td>�˿�</td><td>���ó���</td><td>ϵͳ�����</td><td>���������</td><td>��λ</td></tr>
    <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
    <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
    <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
    
    <tr><td colspan="9"><textarea rows="3" cols="100%">���ڷ������ɸ�ҳ����</textarea> </td></tr>
    
     <tr><td>���÷������ɸ�ҳ����</td>
     <td><input type="checkbox" >A ��������  </td>
     <td><input type="checkbox" >B ����˵�� </td>
     <td><input type="checkbox" >C �ɱ�˵�� </td>
     <td><input type="checkbox" >D ����˵�� </td>
     <td><input type="checkbox" >E ʵʩ���� </td></tr>
    <tr><td colspan="9"><textarea rows="3" cols="100%"></textarea> </td></tr>
    
    <tr><td>�������:</td>
      <td><input type="checkbox" >��Ƹ���  </td>
     <td><input type="checkbox" >���ϸ��� </td>
     <td><input type="checkbox" >���ո��� </td>
     <td><input type="checkbox" >���ͨ�� </td>
     <td><input type="checkbox" >�����Ľ� </td>
      <td><input type="checkbox" >��Ӧ�̸ĸ� </td>
     <td><input type="checkbox" >���� </td></tr>
     
    <tr><td>���������</td><td><select>
    <option>�Ƽ�ʵʩ</option>
    <option>������</option>
    <option>�ݻ�ʵʩ</option>
    <option>�����Ƽ�</option>
    <option>��������</option>
    </select></td>
    <td>��Ҫʵʩ����</td><td>
    <select>
    <option>��Ʒ��</option>
    <option>���첿</option>
    <option>�ɹ���</option>
    <option>������</option>
    <option>HVAC</option>
    <option>PTC</option>
    <option>��Ӫ��</option>
    <option>�г���</option>
    <option>����</option>
    <option>���²�</option>
    </select>
    </td>
    <td><select>
    <option>��Ʒ��</option>
    <option>���첿</option>
    <option>�ɹ���</option>
    <option>������</option>
    <option>HVAC</option>
    <option>PTC</option>
    <option>��Ӫ��</option>
    <option>�г���</option>
    <option>����</option>
    <option>���²�</option>
    </select></td>
    <td>��׼</td><td></td><td>����</td><td></td></tr>
    
    <tr><td>�ⲿ�����</td><td><select>
    <option>�Ƽ�ʵʩ</option>
    <option>������</option>
    <option>�ݻ�ʵʩ</option>
    <option>�����Ƽ�</option>
    <option>��������</option>
    </select></td>
    <td >��������</td>
    <td colspan="2"></td>
    <td>��׼</td><td></td><td>����</td><td></td></tr>
    
     <tr><td>�ⲿ�����</td><td><select>
    <option>�Ƽ�ʵʩ</option>
    <option>������</option>
    <option>�ݻ�ʵʩ</option>
    <option>�����Ƽ�</option>
    <option>��������</option>
    </select></td>
    <td >��������</td>
    <td colspan="2"></td>
    <td>��׼</td><td></td><td>����</td><td></td></tr>
    
     <tr><td>���������</td><td><select>
    <option>�Ƽ�ʵʩ</option>
    <option>������</option>
    <option>�ݻ�ʵʩ</option>
    <option>�����Ƽ�</option>
    <option>��������</option>
    </select></td>
    <td >�᰸����</td>
    <td colspan="2"></td>
    <td>��׼</td><td></td><td>����</td><td></td></tr>
       
    <tr><td>��Ŀ�Ŷ�</td><td></td><td></td><td></td><td></td><td></td><td>�ƻ����ʱ��</td><td></td></tr>
    <tr><td colspan="5">��������</td><td>������</td><td>�ƻ�����</td><td>�������</td></tr>
    <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
    <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
    
    <tr><td>���ƽ�Լ�ɱ�(Ԫ)</td><td></td><td>������Լ�ɱ�(Ԫ)</td><td></td><td></td><td></td><td>ʵ�ʽ�Լ�ɱ�(Ԫ)</td><td></td></tr>
   <tr><td colspan="9"><textarea rows="3" cols="100%">��Ŀ�ܽᣩ��</textarea> </td></tr>
 </tbody>
 </table>
																												
 <table border="1">
 <thead><tr><td> ���㸽��</td><td>����(D)</td><td>����</td> <td>һ���Է��ã�Ԫ��</td><td>��Ŀ</td><td>����</td><td>��λ	</td></tr> </thead>
 <tbody>
 <tr>
 <td> ��ƿ���</td><td></td>
 <td></td> <td></td>
 <td>���ڷ����ɱ�</td><td></td><td></td>
 </tr>
  <tr>
 <td> ��װ����	</td><td></td>
 <td></td> <td></td>
 <td>���÷����ɱ�</td><td></td><td></td>
 </tr>
  <tr>
 <td>  ��������</td><td></td>
 <td></td> <td></td>
 <td>�������ϼ�</td><td></td><td></td>
 </tr>
  <tr>
 <td> ������֤</td><td></td>
 <td></td> <td></td>
 <td>�ܽ�Լ�ɱ�</td><td></td><td></td>
 </tr>
  <tr>
 <td>�˿�ȷ��</td><td></td>
 <td></td> <td></td>
 <td>һ���Է���</td><td></td><td></td>
 </tr>
   <tr>
 <td rowspan="3"> ��Ʒ�л�</td><td></td>
 <td></td> <td></td>
 <td>����Լ�ɱ�</td><td></td><td></td>
 </tr>
    <tr>
 <td>����(D)</td>
 <td></td> <td></td>
 <td>C/D/S�������%</td><td></td><td></td>
 </tr>
    <tr>
 <td>����(D)</td>
 <td></td> <td></td>
 <td>SDAAC����Լ�ɱ�</td><td></td><td></td>
 </tr>
 </tbody>
</table>
 
   
   
  </body>
</html>
