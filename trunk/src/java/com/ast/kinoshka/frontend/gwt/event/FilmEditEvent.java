package com.ast.kinoshka.frontend.gwt.event;

import com.ast.kinoshka.frontend.gwt.event.handler.FilmEditEventHandler;
import com.ast.kinoshka.frontend.gwt.model.FilmInfo;
import com.google.gwt.event.shared.GwtEvent;

public class FilmEditEvent extends GwtEvent<FilmEditEventHandler> {

  public static final Type<FilmEditEventHandler> TYPE = new Type<FilmEditEventHandler>();
  FilmInfo filmToEdit;

  public FilmEditEvent(FilmInfo film) {
    this.filmToEdit = film;
  }

  @Override
  protected void dispatch(FilmEditEventHandler handler) {
     handler.doEdit(this);
  }

  @Override
  public com.google.gwt.event.shared.GwtEvent.Type<FilmEditEventHandler> getAssociatedType() {
    return TYPE;
  }

  public FilmInfo getFilmToEdit() {
    return filmToEdit;
  }

}
