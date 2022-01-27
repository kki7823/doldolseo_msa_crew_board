package com.doldolseo.doldolseo_msa_crew_board.utils;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadCrewPostFileUtil extends UploadFileUtil {
    private final String ROOT_PATH;
    private final String CREW_POST_IMG_PATH;
    private final String REW_POST_IMG_PATH_TEMP;

    public UploadCrewPostFileUtil(String uploadPath)  {
        super(uploadPath);
        this.ROOT_PATH = uploadPath;
        this.CREW_POST_IMG_PATH = ROOT_PATH + "/crew_post_image/";
        this.REW_POST_IMG_PATH_TEMP = CREW_POST_IMG_PATH + "/temp/";
    }

    public String saveCrewPostImg(String uuid, MultipartFile reviewImgFile) {
        if (reviewImgFile != null) {
            String originalImgFileName = reviewImgFile.getOriginalFilename();
            makeDirIfNoExist(CREW_POST_IMG_PATH + uuid);
            Path savePath = Paths.get(CREW_POST_IMG_PATH + uuid + "/" + originalImgFileName);
            trasferFile(reviewImgFile, savePath);

            return originalImgFileName;
        }
        return null;
    }

}
