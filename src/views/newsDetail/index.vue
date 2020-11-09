<template>
    <div>
        详细内容
        <button @click="back">返回</button>
    </div>
</template>
<script>
export default {
    methods: {
        back(){
            this.$router.go(-1);
        }
    },
}
</script>