import Vue from "vue";
import VueRouter from "vue-router";

import Home from "@/components/Main.vue";
import SpotMain from "@/components/spotadmin/Main.vue";
import SpotSignUp from "@/components/spotadmin/Signup.vue";
import SpotRegister from "@/components/spotadmin/Register.vue";
import SpotManage from "@/components/spotadmin/Manage.vue";
import CodeIssue from "@/components/spotadmin/Issue.vue";
import CodeDestroy from "@/components/spotadmin/Destroy.vue";
import Admin from "@/components/Admin.vue";

Vue.use(VueRouter);

export default new VueRouter({
  mode: "history",
  routes: [
    {
      path: "/",
      name: "home",
      component: Home
    },
    {
      path: "/spotsignup",
      name: "spotsignup",
      components: SpotSignUp
    },
    {
      path: "/spot",
      name: "spot",
      component: SpotMain
    },
    {
      path: "/spotregist",
      name: "spotregist",
      component: SpotRegister
    },
    {
      path: "/spotdetail",
      name: "spotdetail",
      component: SpotManage
    },
    {
      path: "/codeissue",
      name: "codeissue",
      component: CodeIssue
    },
    {
      path: "/codedestroy",
      name: "codedestroy",
      component: CodeDestroy
    },
    {
      path: "/admin",
      name: "admin",
      component: Admin
    }
  ]
});
