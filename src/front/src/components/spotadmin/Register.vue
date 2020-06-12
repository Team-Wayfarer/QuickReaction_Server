<template>
    <div>
        <div class="main">
            <div class="section">
                <slot>
                    <input type="text" v-model="name" ref="name" placeholder="장소의 이름을 입력해주세요">
                </slot>
                <slot>
                    <input type="text" v-model="city" placeholder="시,도를 입력해주세요">
                    <input type="text" v-model="gungu" placeholder="군,구를 입력해주세요">
                    <input type="text" v-model="zipcode" placeholder="우편번호를 입력해주세요">
                    <input type="text" v-model="detail" placeholder="상세주소를 입력해주세요">
                </slot>
                <div>
                    <button class="btn btn-primary" @click="regist">등록</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios"
export default {
    name: "spotregist",
    data() {
        return {
            name: '',
            city: '',
            gungu: '',
            zipcode: '',
            detail: ''
        }
    },
    methods: {
        regist() {
            const baseUrl = "https://localhost:8080/cfcqr/api/spots/";
            axios.post(baseUrl + this.$store.id, {
                name: this.name,
                city: this.city,
                gungu: this.gungu,
                zipcode: this.zipcode,
                detail: this.detail
            })
            .then((/*data*/)=> {
                //받아온 datad의 spot id를 넣어야 함
                //this.$store.state.spotid = data.id;
                this.$route.push("/spot");
            })
        }
    }
}
</script>

<style>
    
</style>