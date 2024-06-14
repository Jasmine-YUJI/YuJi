package com.yuji.file.service.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuji.common.core.domain.StroageArgs;
import com.yuji.common.core.enums.FileType;
import com.yuji.common.core.exception.CommonErrorCode;
import com.yuji.common.core.utils.StringUtils;
import com.yuji.common.core.utils.file.FileTypeUtils;
import com.yuji.common.core.utils.i18n.I18nUtils;
import com.yuji.file.exception.FileStorageException;
import com.yuji.file.service.ISysFileService;
import com.yuji.file.utils.OSSClient;
import io.minio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.nacos.common.utils.IoUtils;
import com.yuji.file.config.MinioConfig;
import com.yuji.file.utils.FileUploadUtils;

/**
 * Minio 文件存储
 *
 * @author Liguoqiang
 */
@Component(ISysFileService.BEAN_NAME_PREIFX + MinioSysFileServiceImpl.TYPE)
public class MinioSysFileServiceImpl implements ISysFileService
{
    public final static String TYPE = "MinIO";

    private Map<String, OSSClient<MinioClient>> clients = new HashMap<>();

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String getName() {
        return I18nUtils.get("{STORAGE.TYPE." + TYPE + "}");
    }

    private OSSClient<MinioClient> getClient(String endpoint, String accessKey, String accessSecret) {
        OSSClient<MinioClient> client = this.clients.get(endpoint);
        if (client == null) {
            client = new OSSClient<>();
            client.setClient(MinioClient.builder().endpoint(endpoint).credentials(accessKey, accessSecret).build());
            this.clients.put(endpoint, client);
        }
        client.setLastActiveTime(System.currentTimeMillis());
        return client;
    }

    @Override
    public boolean testConnection(String endpoint, String accessKey, String accessSecret) {
        OSSClient<MinioClient> client = this.getClient(endpoint, accessKey, accessSecret);
        if (client == null) {
            throw new FileStorageException("OSSClient cannot be empty: " + TYPE);
        }
        try {
            client.getClient().listBuckets();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建存储桶
     *
     * @param minioClientKey
     * @param bucketName
     */
    @Override
    public void createBucket(StroageArgs args) {
        if (StringUtils.isEmpty(args.getBucket())) {
            throw CommonErrorCode.NOT_EMPTY.exception("bucket");
        }
        OSSClient<MinioClient> client = this.getClient(args.getEndpoint(), args.getAccessKey(), args.getAccessSecret());
        this.createBucket0(client, args.getBucket());
    }

    private void createBucket0(OSSClient<MinioClient> client, String bucketName) {
        try {
            BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder().bucket(bucketName).build();
            if (client.getClient().bucketExists(bucketExistsArgs)) {
                return;
            }
            MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder().bucket(bucketName).build();
            client.getClient().makeBucket(makeBucketArgs);
        } catch (Exception e) {
            throw new FileStorageException(e);
        }
    }

    @Override
    public void reloadClient(String endpoint, String accessKey, String accessSecret) {
        this.clients.remove(endpoint);
        this.getClient(endpoint, accessKey, accessSecret);
    }

    @Override
    public InputStream read(StroageArgs args) {
        try {
            OSSClient<MinioClient> client = this.getClient(args.getEndpoint(), args.getAccessKey(),
                    args.getAccessSecret());
            GetObjectArgs getObjectArgs = GetObjectArgs.builder().bucket(args.getBucket()).object(args.getPath())
                    .build();
            return client.getClient().getObject(getObjectArgs);
        } catch (Exception e) {
            throw new FileStorageException(e);
        }
    }

    @Override
    public void write(StroageArgs args) {
        try {
            if (args.getInputStream() == null) {
                throw CommonErrorCode.NOT_EMPTY.exception("inputStream");
            }
            String mimetype = FileTypeUtils.getFileType(args.getPath());
            OSSClient<MinioClient> client = this.getClient(args.getEndpoint(), args.getAccessKey(),
                    args.getAccessSecret());
            PutObjectArgs putObjectArgs = PutObjectArgs.builder().bucket(args.getBucket()).object(args.getPath())
                    .contentType(mimetype).stream(args.getInputStream(), args.getInputStream().available(), -1).build();
            client.getClient().putObject(putObjectArgs);
        } catch (Exception e) {
            throw new FileStorageException(e);
        }
    }

    @Override
    public void remove(StroageArgs args) {
        try {
            OSSClient<MinioClient> client = this.getClient(args.getEndpoint(), args.getAccessKey(),
                    args.getAccessSecret());
            RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder().bucket(args.getBucket())
                    .object(args.getPath()).build();
            client.getClient().removeObject(removeObjectArgs);
        } catch (Exception e) {
            throw new FileStorageException(e);
        }
    }

    @Override
    public void copy(StroageArgs args) {
        OSSClient<MinioClient> client = this.getClient(args.getEndpoint(), args.getAccessKey(), args.getAccessSecret());
        ComposeSource composeSource = new ComposeSource(
                CopySource.builder().bucket(args.getBucket()).object(args.getSourcePath()).build());
        ComposeObjectArgs objectArgs = ComposeObjectArgs.builder().bucket(args.getBucket())
                .sources(List.of(composeSource)).object(args.getDestPath()).build();
        CopyObjectArgs copyArgs = new CopyObjectArgs(objectArgs);
        try {
            client.getClient().copyObject(copyArgs);
        } catch (Exception e) {
            throw new FileStorageException(e);
        }
    }

    @Override
    public void move(StroageArgs args) {
        OSSClient<MinioClient> client = this.getClient(args.getEndpoint(), args.getAccessKey(), args.getAccessSecret());
        ComposeSource composeSource = new ComposeSource(
                CopySource.builder().bucket(args.getBucket()).object(args.getSourcePath()).build());
        ComposeObjectArgs objectArgs = ComposeObjectArgs.builder().bucket(args.getBucket())
                .sources(List.of(composeSource)).object(args.getDestPath()).build();
        CopyObjectArgs copyArgs = new CopyObjectArgs(objectArgs);
        try {
            client.getClient().copyObject(copyArgs);
        } catch (Exception e) {
            throw new FileStorageException(e);
        }
        // 复制后删除源
        RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder().bucket(args.getBucket())
                .object(args.getSourcePath()).build();
        try {
            client.getClient().removeObject(removeObjectArgs);
        } catch (Exception e) {
            throw new FileStorageException(e);
        }
    }

    @Autowired
    private MinioConfig minioConfig;

    @Autowired
    private MinioClient client;

    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        String fileName = FileUploadUtils.extractFilename(file);
        InputStream inputStream = file.getInputStream();
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(minioConfig.getBucketName())
                .object(fileName)
                .stream(inputStream, file.getSize(), -1)
                .contentType(file.getContentType())
                .build();
        client.putObject(args);
        IoUtils.closeQuietly(inputStream);
        return minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/" + fileName;
    }

}
