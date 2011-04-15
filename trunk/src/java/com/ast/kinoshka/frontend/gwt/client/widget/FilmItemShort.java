package com.ast.kinoshka.frontend.gwt.client.widget;

import com.ast.kinoshka.frontend.gwt.model.FilmInfo;
import com.ast.kinoshka.frontend.gwt.utils.ResourcesUtil;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class FilmItemShort extends Composite {

  private Image img = new Image();
  private Label label = new Label();
  private FlowPanel panel = new FlowPanel();

  private FilmInfo info;

  public FilmItemShort(final FilmInfo info) {
    this.info = info;

    label.setText(info.getName());
    img.setUrl(ResourcesUtil.filmImg(info.getImageName()));

    panel.add(img);
    panel.add(label);
    panel.setStyleName("film");
    initWidget(panel);
  }

  public Label getLabel() {
    return label;
  }

  public FilmInfo getInfo() {
    return info;
  }
}

