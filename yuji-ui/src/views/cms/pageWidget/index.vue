<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="所属站点ID" prop="siteId">
        <el-input
          v-model="queryParams.siteId"
          placeholder="请输入所属站点ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属栏目ID" prop="catalogId">
        <el-input
          v-model="queryParams.catalogId"
          placeholder="请输入所属栏目ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属栏目祖级IDs" prop="catalogAncestors">
        <el-input
          v-model="queryParams.catalogAncestors"
          placeholder="请输入所属栏目祖级IDs"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="编码，站内唯一标识" prop="code">
        <el-input
          v-model="queryParams.code"
          placeholder="请输入编码，站内唯一标识"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="state">
        <el-input
          v-model="queryParams.state"
          placeholder="请输入状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发布通道编码" prop="publishPipeCode">
        <el-input
          v-model="queryParams.publishPipeCode"
          placeholder="请输入发布通道编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="模板" prop="template">
        <el-input
          v-model="queryParams.template"
          placeholder="请输入模板"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发布目录" prop="path">
        <el-input
          v-model="queryParams.path"
          placeholder="请输入发布目录"
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
          v-hasPermi="['cms:pageWidget:add']"
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
          v-hasPermi="['cms:pageWidget:edit']"
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
          v-hasPermi="['cms:pageWidget:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['cms:pageWidget:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pageWidgetList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="pageWidgetId" />
      <el-table-column label="所属站点ID" align="center" prop="siteId" />
      <el-table-column label="所属栏目ID" align="center" prop="catalogId" />
      <el-table-column label="所属栏目祖级IDs" align="center" prop="catalogAncestors" />
      <el-table-column label="类型" align="center" prop="type" />
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="编码，站内唯一标识" align="center" prop="code" />
      <el-table-column label="状态" align="center" prop="state" />
      <el-table-column label="发布通道编码" align="center" prop="publishPipeCode" />
      <el-table-column label="模板" align="center" prop="template" />
      <el-table-column label="发布目录" align="center" prop="path" />
      <el-table-column label="页面部件内容" align="center" prop="content" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['cms:pageWidget:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['cms:pageWidget:remove']"
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

    <!-- 添加或修改页面部件对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="所属站点ID" prop="siteId">
          <el-input v-model="form.siteId" placeholder="请输入所属站点ID" />
        </el-form-item>
        <el-form-item label="所属栏目ID" prop="catalogId">
          <el-input v-model="form.catalogId" placeholder="请输入所属栏目ID" />
        </el-form-item>
        <el-form-item label="所属栏目祖级IDs" prop="catalogAncestors">
          <el-input v-model="form.catalogAncestors" placeholder="请输入所属栏目祖级IDs" />
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="编码，站内唯一标识" prop="code">
          <el-input v-model="form.code" placeholder="请输入编码，站内唯一标识" />
        </el-form-item>
        <el-form-item label="状态" prop="state">
          <el-input v-model="form.state" placeholder="请输入状态" />
        </el-form-item>
        <el-form-item label="发布通道编码" prop="publishPipeCode">
          <el-input v-model="form.publishPipeCode" placeholder="请输入发布通道编码" />
        </el-form-item>
        <el-form-item label="模板" prop="template">
          <el-input v-model="form.template" placeholder="请输入模板" />
        </el-form-item>
        <el-form-item label="发布目录" prop="path">
          <el-input v-model="form.path" placeholder="请输入发布目录" />
        </el-form-item>
        <el-form-item label="页面部件内容">
          <editor v-model="form.content" :min-height="192"/>
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
import { listPageWidget, getPageWidget, delPageWidget, addPageWidget, updatePageWidget } from "@/api/cms/contentcore/pageWidget";

export default {
  name: "PageWidget",
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
      // 页面部件表格数据
      pageWidgetList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        siteId: null,
        catalogId: null,
        catalogAncestors: null,
        type: null,
        name: null,
        code: null,
        state: null,
        publishPipeCode: null,
        template: null,
        path: null,
        content: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        siteId: [
          { required: true, message: "所属站点ID不能为空", trigger: "blur" }
        ],
        catalogId: [
          { required: true, message: "所属栏目ID不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "类型不能为空", trigger: "change" }
        ],
        name: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        code: [
          { required: true, message: "编码，站内唯一标识不能为空", trigger: "blur" }
        ],
        state: [
          { required: true, message: "状态不能为空", trigger: "blur" }
        ],
        publishPipeCode: [
          { required: true, message: "发布通道编码不能为空", trigger: "blur" }
        ],
        path: [
          { required: true, message: "发布目录不能为空", trigger: "blur" }
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
    /** 查询页面部件列表 */
    getList() {
      this.loading = true;
      listPageWidget(this.queryParams).then(response => {
        this.pageWidgetList = response.rows;
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
        pageWidgetId: null,
        siteId: null,
        catalogId: null,
        catalogAncestors: null,
        type: null,
        name: null,
        code: null,
        state: null,
        publishPipeCode: null,
        template: null,
        path: null,
        content: null,
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
      this.ids = selection.map(item => item.pageWidgetId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加页面部件";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const pageWidgetId = row.pageWidgetId || this.ids
      getPageWidget(pageWidgetId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改页面部件";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.pageWidgetId != null) {
            updatePageWidget(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPageWidget(this.form).then(response => {
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
      const pageWidgetIds = row.pageWidgetId || this.ids;
      this.$modal.confirm('是否确认删除页面部件编号为"' + pageWidgetIds + '"的数据项？').then(function() {
        return delPageWidget(pageWidgetIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('cms/pageWidget/export', {
        ...this.queryParams
      }, `pageWidget_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
