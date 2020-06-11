import Vue from "vue";
import VueRouter from "vue-router";

import Main from "@/components/Main.vue";
import SpotMain from "@/components/spotadmin/Main.vue";
import SpotRegister from "@/components/spotadmin/Register.vue";
import SpotManage from "@/components/spotadmin/Manage.vue";
import CodeIssue from "@/components/spotadmin/Isuue.vue";
import CodeDestroy from "@/components/spotadmin/Destroy.vue";

Vue.use(VueRouter);

export default new VueRouter({
  mode: "history",
  routes: [
    {
      path: "/",
      name: "main",
      component: Main
    },
    {
      path: "/spot",
      name: "spot",
      component: SpotMain
    },
    {
      path: "/spot/regist",
      name: "spotregist",
      component: SpotRegister
    },
    {
      path: "/spot/detail",
      name: "spotdetail",
      component: SpotManage
    },
    {
      path: "/code/issue",
      name: "codeissue",
      component: CodeIssue
    },
    {
      path: "/code/destroy",
      name: "codedestroy",
      component: CodeDestroy
    }
  ]
});
