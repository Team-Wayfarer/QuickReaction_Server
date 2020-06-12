<template>
    <div>
        <div>
            <input type="text" placeholder="환자 번호를 입력해주세요" v-model="useridx" ref="useridx" v-bind="useridx">
        </div>
        <div>
            <select id="userstaus" v-model="userstatus" ref="userstatus" aria-placeholder="상태를 선택하세요">
                <option value="userstatus = infect">확진</option>
                <option value="userstatus = doubt">접촉자</option>
                <option value="userstatus = normal">정상</option>
            </select>
        </div>
        <div>
            <button class="btn btn-primary" @click="setstatus">등록</button>
        </div>
    </div>
</template>

<script>
import axios from "axios";
export default {
    name: "admin",
    data() {
        return {
            useridx: "",
            userstatus: ""
        }
    },methods: {
        setstatus() {
            const baseurl = "http://localhost:8080/cfcqr/api/users/";
            console.log(this.useridx);
            console.log(this.userstatus);
            axios.post(baseurl + this.useridx + "/change/" + this.userstatus +"/infect").then((data) => {
                alert(data.id +"번 변경완료");
                this.$router.push("/admin");
            }).catch(()=>{
                alert("error 발생");
                this.$refs.useridx.focus();
            })
        }
    }
};
</script>