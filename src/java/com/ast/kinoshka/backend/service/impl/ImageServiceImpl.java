package com.ast.kinoshka.backend.service.impl;

import com.ast.kinoshka.backend.common.ServiceException;
import com.ast.kinoshka.backend.service.ImageService;
import com.ast.kinoshka.backend.service.util.FileUtil;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * ImageService default implementation.
 * @author Aleh_Stsiapanau
 */
public class ImageServiceImpl implements ImageService {

  public static final String TMP_FILE_NAME = "tmp-image";

  @Inject
  @Named(value="images.tmp")
  private String tmpDir;

  @Inject
  @Named(value="images.covers")
  private String imgDir;

  /**
   * {@inheritDoc}
   */
  @Override
  public String acceptTmpImage() {
    File tmpfile = FileUtil.getFirstFile(tmpDir);
    String fileName = null;

    if (tmpfile != null) {
      try {
        String tmpFileName = tmpfile.getName();
        FileUtil.moveFileToDir(tmpfile, imgDir);
        fileName = tmpFileName;
      } catch (IOException e) {
        throw new ServiceException("Cannot copy tmp image file to target dir.", e);
      }
    }
    return fileName;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteImage(String imageName) {
    File file = FileUtil.getFile(imgDir, imageName);
    if (file != null && file.exists()) {
      file.delete();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String saveTmpImage(byte[] image, String imageName) {

    FileUtil.cleanDirectory(tmpDir);
    String tmpImageName = FileUtil.getUniqueFileName(FileUtil.getExtension(imageName));

    try {
      FileUtil.writeToFile(image, FileUtil.getFile(tmpDir, tmpImageName));
    } catch (FileNotFoundException e) {
      throw new ServiceException("Cannot create temporary image file.", e);
    } catch (IOException e) {
      throw new ServiceException("Cannot write to tmp image file.", e);
    }

    return tmpImageName;
  }

}
