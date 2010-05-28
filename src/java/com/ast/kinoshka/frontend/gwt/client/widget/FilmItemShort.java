package com.ast.kinoshka.frontend.gwt.client.widget;

import com.ast.kinoshka.frontend.gwt.model.FilmInfo;
import com.ast.kinoshka.frontend.gwt.utils.ResourcesUtil;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

public class FilmItemShort extends Composite {

  private Label label = new Label();
  private FilmInfo info;

  public FilmItemShort(FilmInfo info) {
    initWidget(label);
    this.info = info;
    label.setText(ResourcesUtil.filmName(info.getName(), info.getOriginalName()));
  }

  public Label getLabel() {
    return label;
  }

  public FilmInfo getInfo() {
    return info;
  }
}

