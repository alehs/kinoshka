package com.ast.kinoshka.frontend.gwt.utils;

import com.google.gwt.i18n.client.Messages;

public interface MessagesBundle extends Messages {

  /* ************** Failures ****************** */ 
  String failLoadDictionary();
  String failLoadFilms();
  String failGeneral();
  String failSearching();
  String failDeleting(String name);
  String failUploading();

  /* ************** Form Validation Messages ********** */
  String requiredName();
  String requiredGenre();
  String numericField(String field);
  
  /* ************** Service Messages ********** */
  String helloWorld();
  String removed(String name);
  String added(String name);
  String saved(String name);

  /* *************** Categories *************** */
  String titleAll();
  String titleGenre();
  String titleActor();
  String titleDirector();
  String titleYear();
  String titleBox();
  String titleDisk();
  String titleCountry();
}
