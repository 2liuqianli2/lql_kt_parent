package com.cq;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicSessionCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import com.alibaba.fastjson.JSON;

import java.io.File;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;


/**
 * Tool:IntelliJ IDEA
 * Date:2024-06-25-9:31
 * Description:
 *
 * @ Author:两袖青蛇
 */
public class Test {
    public static void main(String[] args) {
        // 1 传入获取到的临时密钥 (tmpSecretId, tmpSecretKey, sessionToken)
        String aa = "";
        String tmpSecretKey = "4yseHVjr6Z3a5Ys47mV9ucPBrdIxePLI";
        String sessionToken = "TOKEN";
        BasicSessionCredentials cred = new BasicSessionCredentials(aa, tmpSecretKey, sessionToken);
// 2 设置 bucket 的地域
// clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分
        Region region = new Region("ap-shanghai"); //COS_REGION 参数：配置成存储桶 bucket 的实际地域，例如 ap-beijing，更多 COS 地域的简称请参见 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(region);
// 3 生成 cos 客户端
        COSClient cosClient = new COSClient(cred, clientConfig);

        // 指定要上传的文件
        File localFile = new File("C:\\Users\\30477\\Pictures\\qq\\1645489219911.jpg");
// 指定文件将要存放的存储桶
        String bucketName = "liuqianli-1327507405";
// 指定文件上传到 COS 上的路径，即对象键。例如对象键为 folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
        String key = "01.jpg";
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        System.out.println(com.alibaba.fastjson.JSON.toJSONString(putObjectResult));
    }
}
