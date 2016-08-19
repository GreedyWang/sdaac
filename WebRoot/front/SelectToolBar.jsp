<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
  <tr>
  <!-- 工作时间 -->
  <td align="center" class="altbg1">工作日期:</td><td align="center" class="altbg1"><input type="text"  name="begin" id="begin" onfocus="calShow('begin');" ></td>
  <td align="center" class="altbg1">--</td><td align="center" class="altbg1"><input type="text"  name="end" id="end" onfocus="calShow('end');" ></td>
    <!-- 记录时间 -->
  <td align="center" class="altbg1">记录日期:</td><td align="center" class="altbg1"><input type="text"  name="item.beginRegisterTime" id="registerBegin" onfocus="calShow('registerBegin');" ></td>
  <td align="center" class="altbg1">--</td><td align="center" class="altbg1"><input type="text"  name="item.endRegisterTime" id="rsgisterEnd" onfocus="calShow('rsgisterEnd');" ></td>
  
  <tr>
  <td align="center" class="altbg1">员工编号</td>
  <td align="center" class="altbg1"><input type="text"  name="item.tempolyee.uid" id="item.tempolyee.uid" ></td><td align="center" class="altbg1"></td>
  <td align="center" class="altbg1">组长编号</td>
  <td align="center" class="altbg1"><input type="text"  name="item.teamLeaderId"  id="item.teamLeaderId" ></td><td align="center" class="altbg1"></td>
  <td align="center" class="altbg1"></td></tr>
  <td align="center" class="altbg1">类型
  <select name="item.type" >
  <option value="-1">所有</option>
  <option value="1">岗位工资-104B</option>
  <option value="2">车间奖金-104A</option>
   <option value="3">车间奖金-100</option>
   <option value="4">车间奖金-103</option>
   <option value="5">车间奖金-出口</option>
   <option value="6">车间奖金-101</option>
  </select></td>
  <td align="center" class="altbg1">岗位号</td>
  <td align="center" class="altbg1"><input type="text"  name="item.tpost.id"   ></td>
  <input name="registerID" type="hidden" value="${logineduser.uid }">
    <td><input type="submit"  value="查询"></td>
   <td><input type="reset" value="重置"></td>
  </tr>
