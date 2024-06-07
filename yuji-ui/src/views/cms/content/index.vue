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
      <el-form-item label="所属顶级栏目ID" prop="topCatalog">
        <el-input
          v-model="queryParams.topCatalog"
          placeholder="请输入所属顶级栏目ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="部门ID" prop="deptId">
        <el-input
          v-model="queryParams.deptId"
          placeholder="请输入部门ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="部门编码" prop="deptCode">
        <el-input
          v-model="queryParams.deptCode"
          placeholder="请输入部门编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="副标题" prop="subTitle">
        <el-input
          v-model="queryParams.subTitle"
          placeholder="请输入副标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="短标题" prop="shortTitle">
        <el-input
          v-model="queryParams.shortTitle"
          placeholder="请输入短标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标题样式" prop="titleStyle">
        <el-input
          v-model="queryParams.titleStyle"
          placeholder="请输入标题样式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="引导图" prop="logo">
        <el-input
          v-model="queryParams.logo"
          placeholder="请输入引导图"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="来源" prop="source">
        <el-input
          v-model="queryParams.source"
          placeholder="请输入来源"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="来源URL" prop="sourceUrl">
        <el-input
          v-model="queryParams.sourceUrl"
          placeholder="请输入来源URL"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否原创" prop="original">
        <el-input
          v-model="queryParams.original"
          placeholder="请输入是否原创"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作者" prop="author">
        <el-input
          v-model="queryParams.author"
          placeholder="请输入作者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="编辑" prop="editor">
        <el-input
          v-model="queryParams.editor"
          placeholder="请输入编辑"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="自定义内容静态化文件路径" prop="staticPath">
        <el-input
          v-model="queryParams.staticPath"
          placeholder="请输入自定义内容静态化文件路径"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="内容属性" prop="attributes">
        <el-input
          v-model="queryParams.attributes"
          placeholder="请输入内容属性"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="置顶标识" prop="topFlag">
        <el-input
          v-model="queryParams.topFlag"
          placeholder="请输入置顶标识"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="置顶时间" prop="topDate">
        <el-date-picker clearable
          v-model="queryParams.topDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择置顶时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="排序字段" prop="sortFlag">
        <el-input
          v-model="queryParams.sortFlag"
          placeholder="请输入排序字段"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关键词" prop="keywords">
        <el-input
          v-model="queryParams.keywords"
          placeholder="请输入关键词"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="TAGs" prop="tags">
        <el-input
          v-model="queryParams.tags"
          placeholder="请输入TAGs"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="复制源ID" prop="copyId">
        <el-input
          v-model="queryParams.copyId"
          placeholder="请输入复制源ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发布时间" prop="publishDate">
        <el-date-picker clearable
          v-model="queryParams.publishDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择发布时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="下线时间" prop="offlineDate">
        <el-date-picker clearable
          v-model="queryParams.offlineDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择下线时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="发布通道" prop="publishPipe">
        <el-input
          v-model="queryParams.publishPipe"
          placeholder="请输入发布通道"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否链接内容" prop="linkFlag">
        <el-input
          v-model="queryParams.linkFlag"
          placeholder="请输入是否链接内容"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="跳转链接" prop="redirectUrl">
        <el-input
          v-model="queryParams.redirectUrl"
          placeholder="请输入跳转链接"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否已锁定" prop="isLock">
        <el-input
          v-model="queryParams.isLock"
          placeholder="请输入是否已锁定"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="锁定用户名" prop="lockUser">
        <el-input
          v-model="queryParams.lockUser"
          placeholder="请输入锁定用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="deleted">
        <el-input
          v-model="queryParams.deleted"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="likeCount">
        <el-input
          v-model="queryParams.likeCount"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="commentCount">
        <el-input
          v-model="queryParams.commentCount"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="favoriteCount">
        <el-input
          v-model="queryParams.favoriteCount"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="viewCount">
        <el-input
          v-model="queryParams.viewCount"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="contributorId">
        <el-input
          v-model="queryParams.contributorId"
          placeholder="请输入${comment}"
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
          v-hasPermi="['cms:content:add']"
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
          v-hasPermi="['cms:content:edit']"
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
          v-hasPermi="['cms:content:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['cms:content:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="contentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="contentId" />
      <el-table-column label="所属站点ID" align="center" prop="siteId" />
      <el-table-column label="所属栏目ID" align="center" prop="catalogId" />
      <el-table-column label="所属栏目祖级IDs" align="center" prop="catalogAncestors" />
      <el-table-column label="所属顶级栏目ID" align="center" prop="topCatalog" />
      <el-table-column label="部门ID" align="center" prop="deptId" />
      <el-table-column label="部门编码" align="center" prop="deptCode" />
      <el-table-column label="内容类型" align="center" prop="contentType" />
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="副标题" align="center" prop="subTitle" />
      <el-table-column label="短标题" align="center" prop="shortTitle" />
      <el-table-column label="标题样式" align="center" prop="titleStyle" />
      <el-table-column label="引导图" align="center" prop="logo" />
      <el-table-column label="来源" align="center" prop="source" />
      <el-table-column label="来源URL" align="center" prop="sourceUrl" />
      <el-table-column label="是否原创" align="center" prop="original" />
      <el-table-column label="作者" align="center" prop="author" />
      <el-table-column label="编辑" align="center" prop="editor" />
      <el-table-column label="摘要" align="center" prop="summary" />
      <el-table-column label="自定义内容静态化文件路径" align="center" prop="staticPath" />
      <el-table-column label="内容状态" align="center" prop="status" />
      <el-table-column label="内容属性" align="center" prop="attributes" />
      <el-table-column label="置顶标识" align="center" prop="topFlag" />
      <el-table-column label="置顶时间" align="center" prop="topDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.topDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="排序字段" align="center" prop="sortFlag" />
      <el-table-column label="关键词" align="center" prop="keywords" />
      <el-table-column label="TAGs" align="center" prop="tags" />
      <el-table-column label="复制类型" align="center" prop="copyType" />
      <el-table-column label="复制源ID" align="center" prop="copyId" />
      <el-table-column label="发布时间" align="center" prop="publishDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.publishDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="下线时间" align="center" prop="offlineDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.offlineDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发布通道" align="center" prop="publishPipe" />
      <el-table-column label="发布通道属性，配置独立模板用" align="center" prop="publishPipeProps" />
      <el-table-column label="是否链接内容" align="center" prop="linkFlag" />
      <el-table-column label="跳转链接" align="center" prop="redirectUrl" />
      <el-table-column label="是否已锁定" align="center" prop="isLock" />
      <el-table-column label="锁定用户名" align="center" prop="lockUser" />
      <el-table-column label="扩展属性" align="center" prop="configProps" />
      <el-table-column label="${comment}" align="center" prop="deleted" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="${comment}" align="center" prop="likeCount" />
      <el-table-column label="${comment}" align="center" prop="commentCount" />
      <el-table-column label="${comment}" align="center" prop="favoriteCount" />
      <el-table-column label="${comment}" align="center" prop="viewCount" />
      <el-table-column label="${comment}" align="center" prop="contributorId" />
      <el-table-column label="SEO标题" align="center" prop="seoTitle" />
      <el-table-column label="SEO关键词" align="center" prop="seoKeywords" />
      <el-table-column label="SEO描述" align="center" prop="seoDescription" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['cms:content:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['cms:content:remove']"
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

    <!-- 添加或修改内容管理对话框 -->
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
        <el-form-item label="所属顶级栏目ID" prop="topCatalog">
          <el-input v-model="form.topCatalog" placeholder="请输入所属顶级栏目ID" />
        </el-form-item>
        <el-form-item label="部门ID" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入部门ID" />
        </el-form-item>
        <el-form-item label="部门编码" prop="deptCode">
          <el-input v-model="form.deptCode" placeholder="请输入部门编码" />
        </el-form-item>
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="副标题" prop="subTitle">
          <el-input v-model="form.subTitle" placeholder="请输入副标题" />
        </el-form-item>
        <el-form-item label="短标题" prop="shortTitle">
          <el-input v-model="form.shortTitle" placeholder="请输入短标题" />
        </el-form-item>
        <el-form-item label="标题样式" prop="titleStyle">
          <el-input v-model="form.titleStyle" placeholder="请输入标题样式" />
        </el-form-item>
        <el-form-item label="引导图" prop="logo">
          <el-input v-model="form.logo" placeholder="请输入引导图" />
        </el-form-item>
        <el-form-item label="来源" prop="source">
          <el-input v-model="form.source" placeholder="请输入来源" />
        </el-form-item>
        <el-form-item label="来源URL" prop="sourceUrl">
          <el-input v-model="form.sourceUrl" placeholder="请输入来源URL" />
        </el-form-item>
        <el-form-item label="是否原创" prop="original">
          <el-input v-model="form.original" placeholder="请输入是否原创" />
        </el-form-item>
        <el-form-item label="作者" prop="author">
          <el-input v-model="form.author" placeholder="请输入作者" />
        </el-form-item>
        <el-form-item label="编辑" prop="editor">
          <el-input v-model="form.editor" placeholder="请输入编辑" />
        </el-form-item>
        <el-form-item label="摘要" prop="summary">
          <el-input v-model="form.summary" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="自定义内容静态化文件路径" prop="staticPath">
          <el-input v-model="form.staticPath" placeholder="请输入自定义内容静态化文件路径" />
        </el-form-item>
        <el-form-item label="内容属性" prop="attributes">
          <el-input v-model="form.attributes" placeholder="请输入内容属性" />
        </el-form-item>
        <el-form-item label="置顶标识" prop="topFlag">
          <el-input v-model="form.topFlag" placeholder="请输入置顶标识" />
        </el-form-item>
        <el-form-item label="置顶时间" prop="topDate">
          <el-date-picker clearable
            v-model="form.topDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择置顶时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="排序字段" prop="sortFlag">
          <el-input v-model="form.sortFlag" placeholder="请输入排序字段" />
        </el-form-item>
        <el-form-item label="关键词" prop="keywords">
          <el-input v-model="form.keywords" placeholder="请输入关键词" />
        </el-form-item>
        <el-form-item label="TAGs" prop="tags">
          <el-input v-model="form.tags" placeholder="请输入TAGs" />
        </el-form-item>
        <el-form-item label="复制源ID" prop="copyId">
          <el-input v-model="form.copyId" placeholder="请输入复制源ID" />
        </el-form-item>
        <el-form-item label="发布时间" prop="publishDate">
          <el-date-picker clearable
            v-model="form.publishDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择发布时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="下线时间" prop="offlineDate">
          <el-date-picker clearable
            v-model="form.offlineDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择下线时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="发布通道" prop="publishPipe">
          <el-input v-model="form.publishPipe" placeholder="请输入发布通道" />
        </el-form-item>
        <el-form-item label="发布通道属性，配置独立模板用" prop="publishPipeProps">
          <el-input v-model="form.publishPipeProps" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="是否链接内容" prop="linkFlag">
          <el-input v-model="form.linkFlag" placeholder="请输入是否链接内容" />
        </el-form-item>
        <el-form-item label="跳转链接" prop="redirectUrl">
          <el-input v-model="form.redirectUrl" placeholder="请输入跳转链接" />
        </el-form-item>
        <el-form-item label="是否已锁定" prop="isLock">
          <el-input v-model="form.isLock" placeholder="请输入是否已锁定" />
        </el-form-item>
        <el-form-item label="锁定用户名" prop="lockUser">
          <el-input v-model="form.lockUser" placeholder="请输入锁定用户名" />
        </el-form-item>
        <el-form-item label="扩展属性" prop="configProps">
          <el-input v-model="form.configProps" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="${comment}" prop="deleted">
          <el-input v-model="form.deleted" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="${comment}" prop="likeCount">
          <el-input v-model="form.likeCount" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="commentCount">
          <el-input v-model="form.commentCount" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="favoriteCount">
          <el-input v-model="form.favoriteCount" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="viewCount">
          <el-input v-model="form.viewCount" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="contributorId">
          <el-input v-model="form.contributorId" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="SEO标题" prop="seoTitle">
          <el-input v-model="form.seoTitle" placeholder="请输入SEO标题" />
        </el-form-item>
        <el-form-item label="SEO关键词" prop="seoKeywords">
          <el-input v-model="form.seoKeywords" placeholder="请输入SEO关键词" />
        </el-form-item>
        <el-form-item label="SEO描述" prop="seoDescription">
          <el-input v-model="form.seoDescription" placeholder="请输入SEO描述" />
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
import { listContent, getContent, delContent, addContent, updateContent } from "@/api/cms/content";

export default {
  name: "Content",
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
      // 内容管理表格数据
      contentList: [],
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
        topCatalog: null,
        deptId: null,
        deptCode: null,
        contentType: null,
        title: null,
        subTitle: null,
        shortTitle: null,
        titleStyle: null,
        logo: null,
        source: null,
        sourceUrl: null,
        original: null,
        author: null,
        editor: null,
        summary: null,
        staticPath: null,
        status: null,
        attributes: null,
        topFlag: null,
        topDate: null,
        sortFlag: null,
        keywords: null,
        tags: null,
        copyType: null,
        copyId: null,
        publishDate: null,
        offlineDate: null,
        publishPipe: null,
        publishPipeProps: null,
        linkFlag: null,
        redirectUrl: null,
        isLock: null,
        lockUser: null,
        configProps: null,
        deleted: null,
        likeCount: null,
        commentCount: null,
        favoriteCount: null,
        viewCount: null,
        contributorId: null,
        seoTitle: null,
        seoKeywords: null,
        seoDescription: null
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
        catalogAncestors: [
          { required: true, message: "所属栏目祖级IDs不能为空", trigger: "blur" }
        ],
        topCatalog: [
          { required: true, message: "所属顶级栏目ID不能为空", trigger: "blur" }
        ],
        contentType: [
          { required: true, message: "内容类型不能为空", trigger: "change" }
        ],
        title: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
        original: [
          { required: true, message: "是否原创不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "内容状态不能为空", trigger: "change" }
        ],
        topFlag: [
          { required: true, message: "置顶标识不能为空", trigger: "blur" }
        ],
        sortFlag: [
          { required: true, message: "排序字段不能为空", trigger: "blur" }
        ],
        copyType: [
          { required: true, message: "复制类型不能为空", trigger: "change" }
        ],
        linkFlag: [
          { required: true, message: "是否链接内容不能为空", trigger: "blur" }
        ],
        isLock: [
          { required: true, message: "是否已锁定不能为空", trigger: "blur" }
        ],
        deleted: [
          { required: true, message: "$comment不能为空", trigger: "blur" }
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
    /** 查询内容管理列表 */
    getList() {
      this.loading = true;
      listContent(this.queryParams).then(response => {
        this.contentList = response.rows;
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
        contentId: null,
        siteId: null,
        catalogId: null,
        catalogAncestors: null,
        topCatalog: null,
        deptId: null,
        deptCode: null,
        contentType: null,
        title: null,
        subTitle: null,
        shortTitle: null,
        titleStyle: null,
        logo: null,
        source: null,
        sourceUrl: null,
        original: null,
        author: null,
        editor: null,
        summary: null,
        staticPath: null,
        status: null,
        attributes: null,
        topFlag: null,
        topDate: null,
        sortFlag: null,
        keywords: null,
        tags: null,
        copyType: null,
        copyId: null,
        publishDate: null,
        offlineDate: null,
        publishPipe: null,
        publishPipeProps: null,
        linkFlag: null,
        redirectUrl: null,
        isLock: null,
        lockUser: null,
        configProps: null,
        deleted: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null,
        likeCount: null,
        commentCount: null,
        favoriteCount: null,
        viewCount: null,
        contributorId: null,
        seoTitle: null,
        seoKeywords: null,
        seoDescription: null
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
      this.ids = selection.map(item => item.contentId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加内容管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const contentId = row.contentId || this.ids
      getContent(contentId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改内容管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.contentId != null) {
            updateContent(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addContent(this.form).then(response => {
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
      const contentIds = row.contentId || this.ids;
      this.$modal.confirm('是否确认删除内容管理编号为"' + contentIds + '"的数据项？').then(function() {
        return delContent(contentIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('cms/content/export', {
        ...this.queryParams
      }, `content_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
