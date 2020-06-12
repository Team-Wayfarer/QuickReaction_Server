<template>
    <div>
        <div class="main">
            <div class="section">
                <slot>
                    <input type="text" v-model="id" ref="id" placeholder="아이디를 입력해주세요">
                </slot>
                <br>
                <slot>
                    <input type="password" v-model="password" placeholder="비밀번호를 입력해주세요">
                </slot>
                <div>
                    <button class="btn btn-primary" @click="signin">로그인</button>
                </div>  
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios"
export default {
    name: "Login",
    data() {
        return {
            id: '',
            password: ''
        }
    },
    methods: {
        signin() {
            const baseUrl = "http://localhost:8080/cfcqr/api/login";
            axios.post(baseUrl + "/center", {
                id: this.id,
                password: this.password,
            })
            .then((data)=> {
                console.log(data);
                //여기서 받아온 토큰과 id를 store해야한다,
                // this.$store.state.token = data.accessToken;
                // this.$store.state.id = data.id;
                this.$router.push("/admin");
            })
            .catch(()=>{
                alert("아이디 및 패스워드 확인바랍니다");
                this.$refs.id.focus();
            });
        }
    }
}
</script>

<style>
    
</style>