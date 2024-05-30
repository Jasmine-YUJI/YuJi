<template>
  <div class="dashboard-editor-container">

    <el-row :gutter="10">
      <el-col :span="24">
        <user-info></user-info>
      </el-col>
    </el-row>

  </div>
</template>

<script>

import SysUserInfo from '@/views/system/dashboard/userInfo'

export default {
  name: 'Index',
  components: {
    'user-info': SysUserInfo
  },
  data() {
    return {

    }
  },
  methods: {

  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width:1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
