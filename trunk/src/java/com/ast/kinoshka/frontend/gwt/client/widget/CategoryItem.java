package com.ast.kinoshka.frontend.gwt.client.widget;

import com.ast.kinoshka.frontend.gwt.model.FilmAttributeInfo;
import com.ast.kinoshka.frontend.gwt.utils.ResourcesUtil;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * Widget that holds FilmAttributeInfo data and displays it's name.
 * @author Aleh_Stsiapanau
 */
public class CategoryItem extends Composite {

  protected FlowPanel panel = new FlowPanel();
  protected Label label = new Label();
  private String value;

  public CategoryItem(String name, String value) {
    initWidget(panel);
    panel.add(label);
    this.value = value;
    this.label.setText(name);
  }

  public CategoryItem(FilmAttributeInfo item) {
    this(ResourcesUtil.filmName(item.getName(), item.getItemsCount().toString()), item.getId());
  }

  public Label getLabel() {
    return label;
  }

  public String getName() {
    return label.getText();
  }

  public String getValue() {
    return value;
  }
}
