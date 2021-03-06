/*
  	作者：艺灵
  	时间：2017-04-29
  	描述：vue版公用验证信息
  	来源：www.yilingsj.com
  	
*/




var Y={Loc:location};
Y.Size=function(a){
	return a.replace(/[^\x00-\xff]/g,'yl');
};

Y.zj=function(i){
	i?i:i=0;
	var a={
			num:i,
			dat:{
				custId:0,
				nam:"",
				guo:"+86",
				quh:"",
				pho:"",
				ema:"",
				sign:"zj"+i
			}
		}
	return a;
}; 

Y.kh=function(i){
	i?i:i=0;
	var a={
			num:i,
			dat:{
				custId:0,
				nam:"",
				guo:"+86",
				quh:"",
				pho:"",
				ema:"",
				sign:"kh"+i
			}
		}
	return a;
}; 
//window.onload=function(){
	

var Vbus=new Vue({
				data:{
					M:{
						//错误iconfont
						e:"<i class='icon iconfont'>&#xe613;</i>",
						nN:"用户名不能为空",
						pN:"密码不能为空",
						n:"支持中文+数字+英文字母(3~20位)",
						n1:"支持中文+数字+英文字母(6~20位)",
						pl:"内容限(3~200字)",
						p:"大小写字母+数字(6~20位)",
						//验证码
						c:"长度限4位",
						e1:"邮箱格式有误(10~30位)",
						y:"验证码错误",
						rs:"邮件已发送成功!现在去邮箱中点击激活邮件即可成功激活帐号哦^o^",
						Gps:"邮件已发送到看官的邮箱中，请前往邮箱点击链接重置密码。",
						w:"提交中，请稍等片刻......",
						//常规提交内容
						f:"检测到有非法字符",
						
					}
					
				}
				,created:function(){
					//console.log('准备就绪0++'+Y);
					_thi2=this;
					//判断有无jq库
					//console.log(' layer !=="object"'+typeof $);
					//Y.JQu();
					//判断有无弹层
					//_thi2.vPop();
			    	//Y.Pop();
					
				}
				,mounted:function(){
					//console.log('mounted2 -layer:');
					//this.vPop();
				}
				,methods:{
					//常规检测
					rstr:function(a){
						a = a.replace(/\s/g,' ');
						a = a.replace(/[!！￥……*~——+、:;#$%&'"‘’“”\/\\=?？《》<>·^`{|}]|\-|\(|\)|\[|\]|[\uff00-\uffff]/g,'');
		    			return a;
					},
					//评论内容
					rTex:function(a,b){
						return /^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9]|，|。|？|！|“|”)+$/.test(a);
						
					},
					//再新注册的以这个为标准，之前注册过的就算了 ，支持中文+数字+英文字母
					rName:function(a){
						/*[\u4E00-\uFA29]|[\uE7C7-\uE7F3]汉字编码范围 
						var re1 = new RegExp("^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9])*$"); 
						*/
						return /^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9_-]|\.|@)+$/.test(a);
						
					}
					//必须为字母加数字且长度不小于6位 
					,rPass:function(a){
						return /^([a-zA-Z0-9_-]|\.|,|@){6,8}$/.test(a);
					}
					//必须为字母 4位 
					,rCod:function(a){
						return /^[a-zA-Z0-9]{4}$/.test(a);
					}
					//手机号 
					,rMob:function(a){
						return /^1[3|4|5|6|7|8|9]{1}[0-9]{9}$/.test(a);
					}
					//国家码 
					,rGuo:function(a){
						return /^\+([1-9]|[1-9][0-9]*)$/.test(a);
					}
					//区号 
					,rArea:function(a){
						return /^\+?[0-9]*$/.test(a);
					}
					//电话号码 
					,rTel:function(a){
						return /^\+?[0-9]*$/.test(a);
					},
					//数字
					rNum:function(a){
						return /^[0-9]*$/.test(a);
					},
        //邮箱
					rema:function(a){
						return /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(a);
					}
					//公用用户名验证
					//公用密码
					,Gpass:function(b){
						if(_thi2.rPass(b.v)==false){
										//console.log(584)
										if(b.v=="" || Y.Size(b.v).length<6){
											b.e=_thi2.M.e+_thi2.M.p;
										}else{
											b.e=_thi2.M.e+_thi2.M.f;
										}
										//lVm.$set(b,'e',Vbus.M.n);
										b.s=0;
										return false;
										//console.log(JSON.stringify(b))
									}
						b.e='';
											b.s=1;
					}
					//公用验证码
					,Gcode:function(b,Djv){
						//console.log('_thi2:'+_thi2.rCod(b.v))
						//console.log('_this:'+_this.rName(b.v))
						if(_thi2.rCod(b.v)==false){
										if(b.v=="" || Y.Size(b.v).length!=4){
											b.e=_thi2.M.e+_thi2.M.c;
										}else{
											b.e=_thi2.M.e+_thi2.M.f;
										}
										b.s=0;
										return false;
						}
						
										b.e='';
										b.s=1;
					}
					//计时器
					,Stime:function(a){
						console.log('a:'+a)
						$(a).css('pointer-events','auto').attr('disabled',1).text('发送中...');
						var i=120;
						var k=setInterval(function(){
								if(i==0){
									$(a).text('重新发送').css('pointer-events','inherit').removeAttr('disabled');
									clearInterval(k);
								}else{
									i--;
									$(a).text(i+'秒');
								}
							},1E3); 
					},
					checkEquanl:function(datas,name,key,data,Did){
						if(name=="zj"){
							   for(var i=0;i<datas.zj.length;i++){
							    	if(i!=key){
							    		if(datas.zj[i].dat[Did]==data){
											return false;
							    		}
							    	}
							   }
						   }else{
							   for(var i=0;i<datas.zj.length;i++){
							    	if(datas.zj[i].dat[Did]==data){
							    			return false;
							    	}
							   }
						   }
	                   if(name=="kh"){
						   for(var i=0;i<datas.kh.length;i++){
							   if(i!=key){
						    		if(datas.kh[i].dat[Did]==data){
						    			return false
						    		}
						    	}
						    }
					   }else{
						   for(var i=0;i<datas.kh.length;i++){
						    	if(datas.kh[i].dat[Did]==data){
						    		return false
						    	}
						    }
					   }
					},
				}
			});
//}			