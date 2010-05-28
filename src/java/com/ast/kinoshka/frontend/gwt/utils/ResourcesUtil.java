package com.ast.kinoshka.frontend.gwt.utils;

import com.ast.kinoshka.frontend.gwt.model.Category;

/**
 * Utility class that provides methods to deal with UI resources.
 * @author Aleh_Stsiapanau
 */
public class ResourcesUtil {

  private final static String COUNTRIES_PATH = "images/countries/";
  private final static String FILM_ICON_PATH = "images/covers/";
  private final static String FILM_TMP_PATH = "images/tmp/";

  private ResourcesUtil() {/*prevent instantiation*/}

  public static String filmName(String name, String originalName) {
    String result = name;
    if (originalName != null) {
      result += " (" + originalName + ")";
    }
    return result;
  }

  /**
   * Returns local path for country image.
   * @param imageName image file name
   * @return local path for image
   */
  public static String countryImg(String imageName) {
    String result;
    if (imageName == null || imageName.length() == 0) {
      result = "no-image.gif";
    } else {
      result = imageName + ".gif";
    }
    return COUNTRIES_PATH + result;
  }

  /**
   * Returns local path for film cover image.
   * @param imageName image file name
   * @return local path for image
   */
  public static String filmImg(String imageName) {
    String result;
    if (imageName == null || imageName.length() == 0) {
      result = "no-image.gif";
    } else {
      result = imageName;
    }
    return FILM_ICON_PATH + result;
  }

  /**
   * Returns local path for film cover image.
   * @param imageName image file name
   * @return local path for image
   */
  public static String tmpImg(final String imageName) {
    String result;
    if (imageName == null || imageName.length() == 0) {
      result = FILM_ICON_PATH + "no-image.gif";
    } else {
      result = FILM_TMP_PATH + imageName;
    }
    return result;
  }

  /**
   * Generates history token value for category item.
   * @param category category
   * @param categoryItem category item id
   * @return hyper link HREF value 
   */
  public static String target(final Category category, final String categoryItem) {
    return target(category, categoryItem, categoryItem); 
  }

  /**
   * Generates history token value for category item.
   * @param category category
   * @param categoryItem category item id
   * @param categoryItemName category item name
   * @return hyper link HREF value 
   */
  public static String target(final Category category, final String categoryItem,
      final String categoryItemName) {
    return category.name() + "?" + categoryItem + "&" + categoryItemName;
  }

  /**
   * Returns content title for the specified parameters.
   * @param category category
   * @param categoryItemName category item name
   * @return content title
   */
  public static String contentTitle(final Category category, final String categoryItemName) {
    return categoryName(category) + ": " + categoryItemName;
  }

  /**
   * Returns localized category name caption for the given category.
   * @param category category to get caption for
   * @return caption for the given category
   */
  public static String categoryName(Category category) {
    String result;
    switch (category) {
    case ALL:
      result = Messages.get().titleAll();
      break;
    case ACTOR:
      result = Messages.get().titleActor();
      break;
    case DIRECTOR:
      result = Messages.get().titleDirector();
      break;
    case COUNTRY:
      result = Messages.get().titleCountry();
      break;
    case BOX:
      result = Messages.get().titleBox();
      break;
    case DISK:
      result = Messages.get().titleDisk();
      break;
    case GENRE:
      result = Messages.get().titleGenre();
      break;
    case YEAR:
      result = Messages.get().titleYear();
      break;
    default:
      result = "";
      break;
    }
    return result;
  }
}
