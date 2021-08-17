var apiKey = "5f1b677d-5ddd-401f-a553-79412d59c93d";
var sysCode = "FSSGXYY";
var userInfo = "bGlsaXBpbmc=";

var processDefName1 = "FSSGXYY_GDFZCKB";
var processDefName2 = "FSSGXYY_GZQD";
// 余额调节表bpmcode
var processDefName3 = "FSSGXYY_YETJB";
// 往来清理bpmcode
var processDefName4="FSSGXYY_SJBBWLQLJCSJ";
// 测试环境
//var appip='10.231.135.104:8889';
//var bpmip='bpm3.longfor.sit:8088';
//预生产
//var appip='fsscrpt-pre.longhu.net';
//预生产
//var appip='10.231.194.166:8080';
//var appip='10.231.207.18';

//测试
//var appip="172.18.66.134:8889";
var bpmip='bpm3.longhu.net';

//预生产
var protocal = "https://"
var appid = "10.231.207.18";


//生产
//var protocal = "http://"
//var appip='fsscrpt-web.longhu.net';




function commonHeader(request) {
    request.setRequestHeader("X-Gaia-Api-key", apiKey);
    request.setRequestHeader("X-ER-system-Info", sysCode);
    request.setRequestHeader("X-ER-User-Info", userInfo);
}

/**
 * 用户名编码
 * @param userId
 */
function encodeUserCode(userId) {
    $.ajax({
        //url: 'http://api.longfor.sit/bpm3-engine-sit/bpm-server/api/system/userCode?userId='+userId,
        url: '/bpm/bpm-server/api/system/userCode?userId='+userId,
        beforeSend: commonHeader,
        contentType: "application/json",
        success: function (data) {
            alert(data);
        },
        error: function () {
            alert('请求失败');
        }
    });
}

/**
 * 工抵房创建流程
 * var userCode="liliping";
    var processDefName="FSSGXYY_DEMO";
    var processInstName="工抵房审批流程";
    var processInstDesc='DEMO';
 */
function createProcess(userCode, processDefName, processInstName, processInstDesc,usearea,usedate) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/bpm/createProcess',
        dataType:'json',
        data:{userCode:userCode,processDefName:processDefName1,processInstName:processInstName,processInstDesc:processInstDesc,area:usearea,month:usedate},
        success: function (res) {
            if (res.code=="200") {
                openProcessPage(res.data);
            }else{
				alert(res.msg);
            }
        },
        error: function () {
            alert('流程请求异常！');
        }
    });
}

/**
 * 月度关帐清单创建流程
 *  var userCode="liliping";
    var id="1','2','3','4"
    var processDefName="MOUNTH_CLOSE_ACCOUNT_PROCESS";
    var processInstName="月度关帐清单流程";
    var processInstDesc='月度关帐清单审批流程提交';
 */
function mounthCloseAccountProcess(userCode,id, processDefName, processInstName, processInstDesc,pt,haveNopass) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/bpm/createProcessId',
        dataType:'json',
        data:{userCode:userCode,id:id,processDefName:processDefName2,processInstName:processInstName,processInstDesc:processInstDesc,pt:pt,haveNopass:haveNopass},
        success: function (res) {
            if (res.code=="200") {
                openProcessPage(res.data);
            }else{
                alert(res.msg);
            }
            
        },
        error: function () {
            alert('流程请求异常！');
        }
    });
}

/**
 * 月度关帐清单-撤回提交
 *  var userCode="liliping";
    var instanceId="1','2','3','4"
 */
function withdrawalProcess(userCode,instanceId) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/bpm/withdrawalProcess',
        dataType:'json',
        data:{userCode:userCode,instanceId:instanceId},
        success: function (res) {
			//alert(res.status)
            if (res.status=="200") {
                alert(res.result);
            }else{
                alert('请求失败');
            }
        },
        error: function () {
            alert('请求失败');
        }
    });
}


/**
 * 月度关帐清单单加取数更新
 *  var userCode="liliping";
    var instanceId="1','2','3','4"
 */
function getRpaData(codeCr,month) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/dwcalistofmonth/getRpaData',
        dataType:'json',
        data:{codeCr:codeCr,month:month},
        success: function (res) {
            if (res.status=="200") {
                alert(res.result);
                console.log(res.id);
                dwcalistofmonthtaskTimed(res.id);
            }else{
                 alert(res.result);
            }
        },
        error: function () {
            alert('请求失败');
        }
    });
}

/** 2020/11/30 
 * gxy
 *  关帐清单手动更新返回更新结果
    var uuid="xxxxxxxxxxxxxxxxxxxxxxxxx"
 */
function dwcalistofmonthtaskTimed(uuid){
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/dwcalistofmonth/taskTimed',
        dataType:'json',
        data:{uuid:uuid},
        success: function (res) {
            if (res.status=="200") {
                alert(res.result);
                //执行当前页面刷新操作
                contentPane.parameterCommit();
            }else {
                alert(res.result);
            }
        },
        error: function () {
            alert('请求失败');
        }
    });
}


/**
 * 银行余额汇总创建流程
 *  var userCode="liliping";
    var id="1','2','3','4"
    var processDefName="MOUNTH_CLOSE_ACCOUNT_PROCESS";
    var processInstName="月度关帐清单流程";
    var processInstDesc='月度关帐清单审批流程提交';
 */
function bankAdjustProcess(userCode,id, processDefName, processInstName, processInstDesc,pt) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/bpm/createProcessId',
        dataType:'json',
        data:{userCode:userCode,id:id,processDefName:processDefName3,processInstName:processInstName,processInstDesc:processInstDesc,pt:pt},
        success: function (res) {
            if (res.code=="200") {
                openProcessPage(res.data);
            }else{
                alert(res.msg);
            }
        },
        error: function () {
            alert('流程请求异常！');
        }
    });
}

/**
 * 银行余额调节表勾兑
    var id="1','2','3','4"
    
 */
function baBalanceProcess(id,month) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/reconciliation/selectStete',
        dataType:'json',
		async:false,//同步
        data:{id:id,month:month},
        success: function (res) {

            if (res.status=="200") {
                alert('勾兑成功');
            }else{
                alert('勾兑失败'+res.result+',资金汇总：'+res.ls+'nc汇总：'+res.nc);
            }

        },
        error: function () {
            alert('请求失败');
        }
    });
}

/**
 * 银行余额调节表，取消勾兑
    var rid="1','2','3','4"
    
 */
function nobaBalanceProcess(rid,month) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/reconciliation/notSelectStete',
        dataType:'json',
        data:{rid:rid,month:month},
        success: function (res) {
			//alert(res.status);
            if (res.status=="200") {
                alert('取消勾兑成功');
            }else{
                alert('取消勾兑失败'+res.result);
            }

        },
        error: function () {
            alert('请求失败');
        }
    });
}

/**
 * 对账明细表锁数 
 * var id="44353401040013347"
 * var status="1" //等于1代表锁数 
 * 'http://'+appip+'/fssc_report/reconciliation/lockState'
 */
function  BalanceProcessLockState( status,id,month) {
	var resstatus="";
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/reconciliation/lockState',
        dataType:'json',
		async:false,//同步
        data:{status:status,id:id,month:month},
        success: function (res) {
            if (res.status=="200") { 
			    resstatus=res.status;
                alert('锁数成功');
				FR.doHyperlink(event||window.event, [{"data":"var as=arguments; return FR.tc(function(){FR.doHyperlinkByGet4Reportlet({\"url\":\"/webroot/decision/view/report?viewlet=%252F%25E8%25B4%25A2%25E5%258A%25A1%25E5%2585%25B1%25E4%25BA%25AB%25E5%25B9%25B3%25E5%258F%25B0%252FWorkBook19.cpt\",\"para\":{\"__pi__\":true,\"op\":\"view\",\"id\":\""+id+"\",\"fromtype\":\""+"锁数"+"\",\"month\":\""+month+"\"},\"target\":\"_dialog\",\"feature\":{\"width\":400,\"height\":300,\"isCenter\":true,\"title\":\"\"},\"title\":\"网络报表1\"})}, this, as)","name":"网络报表1"}], true)
          
            }else{
				resstatus=res.status;
                alert('锁数失败');
				 FR.doHyperlink(event||window.event, [{"data":"var as=arguments; return FR.tc(function(){FR.doHyperlinkByGet4Reportlet({\"url\":\"/webroot/decision/view/report?viewlet=%252F%25E8%25B4%25A2%25E5%258A%25A1%25E5%2585%25B1%25E4%25BA%25AB%25E5%25B9%25B3%25E5%258F%25B0%252FWorkBook19.cpt\",\"para\":{\"__pi__\":true,\"op\":\"view\",\"id\":\""+id+"\",\"fromtype\":\""+"锁数"+"\",\"month\":\""+month+"\"},\"target\":\"_dialog\",\"feature\":{\"width\":400,\"height\":300,\"isCenter\":true,\"title\":\"\"},\"title\":\"网络报表1\"})}, this, as)","name":"网络报表1"}], true)
			}
        },
        error: function () {
            alert('请求失败');
        }
    });
	return resstatus;
}

/**
 * 汇总余额表，提交
    var id="27003"   
 */
function BalanceProcesssubmit(id,month) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/reconciliation/submit',
        dataType:'json',
        data:{id:id,month:month},
        success: function (res) {
			//alert(res.status);
            if (res.status=="200") {
                alert('提交成功');
            }else{
                alert('提交失败');
            }

        },
        error: function () {
            alert('请求失败');
        }
    });
}

/**
 * 汇总余额表，撤回提交
    var id="27003"   
 */
function BalanceProcessnotsubmit(id,month) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/reconciliation/notSubmit',
        dataType:'json',
        data:{id:id,month:month},
        success: function (res) {
			//alert(res.status);
            if (res.status=="200") {
                alert('撤回提交成功');
            }else{
                alert('撤回提交失败');
            }

        },
        error: function () {
            alert('请求失败');
        }
    });
}

/**
 * 设置参数
 */
function setParam() {
    $.ajax({
        type:'put',
        url: '/bpm/bpm-server/api/runtime/process-instances/100000000075081/actions/variables',
        beforeSend: commonHeader,
        contentType: "application/json",
        dataType:'json',
        data:JSON.stringify({startOrg:"L-BJ00-BJZBD00.01",basePrice:"1000",price:"2000"}),
        success: function (res) {
            if (res.code=="0") {
                openProcessPage(res.data);
            }else{
                alert(res.msg);
            }
            alert(res);
        },
        error: function () {
            alert('请求失败');
        }
    });
}

/** 2020/07/31 
 * gxy
 * 帆软产品无法调整记录删除日志，删除日志由此添加 参数：删除用户，日志头表id,删除明细数据id
    var deleteparm="{'user':'liliping','headerid':'246','id':[8376,8375,8315]}"   
 */
function deleteDataLog(deleteParm) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/reconciliation/copyDetail',
        dataType:'json',
		async:false,//同步
        data:{deleteParm:deleteParm},
        success: function (res) {
			//alert(res.status);
            if (res.status=="200") {
                //alert('删除明细表日志记录成功');
				console.log('删除明细表日志记录成功');
            }else{
                //alert('删除明细表日志记录失败');
				console.log('删除明细表日志记录失败');
            }

        },
        error: function () {
            alert('请求失败');
        }
    });
}

/** 2020/07/31 
 * gxy
 * 一键生成余额调节表校验
    var id="1','2','3','4"
    var month="2020-06"
 */
function oneTouchTotal(accountsId,month) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/reconciliation/oneTouchTotal',
        dataType:'json',
        data:{accountsId:accountsId,month:month},
        success: function (res) {

            if (res.status=="200") {
				var resstr=res.result;
				var resparm="";
				for(i = 0; i < resstr.length; i++){
					if(i==0&&resstr.length==1){
						resparm=resstr[i];
					}else if (i==0&&resstr.length>1){
						resparm=resparm+resstr[i]+"','";
					}else if (i>0&&i<resstr.length-1){
						resparm=resparm+resstr[i]+"','";
					}else {
						resparm=resparm+resstr[i];
					}
				}
				FR.doHyperlink(event || window.event, [{ "data": "var as=arguments; return FR.tc(function(){FR.doHyperlinkByPost({\"url\":\"/webroot/decision/view/report?viewlet=%252F%25E8%25B4%25A2%25E5%258A%25A1%25E5%2585%25B1%25E4%25BA%25AB%25E5%25B9%25B3%25E5%258F%25B0%252Fbank_reconciliation_statement%252Fdetail_pop.cpt&op=view\",\"para\":{\"__pi__\":false,\"account_time\":\""+month+"\",\"accounts_id\":\""+accountsId+"\",\"account_id\":\""+resparm+"\"},\"target\":\"_dialog\",\"feature\":{\"width\":600,\"height\":400,\"isCenter\":true,\"title\":\"可提交账户\"},\"title\":\"网络报表1\"})}, this, as)", "name": "网络报表1" }], true)
				//resstrup=resstr.replace(/；/g,'\r')
                //alert('调节表生成成功!'+'\r'+resstrup);
            }else if(res.status=="0"){
                alert('调节表可锁数账户为0条');
            }else {
                alert('调节表校验失败');
            }

        },
        error: function () {
            alert('请求失败');
        }
    });
}

/** 2020/09/07 
 * gxy
 * 一键生成余额调节表
    var id="1','2','3','4"
    var month="2020-06"
 */
function oneTouchTotalSubmit(accountsId,month) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/reconciliation/oneTouchTotalSubmit',
        dataType:'json',
        data:{accountsId:accountsId,month:month},
        success: function (res) {

            if (res.status=="200") {
				resstr=res.result;
				resstrup=resstr.replace(/；/g,'\r')
                alert('调节表生成成功!'+'\r'+resstrup);
            }else{
                alert('调节表生成失败');
            }

        },
        error: function () {
            alert('请求失败');
        }
    });
}

/** 2020/07/31 
 * gxy
 *  手动更新（废弃）
    var id="1"
    var month="2020-06"
 */
function getvResults(accountId,month) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/reconciliation/getvResults',
        dataType:'json',
        data:{accountId:accountId,month:month},
        success: function (res) {

            if (res.status=="200") {
                alert('手动更新成功');
            }else{
                alert('手动更新失败');
            }

        },
        error: function () {
            alert('请求失败');
        }
    });
}

/** 2020/09/10 
 * gxy
 *  手动更新
    var companyName='202023'
    var id="1"
    var month="2020-06"
 */
function getRpaDataBa(companyName,accountid,yearMonth) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/task/getRpaData',
        dataType:'json',
        data:{companyName:companyName,accountid:accountid,yearMonth:yearMonth},
        success: function (res) {

            if (res.status=="200") {
                alert(res.result);
				console.log(res.id);
				taskTimed(res.id);
            }else{
                alert(res.result);
            }

        },
        error: function () {
            alert('请求失败');
        }
    });
}
/** 2020/09/10 
 * gxy
 *  手动更新返回更新结果
    var uuid="xxxxxxxxxxxxxxxxxxxxxxxxx"
 */
function taskTimed(uuid){
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/task/taskTimed',
        dataType:'json',
        data:{uuid:uuid},
        success: function (res) {
            if (res.status=="200") {
                alert(res.result);
                //执行当前页面刷新操作
                contentPane.parameterCommit();
            }else {
                alert(res.result);
            }

        },
        error: function () {
            alert('请求失败');
        }
    });
}

/** 2020/07/31 
 *  gxy
 *  更新汇总表
    var id="1"
    var month="2020-06"
 */
function updateStoredProcedure(accountId,month) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/reconciliation/updateStoredProcedure',
        dataType:'json',
        data:{accountId:accountId,month:month},
        success: function (res) {

            if (res.status=="200") {
                alert('汇总表更新成功');
            }else{
                alert('汇总表更新失败');
            }

        },
        error: function () {
            alert('请求失败');
        }
    });
}

/** 2020/07/31 
 *  gxy
 *  余额调节汇总表锁数操作
    var id="1"
    var month="2020-06"
 */
function lockState( status,id,month) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/reconciliation/lockState',
        dataType:'json',
		async:false,//同步
        data:{status:status,id:id,month:month},
        success: function (res) {
			
			
            if (res.status=="200") {
                alert('操作成功');
            }else{
				alert(res.result);
            }

        },
        error: function () {
            alert('请求失败');
        }
    });
}

/** 2020/08/03 
 *  gxy
 *  余额调节表明细校验
    var id="1"
    var month="2020-06"
    
    'http://'+appip+'/fssc_report/reconciliation/preservation'
 */
function preservation( accountId,month) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/reconciliation/preservation',
        dataType:'json',
        data:{accountId:accountId,month:month},
		async:false,//同步
        success: function (res) {
			
            if (res.status=="200") {
                alert('操作成功');
            }else{
				alert(res.result);
            }

        },
        error: function () {
            alert('请求失败');
        }
    });
}
/**
 * 打开审核窗口
 */
function openProcessPage(instanceId) {
	 var url='http://'+bpmip+'/#/process/processInfo?1=1&systemId='+sysCode+'&instanceId='+instanceId
     var obj=window.open(url);
     obj.document.title=obj.document.title+instanceId;
   // window.open('http://bpm3.longfor.sit:8088/#/process/processInfo?1=1&systemId='+sysCode+'&instanceId='+instanceId);
	 //window.open('http://'+bpmip+'/#/process/processInfo?1=1&systemId='+sysCode+'&instanceId='+instanceId);
    
    //window.open('bpm-server/api/runtime/process-instances/100000000075081/actions/variables');
}





// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "H+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

/** 2020/07/31 
 *  gxy
 *  初始化后修改初始化余额汇总状态
    var accountId="1"
    var month="2020-06"
 */
function initAdjust( accountId,month,PLAT_NAME,AREA_NAME,ACCOUNTS_ID) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/reconciliation/initAdjust',
        dataType:'json',
		async:false,//同步
        data:{accountId:accountId,month:month},
        success: function (res) {

            if (res.status=="200") {
                alert('操作成功');
					//window.location.href = "${servletURL}?viewlet=/财务共享平台/bank_reconciliation_statement/Summary_balance_statement_per—bak_initback.cpt&op=view" + parm;
					FR.doHyperlink(event||window.event, [{"data":"var as=arguments; return FR.tc(function(){FR.doHyperlinkByGet4Reportlet({\"url\":\"/webroot/decision/view/report?viewlet=%252F%25E8%25B4%25A2%25E5%258A%25A1%25E5%2585%25B1%25E4%25BA%25AB%25E5%25B9%25B3%25E5%258F%25B0%252Fbank_reconciliation_statement%252FSummary_balance_statement_per%25E2%2580%2594bak_initback.cpt\",\"para\":{\"__pi__\":true,\"op\":\"view\",\"PLAT_NAME\":\""+PLAT_NAME+"\",\"AREA_NAME\":\""+AREA_NAME+"\",\"ACCOUNTS_ID\":\""+ACCOUNTS_ID+"\"},\"target\":\"_self\",\"feature\":{\"width\":600,\"height\":400,\"isCenter\":true,\"title\":\"\"},\"title\":\"网络报表1\"})}, this, as)","name":"网络报表1"}], true);
            }else{
                alert(res.result);
            }
			return res.status;

        },
        error: function () {
            alert('请求失败');
        }
    });
}



/* 解决参数面板点击下拉面板拉伸问题，由于此方案存在缺陷，替换
$('.parameter-container-collapseimg-down').click(function(){
alert("down");
 // setTimeout(function(){
                    var width = FR.windowWidth;
                    var height = FR.windowHeight;
                    var from = _g().getWidgetByName('form');
                    form.oriWidth = width;
                    form.oriHeight = height;
                    form.loadContentPane(true);
                  //  },300);
});
$('.parameter-container-collapseimg-up').click(function(){
 alert("up");
  // setTimeout(function(){
                    var width = FR.windowWidth;
                    var height = FR.windowHeight;
                    var from = _g().getWidgetByName('form');
                    form.oriWidth = width;
                    form.oriHeight = height;
                    form.loadContentPane(true);
               //       },300);
})
*/


/** 2020/08/06 
 * gxy
 * 项目付款单明细表填报成功后，更新成本台账模块数据
    var company="成都旭泰置业有限公司"
    var month="2020-06"
 */
function updataCbtz(company,month,flag) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/costaccount/updataCbtz',
        dataType:'json',
        data:{company:company,month:month,flag:flag},
        success: function (res) {

            if (res.status=="200") {
                alert('成本台账模块数据更新成功');
            }else{
                alert('成本台账模块数据更新失败');
            }

        },
        error: function () {
            alert('请求失败');
        }
    });
}

/** 2020/11/12
 * 资金自查创建流程
 * var userCode="liliping";
    var processDefName="FSSGXYY_DPZHKBZC";
    var processInstName="资金自查审批流程";
    var processInstDesc='';
 */
function createProcessZjzc(id,userCode,processInstDesc,processInstName,processDefName,dq){
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/bpm/createProcessByZjzc',
        dataType:'json',
        data:{id:id,userCode:userCode,processInstDesc:processInstDesc,processInstName:processInstName,processDefName:processDefName,dq:dq},
        success: function (res) {
            if (res.code=="200") {
                openProcessPage(res.data);
            }else{
    alert(res.msg);
            }
        },
        error: function () {
            alert('流程请求异常！');
        }
    });
}
/** 2020/08/07 
 * gxy
 * 项目付款单明细表提交
    var companyName="成都旭泰置业有限公司"
    var month="2020-06"
 */
function updataPayment(companyName,month,flag) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/costaccount/updataPayment',
        dataType:'json',
        data:{companyName:companyName,month:month,flag:flag},
        success: function (res) {

            if (res.status=="200") {
                alert('提交成功');
            }else{
                alert('提交失败');
            }

        },
        error: function () {
            alert('请求失败');
        }
    });
}


/** 2020/08/13
 * gxy
 * 关账明细确认后更新汇总表
    var company="成都旭泰置业有限公司"
    var month="2020-06"
 */
function updataDwCaListOfMonth(company,month) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/dwcalistofmonth/updataDwCaListOfMonth',
        dataType:'json',
        data:{company:company,month:month},
        success: function (res) {
            if (res.status=="200") {
                alert('关账清单汇总更新成功');
            }else{
                alert('关账清单汇总更新失败');
            }

        },
        error: function () {
            alert('请求失败');
        }
    });
}


/** 2020/08/31
 * gxy
 * 成本汇总表：开发成本合计≠NC账面数时，需人工确认 
    var company="成都旭泰置业有限公司"
    var date="2020-06"
	var fq='一期'
 */
function manualConfirmation(company,date,fq) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/costaccount/manualConfirmation',
        dataType:'json',
        data:{company:company,date:date,fq:fq},
        success: function (res) {
            if (res.status=="200") {
                alert('人工确认成功');
            }else{
                alert('人工确认失败');
            }

        },
        error: function () {
            alert('请求失败');
        }
    });
}


/**-----------------------------------往来清理start------------------------------------------------ */
/**
 * 往来清理汇总创建流程
 *  var userCode="liliping";
    var accountsId="1,2,3,4"
    var param=往来清理code;
    var quarter="2020Q3"
    needconfirm 1. pt参数需要传吗？2.返回结果是res.msg吗？  confirm  pt参数不传，返回值res.result
    String userCode, String quarter,BpmCreateProcessReqParam param,String accountsId
    @date 20201110 @author gxy
 */

function clearsumcreateProcessId(userCode,id, quarter) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/clear/createProcessId',
        dataType:'json',
        data:{userCode:userCode,quarter:quarter,processDefName:processDefName4,processInstName:"数据报表往来清理基础数据流程审批",processInstDesc:"数据报表往来清理基础数据流程审批提交",accountsId:id},
        success: function (res) {
            if (res.status=="200") {
                console.log("提交的账套为："+res.data);
                //循环打开审批页面  nottest              
                for(i=0;i<res.data.length;i++){
                    console.log("运行账套为："+res.data[i]);
                    openProcessPage(res.data[i]);  
                }
                //执行当前页面刷新操作 nottest
                contentPane.parameterCommit();
            }else{
                alert(res.result);
            }
        },
        error: function () {
            alert('流程请求异常！请重试！若重试后问题仍未解决，请联系管理员！');
        }
    });
}


/**
 * 往来清理撤回提交流程
 *--@relation：调用后台往来清理撤回提交接口
	--interface1：clearsumwithdrawalProcess 撤回提交流程(frjs)
		--parm：userCode:liliping, instanceId（xxx,xxx,xxx）
clear/withdrawalProcess(String userCode,String instanceId)
    @date 20201114 @author gxy
 */
function clearsumwithdrawalProcess(userCode,instanceId) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/clear/withdrawalProcess',
        dataType:'json',
        data:{userCode:userCode,instanceId:instanceId},
        success: function (res) {
            if (res.status=="200") {
                alert(res.result);
                //执行当前页面刷新操作 nottest
                contentPane.parameterCommit();
            }else{
                alert(res.result);
            }
        },
        error: function () {
            alert('流程撤回异常！请重试！若重试后问题仍未解决，请联系管理员！');
        }
    });
}


/**
 * 往来清理汇总表锁数
//--@relation：调用后台往来清理锁数接口（汇总表不传subject参数）
//	--interface1：clearNumberOfLocks 往来清理锁数(frjs)
//		--parm：accountsId：xxx,quarter:2020Q3
clear/numberOfLocks(String accountsId,String quarter,String subjectCode)
    @date 20201114 @author gxy
 */
function clearNumberOfLocks(accountsId,quarter) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/clear/numberOfLocks',
        dataType:'json',
        data:{accountsId:accountsId,quarter:quarter},
        success: function (res) {
            if (res.status=="200") {
                alert(res.result);
                //执行当前页面刷新操作 nottest
                contentPane.parameterCommit();
            }else{
                alert(res.result);
            }
        },
        error: function () {
            alert('锁数异常！请重试！若重试后问题仍未解决，请联系管理员!');
        }
    });
}


/**
 * 往来清明细锁数
//--@relation：调用后台往来清理锁数接口
//	--interface2：clearNumberOfLocksdetail 往来清理锁数(frjs)
//		--parm：accountsId：xxx,quarter:2020Q3,subject_code:xxxx
clear/numberOfLocks(String accountsId,String quarter,String subjectCode)
    @date 20201114 @author gxy
 */
function clearNumberOfLocksdetail(accountsId,quarter,subject_code) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/clear/numberOfLocks',
        dataType:'json',
        data:{accountsId:accountsId,quarter:quarter,subjectCode:subject_code},
        success: function (res) {
            if (res.status=="200") {
                alert(res.result);
                //执行当前页面刷新操作 nottest
                contentPane.parameterCommit();

            }else{
                alert(res.result);
            }
        },
        error: function () {
            alert('锁数异常！请重试！若重试后问题仍未解决，请联系管理员!');
        }
    });
}



/**
 * 往来清明细表锁数
//--@relation：调用后台往来清理锁数接口
//	--interface2：clearNumberOfLocksdetaillist 往来清理锁数(frjs)
//		--parm：accountsId：xxx,quarter:2020Q3,subject_code:xxxx
clear/numberOfLocks(String accountsId,String quarter,String subjectCode)
    @date 20201114 @author gxy
 */
function clearNumberOfLocksdetaillist(accountsId,quarter,subject_code) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/clear/numberOfLocks',
        dataType:'json',
        data:{accountsId:accountsId,quarter:quarter,subjectCode:subject_code},
        success: function (res) {
            if (res.status=="200") {
                alert(res.result);
                //执行当前页面刷新操作 nottest
                //contentPane.parameterCommit();
                //FR.doHyperlink(event||window.event, [{"data":"var as=arguments; return FR.tc(function(){FR.doHyperlinkByGet4Reportlet({\"url\":\"/webroot/decision/view/report?viewlet=%252F%25E8%25B4%25A2%25E5%258A%25A1%25E5%2585%25B1%25E4%25BA%25AB%25E5%25B9%25B3%25E5%258F%25B0%252FWorkBook19.cpt\",\"para\":{\"__pi__\":true,\"op\":\"view\",\"id\":\""+id+"\",\"fromtype\":\""+"锁数"+"\",\"month\":\""+month+"\"},\"target\":\"_dialog\",\"feature\":{\"width\":400,\"height\":300,\"isCenter\":true,\"title\":\"\"},\"title\":\"网络报表1\"})}, this, as)","name":"网络报表1"}], true);
                FR.doHyperlink(event||window.event, [{"data":"var as=arguments; return FR.tc(function(){FR.doHyperlinkByGet4Reportlet({\"url\":\"/webroot/decision/view/report?viewlet=%25E8%25B4%25A2%25E5%258A%25A1%25E5%2585%25B1%25E4%25BA%25AB%25E5%25B9%25B3%25E5%258F%25B0%252F%25E5%25BE%2580%25E6%259D%25A5%25E6%25B8%2585%25E7%2590%2586%252Freasonview.cpt\",\"para\":{\"__pi__\":true,\"op\":\"view\",\"QUARTER\":\""+quarter+"\",\"ACCOUNTS_ID\":\""+accountsId+"\",\"SUBJECT_CODE\":\""+subject_code+"\"},\"target\":\"_dialog\",\"feature\":{\"width\":400,\"height\":300,\"isCenter\":true,\"title\":\"\"},\"title\":\"网络报表1\"})}, this, as)","name":"网络报表1"}], true);
            }else{
                alert(res.result);
                FR.doHyperlink(event||window.event, [{"data":"var as=arguments; return FR.tc(function(){FR.doHyperlinkByGet4Reportlet({\"url\":\"/webroot/decision/view/report?viewlet=%25E8%25B4%25A2%25E5%258A%25A1%25E5%2585%25B1%25E4%25BA%25AB%25E5%25B9%25B3%25E5%258F%25B0%252F%25E5%25BE%2580%25E6%259D%25A5%25E6%25B8%2585%25E7%2590%2586%252Freasonview.cpt\",\"para\":{\"__pi__\":true,\"op\":\"view\",\"QUARTER\":\""+quarter+"\",\"ACCOUNTS_ID\":\""+accountsId+"\",\"SUBJECT_CODE\":\""+subject_code+"\"},\"target\":\"_dialog\",\"feature\":{\"width\":400,\"height\":300,\"isCenter\":true,\"title\":\"\"},\"title\":\"网络报表1\"})}, this, as)","name":"网络报表1"}], true);
            }
        },
        error: function () {
            alert('锁数异常！请重试！若重试后问题仍未解决，请联系管理员!');
        }
    });
}



/**
//--@relation：调用后台取数更新接口
//	--interface1：clearsumGetNcDate 往来清理账套取数更新(frjs)
//		--parm：quarter:2020Q3,accountsId:XXX
clear/getNcDate(String quarter,String accountsId,String subjectCode)
    @date 20201114 @author gxy
 */
function clearsumGetNcDate(quarter,accountsId) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/clear/getNcDate',
        dataType:'json',
        data:{accountsId:accountsId,quarter:quarter},
        success: function (res) {
            if (res.status=="200") {
                alert(res.result);
                //执行当前页面刷新操作 nottest
                contentPane.parameterCommit();
            }else{
                alert(res.result);
            }
        },
        error: function () {
            alert('取数异常！请重试！若重试后问题仍未解决，请联系管理员!');
        }
    });
}


/**
//--@relation：调用后台取数更新接口
//	--interface2：clearGetNcDate 往来清理单科目取数更新(frjs)
//		--parm：quarter:2020Q3,accountsId:XXX,subjectCode:XXX
clear/getNcDate(String quarter,String accountsId,String subjectCode)
    @date 20201114 @author gxy
 */
function clearGetNcDate(quarter,accountsId,subjectCode) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/clear/getNcDate',
        dataType:'json',
        data:{accountsId:accountsId,quarter:quarter,subjectCode:subjectCode},
        success: function (res) {
            if (res.status=="200") {
                alert(res.result);
                //执行当前页面刷新操作 nottest
                contentPane.parameterCommit();
            }else{
                alert(res.result);
            }
        },
        error: function () {
            alert('取数异常！请重试！若重试后问题仍未解决，请联系管理员!');
        }
    });
}



/**
//--@relation：调用后台解锁接口
//	--interface2：clearnumberOfLocksCancelled 往来清理解锁(frjs)
//		--parm：quarter:2020Q3,accountsId:XXX,subjectCode:XXX
//clear/numberOfLocksCancelled(String accountsId,String quarter,String subjectCode)
    @date 20201114 @author gxy
 */
function clearnumberOfLocksCancelled(quarter,accountsId,subjectCode) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/clear/numberOfLocksCancelled',
        dataType:'json',
        data:{accountsId:accountsId,quarter:quarter,subjectCode:subjectCode},
        success: function (res) {
            if (res.status=="200") {
                alert(res.result);
                //执行当前页面刷新操作 nottest
                contentPane.parameterCommit();
            }else{
                alert(res.result);
            }
        },
        error: function () {
            alert('解锁异常！请重试！若重试后问题仍未解决，请联系管理员!');
        }
    });
}


/**
//--@relation：调用后台初始化
//	--interface2：clearinitData 往来清理初始化(frjs)
//		--parm：quarter:2020Q3,accountsId:XXX,
//clear/initData(String quarter,String accountsId)
    @date 20201114 @author gxy
 */
function clearinitData(quarter,accountsId,AREA_CODE,PLAT_CODE) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/clear/initData',
        dataType:'json',
        data:{accountsId:accountsId,quarter:quarter},
        success: function (res) {
            if (res.status=="200") {
                alert(res.result);
                FR.doHyperlink(event||window.event, [{"data":"var as=arguments; return FR.tc(function(){FR.doHyperlinkByGet4Reportlet({\"url\":\"/webroot/decision/view/report?viewlet=%25E8%25B4%25A2%25E5%258A%25A1%25E5%2585%25B1%25E4%25BA%25AB%25E5%25B9%25B3%25E5%258F%25B0%252F%25E5%25BE%2580%25E6%259D%25A5%25E6%25B8%2585%25E7%2590%2586%252FSummary_of_transactions.cpt\",\"para\":{\"__pi__\":true,\"op\":\"view\",\"PLAT_CODE\":\""+PLAT_CODE+"\",\"AREA_CODE\":\""+AREA_CODE+"\",\"ACCOUNT_TIME\":\""+quarter+"\",\"ACCOUNTS_ID\":\""+accountsId+"\"},\"target\":\"_self\",\"feature\":{\"width\":600,\"height\":400,\"isCenter\":true,\"title\":\"\"},\"title\":\"网络报表1\"})}, this, as)","name":"网络报表1"}], true);
            }else{
                alert(res.result);
            }
        },
        error: function () {
            alert('解锁异常！请重试！若重试后问题仍未解决，请联系管理员!');
        }
    });
}
/**
//--@relation：调用后台初始化
//	--interface2：clearinitData 往来清理初始化(frjs)
//		--parm：quarter:2020Q3,accountsId:XXX,
//clear/initData(String quarter,String accountsId)
    @date 20201114 @author gxy
 */
function clearinitData1(quarter,accountsId,AREA_CODE,PLAT_CODE) {
	$.ajax({
		type:'post',
		url: 'http://'+appip+'/fssc_report/clear/initData1',
		dataType:'json',
		data:{accountsId:accountsId,quarter:quarter},
		success: function (res) {
			if (res.status=="200") {
				alert(res.result);
				FR.doHyperlink(event||window.event, [{"data":"var as=arguments; return FR.tc(function(){FR.doHyperlinkByGet4Reportlet({\"url\":\"/webroot/decision/view/report?viewlet=%25E8%25B4%25A2%25E5%258A%25A1%25E5%2585%25B1%25E4%25BA%25AB%25E5%25B9%25B3%25E5%258F%25B0%252F%25E5%25BE%2580%25E6%259D%25A5%25E6%25B8%2585%25E7%2590%2586%252FSummary_of_transactions.cpt\",\"para\":{\"__pi__\":true,\"op\":\"view\",\"PLAT_CODE\":\""+PLAT_CODE+"\",\"AREA_CODE\":\""+AREA_CODE+"\",\"ACCOUNT_TIME\":\""+quarter+"\",\"ACCOUNTS_ID\":\""+accountsId+"\"},\"target\":\"_self\",\"feature\":{\"width\":600,\"height\":400,\"isCenter\":true,\"title\":\"\"},\"title\":\"网络报表1\"})}, this, as)","name":"网络报表1"}], true);
			}else{
				alert(res.result);
			}
		},
		error: function () {
			alert('解锁异常！请重试！若重试后问题仍未解决，请联系管理员!');
		}
	});
}


/**
//--@relation：调用往来清理取消勾兑
//	--interface1：clearnotClearDetailStatus 往来清理取消勾兑(frjs)
//		--parm：uuid：xxx，xxx,xxx
clear/notClearDetailStatus(String uuid)
    @date 20201114 @author gxy
 */
function clearnotClearDetailStatus(uuid) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/clear/notClearDetailStatus',
        dataType:'json',
        data:{uuid:uuid},
        success: function (res) {
            if (res.status=="200") {
                alert(res.result);
            }else{
                alert(res.result);
            }
        },
        error: function () {
            alert('请求失败');
        }
    });
}


/**
//--@relation：调用往来清理勾兑
//	--interface1：clearUpdataClearDetailStatus 往来取消勾兑(frjs)
//		--parm：ids：xxx，xxx,xxx
clear/updataClearDetailStatus(String ids)
    @date 20201114 @author gxy
 */
function clearUpdataClearDetailStatus(ids) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/clear/updataClearDetailStatus',
        dataType:'json',
		async:false,//同步
        data:{ids:ids},
        success: function (res) {
            if (res.status=="200") {
                alert(res.result);
            }else{
                alert(res.result);
            }
        },
        error: function () {
            alert('请求失败');
        }
    });
}

/**-----------------------------------往来清理end ------------------------------------------------ */


/**--------------------------------------重点税源------------------------------------------ */
/**
 * 重点税源创建流程
 *  var userCode="liliping"
    var processDefName="FSSGXYY_SJBBZDSYJCSSJ";
    var processInstName="重点税源流程";
    var processInstDesc='重点税源流程流程提交';
 */
function imptaxsourceprocess( userCode,processDefName,month,accountsId) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/bpm/processIdBySources',
        dataType:'json',
        data:{userCode:userCode,processDefName:processDefName,processInstName:"数据报表重点税源基础数据流程审批",processInstDesc:"数据报表重点税源基础数据流程审批提交",month:month,accountsId:accountsId},
        success: function (res) {
            if (res.code=="200") {
                openProcessPage(res.data);
            }else{
                alert(res.msg);
            }
        },
        error: function () {
            alert('流程请求异常！');
        }
    });
}
/**--------------------------------------重点税源end------------------------------------------ */

/**--------------------------------------资金自查------------------------------------------ */
/**
 * 资金自查创建流程
 *  var userCode="liliping"
    var processDefName="FSSGXYY_DPZHKBZC";
    var processInstName="资金自查流程";
    var processInstDesc='资金自查流程流程提交';
 */
function selfCheckingOffundsProcess( userCode,processDefName,month,area) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/bpm/createProcessByZjzc',
        dataType:'json',
        data:{userCode:userCode,processDefName:processDefName,month:month,area:area},
        success: function (res) {
            if (res.code=="200") {
                openProcessPage(res.data);
            }else{
                alert(res.msg);
            }
        },
        error: function () {
            alert('流程请求异常！');
        }
    });
}
/**--------------------------------------资金自查end------------------------------------------ */


/**-----------------------------------余额调节表大数据问题start--------------------------------- */
/**
//--@relation：执行选择操作
    接口：/fssc_report/reconciliation/takeDataBlendingFlag
        selectType：文本内容： 全选/单选
        blendingType ：文本内容：已勾兑/未勾兑
        id：该参数需根据blendingType进行区分，若为已勾兑，此处的含义为匹配号（uuid），若为未勾兑，此处的含义为数据id（id）
        userCode：用户名
        selectFlag：文本内容：选择/取消选择
        month：月度
        accountId：账户id
        datatype：文本内容：ls/nc
        unrecive_type  未达原因类型 参数为空该条件无
        ls_remarks 流水备注 参数为空该条件无
        ls_date '%2020/1%' 流水日期 参数为空该条件无
        ds_account_name 对方户名 参数为空该条件无
        ds_bank  对方银行 参数为空该条件无
        ds_account 对方账户  参数为空该条件无
        ls_amount 流水金额 参数为空该条件无
        nc_voucher_no   --NC凭证号
        nc_voucher_summary NC凭证摘要
        nc_entry_date NC入账日期
        nc_amount NC金额（借＋贷-）
    @date 20201203 @author gxy
 */
function takeDataBlendingFlag( selectType,blendingType,id,userCode,selectFlag,month,accountId,datatype) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/reconciliation/takeDataBlendingFlag',
        dataType:'json',
        data:{
        	
        	selectType:selectType,
        	blendingType:blendingType,
        	id:id,
        	userCode:userCode,
        	selectFlag:selectFlag,
        	month:month,
        	accountId:accountId,
        	datatype:datatype
        	
        },
        success: function (res) {
            if (res.code=="200") {
                console.log(res.msg);
                if(selectType=='全选'){
                    //FR.doHyperlink(event||window.event, [{"data":"var as=arguments; return FR.tc(function(){FR.doHyperlinkByGet4Reportlet({\"url\":\"/webroot/decision/view/form?viewlet=%252F%25E8%25B4%25A2%25E5%258A%25A1%25E5%2585%25B1%25E4%25BA%25AB%25E5%25B9%25B3%25E5%258F%25B0%252Fbank_reconciliation_statement%252Freconciliation_statement_lot.frm\",\"para\":{\"__pi__\":true,\"ACCOUNT_TIME\":\"\",\"ACCOUNT_ID\":\"\",\"refresh\":\"f\"},\"target\":\"_self\",\"feature\":{\"width\":600,\"height\":400,\"isCenter\":true,\"title\":\"\"},\"title\":\"网络报表1\"})}, this, as)","name":"网络报表1"}], true);
                    //执行当前页面刷新操作
                    contentPane.parameterCommit();
                }
            }else{
                console.log(res.msg);
            }
        },
        error: function () {
            alert('请求异常！');
        }
    });
}

/**
//--@relation：进行勾兑/取消勾兑/批量更新
    接口：/fssc_report/reconciliation/takeBlendingUpdate
        userCode：用户名
        operateFlag：文本内容：勾兑/取消勾兑/批量更新
        month：月度
        accountId：账户id
        updateRes：批量更新原因
    @date 20201203 @author gxy
 */
function takeBlendingUpdate( userCode, operateFlag,month,accountId, updateRes,datatype) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/reconciliation/takeBlendingUpdate',
        dataType:'json',
        data:{userCode:userCode, operateFlag:operateFlag,month:month,accountId:accountId,updateRes:updateRes,datatype:datatype},
        success: function (res) {
            if (res.code=="200") {
                alert(res.msg);
                if(operateFlag=="批量提交"){
					contentPane.parameterCommit();
	            }else if(operateFlag=="勾兑"||operateFlag=="取消勾兑"){
	                _g().parameterCommit();
	            }else{
	                alert("操作参数不符合规范");
	            }
	            }else{
	                alert(res.msg);
	            }
        },
        error: function () {
            alert('请求异常！');
        }
    });
}

/**
//--@relation：初始化选择内容
    接口：/fssc_report/reconciliation/takeBlendingInit
        month：月度
        accountId：账户id
    @date 20201203 @author gxy
 */
function takeBlendingInit(month,accountId) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/reconciliation/takeBlendingInit',
        dataType:'json',
        data:{month:month,accountId:accountId},
        success: function (res) {
            if (res.code=="200") {
                console.log(res.msg);
                contentPane.parameterCommit();
            }else{
                console.log(res.msg);
            }
        },
        error: function () {
            alert('请求异常！');
        }
    });
}

/**
//--@relation：执行选择操作带参数
    接口：/fssc_report/reconciliation/takeDataBlendingFlag
        selectType：文本内容： 全选/单选
        blendingType ：文本内容：已勾兑/未勾兑
        id：该参数需根据blendingType进行区分，若为已勾兑，此处的含义为匹配号（uuid），若为未勾兑，此处的含义为数据id（id）
        userCode：用户名
        selectFlag：文本内容：选择/取消选择
        month：月度
        accountId：账户id
        datatype：文本内容：ls/nc
        unrecive_type       未达原因类型 参数为空该条件无
        ls_remarks          流水备注 参数为空该条件无
        ls_date             '%2020/1%' 流水日期 参数为空该条件无
        ds_account_name     对方户名 参数为空该条件无
        ds_bank              对方银行 参数为空该条件无
        ds_account           对方账户  参数为空该条件无
        ls_amount           流水金额 参数为空该条件无
        nc_voucher_no         --NC凭证号
        nc_voucher_summary   NC凭证摘要
        nc_entry_date        NC入账日期
        nc_amount            NC金额（借＋贷-）
    @date 20201203 @author gxy
 */
function takeDataBlendingParmFlag( selectType,blendingType,id,userCode,selectFlag,month,accountId,datatype,unrecive_type,ls_remarks,ls_date,ds_account_name,ds_bank,ds_account,ls_amount,nc_voucher_no,nc_voucher_summary,nc_entry_date,nc_amount) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/reconciliation/takeDataBlendingFlag',
        dataType:'json',
        data:{selectType:selectType,blendingType:blendingType,id:id,
                userCode:userCode,selectFlag:selectFlag,month:month,
                accountId:accountId,datatype:datatype
                ,unrecive_type:unrecive_type,ls_remarks:ls_remarks,ls_date:ls_date
                ,ds_account_name:ds_account_name,ds_bank:ds_bank,ds_account:ds_account
                ,ls_amount:ls_amount,nc_voucher_no:nc_voucher_no,nc_voucher_summary:nc_voucher_summary
                ,nc_entry_date:nc_entry_date,nc_amount:nc_amount},
        success: function (res) {
            if (res.code=="200") {
                console.log(res.msg);
                if(selectType=='全选'){
                    //FR.doHyperlink(event||window.event, [{"data":"var as=arguments; return FR.tc(function(){FR.doHyperlinkByGet4Reportlet({\"url\":\"/webroot/decision/view/form?viewlet=%252F%25E8%25B4%25A2%25E5%258A%25A1%25E5%2585%25B1%25E4%25BA%25AB%25E5%25B9%25B3%25E5%258F%25B0%252Fbank_reconciliation_statement%252Freconciliation_statement_lot.frm\",\"para\":{\"__pi__\":true,\"ACCOUNT_TIME\":\"\",\"ACCOUNT_ID\":\"\",\"refresh\":\"f\"},\"target\":\"_self\",\"feature\":{\"width\":600,\"height\":400,\"isCenter\":true,\"title\":\"\"},\"title\":\"网络报表1\"})}, this, as)","name":"网络报表1"}], true);
                    //执行当前页面刷新操作
                    contentPane.parameterCommit();
                }
            }else{
                console.log(res.msg);
            }
        },
        error: function () {
            alert('请求异常！');
        }
    });
}


/**-----------------------------------余额调节表大数据问题end--------------------------------- */

/**----------------------------月关单家取数汇总和明细区分开------------------------------------ */
/** 
2020-12-11 月关单家取数汇总和明细区分开
月度关账-汇总/明细取数更新
codeCr：公司名称
month：年月
dealType：处理TYPE 1:汇总落库    2：详细落库
detailKemu：0000000:全部
           1201001: 科目检查-进项税
           1201002:科目检查-固定资产
           1201003:科目检查-预收收据&预收发票
           1201004:科目检查-内外部单位往来科目客商辅助
           1201005:协同检查
           1201006:科目检查-损益类科目
           1201007:现流分析-长期借款
           1201008:现流分析-保理检查
           1201009:现流分析-工程造价、研发设计、报建支出、工程相关（含税）
           1201010:现流分析-增值税及附加、土增税、企业所得税
           1202001:科目检查-银行存款
           1202002:科目检查-销项税
           1202003:现流分析-票据检查
           1202004:现流分析-管费、土地支出，构建资产、表内往来现金流
           1202005:现流分析-其他流入流出
           1202006:科目检查-过渡性科目
           1202007:科目检查-应收应付科目

注: 1201008： 现流分析-保理检查-保理发行,现流分析-保理检查-保理兑付
   1201010：现流分析-增值税及附加，现流分析-企业所得税， 现流分析-土增税
   1202003: 现流分析-票据检查-票据出票，现流分析-票据检查-票据兑付
*/
function getRpaDataHz(codeCr, month, dealType, detailKemu,sqlresult,result_name) {
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/dwcalistofmonth/ydgzTion',
        dataType:'json',
        data:{codeCr:codeCr, month:month, dealType:dealType, detailKemu:detailKemu},
        success: function (res) {

            if (res.status=="200") {
                 alert(res.result);
                 console.log(res.id);
				 if(dealType=="1"){
                 dwcalistofmonthtaskTimed(res.id);
				 }else{
				dwcalistofmonthtaskdetailTimed(res.id,detailKemu,codeCr, month,sqlresult,result_name);
				 }
            }else{
                 alert(res.result);
            }
        },
        error: function () {
            alert('请求失败');
        }
    });
}

/** 2020/12/11 
 * gxy
 *  关帐清单手动更新返回更新结果执行
    var uuid="xxxxxxxxxxxxxxxxxxxxxxxxx"
 */
function dwcalistofmonthtaskdetailTimed(uuid,detailKemu,codeCr, month,sqlresult,result_name){
    $.ajax({
        type:'post',
        url: 'http://'+appip+'/fssc_report/dwcalistofmonth/taskTimed',
        dataType:'json',
        data:{uuid:uuid},
        success: function (res) {
            if (res.status=="200") {
                alert(res.result);
                //contentPane.parameterCommit();
                //执行打开页面
                dwcalistofmonthtaskopdetail(detailKemu,codeCr, month,sqlresult,result_name);
            }else {
                alert(res.result);
            }
        },
        error: function () {
            alert('请求失败');
        }
    });
}

function dwcalistofmonthtaskopdetail(detailKemu,codeCr, month,sqlresult,result_name){
    var nameall = [
        "【NC待认证】当期余额异常"
        , "【NC已认证】与【税局已认证】不相符"
        , "集团外部单位往来客商余额有异常"
        , "集团内部单位往来客商余额有异常"
        , "NC固定资产【发生贷】与固定资产卡片【减少】不相符"
        , "NC固定资产【发生借】与固定资产卡片【增加】不相符"
        , "预收收据与预收发票有差异"
        , "公有协同中心有异常"
        , "公有协同确认有异常"
        , "损益类科目余额异常"
        , "损益类费用类科目方向有异常"
        , "损益类收入类科目方向有异常"
        , "长期借款借方与偿还银行借款有差异"
        , "长期借款贷方与银行借贷有差异"
        , "NC长期借款借方科目核对异常"
        , "NC长期借款贷方科目核对异常"
        , "NC偿还银行借款项现金流异常"
        , "NC银行借贷项现金流异常"
        , "保理发行当月有异常"
        , "保理发行累计有异常"
        , "保理兑付当月有异常"
        , "保理兑付累计有异常"
        , "工程造价（含税）有异常"
        , "工程相关（含税）有异常"
        , "报建支出（含税）有异常"
        , "研发设计（含税）有异常"
        , "增值税及附加实际缴纳的税金与记账科目不符"
        , "土增税实际缴纳的税金与记账科目不符"
        , "企业所得税实际缴纳的税金与记账科目不符"];
    var title = ["待认证进项税明细对比查看"
        , "已认证进项税明细对比查看"
        , "集团外部单位往来明细"
        , "集团内部单位往来明细"
        , "固定资产【发生贷】与【减少表】明细对比"
        , "固定资产【发生借】与【增加表】差异对比"
        , "预收收据与预收发票明细对比"
        , "公有协同中心明细"
        , "公有协同确认明细"
        , "损益类科目末级科目余额(不为0)异常明细"
        , "损益类科目方向(应为借方)异常明细"
        , "损益类科目方向(应为贷方)异常明细"
        , "借方与偿还银行借款差异异常明细查看"
        , "贷方与银行借贷差异异常明细查看"
        , "长期借款科目借方当月发生额明细查看"
        , "长期借款科目贷方当月发生额明细查看"
        , "＂偿还银行借款＂项中金额明细查看"
        , "＂银行借贷＂项中金额明细查看"
        , "保理当月明细比对查看"
        , "保理累计明细比对查看"
        , "保理到期承兑当月明细比对查看"
        , "保理到期承兑累计明细比对查看"
        , "工程造价(含税)NC*现金流附表明细查看"
        , "工程相关(含税)NC*现金流附表明细查看"
        , "报建支出(含税)NC*现金流附表明细查看"
        , "研发设计(含税)NC*现金流附表明细查看"
        , "增值税及附加实际缴纳的税金与记账科目明细"
        , "土增税实际缴纳的税金与记账科目明细"
        , "企业所得税实际缴纳的税金与记账科目明细"];
    var link = ["/财务共享平台/Monthly_Closing_Llist/list_detail/LH_inputtax_details_drz.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_inputtax_details_yrz.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_merchants_wb.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_merchants_nb.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_fixed_assets_df.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_fixed_assets_jf.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_depositreceived.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_synergy_xtzx.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_synergy_xtqr.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_profit_loss_ye.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_profit_loss_fy.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_profit_loss_sr.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_funded_jfycy.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_funded_dfycy.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_funded_cqjk_jf.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_funded_cqjk_df.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_funded_chjk.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_funded_yhjd.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_factoring_inspect_fxdy.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_factoring_inspect_fxlj.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_factoring_inspect_dfdy.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_factoring_inspect_dflj.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_project_cost.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_project_relevant.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_project_application.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_project_design.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_tax_zzs.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_tax_tdzzs.cpt&op=view"
        , "/财务共享平台/Monthly_Closing_Llist/list_detail/LH_tax_qysds.cpt&op=view"];
    var data_type = ["NA", "NA", "1", "0", "1", "0", "NA", "1", "0", "2", "1", "0", "4", "5", "0", "1", "2", "3", "1", "2", "3", "4", "1", "4", "3", "2", "1", "2", "3"]
    //与传入参数进行匹配，并筛选出其对应的连接参数
    var namein = result_name;
    var p_date = month;
    var p_company = codeCr;
    var sqlresult = sqlresult; 
    var titleappend = "";
    var linkdes = "";
    var data_typeval = "";
    for (i = 0; i < 29; i++) {
        if (nameall[i] == namein) {
            console.log(i);
            titleappend = title[i];
            linkdes = link[i];
            data_typeval = data_type[i];
        }
    }
    var parmappend = "";
    if (data_typeval != "NA") {
        parmappend += "\",\"data_type\":\"" + data_typeval
    }
    //打开下钻页面查看明细
	/*
    FR.doHyperlink(window.event, [{
        "data": "var as=arguments; return FR.tc(function(){FR.doHyperlinkByGet4Reportlet({\"url\":\"/webroot/decision/view/report?viewlet=/财务共享平台/Monthly_Closing_Llist/list_detail/LH_inputtax_details_drz.cpt&op=view\",\"para\":{\"__pi__\":true,\"p_date\":\"" + p_date + "\",\"p_company\":\"" + p_company + parmappend + "\"},\"target\":\"_dialog\",\"feature\":{\"width\":1200,\"height\":530,\"isCenter\":true,\"title\":\"" + sqlresult + titleappend + "\"},\"title\":\"网络报表1\"})}, this, as)",
        "name": "网络报表1"
    }], true)
	    */
	FR.doHyperlink(window.event, [{
        "data": "var as=arguments; return FR.tc(function(){FR.doHyperlinkByGet4Reportlet({\"url\":\"/webroot/decision/view/report?viewlet=\"+\"" + linkdes  + "\",\"para\":{\"__pi__\":true,\"p_date\":\"" + p_date + "\",\"p_company\":\"" + p_company + parmappend + "\"},\"target\":\"_dialog\",\"feature\":{\"width\":1200,\"height\":530,\"isCenter\":true,\"title\":\"" + sqlresult + titleappend + "\"},\"title\":\"网络报表1\"})}, this, as)",
        "name": "网络报表1"
    }], true)

}

/**----------------------------月关单家取数汇总和明细区分开------------------------------------ */

/**-------------------------------余额调节表自动勾兑------------------------------------------ */
/**
//--@relation：余额调节自动勾兑
        month：月度
        accountId：账户id
        userCode：用户
    @date 20201203 @author gxy
 */
function autoTakeBlendingUpdate(month,accountId,userCode) {
    $.ajax({
        type:'post',
        url: 'https://10.231.207.18/fssc_report/reconciliation/autoTakeBlendingUpdate',
        dataType:'json',
        data:{month:month,accountId:accountId,userCode:userCode},
        success: function (res) {
            if (res.code=="200") {
                console.log(res.msg);
                contentPane.parameterCommit();
            }else{
                console.log(res.msg);
            }
        },
        error: function () {
            alert('请求异常！');
        }
    });
}
/**-------------------------------余额调节表自动勾兑end--------------------------------------- */

/**-----------------------------------往来清理大数据问题start--------------------------------- */
/**
//--@relation：执行选择操作
    接口：/fssc_report/clear/takeDataBlendingFlag
    selectType：文本内容： 全选/单选
    blendingType ：文本内容：已勾兑/未勾兑
    id：该参数需根据blendingType进行区分，若为已勾兑，此处的含义为匹配号（uuid），若为未勾兑，此处的含义为数据id（id）
    userCode：用户名
    selectFlag：文本内容：选择/取消选择
    month：月度
    accountsId：账套id
    subjectCode：科目
    @date 20201203 @author gxy
 */
function clearTakeDataBlendingFlag(selectType, blendingType, id, userCode, selectFlag, month, accountsId, subjectCode) {
    $.ajax({
        type: 'post',
        url: 'http://' + appip + '/fssc_report/clear/takeDataBlendingFlag',
        dataType: 'json',
        data: {
            selectType: selectType,
            blendingType: blendingType,
            id: id,
            userCode: userCode,
            selectFlag: selectFlag,
            month: month,
            accountsId: accountsId,
            subjectCode: subjectCode
        },
        success: function (res) {
            if (res.code == "200") {
                console.log(res.msg);
                if (selectType == '全选') {
                    //执行当前页面刷新操作
                    setTimeout(function(){
                        FR.doHyperlink(event||window.event, [{"data":"var as=arguments; return FR.tc(function(){FR.doHyperlinkByGet4Reportlet({\"url\":\"/webroot/decision/view/report?viewlet=%252F%25E8%25B4%25A2%25E5%258A%25A1%25E5%2585%25B1%25E4%25BA%25AB%25E5%25B9%25B3%25E5%258F%25B0%252F%25E5%25BE%2580%25E6%259D%25A5%25E6%25B8%2585%25E7%2590%2586%252FContact_details_mc.cpt\",\"para\":{\"__pi__\":true,\"op\":\"write\",\"__cutpage__\":\"v\",\"ACCOUNTS_ID\":\""+accountsId+"\",\"ACCOUNT_TIME\":\""+month+"\",\"SUBJECT_CODE\":\""+subjectCode+"\",\"selectallFlag\":\"全选\"},\"target\":\"_blank\",\"feature\":{\"width\":600,\"height\":400,\"isCenter\":true,\"title\":\"\"},\"title\":\"网络报表1\"})}, this, as)","name":"网络报表1"}], true);
                          },500);
                }
            } else {
                console.log(res.msg);
            }
        },
        error: function () {
            alert('请求异常！');
        }
    });
}

/**
//--@relation：进行勾兑/取消勾兑
    接口：/fssc_report/clear/takeBlendingUpdate
    userCode：用户名
    operateFlag：文本内容：勾兑/取消勾兑/批量更新
    month：月度
    accountsId：账套id
    subjectCode：科目
    @date 20201203 @author gxy
 */
function ClearTakeBlendingUpdate(userCode, operateFlag, month, accountsId, subjectCode) {
    $.ajax({
        type: 'post',
        url: 'http://' + appip + '/fssc_report/clear/takeBlendingUpdate',
        dataType: 'json',
        data: { userCode: userCode, operateFlag: operateFlag, month: month, accountsId: accountsId, subjectCode: subjectCode },
        success: function (res) {
            if (res.code == "200") {
                alert(res.msg);
                if (operateFlag == "勾兑" || operateFlag == "取消勾兑") {
                    contentPane.parameterCommit();
                } else {
                    alert("操作参数不符合规范");
                }
            } else {
                alert(res.msg);
            }
        },
        error: function () {
            alert('请求异常！');
        }
    });
}

/**
//--@relation：初始化选择内容
    接口：/fssc_report/clear/clearBlendInit
    userCode：用户名
    operateFlag：文本内容：全选/为空
    month：月度
    accountsId：账套id
    subjectCode：科目
    @date 20201203 @author gxy
 */
function clearBlendInit(userCode, operateFlag,month,accountsId, subjectCode) {
    $.ajax({
        type: 'post',
        url: 'http://' + appip + '/fssc_report/clear/clearBlendInit',
        dataType: 'json',
        data: { userCode:userCode, operateFlag:operateFlag,month: month, accountsId: accountsId,subjectCode:subjectCode },
        success: function (res) {
            if (res.code == "200") {
                console.log(res.msg);
                if ( operateFlag == '全选') {
                    //执行当前页面刷新操作
                    setTimeout(function(){
                        FR.doHyperlink(event||window.event, [{"data":"var as=arguments; return FR.tc(function(){FR.doHyperlinkByGet4Reportlet({\"url\":\"/webroot/decision/view/report?viewlet=%252F%25E8%25B4%25A2%25E5%258A%25A1%25E5%2585%25B1%25E4%25BA%25AB%25E5%25B9%25B3%25E5%258F%25B0%252F%25E5%25BE%2580%25E6%259D%25A5%25E6%25B8%2585%25E7%2590%2586%252FContact_details_mc.cpt\",\"para\":{\"__pi__\":true,\"op\":\"write\",\"__cutpage__\":\"v\",\"ACCOUNTS_ID\":\""+accountsId+"\",\"ACCOUNT_TIME\":\""+month+"\",\"SUBJECT_CODE\":\""+subjectCode+"\",\"selectallFlag\":\"全选\"},\"target\":\"_blank\",\"feature\":{\"width\":600,\"height\":400,\"isCenter\":true,\"title\":\"\"},\"title\":\"网络报表1\"})}, this, as)","name":"网络报表1"}], true);
                          },500);
                }else{
                contentPane.parameterCommit();
            }
            } else {
                console.log(res.msg);
            }
        },
        error: function () {
            alert('请求异常！');
        }
    });
}

/**-----------------------------------往来清理大数据问题end--------------------------------- */