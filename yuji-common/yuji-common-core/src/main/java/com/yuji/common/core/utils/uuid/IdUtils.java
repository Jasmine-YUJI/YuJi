package com.yuji.common.core.utils.uuid;


import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;

import java.util.Iterator;
import java.util.List;

/**
 * ID生成器工具类
 * 
 * @author Liguoqiang
 */
public class IdUtils
{

    public static long getSnowflakeId() {
        return YitIdHelper.nextId();
    }

    public static String getSnowflakeIdStr() {
        return String.valueOf(getSnowflakeId());
    }
    /**
     * 获取随机UUID
     * 
     * @return 随机UUID
     */
    public static String randomUUID()
    {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     * 
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID()
    {
        return UUID.randomUUID().toString(true);
    }

    /**
     * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
     * 
     * @return 随机UUID
     */
    public static String fastUUID()
    {
        return UUID.fastUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线，使用性能更好的ThreadLocalRandom生成UUID
     * 
     * @return 简化的UUID，去掉了横线
     */
    public static String fastSimpleUUID()
    {
        return UUID.fastUUID().toString(true);
    }

    /**
     * 校验列表中的ID是否正确
     *
     * @param ids
     * @param removeInvalidId
     *            是否移除错误ID
     * @return
     */
    public static boolean validate(List<Long> ids, boolean removeInvalidId) {
        if (ids == null || ids.size() == 0) {
            return false;
        }
        for (Iterator<Long> iterator = ids.iterator(); iterator.hasNext();) {
            Long id = iterator.next();
            if (id == null || id <= 0) {
                if (removeInvalidId) {
                    iterator.remove();
                } else {
                    return false;
                }
            }
        }
        return ids.size() > 0;
    }

    public static boolean validate(List<Long> ids) {
        return validate(ids, false);
    }

    public static boolean validate(Long id) {
        return id != null && id > 0;
    }

    public static void main(String[] args) {
        System.out.println(getSnowflakeId());
    }
}
