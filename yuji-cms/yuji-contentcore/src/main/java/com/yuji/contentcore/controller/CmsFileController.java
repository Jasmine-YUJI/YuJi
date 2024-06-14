package com.yuji.contentcore.controller;

import com.yuji.common.core.domain.R;
import com.yuji.common.core.domain.TreeNode;
import com.yuji.common.core.exception.CommonErrorCode;
import com.yuji.common.core.utils.Assert;
import com.yuji.common.core.utils.ServletUtils;
import com.yuji.common.core.web.controller.BaseController;
import com.yuji.common.log.annotation.Log;
import com.yuji.common.log.enums.BusinessType;
import com.yuji.common.security.annotation.RequiresPermissions;
import com.yuji.contentcore.config.CMSConfig;
import com.yuji.contentcore.domain.CmsSite;
import com.yuji.contentcore.domain.dto.FileAddDTO;
import com.yuji.contentcore.domain.dto.FileOperateDTO;
import com.yuji.contentcore.service.ICmsFileService;
import com.yuji.contentcore.service.ICmsSiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 文件管理
 * 
 * @author Liguoqiang
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class CmsFileController extends BaseController {

	private final ICmsSiteService cmsSiteService;

	private final ICmsFileService cmsFileService;

	/**
	 * 查询文件列表
	 *
	 * @param filePath 文件目录路径
	 * @param fileName 文件名
	 */
	@RequiresPermissions("cms:file:list")
	@GetMapping("/list")
	public R<?> getFileList(@RequestParam @NotEmpty String filePath,
							@RequestParam(required = false, defaultValue = "") String fileName) {
		CmsSite site = cmsSiteService.getCurrentSite(ServletUtils.getRequest());
		return cmsFileService.getSiteFileList(site, filePath, fileName);
	}

	/**
	 * 获取目录树数据
	 */
	@RequiresPermissions("cms:file:list")
	@GetMapping("/directoryTreeData")
	public R<?> getDirectoryTree() {
		CmsSite site = cmsSiteService.getCurrentSite(ServletUtils.getRequest());
		List<TreeNode<String>> list = cmsFileService.getSiteDirectoryTreeData(site);
		return R.ok(Map.of("tree", list, "resourceRoot", CMSConfig.getResourceRoot()));
	}

	/**
	 * 重命名文件
	 *
	 * @param dto
	 * @return
	 * @throws IOException
	 */
	@RequiresPermissions("cms:file:rename")
	@Log(title = "文件重命名", businessType = BusinessType.UPDATE)
	@PostMapping("/rename")
	public R<?> renameFile(@RequestBody @Validated FileOperateDTO dto) throws IOException {
		CmsSite site = cmsSiteService.getCurrentSite(ServletUtils.getRequest());
		cmsFileService.renameFile(site, dto.getFilePath(), dto.getRename());
		return R.ok();
	}

	/**
	 * 新建文件
	 *
	 * @param dto
	 * @return
	 * @throws IOException
	 */
	@RequiresPermissions("cms:file:add")
	@Log(title = "新建文件", businessType = BusinessType.UPDATE)
	@PostMapping("/add")
	public R<?> addFile(@RequestBody @Validated FileAddDTO dto) throws IOException {
		CmsSite site = cmsSiteService.getCurrentSite(ServletUtils.getRequest());
		cmsFileService.addFile(site, dto);
		return R.ok();
	}

	/**
	 * 上传文件
	 *
	 * @return
	 * @throws IOException
	 */
	@RequiresPermissions("cms:file:upload")
	@Log(title = "上传文件", businessType = BusinessType.UPDATE)
	@PostMapping("/upload")
	public R<?> uploadFile(@RequestParam("dir") @NotEmpty String dir, @RequestParam("file") MultipartFile multipartFile)
			throws IOException {
		Assert.notNull(multipartFile, () -> CommonErrorCode.NOT_EMPTY.exception("file"));

		CmsSite site = cmsSiteService.getCurrentSite(ServletUtils.getRequest());
		cmsFileService.uploadFile(site, dir, multipartFile);
		return R.ok();
	}

	/**
	 * 读取文件内容
	 *
	 * @param dto
	 * @return
	 * @throws IOException
	 */
	@RequiresPermissions("cms:file:read")
	@Log(title = "读取文件", businessType = BusinessType.OTHER)
	@PostMapping("/read")
	public R<?> readFile(@RequestBody @Validated FileOperateDTO dto) throws IOException {
		CmsSite site = cmsSiteService.getCurrentSite(ServletUtils.getRequest());
		return R.ok(cmsFileService.readFile(site, dto.getFilePath()));
	}

	/**
	 * 修改文件内容
	 *
	 * @param dto
	 * @return
	 * @throws IOException
	 */
	@RequiresPermissions("cms:file:edit")
	@Log(title = "修改文件", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	public R<?> editFile(@RequestBody @Validated FileOperateDTO dto) throws IOException {
		CmsSite site = cmsSiteService.getCurrentSite(ServletUtils.getRequest());
		cmsFileService.editFile(site, dto.getFilePath(), dto.getFileContent());
		return R.ok();
	}

	/**
	 * 删除文件
	 *
	 * @param dtoList
	 * @return
	 * @throws IOException
	 */
	@RequiresPermissions("cms:file:remove")
	@Log(title = "删除文件", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
	public R<?> deleteFile(@RequestBody @NotEmpty List<FileOperateDTO> dtoList) throws IOException {
		CmsSite site = cmsSiteService.getCurrentSite(ServletUtils.getRequest());
		String[] filePathArr = dtoList.stream().map(FileOperateDTO::getFilePath).toArray(String[]::new);
		cmsFileService.deleteFiles(site, filePathArr);
		return R.ok();
	}
}
