package com.yuji.common.file.local;

import com.yuji.common.core.enums.FileType;
import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.core.utils.file.FileTypeUtils;
import com.yuji.common.core.utils.i18n.I18nUtils;
import com.yuji.common.file.*;
import com.yuji.common.file.exception.FileStorageException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.*;

@Component(IFileStorageType.BEAN_NAME_PREIFX + LocalFileStorageType.TYPE)
public class LocalFileStorageType implements IFileStorageType {

	public final static String TYPE = "Local";

	@Override
	public String getType() {
		return TYPE;
	}
	
	@Override
	public String getName() {
		return I18nUtils.get("{STORAGE.TYPE." + TYPE + "}");
	}

	@Override
	public boolean exists(StorageExistArgs args) {
		String bucket = args.getBucket() == null ? StringUtils.EMPTY : args.getBucket();
		if (!bucket.endsWith("/")) {
			bucket += "/";
		}
		String filePath = bucket + args.getPath();
		File file = new File(filePath);
		return file.exists() && file.isFile();
	}

	@Override
	public void copy(StorageCopyArgs args) {
		try {
			String bucket = args.getBucket() == null ? StringUtils.EMPTY : args.getBucket();
			if (!bucket.endsWith("/")) {
				bucket += "/";
			}
			FileUtils.copyDirectory(new File(bucket + args.getSourcePath()), new File(bucket + args.getDestPath()));
		} catch (IOException e) {
			throw new FileStorageException(e);
		}
	}

	@Override
	public void move(StorageMoveArgs args) {
		try {
			String bucket = args.getBucket() == null ? StringUtils.EMPTY : args.getBucket();
			if (!bucket.endsWith("/")) {
				bucket += "/";
			}
			FileUtils.moveDirectory(new File(bucket + args.getSourcePath()), new File(bucket + args.getDestPath()));
		} catch (IOException e) {
			throw new FileStorageException(e);
		}
	}

	@Override
	public InputStream read(StorageReadArgs args) {
		try {
			String bucket = args.getBucket() == null ? StringUtils.EMPTY : args.getBucket();
			if (!bucket.endsWith("/")) {
				bucket += "/";
			}
			String filePath = bucket + args.getPath();
			File file = new File(filePath);
			if (!file.exists()) {
				throw new FileNotFoundException();
			}
			return new FileInputStream(new File(filePath));
		} catch (IOException e) {
			throw new FileStorageException(e);
		}
	}

	@Override
	public void write(StorageWriteArgs args) {
		try {
			String bucket = args.getBucket() == null ? StringUtils.EMPTY : args.getBucket();
			if (!bucket.endsWith("/")) {
				bucket += "/";
			}
			String filePath = bucket + args.getPath();
			File file = new File(filePath);
			FileTypeUtils.mkdirs(file.getParentFile().getAbsolutePath());
			FileUtils.writeByteArrayToFile(file, IOUtils.toByteArray(args.getInputStream()));
		} catch (Exception e) {
			throw new FileStorageException(e);
		}
	}

	@Override
	public void remove(StorageRemoveArgs args) {
		try {
			String bucket = args.getBucket() == null ? StringUtils.EMPTY : args.getBucket();
			if (!bucket.endsWith("/")) {
				bucket += "/";
			}
			String filePath = bucket + args.getPath();
			FileUtils.delete(new File(filePath));
		} catch (Exception e) {
			throw new FileStorageException(e);
		}
	}
}
