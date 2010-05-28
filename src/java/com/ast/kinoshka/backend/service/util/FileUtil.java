package com.ast.kinoshka.backend.service.util;

import com.google.common.base.Preconditions;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Utility class that provides file related operations
 * @author Aleh_Stsiapanau
 * 
 */
public class FileUtil {

  private FileUtil() {/* prevent instantiation. */}

  /**
   * Generates unique file name with specified extension.
   * @param ext file name extension
   * @return unique file name
   */
  public static final String getUniqueFileName(String ext) {
    return UUID.randomUUID().toString() + "." + ext;
  }

  /**
   * Returns file extension.
   * @param fileFullName file name
   * @return file extension
   */
  public static final String getExtension(String fileFullName) {
    return FilenameUtils.getExtension(fileFullName);
  }

  /**
   * Gets file from the specified directory.
   * @param dirName
   * @param fileName
   * @return
   */
  public static final File getFile(String dirName, String fileName) {
    Preconditions.checkNotNull(dirName);
    Preconditions.checkNotNull(fileName);
    File targetDir = new File(dirName);
    if (!targetDir.exists()) {
      targetDir.mkdir();
    }
    return new File(targetDir, fileName);
  }

  /**
   * Returns file from directory.
   * @param dirName directory to get file from
   * @return first got file in directory
   */
  public static final File getFirstFile(String dirName) {
    Preconditions.checkNotNull(dirName);
    File dir = new File(dirName);
    File result = null;

    if (dir.exists()) {
      File[] files = dir.listFiles();
      if (files.length > 0) {
        result = files[0];
      }
    }
    return result;
  }
  
  /**
   * Moves file with specified name to specified directory.
   * @param toCopy file to copy
   * @param targetDirName target directory name
   */
  public static final void moveFileToDir(File toCopy, String targetDirName) throws IOException {
    FileUtils.moveFileToDirectory(toCopy, new File(targetDirName), true);
  }

  /**
   * Cleans a directory without deleting it.
   * @param dirName dir to clean
   */
  public static final void cleanDirectory(String dirName) {
    Preconditions.checkNotNull(dirName);
    File dir = new File(dirName);
    if (dir.exists()) {
      File[] files = dir.listFiles();
      for (File file : files) {
        file.delete();
      }
    }
  }

  /**
   * Writes byte array to file.
   * @param data byte array to write
   * @param out file to write to
   * @throws FileNotFoundException if bad thing happens
   * @throws IOException if worse thing happens
   */
  public static final void writeToFile(byte[] data, File out) throws FileNotFoundException, IOException {
    Preconditions.checkNotNull(out);
    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream(out);
      fos.write(data);
    } finally {
      fos.close();
    }
  }

  /**
   * Returns file with specified name prefix from directory.
   * @param dirName directory to get file from
   * @param fileNamePrefix file name prefix
   * @return first got file with specified prefix in directory
   */
  public static final File getFileByPattern(String dirName, String fileNamePrefix) {
    Preconditions.checkNotNull(dirName);
    Preconditions.checkNotNull(fileNamePrefix);
    File dir = new File(dirName);
    File result = null;

    if (dir.exists()) {
      File[] files = dir.listFiles();
      for (File file : files) {
        if (file.getName().contains(fileNamePrefix)) {
          result = file;
          break;
        }
      }
    }
    return result;
  }

}
