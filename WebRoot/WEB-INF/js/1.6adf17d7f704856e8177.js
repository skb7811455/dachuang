webpackJsonp([1],{Pt1D:function(t,n,e){"use strict";Object.defineProperty(n,"__esModule",{value:!0});var i={data:function(){return{loginUrl:"Login",loginModel:{id:"",password:""},msg:""}},methods:{login:function(){var t=this;t.msg="",this.isloginEmpty()||t.$http.post(t.loginUrl,{body:t.loginModel,headers:{"Content-Type":"application/json;charset=utf-8"}}).then(function(n){console.log("登录成功"),t.msg="登录成功"})}}},s={render:function(){this.$createElement;this._self._c;return this._m(0)},staticRenderFns:[function(){var t=this.$createElement,n=this._self._c||t;return n("div",[n("div",[n("form",[n("input",{attrs:{type:"text"}}),this._v(" "),n("input",{attrs:{type:"text"}}),this._v(" "),n("input",{attrs:{type:"button",value:"登录"}})])])])}]};var o={components:{login:e("VU/8")(i,s,!1,function(t){e("TkE5")},null,null).exports}},r={render:function(){var t=this.$createElement,n=this._self._c||t;return n("div",{attrs:{id:"index"}},[n("div",[n("div",[this._v("首页")]),this._v(" "),n("login")],1)])},staticRenderFns:[]};var l=e("VU/8")(o,r,!1,function(t){e("keNq")},null,null);n.default=l.exports},TkE5:function(t,n){},keNq:function(t,n){}});
//# sourceMappingURL=1.6adf17d7f704856e8177.js.map