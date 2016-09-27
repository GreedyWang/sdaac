/**
 * 财务确认
 * 
 * @param {}
 *            proposalId
 * @param {}
 *            teamWorkId
 */
function projectFinish(proposalId, teamWorkId) {
	Ext.Ajax.request({
				url : 'task.do?actionType=doFinaceConfirm',
				success : function someFn() {
					Ext.Msg.alert("success");
				},
				failure : function otherFn() {
					Ext.Msg.alert("failure");
				},
				// headers: {
				// 'my-header': 'foo'
				// },
				params : {
					proposalId : proposalId,
					workTeamID : teamWorkId
				}
			});
}

/**
 * 产品切换
 * 
 * @param {}
 *            proposalId
 * @param {}
 *            teamWorkId
 */
function projectFinish2(proposalId, teamWorkId) {
	Ext.Ajax.request({
				url : 'task.do?actionType=doFinaceConfirm',
				success : function someFn() {
					Ext.Msg.alert("success");
				},
				failure : function otherFn() {
					Ext.Msg.alert("failure");
				},
				// headers: {
				// 'my-header': 'foo'
				// },
				params : {
					proposalId : proposalId,
					workTeamID : teamWorkId
				}
			});
}




	/**
	 * 更变项目计划完成日期/节约金额
	 * @param {} projectId
	 */
	function changeTeamPlan(projectId)
	{
		var html2="<iframe id='f3' frameBorder='0'  id='context' src='vave/newTask/changeTeamPlane.jsp?projectId="+projectId +"'  scrolling='auto' height='100%' width='100%'></iframe>"
		openWind2('更变项目计划完成日期/节约金额',html2)
	}
	
	
	/**
 	 * 全部完成
 	 */
	function finish(proposalId, teamWorkId)
	{
		var html2="<iframe id='f3' frameBorder='0'  id='context' src='vave/newTask/changeTeamFinish.jsp?projectId="+teamWorkId +"&proposalId="+proposalId+"'  scrolling='auto' height='100%' width='100%'></iframe>"
		openWind2('实际节约金额及完成日期',html2)
	}
/**
 * 全部完成

function finish(proposalId, teamWorkId) {

	Ext.Msg.prompt('Name', '实际节约金额及完成日期:', function(btn, text) {
				if (btn == 'ok') {
					Ext.Ajax.request({
								url : 'proposal.do?actionType=doFinish',
								success : function someFn() {
									Ext.Msg.alert("success");
								},
								failure : function otherFn() {
									Ext.Msg.alert("failure");
								},
								// headers: {
								// 'my-header': 'foo'
								// },
								params : {
									proposalId : proposalId,
									workTeamId : teamWorkId,
									actSaving : text
								}
							});
				}
			})
			
} */
	
/**
 * 查看原始提案
 */	
	function showProposal(proposalId){
		var url='proposal.do?actionType=doSelectByProposalID_SelfShow&proposalID='+proposalId;
		window.open(url,  "blank");
	}
	