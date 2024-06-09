<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="站点ID" prop="siteId">
        <el-input
          v-model="queryParams.siteId"
          placeholder="请输入站点ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="父级栏目ID" prop="parentId">
        <el-input
          v-model="queryParams.parentId"
          placeholder="请输入父级栏目ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="栏目名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入栏目名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="栏目引导图" prop="logo">
        <el-input
          v-model="queryParams.logo"
          placeholder="请输入栏目引导图"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="栏目别名" prop="alias">
        <el-input
          v-model="queryParams.alias"
          placeholder="请输入栏目别名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="栏目简介" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="请输入栏目简介"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属部门编码" prop="deptCode">
        <el-input
          v-model="queryParams.deptCode"
          placeholder="请输入所属部门编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="站点路径" prop="path">
        <el-input
          v-model="queryParams.path"
          placeholder="请输入站点路径"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标题栏目跳转地址" prop="redirectUrl">
        <el-input
          v-model="queryParams.redirectUrl"
          placeholder="请输入标题栏目跳转地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否生成静态页面" prop="staticFlag">
        <el-input
          v-model="queryParams.staticFlag"
          placeholder="请输入是否生成静态页面"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="栏目是否可见" prop="visibleFlag">
        <el-input
          v-model="queryParams.visibleFlag"
          placeholder="请输入栏目是否可见"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="排序字段" prop="sortFlag">
        <el-input
          v-model="queryParams.sortFlag"
          placeholder="请输入排序字段"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="栏目首页模板" prop="indexTemplate">
        <el-input
          v-model="queryParams.indexTemplate"
          placeholder="请输入栏目首页模板"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="栏目首页命名" prop="indexFileName">
        <el-input
          v-model="queryParams.indexFileName"
          placeholder="请输入栏目首页命名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="列表页模板" prop="listTemplate">
        <el-input
          v-model="queryParams.listTemplate"
          placeholder="请输入列表页模板"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="列表页命名规则" prop="listNameRule">
        <el-input
          v-model="queryParams.listNameRule"
          placeholder="请输入列表页命名规则"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="详情页模板" prop="detailTemplate">
        <el-input
          v-model="queryParams.detailTemplate"
          placeholder="请输入详情页模板"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="详情页命名规则" prop="detailNameRule">
        <el-input
          v-model="queryParams.detailNameRule"
          placeholder="请输入详情页命名规则"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="栏目层级" prop="treeLevel">
        <el-input
          v-model="queryParams.treeLevel"
          placeholder="请输入栏目层级"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="子栏目数" prop="childCount">
        <el-input
          v-model="queryParams.childCount"
          placeholder="请输入子栏目数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="内容数量" prop="contentCount">
        <el-input
          v-model="queryParams.contentCount"
          placeholder="请输入内容数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="点击量" prop="hitCount">
        <el-input
          v-model="queryParams.hitCount"
          placeholder="请输入点击量"
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
          v-hasPermi="['cms:catalog:add']"
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
          v-hasPermi="['cms:catalog:edit']"
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
          v-hasPermi="['cms:catalog:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['cms:catalog:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="catalogList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="catalogId" />
      <el-table-column label="站点ID" align="center" prop="siteId" />
      <el-table-column label="父级栏目ID" align="center" prop="parentId" />
      <el-table-column label="栏目名称" align="center" prop="name" />
      <el-table-column label="栏目引导图" align="center" prop="logo" />
      <el-table-column label="栏目别名" align="center" prop="alias" />
      <el-table-column label="栏目简介" align="center" prop="description" />
      <el-table-column label="栏目祖级IDs" align="center" prop="ancestors" />
      <el-table-column label="所属部门编码" align="center" prop="deptCode" />
      <el-table-column label="栏目类型" align="center" prop="catalogType" />
      <el-table-column label="站点路径" align="center" prop="path" />
      <el-table-column label="标题栏目跳转地址" align="center" prop="redirectUrl" />
      <el-table-column label="是否生成静态页面" align="center" prop="staticFlag" />
      <el-table-column label="栏目是否可见" align="center" prop="visibleFlag" />
      <el-table-column label="排序字段" align="center" prop="sortFlag" />
      <el-table-column label="栏目首页模板" align="center" prop="indexTemplate" />
      <el-table-column label="栏目首页命名" align="center" prop="indexFileName" />
      <el-table-column label="列表页模板" align="center" prop="listTemplate" />
      <el-table-column label="列表页命名规则" align="center" prop="listNameRule" />
      <el-table-column label="详情页模板" align="center" prop="detailTemplate" />
      <el-table-column label="详情页命名规则" align="center" prop="detailNameRule" />
      <el-table-column label="栏目层级" align="center" prop="treeLevel" />
      <el-table-column label="子栏目数" align="center" prop="childCount" />
      <el-table-column label="内容数量" align="center" prop="contentCount" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="点击量" align="center" prop="hitCount" />
      <el-table-column label="SEO关键词" align="center" prop="seoKeywords" />
      <el-table-column label="SEO描述" align="center" prop="seoDescription" />
      <el-table-column label="SEO标题" align="center" prop="seoTitle" />
      <el-table-column label="发布平台属性" align="center" prop="publishPipeProps" />
      <el-table-column label="扩展配置" align="center" prop="configProps" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['cms:catalog:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['cms:catalog:remove']"
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

    <!-- 添加或修改栏目管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="站点ID" prop="siteId">
          <el-input v-model="form.siteId" placeholder="请输入站点ID" />
        </el-form-item>
        <el-form-item label="父级栏目ID" prop="parentId">
          <el-input v-model="form.parentId" placeholder="请输入父级栏目ID" />
        </el-form-item>
        <el-form-item label="栏目名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入栏目名称" />
        </el-form-item>
        <el-form-item label="栏目引导图" prop="logo">
          <el-input v-model="form.logo" placeholder="请输入栏目引导图" />
        </el-form-item>
        <el-form-item label="栏目别名" prop="alias">
          <el-input v-model="form.alias" placeholder="请输入栏目别名" />
        </el-form-item>
        <el-form-item label="栏目简介" prop="description">
          <el-input v-model="form.description" placeholder="请输入栏目简介" />
        </el-form-item>
        <el-form-item label="所属部门编码" prop="deptCode">
          <el-input v-model="form.deptCode" placeholder="请输入所属部门编码" />
        </el-form-item>
        <el-form-item label="站点路径" prop="path">
          <el-input v-model="form.path" placeholder="请输入站点路径" />
        </el-form-item>
        <el-form-item label="标题栏目跳转地址" prop="redirectUrl">
          <el-input v-model="form.redirectUrl" placeholder="请输入标题栏目跳转地址" />
        </el-form-item>
        <el-form-item label="是否生成静态页面" prop="staticFlag">
          <el-input v-model="form.staticFlag" placeholder="请输入是否生成静态页面" />
        </el-form-item>
        <el-form-item label="栏目是否可见" prop="visibleFlag">
          <el-input v-model="form.visibleFlag" placeholder="请输入栏目是否可见" />
        </el-form-item>
        <el-form-item label="排序字段" prop="sortFlag">
          <el-input v-model="form.sortFlag" placeholder="请输入排序字段" />
        </el-form-item>
        <el-form-item label="栏目首页模板" prop="indexTemplate">
          <el-input v-model="form.indexTemplate" placeholder="请输入栏目首页模板" />
        </el-form-item>
        <el-form-item label="栏目首页命名" prop="indexFileName">
          <el-input v-model="form.indexFileName" placeholder="请输入栏目首页命名" />
        </el-form-item>
        <el-form-item label="列表页模板" prop="listTemplate">
          <el-input v-model="form.listTemplate" placeholder="请输入列表页模板" />
        </el-form-item>
        <el-form-item label="列表页命名规则" prop="listNameRule">
          <el-input v-model="form.listNameRule" placeholder="请输入列表页命名规则" />
        </el-form-item>
        <el-form-item label="详情页模板" prop="detailTemplate">
          <el-input v-model="form.detailTemplate" placeholder="请输入详情页模板" />
        </el-form-item>
        <el-form-item label="详情页命名规则" prop="detailNameRule">
          <el-input v-model="form.detailNameRule" placeholder="请输入详情页命名规则" />
        </el-form-item>
        <el-form-item label="栏目层级" prop="treeLevel">
          <el-input v-model="form.treeLevel" placeholder="请输入栏目层级" />
        </el-form-item>
        <el-form-item label="子栏目数" prop="childCount">
          <el-input v-model="form.childCount" placeholder="请输入子栏目数" />
        </el-form-item>
        <el-form-item label="内容数量" prop="contentCount">
          <el-input v-model="form.contentCount" placeholder="请输入内容数量" />
        </el-form-item>
        <el-form-item label="点击量" prop="hitCount">
          <el-input v-model="form.hitCount" placeholder="请输入点击量" />
        </el-form-item>
        <el-form-item label="SEO关键词" prop="seoKeywords">
          <el-input v-model="form.seoKeywords" placeholder="请输入SEO关键词" />
        </el-form-item>
        <el-form-item label="SEO描述" prop="seoDescription">
          <el-input v-model="form.seoDescription" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="SEO标题" prop="seoTitle">
          <el-input v-model="form.seoTitle" placeholder="请输入SEO标题" />
        </el-form-item>
        <el-form-item label="发布平台属性" prop="publishPipeProps">
          <el-input v-model="form.publishPipeProps" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="扩展配置" prop="configProps">
          <el-input v-model="form.configProps" type="textarea" placeholder="请输入内容" />
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
import { listCatalog, getCatalog, delCatalog, addCatalog, updateCatalog } from "@/api/cms/contentcore/catalog";

export default {
  name: "Catalog",
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
      // 栏目管理表格数据
      catalogList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        siteId: null,
        parentId: null,
        name: null,
        logo: null,
        alias: null,
        description: null,
        ancestors: null,
        deptCode: null,
        catalogType: null,
        path: null,
        redirectUrl: null,
        staticFlag: null,
        visibleFlag: null,
        sortFlag: null,
        indexTemplate: null,
        indexFileName: null,
        listTemplate: null,
        listNameRule: null,
        detailTemplate: null,
        detailNameRule: null,
        treeLevel: null,
        childCount: null,
        contentCount: null,
        status: null,
        hitCount: null,
        seoKeywords: null,
        seoDescription: null,
        seoTitle: null,
        publishPipeProps: null,
        configProps: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        siteId: [
          { required: true, message: "站点ID不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "栏目名称不能为空", trigger: "blur" }
        ],
        alias: [
          { required: true, message: "栏目别名不能为空", trigger: "blur" }
        ],
        catalogType: [
          { required: true, message: "栏目类型不能为空", trigger: "change" }
        ],
        path: [
          { required: true, message: "站点路径不能为空", trigger: "blur" }
        ],
        staticFlag: [
          { required: true, message: "是否生成静态页面不能为空", trigger: "blur" }
        ],
        visibleFlag: [
          { required: true, message: "栏目是否可见不能为空", trigger: "blur" }
        ],
        sortFlag: [
          { required: true, message: "排序字段不能为空", trigger: "blur" }
        ],
        treeLevel: [
          { required: true, message: "栏目层级不能为空", trigger: "blur" }
        ],
        childCount: [
          { required: true, message: "子栏目数不能为空", trigger: "blur" }
        ],
        contentCount: [
          { required: true, message: "内容数量不能为空", trigger: "blur" }
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
    /** 查询栏目管理列表 */
    getList() {
      this.loading = true;
      listCatalog(this.queryParams).then(response => {
        this.catalogList = response.rows;
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
        catalogId: null,
        siteId: null,
        parentId: null,
        name: null,
        logo: null,
        alias: null,
        description: null,
        ancestors: null,
        deptCode: null,
        catalogType: null,
        path: null,
        redirectUrl: null,
        staticFlag: null,
        visibleFlag: null,
        sortFlag: null,
        indexTemplate: null,
        indexFileName: null,
        listTemplate: null,
        listNameRule: null,
        detailTemplate: null,
        detailNameRule: null,
        treeLevel: null,
        childCount: null,
        contentCount: null,
        status: null,
        hitCount: null,
        seoKeywords: null,
        seoDescription: null,
        seoTitle: null,
        publishPipeProps: null,
        configProps: null,
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
      this.ids = selection.map(item => item.catalogId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加栏目管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const catalogId = row.catalogId || this.ids
      getCatalog(catalogId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改栏目管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.catalogId != null) {
            updateCatalog(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCatalog(this.form).then(response => {
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
      const catalogIds = row.catalogId || this.ids;
      this.$modal.confirm('是否确认删除栏目管理编号为"' + catalogIds + '"的数据项？').then(function() {
        return delCatalog(catalogIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('cms/catalog/export', {
        ...this.queryParams
      }, `catalog_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
