<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="站点id" prop="siteId">
        <el-input
          v-model="queryParams.siteId"
          placeholder="请输入站点id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="资源名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入资源名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文件保存相对路径" prop="path">
        <el-input
          v-model="queryParams.path"
          placeholder="请输入文件保存相对路径"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文件名称" prop="fileName">
        <el-input
          v-model="queryParams.fileName"
          placeholder="请输入文件名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="后缀名，不带." prop="suffix">
        <el-input
          v-model="queryParams.suffix"
          placeholder="请输入后缀名，不带."
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="宽" prop="width">
        <el-input
          v-model="queryParams.width"
          placeholder="请输入宽"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="高" prop="height">
        <el-input
          v-model="queryParams.height"
          placeholder="请输入高"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文件大小" prop="fileSize">
        <el-input
          v-model="queryParams.fileSize"
          placeholder="请输入文件大小"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="来源地址" prop="sourceUrl">
        <el-input
          v-model="queryParams.sourceUrl"
          placeholder="请输入来源地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="引用关系" prop="usageInfo">
        <el-input
          v-model="queryParams.usageInfo"
          placeholder="请输入引用关系"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['cms:resource:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['cms:resource:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['cms:resource:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['cms:resource:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="resourceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="resourceId" />
      <el-table-column label="站点id" align="center" prop="siteId" />
      <el-table-column label="资源类型" align="center" prop="resourceType" />
      <el-table-column label="存储类型" align="center" prop="storageType" />
      <el-table-column label="资源名称" align="center" prop="name" />
      <el-table-column label="文件保存相对路径" align="center" prop="path" />
      <el-table-column label="文件名称" align="center" prop="fileName" />
      <el-table-column label="后缀名，不带." align="center" prop="suffix" />
      <el-table-column label="宽" align="center" prop="width" />
      <el-table-column label="高" align="center" prop="height" />
      <el-table-column label="文件大小" align="center" prop="fileSize" />
      <el-table-column label="来源地址" align="center" prop="sourceUrl" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="引用关系" align="center" prop="usageInfo" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['cms:resource:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['cms:resource:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改资源对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="站点id" prop="siteId">
          <el-input v-model="form.siteId" placeholder="请输入站点id" />
        </el-form-item>
        <el-form-item label="资源名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入资源名称" />
        </el-form-item>
        <el-form-item label="文件保存相对路径" prop="path">
          <el-input v-model="form.path" placeholder="请输入文件保存相对路径" />
        </el-form-item>
        <el-form-item label="文件名称" prop="fileName">
          <el-input v-model="form.fileName" placeholder="请输入文件名称" />
        </el-form-item>
        <el-form-item label="后缀名，不带." prop="suffix">
          <el-input v-model="form.suffix" placeholder="请输入后缀名，不带." />
        </el-form-item>
        <el-form-item label="宽" prop="width">
          <el-input v-model="form.width" placeholder="请输入宽" />
        </el-form-item>
        <el-form-item label="高" prop="height">
          <el-input v-model="form.height" placeholder="请输入高" />
        </el-form-item>
        <el-form-item label="文件大小" prop="fileSize">
          <el-input v-model="form.fileSize" placeholder="请输入文件大小" />
        </el-form-item>
        <el-form-item label="来源地址" prop="sourceUrl">
          <el-input v-model="form.sourceUrl" placeholder="请输入来源地址" />
        </el-form-item>
        <el-form-item label="引用关系" prop="usageInfo">
          <el-input v-model="form.usageInfo" placeholder="请输入引用关系" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listResource, getResource, delResource, addResource, updateResource } from "@/api/cms/contentcore/resource";

export default {
  name: "Resource",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 资源表格数据
      resourceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        siteId: null,
        resourceType: null,
        storageType: null,
        name: null,
        path: null,
        fileName: null,
        suffix: null,
        width: null,
        height: null,
        fileSize: null,
        sourceUrl: null,
        status: null,
        usageInfo: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        siteId: [
          { required: true, message: "站点id不能为空", trigger: "blur" }
        ],
        resourceType: [
          { required: true, message: "资源类型不能为空", trigger: "change" }
        ],
        storageType: [
          { required: true, message: "存储类型不能为空", trigger: "change" }
        ],
        name: [
          { required: true, message: "资源名称不能为空", trigger: "blur" }
        ],
        path: [
          { required: true, message: "文件保存相对路径不能为空", trigger: "blur" }
        ],
        fileName: [
          { required: true, message: "文件名称不能为空", trigger: "blur" }
        ],
        suffix: [
          { required: true, message: "后缀名，不带.不能为空", trigger: "blur" }
        ],
        fileSize: [
          { required: true, message: "文件大小不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态不能为空", trigger: "change" }
        ],
        createBy: [
          { required: true, message: "创建人不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询资源列表 */
    getList() {
      this.loading = true;
      listResource(this.queryParams).then(response => {
        this.resourceList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        resourceId: null,
        siteId: null,
        resourceType: null,
        storageType: null,
        name: null,
        path: null,
        fileName: null,
        suffix: null,
        width: null,
        height: null,
        fileSize: null,
        sourceUrl: null,
        status: null,
        usageInfo: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.resourceId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加资源";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const resourceId = row.resourceId || this.ids
      getResource(resourceId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改资源";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.resourceId != null) {
            updateResource(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addResource(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const resourceIds = row.resourceId || this.ids;
      this.$modal.confirm('是否确认删除资源编号为"' + resourceIds + '"的数据项？').then(function() {
        return delResource(resourceIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('cms/resource/export', {
        ...this.queryParams
      }, `resource_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
