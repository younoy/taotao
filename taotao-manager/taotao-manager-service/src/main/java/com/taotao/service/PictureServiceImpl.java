package com.taotao.service;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午2:39 on 17-11-26.
 * @Modified By:
 */

@Service
public class PictureServiceImpl implements PictureService{

    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USER}")
    private String FTP_USER;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_BASEPATH}")
    private String FTP_BASEPATH;
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;

    @Override
    public Map uploadPicture(MultipartFile uploadFile){

        Map resultMap = new HashMap();

        try {

            //生成文件名
            String oldName = uploadFile.getOriginalFilename();
            String newName = IDUtils.genImageName();
            newName = newName + oldName.substring(oldName.lastIndexOf("."));

            String imagePath = new DateTime().toString("/yyyy/MM/dd");
            //图片上传
            Boolean result = FtpUtil.uploadFile(FTP_ADDRESS,FTP_PORT,FTP_USER,FTP_PASSWORD,FTP_BASEPATH,
                    imagePath,newName,uploadFile.getInputStream());


            if(!result){
                resultMap.put("error",1);
                resultMap.put("message","文件上传失败");
                return resultMap;
            }

            resultMap.put("error",0);
            resultMap.put("url",IMAGE_BASE_URL+imagePath+"/"+newName);
            return resultMap;

        } catch (IOException e) {
            e.printStackTrace();
            resultMap.put("error",1);
            resultMap.put("message","文件上传失败");
            return resultMap;
        }

    }
}
