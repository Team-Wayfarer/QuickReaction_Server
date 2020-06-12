<template>
    <div>
        <div class="main">
            <div class="section">
                <slot>
                    <input type="text" v-model="email" ref="email" placeholder="이메일을 입력해주세요">
                </slot>
                <slot>
                    <input type="password" v-model="password" placeholder="비밀번호를 입력해주세요">
                </slot>
                <slot>
                    <input type="password" v-model="check" placeholder="비밀번호를 다시 한 번 입력해주세요">
                </slot>
                <slot>
                    <input type="text" v-model="name" placeholder="이름을 입력해주세요">
                </slot>
                <slot>
                    <input type="text" v-model="contact" placeholder="연락처를 입력해주세요">
                </slot>
                <slot>
                    <input type="text" v-model="business" ref="business" placeholder="사업자번호를 입력해주세요">
                </slot>
                <div>
                    <button class="btn btn-primary" @click="regist">가입</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios"
export default {
    name: "spotsignup",
    data() {
        return {
            email: '',
            password: '',
            check: '',
            name: '',
            contact: '',
            business: '',
            config: '',
        }
    },
    methods: {
        regist() {
            const baseUrl = "https://192.168.0.10:8080/cfcqr/api/spotAdmins/";
            axios.post(baseUrl + "/checkmail", {
                email: this.email
            })
            .then(()=> {
                axios.post(baseUrl+"/checknumber", {
                    businessNumber: this.business
                })
                .catch(()=>{
                    alert("사업자 번호 중복");
                    this.$refs.business.focus();
                });
            })
            .catch(()=>{
                alert("이메일 중복");
                this.$refs.email.focus();
            });
            axios.post(baseUrl, {
                name: this.name,
                email: this.email,
                password: this.password,
                contact: this.contact,
                businessNumber: this.business
            })
            .then(()=>{
                alert("가입성공");
                this.$router.push("/spot");
            })
            .catch(()=>{
                alert("서버 오류")
                this.$refs.email.focus();
            });
        }
    }
}
</script>

<style>
    
</style>