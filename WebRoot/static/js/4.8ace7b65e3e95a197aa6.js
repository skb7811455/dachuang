webpackJsonp([4],{EfJ2:function(e,t){},UlFS:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});o("yFfA");var s={data:function(){return{loginUrl:"/dachuang/Login",registerUrl:"",loginModel:{id:"",password:""},registerModel:{id:"",password:"",confirmPassword:""},loginActive:!0,msg:""}},methods:{register:function(){var e=this,t=this.registerModel;this.isModelEmpty(t)?e.msg="账号密码不能为空":e.$http.post(e.registerUrl,e.registerModel,{emulateJSON:!0}).then(function(t){console.log(t.body),1==t.body.code?(e.msg="注册成功",console.log(t.body)):e.msg=t.body.message})},login:function(){var e=this,t=this.loginModel;this.isModelEmpty(t)?e.msg="账号密码不能为空":e.$http.post(e.loginUrl,e.loginModel,{emulateJSON:!0}).then(function(t){if(console.log(t.body),1==t.body.code){e.msg="登录成功";var o=t.body.data.id;console.log(t.body),sessionStorage.user=o,console.log("用户登录成功"),this.$router.push("/sale")}else e.msg=t.body.message})},isModelEmpty:function(e){return""==e.id||""==e.password},moveLoginForm:function(){this.loginActive=!1},moveRegisterForm:function(){this.loginActive=!0}}},i={render:function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",{staticClass:"login"},[o("div",{staticClass:"btnWrapper"},[o("div",{staticClass:"loginButton",on:{click:e.moveRegisterForm}},[e._v("登录")]),e._v(" "),o("div",{staticClass:"registerButton",on:{click:e.moveLoginForm}},[e._v("注册")])]),e._v(" "),e.loginActive?o("form",{staticClass:"loginForm"},[o("p",[e._v(e._s(this.msg))]),e._v(" "),o("input",{directives:[{name:"model",rawName:"v-model",value:e.loginModel.id,expression:"loginModel.id"}],staticClass:"input",attrs:{type:"text",placeholder:"账号"},domProps:{value:e.loginModel.id},on:{input:function(t){t.target.composing||e.$set(e.loginModel,"id",t.target.value)}}}),e._v(" "),o("input",{directives:[{name:"model",rawName:"v-model",value:e.loginModel.password,expression:"loginModel.password"}],staticClass:"input",attrs:{type:"password",placeholder:"密码"},domProps:{value:e.loginModel.password},on:{input:function(t){t.target.composing||e.$set(e.loginModel,"password",t.target.value)}}}),e._v(" "),o("div",[o("input",{staticClass:"confirmButton",attrs:{type:"button",value:"确认"},on:{click:e.login}})])]):e._e(),e._v(" "),e.loginActive?e._e():o("form",{staticClass:"registerForm"},[o("p",[e._v(e._s(this.msg))]),e._v(" "),o("input",{directives:[{name:"model",rawName:"v-model",value:e.registerModel.id,expression:"registerModel.id"}],staticClass:"input",attrs:{type:"text",placeholder:"账号"},domProps:{value:e.registerModel.id},on:{input:function(t){t.target.composing||e.$set(e.registerModel,"id",t.target.value)}}}),e._v(" "),o("input",{directives:[{name:"model",rawName:"v-model",value:e.registerModel.password,expression:"registerModel.password"}],staticClass:"input",attrs:{type:"password",placeholder:"密码"},domProps:{value:e.registerModel.password},on:{input:function(t){t.target.composing||e.$set(e.registerModel,"password",t.target.value)}}}),e._v(" "),o("input",{directives:[{name:"model",rawName:"v-model",value:e.registerModel.confirmPassword,expression:"registerModel.confirmPassword"}],staticClass:"input",attrs:{type:"password",placeholder:"确认"},domProps:{value:e.registerModel.confirmPassword},on:{input:function(t){t.target.composing||e.$set(e.registerModel,"confirmPassword",t.target.value)}}}),e._v(" "),o("div",[o("input",{staticClass:"confirmButton",attrs:{type:"button",value:"提交"},on:{click:e.register}})])])])},staticRenderFns:[]};var r={components:{login:o("VU/8")(s,i,!1,function(e){o("EfJ2")},"data-v-fee6cc9a",null).exports}},n={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticClass:"loginMenu"},[t("div",{staticClass:"loginWrapper"},[t("h2",[this._v("欢迎注册账号")]),this._v(" "),t("login")],1)])},staticRenderFns:[]};var l=o("VU/8")(r,n,!1,function(e){o("xGMS")},"data-v-3b8c324b",null);t.default=l.exports},xGMS:function(e,t){}});
//# sourceMappingURL=4.8ace7b65e3e95a197aa6.js.map