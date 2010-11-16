package com.ast.kinoshka.backend.service;

import com.ast.kinoshka.testcommon.BaseServiceTest;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import junit.framework.Assert;

/**
 * Unit test for image service.
 * @author Aleh_Stsiapanau
 */
public class ImageServiceTest extends BaseServiceTest {

  private static final String TMP_IMAGE_NAME = "tmp-image.txt";
  private static final String TMP_IMAGE_DIR = "test/web/images/tmp/";
  private static final String COVER_IMAGE_DIR = "test/web/images/covers/";
  private ImageService service;

  @Before
  public void setUp() {
    this.service = injector.getInstance(ImageService.class);
  }

  @Test
  public void testSaveTmpImage() throws Exception {
    String fileName = service.saveTmpImage("HelloWorld".getBytes(), "test/file.txt");

    File file = null;
    try {
      file = new File(TMP_IMAGE_DIR + fileName);
      Assert.assertTrue("File not found", file.exists());

    } finally {
      deleteFile(file);
    }
  }

  @Test
  public void testCopyTmpImage() throws Exception {
    File file = new File(TMP_IMAGE_DIR + TMP_IMAGE_NAME);
    file.createNewFile();
    File targetFile = null;

    try {
      String fileName = service.acceptTmpImage();

      targetFile = new File(COVER_IMAGE_DIR + fileName);
      Assert.assertTrue("File not found", targetFile.exists());

    } finally {
      deleteFile(file);
      deleteFile(targetFile);
    }
  }

  @Test
  public void testDeleteImage() throws Exception {
    File file = new File(COVER_IMAGE_DIR + TMP_IMAGE_NAME);
    file.createNewFile();

    try {
      Assert.assertTrue("File was not created", file.exists());
      service.deleteImage(TMP_IMAGE_NAME);
      Assert.assertFalse("File not found", file.exists());

    } finally {
      deleteFile(file);
    }
  }

  @Test
  public void testDeleteWrongName() throws Exception {
    service.deleteImage("wrong_image_name");
  }

  public static void deleteFile(File file) {
    if (file!= null && file.exists()) {
      file.delete();
    }
  }

}
