package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午2:38 on 17-11-26.
 * @Modified By:
 */
public interface PictureService {

    Map uploadPicture(MultipartFile uploadFile);
}
