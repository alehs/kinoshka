package com.ast.kinoshka.backend.service;

/**
 * Manages film covers.
 * @author Aleh_Stsiapanau
 */
public interface ImageService {

  /**
   * Save film image into temporary directory.
   * @param image image bytes to save
   * @param imageName image name
   * @return saved image name
   */
  String saveTmpImage(byte[] image, String imageName);

  /**
   * Accepts temporary film image and copies it into images directory.
   * @return image name
   */
  String acceptTmpImage();

  /**
   * Deletes film cover image. 
   * @param imageName image to delete
   */
  void deleteImage(String imageName);

}
