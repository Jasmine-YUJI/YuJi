<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="父级站点ID" prop="parentId">
        <el-input
          v-model="queryParams.parentId"
          placeholder="请输入父级站点ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="站点名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入站点名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="站点LOGO" prop="logo">
        <el-input
          v-model="queryParams.logo"
          placeholder="请输入站点LOGO"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="站点目录" prop="path">
        <el-input
          v-model="queryParams.path"
          placeholder="请输入站点目录"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="站点资源访问地址" prop="resourceUrl">
        <el-input
          v-model="queryParams.resourceUrl"
          placeholder="请输入站点资源访问地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="顶级栏目编码最大值" prop="catalogMaxCode">
        <el-input
          v-model="queryParams.catalogMaxCode"
          placeholder="请输入顶级栏目编码最大值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属机构编码" prop="deptCode">
        <el-input
          v-model="queryParams.deptCode"
          placeholder="请输入所属机构编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="首页模板" prop="indexTemplate">
        <el-input
          v-model="queryParams.indexTemplate"
          placeholder="请输入首页模板"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="静态文件类型" prop="staticSuffix">
        <el-input
          v-model="queryParams.staticSuffix"
          placeholder="请输入静态文件类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="排序标识" prop="sortFlag">
        <el-input
          v-model="queryParams.sortFlag"
          placeholder="请输入排序标识"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="SEO关键词" prop="seoKeywords">
        <el-input
          v-model="queryParams.seoKeywords"
          placeholder="请输入SEO关键词"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="SEO描述" prop="seoDescription">
        <el-input
          v-model="queryParams.seoDescription"
          placeholder="请输入SEO描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="SEO标题" prop="seoTitle">
        <el-input
          v-model="queryParams.seoTitle"
          placeholder="请输入SEO标题"
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
          v-hasPermi="['cms:site:add']"
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
          v-hasPermi="['cms:site:edit']"
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
          v-hasPermi="['cms:site:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['cms:site:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="siteList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="站点ID" align="center" prop="siteId" />
      <el-table-column label="父级站点ID" align="center" prop="parentId" />
      <el-table-column label="站点名称" align="center" prop="name" />
      <el-table-column label="简介" align="center" prop="description" />
      <el-table-column label="站点LOGO" align="center" prop="logo" />
      <el-table-column label="站点目录" align="center" prop="path" />
      <el-table-column label="站点资源访问地址" align="center" prop="resourceUrl" />
      <el-table-column label="顶级栏目编码最大值" align="center" prop="catalogMaxCode" />
      <el-table-column label="所属机构编码" align="center" prop="deptCode" />
      <el-table-column label="首页模板" align="center" prop="indexTemplate" />
      <el-table-column label="静态文件类型" align="center" prop="staticSuffix" />
      <el-table-column label="排序标识" align="center" prop="sortFlag" />
      <el-table-column label="发布通道属性" align="center" prop="publishPipeProps" />
      <el-table-column label="站点扩展属性" align="center" prop="configProps" />
      <el-table-column label="SEO关键词" align="center" prop="seoKeywords" />
      <el-table-column label="SEO描述" align="center" prop="seoDescription" />
      <el-table-column label="SEO标题" align="center" prop="seoTitle" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['cms:site:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['cms:site:remove']"
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

    <!-- 添加或修改站点管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="父级站点ID" prop="parentId">
          <el-input v-model="form.parentId" placeholder="请输入父级站点ID" />
        </el-form-item>
        <el-form-item label="站点名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入站点名称" />
        </el-form-item>
        <el-form-item label="简介" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="站点LOGO" prop="logo">
          <el-input v-model="form.logo" placeholder="请输入站点LOGO" />
        </el-form-item>
        <el-form-item label="站点目录" prop="path">
          <el-input v-model="form.path" placeholder="请输入站点目录" />
        </el-form-item>
        <el-form-item label="站点资源访问地址" prop="resourceUrl">
          <el-input v-model="form.resourceUrl" placeholder="请输入站点资源访问地址" />
        </el-form-item>
        <el-form-item label="顶级栏目编码最大值" prop="catalogMaxCode">
          <el-input v-model="form.catalogMaxCode" placeholder="请输入顶级栏目编码最大值" />
        </el-form-item>
        <el-form-item label="所属机构编码" prop="deptCode">
          <el-input v-model="form.deptCode" placeholder="请输入所属机构编码" />
        </el-form-item>
        <el-form-item label="首页模板" prop="indexTemplate">
          <el-input v-model="form.indexTemplate" placeholder="请输入首页模板" />
        </el-form-item>
        <el-form-item label="静态文件类型" prop="staticSuffix">
          <el-input v-model="form.staticSuffix" placeholder="请输入静态文件类型" />
        </el-form-item>
        <el-form-item label="排序标识" prop="sortFlag">
          <el-input v-model="form.sortFlag" placeholder="请输入排序标识" />
        </el-form-item>
        <el-form-item label="发布通道属性" prop="publishPipeProps">
          <el-input v-model="form.publishPipeProps" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="站点扩展属性" prop="configProps">
          <el-input v-model="form.configProps" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="SEO关键词" prop="seoKeywords">
          <el-input v-model="form.seoKeywords" placeholder="请输入SEO关键词" />
        </el-form-item>
        <el-form-item label="SEO描述" prop="seoDescription">
          <el-input v-model="form.seoDescription" placeholder="请输入SEO描述" />
        </el-form-item>
        <el-form-item label="SEO标题" prop="seoTitle">
          <el-input v-model="form.seoTitle" placeholder="请输入SEO标题" />
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
import { listSite, getSite, delSite, addSite, updateSite } from "@/api/cms/contentcore/site";

export default {
  name: "Site",
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
      // 站点管理表格数据
      siteList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        parentId: null,
        name: null,
        description: null,
        logo: null,
        path: null,
        resourceUrl: null,
        catalogMaxCode: null,
        deptCode: null,
        indexTemplate: null,
        staticSuffix: null,
        sortFlag: null,
        publishPipeProps: null,
        configProps: null,
        seoKeywords: null,
        seoDescription: null,
        seoTitle: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        parentId: [
          { required: true, message: "父级站点ID不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "站点名称不能为空", trigger: "blur" }
        ],
        path: [
          { required: true, message: "站点目录不能为空", trigger: "blur" }
        ],
        sortFlag: [
          { required: true, message: "排序标识不能为空", trigger: "blur" }
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
    /** 查询站点管理列表 */
    getList() {
      this.loading = true;
      listSite(this.queryParams).then(response => {
        this.siteList = response.rows;
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
        siteId: null,
        parentId: null,
        name: null,
        description: null,
        logo: null,
        path: null,
        resourceUrl: null,
        catalogMaxCode: null,
        deptCode: null,
        indexTemplate: null,
        staticSuffix: null,
        sortFlag: null,
        publishPipeProps: null,
        configProps: null,
        seoKeywords: null,
        seoDescription: null,
        seoTitle: null,
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
      this.ids = selection.map(item => item.siteId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加站点管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const siteId = row.siteId || this.ids
      getSite(siteId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改站点管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.siteId != null) {
            updateSite(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSite(this.form).then(response => {
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
      const siteIds = row.siteId || this.ids;
      this.$modal.confirm('是否确认删除站点管理编号为"' + siteIds + '"的数据项？').then(function() {
        return delSite(siteIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('cms/site/export', {
        ...this.queryParams
      }, `site_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
