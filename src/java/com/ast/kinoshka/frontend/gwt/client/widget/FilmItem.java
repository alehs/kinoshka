package com.ast.kinoshka.frontend.gwt.client.widget;

import com.ast.kinoshka.frontend.gwt.event.FilmEditEvent;
import com.ast.kinoshka.frontend.gwt.model.Category;
import com.ast.kinoshka.frontend.gwt.model.FilmAttributeInfo;
import com.ast.kinoshka.frontend.gwt.model.FilmInfo;
import com.ast.kinoshka.frontend.gwt.utils.ResourcesUtil;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;

public class FilmItem extends Composite {

  private static FilmItemUiBinder uiBinder = GWT.create(FilmItemUiBinder.class);

  interface FilmItemUiBinder extends UiBinder<Widget, FilmItem> {
  }

  @UiField
  Image image;

  @UiField
  InlineLabel name;

  @UiField
  InlineLabel originalName;

  @UiField
  InlineLabel description;

  @UiField
  InlineLabel time;

  @UiField
  InlineHyperlink box;

  @UiField
  InlineHyperlink disc;

  @UiField
  InlineHyperlink year;

  @UiField
  Anchor edit;

  @UiField
  FlowPanel genreePanel;

  @UiField
  FlowPanel actorPanel;

  @UiField
  FlowPanel directorPanel;

  @UiField
  FlowPanel countreePanel;

  private FilmInfo film;
  private HandlerManager eventBus;

  public FilmItem(HandlerManager eventBus) {
    initWidget(uiBinder.createAndBindUi(this));
    this.eventBus = eventBus;

    edit.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent arg0) {
        History.newItem("EDIT", false);
        FilmItem.this.eventBus.fireEvent(new FilmEditEvent(film));
      }
    });
  }

  /**
   * Sets film info to display.
   * @param info film info to display
   */
  public void setFilmInfoToDisplay(FilmInfo info) {
    clear();
    film = info;
    bind();
  }

  /**
   * Binds view with film item.
   */
  public void bind() {
    image.setUrl(ResourcesUtil.filmImg(film.getImageName()));
    name.setText(film.getName());
    originalName.setText(film.getOriginalName());
    description.setText(film.getDescription());

    setNumeric(time, film.getTime());

    setNumericLink(box, film.getBox(), Category.BOX);
    setNumericLink(disc, film.getDisk(), Category.DISK);
    setNumericLink(year, film.getYear(), Category.YEAR);

    setLinkList(actorPanel, film.getActors(), Category.ACTOR);
    setLinkList(genreePanel, film.getGenres(), Category.GENRE);
    setLinkList(directorPanel, film.getDirectors(), Category.DIRECTOR);
    setImageList(countreePanel, film.getCountries(), Category.COUNTRY);
  }

  /**
   * Clears all complex panel fields.
   */
  public void clear() {
    genreePanel.clear();
    actorPanel.clear();
    directorPanel.clear();
    countreePanel.clear();
  }

  private final void setNumeric(final Label item, final Integer number) {
    if (number != null) {
      item.setText(number.toString());
    } else {
      item.setText(null);
    }
  }

  private final void setNumericLink(final Hyperlink item, final Integer number, final Category category) {
    if (number != null) {
      String text = number.toString();
      item.setText(text);
      item.setTargetHistoryToken(ResourcesUtil.target(category, text));
    } else {
      item.setText(null);
    }
  }

  private final void setLinkList(final FlowPanel holder, final ArrayList<FilmAttributeInfo> items,
      final Category category) {
    for (FilmAttributeInfo item : items) {
      appendLink(holder, new InlineHyperlink(item.getName(), ResourcesUtil.target(category, item
          .getId(), item.getName())));
    }
  }

  private final void appendLink(final FlowPanel holder, final Hyperlink link) {
    holder.add(link);
    holder.add(new InlineHTML(",&nbsp;"));
  }

  /**
   * Creates clickable images for given attributes.
   * @param holder holder to put image in
   * @param items attributes to create images for
   * @param category attribute's category
   */
  private final void setImageList(final FlowPanel holder, final ArrayList<FilmAttributeInfo> items,
      final Category category) {
    for (final FilmAttributeInfo item : items) {
      Image img = new Image(ResourcesUtil.countryImg(item.getParam1()));
      img.setTitle(item.getName());
      img.addClickHandler(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          History.newItem(ResourcesUtil.target(Category.COUNTRY, item.getId(), item.getName()));
        }
      });
      holder.add(img);
      holder.add(new InlineHTML("&nbsp;"));
    }
  }

}
